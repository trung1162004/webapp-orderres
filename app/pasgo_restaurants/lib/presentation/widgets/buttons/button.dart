import 'package:flutter/material.dart';
import 'package:pasgo_restaurants_ecommerce/color.style.dart';

class ButtonWidget extends StatelessWidget {
  final String buttonText;
  final VoidCallback onPressed;
  final double? size;
  final Color? colors;
  final double radius;
  final double? width;
  const ButtonWidget({
    Key? key,
    required this.buttonText,
    required this.onPressed,
    this.size,
    this.width=double.infinity,
    this.radius=12,
    this.colors, 
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: onPressed,
      child: Container(
        width: width,
        height: size ?? 60,
        decoration: BoxDecoration(
          gradient: const LinearGradient(
            colors: [MyAppColors.backgroundColor, MyAppColors.tinder],
            begin: Alignment.topLeft,
            end: Alignment.bottomRight,
          ),
          borderRadius: BorderRadius.circular(radius),
        ),
        child: Center(
          child: Text(
            buttonText,
            style: const TextStyle(
              color: MyAppColors.white,
              fontSize: 18,
              fontWeight: FontWeight.bold,
            ),
          ),
        ),
      ),
    );
  }
}
