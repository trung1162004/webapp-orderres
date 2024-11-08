import 'package:flutter/material.dart';
import 'package:pasgo_restaurants_ecommerce/color.style.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/route.configs.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/spacing_unit.dart';

class SplashPage extends StatefulWidget {
  const SplashPage({super.key});

  @override
  State<SplashPage> createState() => _SplashPageState();
}

class _SplashPageState extends State<SplashPage> {
  void _changeScreen() {
    Future.delayed(const Duration(seconds: 3))
        .then((value) => Navigator.pushReplacementNamed(context, AppRouters.home));
  }

  @override
  Widget build(BuildContext context) {
    _changeScreen();
    return const Scaffold(
      backgroundColor: MyAppColors.white,
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          Text(
            "Pasgo",
            textAlign: TextAlign.center,
            style: TextStyle(
                color: MyAppColors.backgroundColor,
                fontSize: SpacingUnit.x8_5,
                fontWeight: FontWeight.bold),
          ),
          Text(
            "Savor the Flavor, Right at Your Door!",
            textAlign: TextAlign.center,
            style: TextStyle(
                color: MyAppColors.backgroundColor,
                fontSize: SpacingUnit.x3_5,
                fontWeight: FontWeight.bold),
          ),

        ],
      ),
    );
  }
}
