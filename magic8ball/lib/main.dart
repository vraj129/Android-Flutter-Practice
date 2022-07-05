import 'dart:math';

import 'package:flutter/material.dart';

void main() {
  runApp(
    MaterialApp(
      home: Scaffold(
        backgroundColor: Colors.blue,
        appBar: AppBar(
          title: Text('Ask me anything'),
          backgroundColor: Colors.blue,
        ),
        body: MagicBall(),
      ),
    ),
  );
}

class MagicBall extends StatefulWidget {
  @override
  _MagicBallState createState() => _MagicBallState();
}

class _MagicBallState extends State<MagicBall> {
  @override
  int magicball = 1;
  Widget build(BuildContext context) {
    return Center(
      child: Row(
        children: <Widget>[
          Expanded(
            child: FlatButton(
              onPressed: () {
                setState(() {
                  magic_click();
                });
              },
              child: Image.asset('images/ball$magicball.png'),
            ),
          ),
        ],
      ),
    );
  }

  void magic_click() {
    magicball = Random().nextInt(5) + 1;
  }
}
