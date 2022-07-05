import 'package:flutter/material.dart';
import 'package:pychatapp/app.dart';
import 'package:pychatapp/screens/screens.dart';
import 'package:pychatapp/screens/select_user_screen.dart';
import 'package:pychatapp/theme.dart';
import 'package:stream_chat_flutter_core/stream_chat_flutter_core.dart';

void main() {
  final client = StreamChatClient(streamKey);
  runApp(MyApp(
    client: client,
  ));
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key, required this.client}) : super(key: key);
  final StreamChatClient client;
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'PyConnect',
      theme: AppTheme.light(),
      darkTheme: AppTheme.dark(),
      themeMode: ThemeMode.dark,
      builder: (context, child) {
        return StreamChatCore(
            client: client,
            child: ChannelsBloc(
              child: UsersBloc(child: child!),
            ));
      },
      home: const SelectUserScreen(),
    );
  }
}
