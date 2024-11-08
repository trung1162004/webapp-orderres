import 'package:json_annotation/json_annotation.dart';
import 'package:pasgo_restaurants_ecommerce/data/models/category/category_model.dart';
import 'package:pasgo_restaurants_ecommerce/data/models/restaurant/uu_model.dart';

part 'restaurant.g.dart';

@JsonSerializable()
class Restaurant {
 final int id;
  final String name;
  final String logo;
  final String status;
  final String email;
  // final String? description;
  final String? address;
  // final String? content;
  final String phone;
  final String type;
  final List<String>? images;
  final double? discount;
  final String? start_date;
  final String? end_date;
  final String? work_time_close;
  final String? work_time_open;
  final String? fit;
  final String? specialDish;
  final String? space;
  final String? parking;
  final String? feature;
  // final List<String>? utilities;
  final String? depositRegulation;
  final String? discountRegulation;
  final String? pasgoTimeRegulation;
  final String? reservationTimeRegulation;
  final String? holdTimeRegulation;
  final String? mininumGuestRegulation;
  final String? invoiceRegulation;
  final String? serviceFeeRegulation;
  final String? bringInFeeRegulation;
  final int quantity;
  final String? price;
  // final String? createdAt;
  final String? updatedAt;
  final UUserModel? user;
  final List<CategoryModel>? categories;

  const Restaurant({
    required this.id,
    required this.name,
    required this.logo,
    required this.status,
    required this.email,
    // this.description,
    this.address,
    // this.content,
    required this.phone,
    required this.type,
    required this.images,
     this.discount,
    this.start_date,
    this.end_date,
    this.work_time_open,
    this.work_time_close,
    required this.quantity,
    required this.price,
    this.fit,
    this.specialDish,
    this.space,
    this.parking,
    this.feature,
    this.depositRegulation,
    this.discountRegulation,
    this.pasgoTimeRegulation,
    this.reservationTimeRegulation,
    this.holdTimeRegulation,
    this.mininumGuestRegulation,
    this.invoiceRegulation,
    this.serviceFeeRegulation,
    this.bringInFeeRegulation,
    // this.utilities,
    // this.createdAt,
    this.updatedAt,
    this.user,
    this.categories,
  });
  factory Restaurant.fromJson(Map<String, dynamic> json) =>
      _$RestaurantFromJson(json);

  Map<String, dynamic> toJson() => _$RestaurantToJson(this);
}
