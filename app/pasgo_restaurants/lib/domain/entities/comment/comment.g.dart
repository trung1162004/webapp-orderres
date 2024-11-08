// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'comment.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

Comment _$CommentFromJson(Map<String, dynamic> json) => Comment(
      id: (json['id'] as num).toInt(),
      name: json['name'] as String,
      status: json['status'] as String,
      email: json['email'] as String,
      // voteNumber: (json['voteNumber'] as num?)?.toInt(),
      phone: json['phone'] as String?,
      content: json['content'] as String,
      createdAt: json['createdAt'] as String?,
      updatedAt: json['updatedAt'] as String?,
      user: UUserModel.fromJson(json['user'] as Map<String, dynamic>),
      restaurant:
          Restaurant.fromJson(json['restaurant'] as Map<String, dynamic>),
    );

Map<String, dynamic> _$CommentToJson(Comment instance) => <String, dynamic>{
      'id': instance.id,
      'status': instance.status,
      'email': instance.email,
      'name': instance.name,
      // 'voteNumber': instance.voteNumber,
      'phone': instance.phone,
      'content': instance.content,
      'createdAt': instance.createdAt,
      'updatedAt': instance.updatedAt,
      'user': instance.user,
      'restaurant': instance.restaurant,
    };
