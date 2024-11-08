// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'order_model.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

OrderModel _$OrderModelFromJson(Map<String, dynamic> json) => OrderModel(
      id: (json['id'] as num).toInt(),
      code: json['code'] as String,
      status: json['status'] as String,
      type: json['type'] as String,
      // receiverAddress: json['receiverAddress'] as String,
      receiverEmail: json['receiverEmail'] as String,
      receiverName: json['receiverName'] as String?,
      receiverPhone: json['receiverPhone'] as String?,
      note: json['note'] as String?,
      // totalPrice: (json['totalPrice'] as num).toDouble(),
      //  discount: (json['discount'] as num?)?.toDouble(),
      restaurantName: json['restaurantName'] as String?,
      logo: json['logo'] as String?,
      // price: json['price'] as String,
      timeBooking: json['timeBooking'] as String,
      quantity: (json['quantity'] as num).toDouble(),
      createdAt: json['createdAt'] as String,
      updatedAt: json['updatedAt'] as String,
      user: UUserModel.fromJson(json['user'] as Map<String, dynamic>),
    );

Map<String, dynamic> _$OrderModelToJson(OrderModel instance) =>
    <String, dynamic>{
      'id': instance.id,
      'code': instance.code,
      'status': instance.status,
      'type': instance.type,
      // 'receiverAddress': instance.receiverAddress,
      'receiverName': instance.receiverName,
      'receiverEmail': instance.receiverEmail,
      'receiverPhone': instance.receiverPhone,
      'note': instance.note,
      // 'totalPrice': instance.totalPrice,
      // 'discount': instance.discount,
      'restaurantName': instance.restaurantName,
      'logo': instance.logo,
      // 'price': instance.price,
      'timeBooking': instance.timeBooking,
      'quantity': instance.quantity,
      'createdAt': instance.createdAt,
      'updatedAt': instance.updatedAt,
      'user': instance.user,
    };
