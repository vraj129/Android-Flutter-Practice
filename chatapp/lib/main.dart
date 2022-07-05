import 'dart:io';
import 'dart:math';

import 'package:chatapp/chatList.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:firebase_storage/firebase_storage.dart';
import 'package:flutter/material.dart';
import 'package:image_cropper/image_cropper.dart';
import 'package:image_picker/image_picker.dart';
import 'package:shared_preferences/shared_preferences.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Add user'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  final _nameTextController = TextEditingController();
  final _introTextController = TextEditingController();
  File _userImageFile = File('');

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          children: <Widget>[
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: GestureDetector(
                onTap: () {
                  //print('take image');
                  _takeImageFromLibrary();
                },
                child: Container(
                  width: 190,
                  height: 190,
                  child: Card(
                    child: (_userImageFile.path != '')
                        ? Image.file(
                            _userImageFile,
                            fit: BoxFit.fill,
                          )
                        : Icon(
                            Icons.add_photo_alternate,
                            size: 40,
                            color: Colors.blue[700],
                          ),
                  ),
                ),
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(right: 20.0, left: 20.0),
              child: TextFormField(
                decoration: const InputDecoration(
                  border: InputBorder.none,
                  icon: Icon(Icons.account_circle),
                  labelText: 'Name',
                  hintText: 'Type Your Name',
                ),
                controller: _nameTextController,
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(right: 20.0, left: 20.0),
              child: TextFormField(
                decoration: const InputDecoration(
                  border: InputBorder.none,
                  icon: Icon(Icons.note),
                  labelText: 'Intro',
                  hintText: 'Type Your Intro',
                ),
                controller: _introTextController,
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(right: 20.0, left: 20.0),
              child: RaisedButton(
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(12),
                ),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: const <Widget>[
                    Text(
                      'Go To Chat List',
                      style: TextStyle(fontSize: 28),
                    ),
                  ],
                ),
                textColor: Colors.white,
                color: Colors.blue[700],
                padding: const EdgeInsets.fromLTRB(10, 10, 10, 10),
                onPressed: () async {
                  String alertString = '';
                  if (_userImageFile.path == '') {
                    alertString += 'Please select a profile picture';
                  }
                  if (_nameTextController.text.trim() == '') {
                    if (alertString.trim() != '') {
                      alertString += '\n\n';
                    }
                    alertString = alertString + 'Please enter your name';
                  }

                  if (_introTextController.text.trim() == '') {
                    if (alertString.trim() != '') {
                      alertString += '\n\n';
                    }
                    alertString += 'Please enter your intro';
                  }
                  if (alertString.trim() != '') {
                    _showDialog(alertString);
                  } else {
                    _saveUserImageToFirebaseStorage();
                    print('pushed data to firebase');
                    // Navigator.push(
                    //     context,
                    //     MaterialPageRoute(
                    //         builder: (context) => const ChatList()));
                  }
                },
              ),
            )
          ],
        ),
      ),
    );
  }

  Future<void> _saveUserImageToFirebaseStorage() async {
    try {
      int randomNumber = Random().nextInt(100000);
      var randomID = '${_nameTextController.text}$randomNumber';
      SharedPreferences prefs = await SharedPreferences.getInstance();
      await prefs.setString('userID', randomID);
      await prefs.setString('name', _nameTextController.text);
      await prefs.setString('intro', _introTextController.text);

      String fileName = 'userImages/$randomID';

      final Reference storageRefernce = FirebaseStorage.instance.ref(fileName);
      storageRefernce.putFile(File(_userImageFile.path));
      // final uploadTask = storageRefernce.putFile(File(_userImageFile.path));
      // uploadTask.snapshotEvents.listen((TaskSnapshot snapshot) async {
      //   switch (snapshot.state) {
      //     case TaskState.success:
      //       // Handle successful uploads on complete
      //       // ...
      //       String imageUrl =
      //           await FirebaseStorage.instance.ref(fileName).getDownloadURL();
      //       _saveUserDataToFirebaseDatabase(randomID, imageUrl);
      //       break;
      //     case TaskState.paused:
      //       // TODO: Handle this case.
      //       break;
      //     case TaskState.running:
      //       // TODO: Handle this case.
      //       break;
      //     case TaskState.canceled:
      //       // TODO: Handle this case.
      //       break;
      //     case TaskState.error:
      //       // TODO: Handle this case.
      //       break;
      //   }
      // });
    } catch (e) {
      rethrow;
    }
  }

  Future<void> _saveUserDataToFirebaseDatabase(randomID, imageUrl) async {
    try {
      await FirebaseFirestore.instance.collection('users').doc(randomID).set({
        'userId': randomID,
        'name': _nameTextController.text,
        'intro': _introTextController.text,
        'userImageUrl': imageUrl,
        'createdAt': DateTime.now().millisecondsSinceEpoch
      });
    } catch (e) {
      //print(e.message);
      rethrow;
    }
  }

  _showDialog(String msg) {
    showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            content: Text(msg),
          );
        });
  }

  _takeImageFromLibrary() async {
    final picker = ImagePicker();

    final PickedFile? imageFilefromLibrary =
        await picker.getImage(source: ImageSource.gallery);
    _userImageFile = File(imageFilefromLibrary!.path);
    _cropImage(File(imageFilefromLibrary.path));
  }

  Future<void> _cropImage(File imageFile) async {
    File? croppedFile = await ImageCropper().cropImage(
        sourcePath: imageFile.path,
        aspectRatioPresets: Platform.isAndroid
            ? [
                CropAspectRatioPreset.square,
                CropAspectRatioPreset.ratio3x2,
                CropAspectRatioPreset.original,
                CropAspectRatioPreset.ratio4x3,
                CropAspectRatioPreset.ratio16x9
              ]
            : [
                CropAspectRatioPreset.original,
                CropAspectRatioPreset.square,
                CropAspectRatioPreset.ratio3x2,
                CropAspectRatioPreset.ratio4x3,
                CropAspectRatioPreset.ratio5x3,
                CropAspectRatioPreset.ratio5x4,
                CropAspectRatioPreset.ratio7x5,
                CropAspectRatioPreset.ratio16x9
              ],
        androidUiSettings: const AndroidUiSettings(
            toolbarTitle: 'Cropper',
            toolbarColor: Colors.deepOrange,
            toolbarWidgetColor: Colors.white,
            initAspectRatio: CropAspectRatioPreset.original,
            lockAspectRatio: false),
        iosUiSettings: const IOSUiSettings(
          title: 'Cropper',
        ));
    if (croppedFile != null) {
      imageFile = croppedFile;
      setState(() {
        _userImageFile = croppedFile;
      });
    }
  }
}
