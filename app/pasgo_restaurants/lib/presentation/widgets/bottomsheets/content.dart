import 'package:flutter/material.dart';
import 'package:pasgo_restaurants_ecommerce/color.style.dart';

Future<T?> showAppModalBottomSheet<T>(
    {required BuildContext context,
    required Widget child,
    bool? isScrollControlled,
    bool isDismissible = true,
    double? bottomSheetHeight,
    Color? barrierColor = MyAppColors.transparent}) {
  return showModalBottomSheet<T>(
    isDismissible: isDismissible,
    context: context,
    barrierColor: barrierColor,
    backgroundColor: MyAppColors.transparent,
    elevation: 0,
    // shape: const RoundedRectangleBorder(
    //   borderRadius: BorderRadius.vertical(
    //       top: Radius.circular(DimensionApp.borderRadius * 4)),
    // ),
    isScrollControlled: isScrollControlled ?? true,
    enableDrag: true,
    builder: (BuildContext context) {
      return child;
    },
  );
}
