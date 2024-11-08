import 'package:flutter/material.dart';
import 'package:pasgo_restaurants_ecommerce/color.style.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/route.configs.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/shareprefs.dart';

class ItemAppBar extends StatelessWidget {
  final VoidCallback onTap;
  final IconData icon;
  final bool isUsingImage;
  final String assets;
  const ItemAppBar(
      {super.key,
      required this.onTap,
      required this.icon,
      this.isUsingImage = false,
       this.assets=""});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () async {
        String? data = await SharePrefs.getUserData();
        if (data == null || data == '{}' || data.isEmpty) {
          if (context.mounted) {
            Navigator.pushNamed(context, AppRouters.login);
          }
        } else {
          onTap();
        }
      },
      child: isUsingImage
          ? Image.asset(assets,width: 26,color: MyAppColors.tinder,)
          : Icon(
              icon,
              size: 32,
            ),
    );
  }
}
