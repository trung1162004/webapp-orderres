// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'restaurant_model.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

RestaurantModel _$RestaurantModelFromJson(Map<String, dynamic> json) =>
    RestaurantModel(
      id: (json['id'] as num).toInt(),
      name: json['name'] as String,
      logo: json['logo'] as String,
      status: json['status'] as String,
      email: json['email'] as String,
      // description: json['description'] as String?,
      address: json['address'] as String?,
      // content: json['content'] as String?,
      phone: json['phone'] as String,
      type: json['type'] as String,
      images:
          (json['images'] as List<dynamic>?)?.map((e) => e as String).toList(),
      discount: (json['discount'] as num?)?.toDouble(),
      start_date: json['start_date'] as String?,
      end_date: json['end_date'] as String?,
      work_time_close: json['work_time_close'] as String?,
            work_time_open: json['work_time_open'] as String?,
      quantity: (json['quantity'] as num).toInt(),
      price: json['price'] as String,
      fit: json['fit'] as String?,
      specialDish: json['specialDish'] as String?,
      space: json['space'] as String?,
      parking: json['parking'] as String?,
      feature: json['feature'] as String?,
       utilities: (json['utilities'] as List<dynamic>?)?.map((e) => e as String).toList() ?? [],
      depositRegulation: json['depositRegulation'] as String?,
      discountRegulation: json['discountRegulation'] as String?,
      pasgoTimeRegulation: json['pasgoTimeRegulation'] as String?,
      reservationTimeRegulation: json['reservationTimeRegulation'] as String?,
      holdTimeRegulation: json['holdTimeRegulation'] as String?,
      mininumGuestRegulation: json['mininumGuestRegulation'] as String?,
      invoiceRegulation: json['invoiceRegulation'] as String?,
      serviceFeeRegulation: json['serviceFeeRegulation'] as String?,
      bringInFeeRegulation: json['bringInFeeRegulation'] as String?,

      // createdAt: json['createdAt'] as String?,
      updatedAt: json['updatedAt'] as String?,
      user: json['user'] == null
          ? null
          : UUserModel.fromJson(json['user'] as Map<String, dynamic>),
      categories: (json['categories'] as List<dynamic>?)
          ?.map((e) => CategoryModel.fromJson(e as Map<String, dynamic>))
          .toList(),
    );

Map<String, dynamic> _$RestaurantModelToJson(RestaurantModel instance) =>
    <String, dynamic>{
      'id': instance.id,
      'name': instance.name,
      'logo': instance.logo,
      'status': instance.status,
      'email': instance.email,
      // 'description': instance.description,
      'address': instance.address,
      // 'content': instance.content,
      'phone': instance.phone,
      'type': instance.type,
      'images': instance.images,
      'discount': instance.discount,
      'start_date': instance.start_date,
      'end_date': instance.end_date,
      'work_time_close': instance.work_time_close,
      'work_time_open':instance.work_time_open,
      'quantity': instance.quantity,
      'price': instance.price,
      'fit': instance.fit,
      'specialDish': instance.specialDish,
      'space': instance.space,
      'parking': instance.parking,
      'feature': instance.feature,
      'utilities': instance.utilities,
      'depositRegulation': instance.depositRegulation,
      'discountRegulation': instance.discountRegulation,
      'pasgoTimeRegulation': instance.pasgoTimeRegulation,
      'reservationTimeRegulation': instance.reservationTimeRegulation,
      'holdTimeRegulation': instance.holdTimeRegulation,
      'mininumGuestRegulation': instance.mininumGuestRegulation,
      'invoiceRegulation': instance.invoiceRegulation,
      'serviceFeeRegulation': instance.serviceFeeRegulation,
      'bringInFeeRegulation': instance.bringInFeeRegulation,


      // 'createdAt': instance.createdAt,
      'updatedAt': instance.updatedAt,
      'user': instance.user,
      'categories': instance.categories,
    };
