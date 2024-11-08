import 'package:flutter/material.dart';
import 'package:html/parser.dart' as html_parser;
import 'package:html/dom.dart' as dom;

class ValidationUtils {
  static final phoneRegex = RegExp(
      r'^(?:\+?\d{1,3}[-.\s]?)?\(?\d{1,4}\)?[-.\s]?\d{1,4}[-.\s]?\d{1,9}$');
  static final emailRegex =
      RegExp(r'^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$');

  static bool isValidEmail(String email) {
    return emailRegex.hasMatch(email);
  }

  static bool isValidPhoneNumber(String phoneNumber) {
    return phoneRegex.hasMatch(phoneNumber);
  }



  static bool isValidTime(TimeOfDay? time) {
    return time != null;
  }

  static String cleanedHtmlText(String txt)=>txt.replaceAll('\\r\\n', ' ');




}
