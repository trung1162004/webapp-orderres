import 'package:flutter/material.dart';

class CarouselIndicator extends StatelessWidget {
  final int currentIndex;
  final int itemCount;

  const CarouselIndicator({
    super.key,
    required this.currentIndex,
    required this.itemCount,
  });

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children: List.generate(
        itemCount,
        (index) => Container(
          margin: const EdgeInsets.symmetric(horizontal: 4.0),
          width: currentIndex == index ? 12.0 : 8.0,
          height: currentIndex == index ? 12.0 : 8.0,
          decoration: BoxDecoration(
            color: currentIndex == index ? Colors.black : Colors.grey,
            shape: BoxShape.circle,
          ),
        ),
      ),
    );
  }
}
