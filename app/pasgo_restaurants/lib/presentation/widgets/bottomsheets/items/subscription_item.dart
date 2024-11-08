import 'package:flutter/material.dart';
import 'package:pasgo_restaurants_ecommerce/color.style.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/dimens.dart';
import 'package:pasgo_restaurants_ecommerce/data/models/subscriptions/subscription_model.dart';

class SubscriptionItem extends StatelessWidget {
  final SubscriptionModel s;
  const SubscriptionItem({super.key, required this.s,});

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: const EdgeInsets.all(2),
      width: DimensionApp.screenWidth,
      decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(8),
          border: Border.all(color: MyAppColors.blueColor, width: 1.5)),
      child: Column(
        children: [
          Container(
            height: 60,
            color: MyAppColors.blueColor,
            alignment: Alignment.center,
            child: Text(
              "${s.price}\$ - ${s.month}months",
              style: const TextStyle(
                  color: MyAppColors.white,
                  fontSize: 22,
                  fontWeight: FontWeight.w700),
            ),
          ),
          const SizedBox(
            height: 20,
          ),
          Expanded(
            child: ListView.separated(
                itemBuilder: (context, index) {
                  return Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      const Icon(Icons.check_circle),
                      const SizedBox(
                        width: 7,
                      ),
                      Text(
                        s.benefits[index],
                        style: const TextStyle(
                          fontSize: 16,
                        ),
                      )
                    ],
                  );
                },
                separatorBuilder: (context, index) => const SizedBox(height: 5),
                itemCount: s.benefits.length),
          )
          // Row(
          //   mainAxisAlignment: MainAxisAlignment.center,
          //   crossAxisAlignment: CrossAxisAlignment.center,
          //   children: [
          //     Text(
          //       "${s.price}\$ / ",
          //       style: TextStyle(fontSize: 22, fontWeight: FontWeight.w700),
          //     ),
          //     Text(
          //       "${s.month}months",
          //       style: TextStyle(fontSize: 18, color: Colors.grey.shade600),
          //     ),
          //   ],
          // )
        ],
      ),
    );
  }
}
