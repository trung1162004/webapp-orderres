import 'package:flutter/material.dart';
import 'package:pasgo_restaurants_ecommerce/color.style.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/route.configs.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/shareprefs.dart';

class ItemOption extends StatelessWidget {
  final VoidCallback onTap;
  final String title;
  const ItemOption({super.key, required this.title, required this.onTap});

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
      child: Container(
        height: 40,
        alignment: Alignment.centerLeft,
        width: double.infinity,
        decoration: const BoxDecoration(
          border: Border(
              bottom: BorderSide(width: 1, color: MyAppColors.backgroundColor)),
        ),
        child: Row(
          children: [
            Expanded(
              child: Text(
                title,
                textAlign: TextAlign.left,
                style:
                    const TextStyle(fontSize: 19, fontWeight: FontWeight.w800),
              ),
            ),
            const Icon(
              Icons.chevron_right,
              size: 32,
            )
          ],
        ),
      ),
    );
  }
}
