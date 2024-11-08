import 'package:carousel_slider/carousel_slider.dart';
import 'package:flutter/material.dart';
import 'package:flutter_paypal_payment/flutter_paypal_payment.dart';
import 'package:pasgo_restaurants_ecommerce/color.style.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/route.configs.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/dimens.dart';
import 'package:pasgo_restaurants_ecommerce/data/models/subscriptions/subscription_model.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/widgets/bottomsheets/items/subscription_item.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/widgets/buttons/button.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/widgets/indicator/indicator.dart';

class SubscriptionPackageBottomSheet extends StatefulWidget {
  const SubscriptionPackageBottomSheet({super.key});

  @override
  State<SubscriptionPackageBottomSheet> createState() =>
      _SubscriptionPackageBottomSheetState();
}

class _SubscriptionPackageBottomSheetState
    extends State<SubscriptionPackageBottomSheet> {
  int _currentIndex = 0;

  @override
  Widget build(BuildContext context) {
    final padding = MediaQuery.of(context).padding;
    return Container(
      padding: EdgeInsets.only(
        left: 8,
        right: 8,
        top: padding.top + 40,
        bottom: 8,
      ),
      height: DimensionApp.screenHeight,
      width: DimensionApp.screenWidth,
      color: MyAppColors.white,
      child: Column(
        children: [
          Align(
            alignment: Alignment.topRight,
            child: GestureDetector(
              onTap: () => Navigator.pop(context),
              child: const Icon(Icons.close, size: 32),
            ),
          ),
          const SizedBox(height: 80),
          const Text(
            "Payment for collaboration package",
            style: TextStyle(fontSize: 22, fontWeight: FontWeight.w600),
          ),
          const SizedBox(height: 30),
          CarouselSlider(
            items: listSubscriptions.map((item) {
              return SubscriptionItem(
                s: item,
              );
            }).toList(),
            options: CarouselOptions(
              height: 200.0,
              autoPlay: true,
              enlargeCenterPage: true,
              aspectRatio: 1 / 1,
              viewportFraction: 1.0,
              onPageChanged: (index, reason) {
                setState(() {
                  _currentIndex = index;
                });
              },
            ),
          ),
          const SizedBox(height: 10),
          CarouselIndicator(
            currentIndex: _currentIndex,
            itemCount: listSubscriptions.length,
          ),
          const SizedBox(
            height: 20,
          ),
          ButtonWidget(
              buttonText: "Buy now",
              onPressed: () {
                Navigator.pushNamed(context, AppRouters.confirms,
                    arguments: listSubscriptions[_currentIndex]);
                
              })
        ],
      ),
    );
  }
}
