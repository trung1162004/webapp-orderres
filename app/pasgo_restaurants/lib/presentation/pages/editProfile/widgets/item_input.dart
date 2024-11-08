import 'package:flutter/material.dart';
import 'package:pasgo_restaurants_ecommerce/color.style.dart';

class ItemInput extends StatelessWidget {
  final String title;
  final TextEditingController controller;
  final bool? isReadonly;
  final Function(String e) callBack;
  final String? Function(String?)? validator;
  final String? errorText;
  final String? hint;
  const ItemInput({
    super.key,
    required this.title,
    required this.controller,
    this.isReadonly = false,
    required this.callBack,
    this.validator,
    this.errorText, this.hint,
  });

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(title),
        TextField(
        
          controller: controller,
          style: const TextStyle(
            
            color: MyAppColors.black,
            fontWeight: FontWeight.w400,
            fontSize: 14,
          ),
          onChanged: callBack,
          readOnly: isReadonly ?? false,
          decoration: InputDecoration(
            filled: true,
            hintText: hint??"",
            fillColor: const Color.fromARGB(197, 214, 214, 214),
            hintStyle: const TextStyle(
              color: MyAppColors.black,
              fontWeight: FontWeight.bold,
            ),
            border: OutlineInputBorder(
              borderSide: BorderSide.none,
              borderRadius: BorderRadius.circular(8),
            ),
            errorText: errorText,
          ),
        ),
        const SizedBox(
          height: 20,
        ),
      ],
    );
  }
}
