// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'user_model.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

UserModel _$UserModelFromJson(Map<String, dynamic> json) => UserModel(
      id: (json['id'] as num).toInt(),
      name: json['name'] as String,
      username: json['username'] as String,
      status: json['status'] as String,
      email: json['email'] as String,
      gender: json['gender'] as String,
      phone: json['phone'] as String?,
      avatar: json['avatar'] as String?,
      type: json['type'] as String,
      address: json['address'] as String?,
    );

Map<String, dynamic> _$UserModelToJson(UserModel instance) => <String, dynamic>{
      'id': instance.id,
      'name': instance.name,
      'username': instance.username,
      'status': instance.status,
      'email': instance.email,
      'gender': instance.gender,
      'phone': instance.phone,
      'avatar': instance.avatar,
      'type': instance.type,
      'address': instance.address,
    };
