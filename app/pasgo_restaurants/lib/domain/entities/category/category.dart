import 'package:json_annotation/json_annotation.dart';

part 'category.g.dart';

@JsonSerializable()
class Category{
  final int id;
  final String name;
  final String status;
  final String? image;

  const Category({
    required this.id,
    required this.name,
    required this.status,
     this.image,

  });

    factory Category.fromJson(Map<String, dynamic> json) =>
      _$CategoryFromJson(json);

  Map<String, dynamic> toJson() => _$CategoryToJson(this);
}