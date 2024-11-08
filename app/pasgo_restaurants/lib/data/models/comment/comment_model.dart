import 'package:json_annotation/json_annotation.dart';
import 'package:pasgo_restaurants_ecommerce/data/models/restaurant/uu_model.dart';
import 'package:pasgo_restaurants_ecommerce/domain/entities/restaurant/restaurant.dart';

part 'comment_model.g.dart';

@JsonSerializable()
class CommentModel {
  final int id;
  final String status;
  final String email;
  final String name;
  // final int? voteNumber;
  final String? phone;
   String content;
  final String? createdAt;
  final String? updatedAt;
  final UUserModel user;
  final Restaurant restaurant;

   CommentModel({
    required this.id,
    required this.name,
    required this.status,
    required this.email,
    // this.voteNumber,
    this.phone,
    required this.content,
    this.createdAt,
    this.updatedAt,
    required this.user,
    required this.restaurant,
  });

  factory CommentModel.fromJson(Map<String, dynamic> json) =>
      _$CommentModelFromJson(json);

  Map<String, dynamic> toJson() => _$CommentModelToJson(this);
}
