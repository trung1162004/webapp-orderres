import 'package:json_annotation/json_annotation.dart';
part 'uu_model.g.dart';

@JsonSerializable()
class UUserModel {
  final int id;
  final String name;
  final String email;
  final String? username;
  final String? avatar;

  const UUserModel({
    required this.id,
    required this.name,
    required this.email,
     this.username,
    this.avatar,
  });

  factory UUserModel.fromJson(Map<String, dynamic> json) => _$UUserModelFromJson(json);
  Map<String, dynamic> toJson() => _$UUserModelToJson(this);
}