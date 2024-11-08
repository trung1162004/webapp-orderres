// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'uu_model.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

UUserModel _$UUserModelFromJson(Map<String, dynamic> json) => UUserModel(
      id: (json['id'] as num).toInt(),
      name: json['name'] as String,
      email: json['email'] as String,
      username: json['username'] as String?,
      avatar: json['avatar'] as String?,
    );

Map<String, dynamic> _$UUserModelToJson(UUserModel instance) =>
    <String, dynamic>{
      'id': instance.id,
      'name': instance.name,
      'email': instance.email,
      'username': instance.username,
      'avatar': instance.avatar,
    };
