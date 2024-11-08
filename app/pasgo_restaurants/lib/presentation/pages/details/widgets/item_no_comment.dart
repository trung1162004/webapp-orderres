import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/route.configs.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/dimens.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/shareprefs.dart';
import 'package:pasgo_restaurants_ecommerce/data/models/restaurant/uu_model.dart';
import 'package:pasgo_restaurants_ecommerce/domain/entities/comment/comment.dart';
import 'package:pasgo_restaurants_ecommerce/domain/entities/restaurant/restaurant.dart';

class ItemNoComment extends StatefulWidget {
  final int rntId;
  const ItemNoComment({
    super.key,
    required this.rntId,
  });

  @override
  State<ItemNoComment> createState() => _ItemNoCommentState();
}

class _ItemNoCommentState extends State<ItemNoComment> {
  int? id;

  Future<void> _getUserId() async {
    String? data = await SharePrefs.getUserData();
    Map<String, dynamic>? userData;
    if (data != null) {
      userData = jsonDecode(data);
    }
    setState(() {
      id = userData!['id'];
    });
  }

  @override
  void initState() {
    super.initState();
    _getUserId();
  }

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        var u = const UUserModel(
            id: 0, name: "name", email: "email", username: "username");
        var res = const Restaurant(
            id: 0,
            name: "name",
            logo: "logo",
            status: "status",
            email: "email",
            // description: "description",
            address: "address",
            // content: "content",
            phone: "phone",
            type: "type",
            quantity: 1,
            price: "price",
            // createdAt: "createdAt",
            updatedAt: "updatedAt",
            images: [],
            discount: 10);
        List<Comment> list = [
          Comment(
              id: 0,
              name: "Your comment like this",
              status: "status",
              email: "email",
              content: "content",
              user: u,
              restaurant: res)
        ];
        Navigator.pushNamed(
          context,
          AppRouters.comments,
          arguments: {
            'listComment': list,
            'rntId': widget.rntId,
            "userId": id,
            'noCmt': true
          },
        );
      },
      child: Container(
          width: DimensionApp.screenWidth,
          height: 70,
          padding: const EdgeInsets.all(10),
          decoration: BoxDecoration(
              color: Colors.grey.withOpacity(0.4),
              borderRadius: BorderRadius.circular(8)),
          child: const Row(
            children: [
              Expanded(
                child: Text(
                  "Add comment",
                  textAlign: TextAlign.start,
                  style: TextStyle(fontSize: 14, color: Colors.grey),
                ),
              ),
              Icon(Icons.chevron_right)
            ],
          )),
    );
  }
}
