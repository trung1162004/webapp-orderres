import 'package:json_annotation/json_annotation.dart';
import 'package:pasgo_restaurants_ecommerce/data/models/restaurant/uu_model.dart';
import 'package:pasgo_restaurants_ecommerce/domain/entities/restaurant/restaurant.dart';

part 'comment.g.dart';

@JsonSerializable()
class Comment {
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

   Comment({
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

  factory Comment.fromJson(Map<String, dynamic> json) =>
      _$CommentFromJson(json);

  Map<String, dynamic> toJson() => _$CommentToJson(this);
}
