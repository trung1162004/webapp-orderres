
import 'package:flutter/services.dart';
import 'package:pasgo_restaurants_ecommerce/domain/enum/screen_option_enum.dart';

class ScreenUtils {
  static Future<void> setPreferredOrientations(
      List<ScreenOrientation> allowedOrientations) async {
    List<DeviceOrientation> orientations = [];

    if (allowedOrientations.contains(ScreenOrientation.portraitUp)) {
      orientations.add(DeviceOrientation.portraitUp);
    }
    if (allowedOrientations.contains(ScreenOrientation.portraitDown)) {
      orientations.add(DeviceOrientation.portraitDown);
    }
    if (allowedOrientations.contains(ScreenOrientation.landscapeRight)) {
      orientations.add(DeviceOrientation.landscapeRight);
    }
    if (allowedOrientations.contains(ScreenOrientation.landscapeLeft)) {
      orientations.add(DeviceOrientation.landscapeLeft);
    }

    await SystemChrome.setPreferredOrientations(orientations);
  }
}
