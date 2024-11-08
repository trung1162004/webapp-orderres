import 'package:flutter/material.dart';

class TruncatedText extends StatelessWidget {
  final String text;
  final int maxLength;

  const TruncatedText({Key? key, required this.text, this.maxLength = 100})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Text(
      text,
      overflow: TextOverflow.ellipsis,
      maxLines: 2,
      style: const TextStyle(fontSize: 15),
    );
  }
}