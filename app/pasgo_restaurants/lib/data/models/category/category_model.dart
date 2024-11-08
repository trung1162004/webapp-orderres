import 'package:json_annotation/json_annotation.dart';

part 'category_model.g.dart';

@JsonSerializable()
class CategoryModel{
  final int id;
  final String name;
  final String status;
  final String? image;

  const CategoryModel({
    required this.id,
    required this.name,
    required this.status,
     this.image,

  });

    factory CategoryModel.fromJson(Map<String, dynamic> json) =>
      _$CategoryModelFromJson(json);

  Map<String, dynamic> toJson() => _$CategoryModelToJson(this);
}