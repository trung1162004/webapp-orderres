import 'package:flutter/material.dart';

class DimensionApp {
  DimensionApp._();
  static late double _screenWidth;
  static late double _screenHeight;
  static late EdgeInsets _padding;

  static const double horizontalPadding = 12.0;
  static const double verticalPadding = 12.0;
  static const double borderRadius = 10.0;
  static late double _heightBottomSheet;
  static void init(BuildContext context) {
    _screenWidth = MediaQuery.of(context).size.width;
    _screenHeight = MediaQuery.of(context).size.height;
    _padding = MediaQuery.of(context).padding;
    _heightBottomSheet=_screenHeight;
  }

  static double get screenWidth => _screenWidth;
  static double get heightBottomSheet => _heightBottomSheet;
  static EdgeInsets get padding => _padding;

  static double get screenHeight => _screenHeight;
}
