import 'package:pasgo_restaurants_ecommerce/data/models/subscriptions/subscription_model.dart';

class SubscriptionMM2 {
  final int uID;
  final String name;
  final String phone;
  final String email;
  final String createdAt;
  final String endAt;
  final List<SubscriptionModel>? smd;

  const SubscriptionMM2({
    required this.uID,
    required this.name,
    required this.phone,
    required this.email,
    required this.createdAt,
    required this.endAt,
    required this.smd,
  });

  factory SubscriptionMM2.fromJson(Map<String, dynamic> json) {
    return SubscriptionMM2(
      uID: json['uID'] as int,
      name: json['name'] as String,
      phone: json['phone'] as String,
      email: json['email'] as String,
      createdAt: json['createdAt'] as String,
      endAt: json['endAt'] as String,
      smd: (json['smd'] as List<dynamic>?)
          ?.map((item) =>
              SubscriptionModel.fromJson(item as Map<String, dynamic>))
          .toList(),
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'uID': uID,
      'name': name,
      'phone': phone,
      'email': email,
      'createdAt': createdAt,
      'endAt': endAt,
      'smd': smd?.map((item) => item.toJson()).toList(),
    };
  }
}
