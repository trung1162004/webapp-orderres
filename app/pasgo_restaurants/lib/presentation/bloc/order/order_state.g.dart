// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'order_state.dart';

// **************************************************************************
// CopyWithGenerator
// **************************************************************************

abstract class _$OrderStateCWProxy {
  OrderState createOrdertatus(OrderStatus createOrdertatus);

  OrderState deleteOrdertatus(OrderStatus deleteOrdertatus);

  OrderState orderStatus(OrderStatus orderStatus);

  OrderState orders(List<OrderM> orders);

  OrderState updateOrdertatus(OrderStatus updateOrdertatus);

  /// This function **does support** nullification of nullable fields. All `null` values passed to `non-nullable` fields will be ignored. You can also use `OrderState(...).copyWith.fieldName(...)` to override fields one at a time with nullification support.
  ///
  /// Usage
  /// ```dart
  /// OrderState(...).copyWith(id: 12, name: "My name")
  /// ````
  OrderState call({
    OrderStatus? createOrdertatus,
    OrderStatus? deleteOrdertatus,
    OrderStatus? orderStatus,
    List<OrderM>? orders,
    OrderStatus? updateOrdertatus,
  });
}

/// Proxy class for `copyWith` functionality. This is a callable class and can be used as follows: `instanceOfOrderState.copyWith(...)`. Additionally contains functions for specific fields e.g. `instanceOfOrderState.copyWith.fieldName(...)`
class _$OrderStateCWProxyImpl implements _$OrderStateCWProxy {
  final OrderState _value;

  const _$OrderStateCWProxyImpl(this._value);

  @override
  OrderState createOrdertatus(OrderStatus createOrdertatus) =>
      this(createOrdertatus: createOrdertatus);

  @override
  OrderState deleteOrdertatus(OrderStatus deleteOrdertatus) =>
      this(deleteOrdertatus: deleteOrdertatus);

  @override
  OrderState orderStatus(OrderStatus orderStatus) =>
      this(orderStatus: orderStatus);

  @override
  OrderState orders(List<OrderM> orders) => this(orders: orders);

  @override
  OrderState updateOrdertatus(OrderStatus updateOrdertatus) =>
      this(updateOrdertatus: updateOrdertatus);

  @override

  /// This function **does support** nullification of nullable fields. All `null` values passed to `non-nullable` fields will be ignored. You can also use `OrderState(...).copyWith.fieldName(...)` to override fields one at a time with nullification support.
  ///
  /// Usage
  /// ```dart
  /// OrderState(...).copyWith(id: 12, name: "My name")
  /// ````
  OrderState call({
    Object? createOrdertatus = const $CopyWithPlaceholder(),
    Object? deleteOrdertatus = const $CopyWithPlaceholder(),
    Object? orderStatus = const $CopyWithPlaceholder(),
    Object? orders = const $CopyWithPlaceholder(),
    Object? updateOrdertatus = const $CopyWithPlaceholder(),
  }) {
    return OrderState(
      createOrdertatus: createOrdertatus == const $CopyWithPlaceholder() ||
              createOrdertatus == null
          ? _value.createOrdertatus
          // ignore: cast_nullable_to_non_nullable
          : createOrdertatus as OrderStatus,
      deleteOrdertatus: deleteOrdertatus == const $CopyWithPlaceholder() ||
              deleteOrdertatus == null
          ? _value.deleteOrdertatus
          // ignore: cast_nullable_to_non_nullable
          : deleteOrdertatus as OrderStatus,
      orderStatus:
          orderStatus == const $CopyWithPlaceholder() || orderStatus == null
              ? _value.orderStatus
              // ignore: cast_nullable_to_non_nullable
              : orderStatus as OrderStatus,
      orders: orders == const $CopyWithPlaceholder() || orders == null
          ? _value.orders
          // ignore: cast_nullable_to_non_nullable
          : orders as List<OrderM>,
      updateOrdertatus: updateOrdertatus == const $CopyWithPlaceholder() ||
              updateOrdertatus == null
          ? _value.updateOrdertatus
          // ignore: cast_nullable_to_non_nullable
          : updateOrdertatus as OrderStatus,
    );
  }
}

extension $OrderStateCopyWith on OrderState {
  /// Returns a callable class that can be used as follows: `instanceOfOrderState.copyWith(...)` or like so:`instanceOfOrderState.copyWith.fieldName(...)`.
  // ignore: library_private_types_in_public_api
  _$OrderStateCWProxy get copyWith => _$OrderStateCWProxyImpl(this);
}

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

OrderState _$OrderStateFromJson(Map<String, dynamic> json) => OrderState(
      orders: (json['orders'] as List<dynamic>?)
              ?.map((e) => OrderM.fromJson(e as Map<String, dynamic>))
              .toList() ??
          const [],
      orderStatus:
          $enumDecodeNullable(_$OrderStatusEnumMap, json['orderStatus']) ??
              OrderStatus.init,
      createOrdertatus:
          $enumDecodeNullable(_$OrderStatusEnumMap, json['createOrdertatus']) ??
              OrderStatus.init,
      updateOrdertatus:
          $enumDecodeNullable(_$OrderStatusEnumMap, json['updateOrdertatus']) ??
              OrderStatus.init,
      deleteOrdertatus:
          $enumDecodeNullable(_$OrderStatusEnumMap, json['deleteOrdertatus']) ??
              OrderStatus.init,
    );

Map<String, dynamic> _$OrderStateToJson(OrderState instance) =>
    <String, dynamic>{
      'orders': instance.orders,
      'orderStatus': _$OrderStatusEnumMap[instance.orderStatus]!,
      'createOrdertatus': _$OrderStatusEnumMap[instance.createOrdertatus]!,
      'updateOrdertatus': _$OrderStatusEnumMap[instance.updateOrdertatus]!,
      'deleteOrdertatus': _$OrderStatusEnumMap[instance.deleteOrdertatus]!,
    };

const _$OrderStatusEnumMap = {
  OrderStatus.init: 'init',
  OrderStatus.start: 'start',
  OrderStatus.success: 'success',
  OrderStatus.fail: 'fail',
};
