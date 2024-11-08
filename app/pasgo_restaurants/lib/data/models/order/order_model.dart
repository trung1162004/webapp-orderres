import 'dart:ffi';

import 'package:json_annotation/json_annotation.dart';
import 'package:pasgo_restaurants_ecommerce/data/models/restaurant/uu_model.dart';

part 'order_model.g.dart';


@JsonSerializable()
class OrderModel {
  final int id;
  final String code;
  final String status;
  final String type;
  // final String receiverAddress;
  final String? receiverName;
  final String receiverEmail;
  final String? receiverPhone;
  final String? note;
  // final double totalPrice;
  final Double? discount;
  final String? restaurantName;
  
  final String? logo;
  // final String? price;
  final String timeBooking;
  final double quantity;
  final String createdAt;
  final String updatedAt;
  final UUserModel user;

 const OrderModel({

  required this.id,
  required this.code,
  required this.status,
  required this.type,
  // required this.receiverAddress,
  required this.receiverEmail,
   this.receiverName,
   this.receiverPhone,
   this.note,
  // required this.totalPrice,
   this.discount,
   this.restaurantName,
   this.logo,
  // required this.price,
  required this.timeBooking,
  required this.quantity,
  required this.createdAt,
  required this.updatedAt,
  required this.user,
 });

  factory OrderModel.fromJson(Map<String, dynamic> json) =>
      _$OrderModelFromJson(json);

  Map<String, dynamic> toJson() => _$OrderModelToJson(this);
}
