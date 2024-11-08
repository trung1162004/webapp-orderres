// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'restaurant_state.dart';

// **************************************************************************
// CopyWithGenerator
// **************************************************************************

abstract class _$RestaurantStateCWProxy {
  RestaurantState actionFavRestaurantStatus(
      RestaurantStatus actionFavRestaurantStatus);

  RestaurantState createRestaurantStatus(
      RestaurantStatus createRestaurantStatus);

  RestaurantState deleteRestaurantStatus(
      RestaurantStatus deleteRestaurantStatus);

  RestaurantState restaurantStatus(RestaurantStatus restaurantStatus);

  RestaurantState restaurants(List<Restaurant> restaurants);

  RestaurantState restaurantsFav(List<Restaurant> restaurantsFav);

  RestaurantState updateRestaurantStatus(
      RestaurantStatus updateRestaurantStatus);

  /// This function **does support** nullification of nullable fields. All `null` values passed to `non-nullable` fields will be ignored. You can also use `RestaurantState(...).copyWith.fieldName(...)` to override fields one at a time with nullification support.
  ///
  /// Usage
  /// ```dart
  /// RestaurantState(...).copyWith(id: 12, name: "My name")
  /// ````
  RestaurantState call({
    RestaurantStatus? actionFavRestaurantStatus,
    RestaurantStatus? createRestaurantStatus,
    RestaurantStatus? deleteRestaurantStatus,
    RestaurantStatus? restaurantStatus,
    List<Restaurant>? restaurants,
    List<Restaurant>? restaurantsFav,
    RestaurantStatus? updateRestaurantStatus,
  });
}

/// Proxy class for `copyWith` functionality. This is a callable class and can be used as follows: `instanceOfRestaurantState.copyWith(...)`. Additionally contains functions for specific fields e.g. `instanceOfRestaurantState.copyWith.fieldName(...)`
class _$RestaurantStateCWProxyImpl implements _$RestaurantStateCWProxy {
  final RestaurantState _value;

  const _$RestaurantStateCWProxyImpl(this._value);

  @override
  RestaurantState actionFavRestaurantStatus(
          RestaurantStatus actionFavRestaurantStatus) =>
      this(actionFavRestaurantStatus: actionFavRestaurantStatus);

  @override
  RestaurantState createRestaurantStatus(
          RestaurantStatus createRestaurantStatus) =>
      this(createRestaurantStatus: createRestaurantStatus);

  @override
  RestaurantState deleteRestaurantStatus(
          RestaurantStatus deleteRestaurantStatus) =>
      this(deleteRestaurantStatus: deleteRestaurantStatus);

  @override
  RestaurantState restaurantStatus(RestaurantStatus restaurantStatus) =>
      this(restaurantStatus: restaurantStatus);

  @override
  RestaurantState restaurants(List<Restaurant> restaurants) =>
      this(restaurants: restaurants);

  @override
  RestaurantState restaurantsFav(List<Restaurant> restaurantsFav) =>
      this(restaurantsFav: restaurantsFav);

  @override
  RestaurantState updateRestaurantStatus(
          RestaurantStatus updateRestaurantStatus) =>
      this(updateRestaurantStatus: updateRestaurantStatus);

  @override

  /// This function **does support** nullification of nullable fields. All `null` values passed to `non-nullable` fields will be ignored. You can also use `RestaurantState(...).copyWith.fieldName(...)` to override fields one at a time with nullification support.
  ///
  /// Usage
  /// ```dart
  /// RestaurantState(...).copyWith(id: 12, name: "My name")
  /// ````
  RestaurantState call({
    Object? actionFavRestaurantStatus = const $CopyWithPlaceholder(),
    Object? createRestaurantStatus = const $CopyWithPlaceholder(),
    Object? deleteRestaurantStatus = const $CopyWithPlaceholder(),
    Object? restaurantStatus = const $CopyWithPlaceholder(),
    Object? restaurants = const $CopyWithPlaceholder(),
    Object? restaurantsFav = const $CopyWithPlaceholder(),
    Object? updateRestaurantStatus = const $CopyWithPlaceholder(),
  }) {
    return RestaurantState(
      actionFavRestaurantStatus:
          actionFavRestaurantStatus == const $CopyWithPlaceholder() ||
                  actionFavRestaurantStatus == null
              ? _value.actionFavRestaurantStatus
              // ignore: cast_nullable_to_non_nullable
              : actionFavRestaurantStatus as RestaurantStatus,
      createRestaurantStatus:
          createRestaurantStatus == const $CopyWithPlaceholder() ||
                  createRestaurantStatus == null
              ? _value.createRestaurantStatus
              // ignore: cast_nullable_to_non_nullable
              : createRestaurantStatus as RestaurantStatus,
      deleteRestaurantStatus:
          deleteRestaurantStatus == const $CopyWithPlaceholder() ||
                  deleteRestaurantStatus == null
              ? _value.deleteRestaurantStatus
              // ignore: cast_nullable_to_non_nullable
              : deleteRestaurantStatus as RestaurantStatus,
      restaurantStatus: restaurantStatus == const $CopyWithPlaceholder() ||
              restaurantStatus == null
          ? _value.restaurantStatus
          // ignore: cast_nullable_to_non_nullable
          : restaurantStatus as RestaurantStatus,
      restaurants:
          restaurants == const $CopyWithPlaceholder() || restaurants == null
              ? _value.restaurants
              // ignore: cast_nullable_to_non_nullable
              : restaurants as List<Restaurant>,
      restaurantsFav: restaurantsFav == const $CopyWithPlaceholder() ||
              restaurantsFav == null
          ? _value.restaurantsFav
          // ignore: cast_nullable_to_non_nullable
          : restaurantsFav as List<Restaurant>,
      updateRestaurantStatus:
          updateRestaurantStatus == const $CopyWithPlaceholder() ||
                  updateRestaurantStatus == null
              ? _value.updateRestaurantStatus
              // ignore: cast_nullable_to_non_nullable
              : updateRestaurantStatus as RestaurantStatus,
    );
  }
}

extension $RestaurantStateCopyWith on RestaurantState {
  /// Returns a callable class that can be used as follows: `instanceOfRestaurantState.copyWith(...)` or like so:`instanceOfRestaurantState.copyWith.fieldName(...)`.
  // ignore: library_private_types_in_public_api
  _$RestaurantStateCWProxy get copyWith => _$RestaurantStateCWProxyImpl(this);
}

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

RestaurantState _$RestaurantStateFromJson(Map<String, dynamic> json) =>
    RestaurantState(
      restaurants: (json['restaurants'] as List<dynamic>?)
              ?.map((e) => Restaurant.fromJson(e as Map<String, dynamic>))
              .toList() ??
          const [],
      restaurantsFav: (json['restaurantsFav'] as List<dynamic>?)
              ?.map((e) => Restaurant.fromJson(e as Map<String, dynamic>))
              .toList() ??
          const [],
      restaurantStatus: $enumDecodeNullable(
              _$RestaurantStatusEnumMap, json['restaurantStatus']) ??
          RestaurantStatus.init,
      createRestaurantStatus: $enumDecodeNullable(
              _$RestaurantStatusEnumMap, json['createRestaurantStatus']) ??
          RestaurantStatus.init,
      updateRestaurantStatus: $enumDecodeNullable(
              _$RestaurantStatusEnumMap, json['updateRestaurantStatus']) ??
          RestaurantStatus.init,
      deleteRestaurantStatus: $enumDecodeNullable(
              _$RestaurantStatusEnumMap, json['deleteRestaurantStatus']) ??
          RestaurantStatus.init,
      actionFavRestaurantStatus: $enumDecodeNullable(
              _$RestaurantStatusEnumMap, json['actionFavRestaurantStatus']) ??
          RestaurantStatus.init,
    );

Map<String, dynamic> _$RestaurantStateToJson(RestaurantState instance) =>
    <String, dynamic>{
      'restaurants': instance.restaurants,
      'restaurantsFav': instance.restaurantsFav,
      'restaurantStatus': _$RestaurantStatusEnumMap[instance.restaurantStatus]!,
      'createRestaurantStatus':
          _$RestaurantStatusEnumMap[instance.createRestaurantStatus]!,
      'updateRestaurantStatus':
          _$RestaurantStatusEnumMap[instance.updateRestaurantStatus]!,
      'deleteRestaurantStatus':
          _$RestaurantStatusEnumMap[instance.deleteRestaurantStatus]!,
      'actionFavRestaurantStatus':
          _$RestaurantStatusEnumMap[instance.actionFavRestaurantStatus]!,
    };

const _$RestaurantStatusEnumMap = {
  RestaurantStatus.init: 'init',
  RestaurantStatus.start: 'start',
  RestaurantStatus.success: 'success',
  RestaurantStatus.fail: 'fail',
};
