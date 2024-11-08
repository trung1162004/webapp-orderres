

import 'package:copy_with_extension/copy_with_extension.dart';
import 'package:equatable/equatable.dart';
import 'package:json_annotation/json_annotation.dart';
import 'package:pasgo_restaurants_ecommerce/domain/entities/order/order.dart';
part 'order_state.g.dart';

enum OrderStatus { init, start, success, fail }

@JsonSerializable()
@CopyWith()
class OrderState extends Equatable {
  final List<OrderM> orders;
  final OrderStatus orderStatus;
  final OrderStatus createOrdertatus;
  final OrderStatus updateOrdertatus;
  final OrderStatus deleteOrdertatus;
  const OrderState({
    this.orders = const [],
    this.orderStatus = OrderStatus.init,
    this.createOrdertatus = OrderStatus.init,
    this.updateOrdertatus = OrderStatus.init,
    this.deleteOrdertatus = OrderStatus.init,
  });

  @override
  List<Object> get props => [
        orders,
        orderStatus,
        createOrdertatus,
        updateOrdertatus,
        deleteOrdertatus
      ];
}

final class OrderInitial extends OrderState {}
