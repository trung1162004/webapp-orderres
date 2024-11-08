import 'package:equatable/equatable.dart';
import 'package:pasgo_restaurants_ecommerce/data/models/order/order_model.dart';

class OrderEvent extends Equatable {
  const OrderEvent();

  @override
  List<Object> get props => [];
}

class GetOrders extends OrderEvent {
  const GetOrders();
  @override
  List<Object> get props => [];
}

class CreateOrder extends OrderEvent {
  final OrderModel o;
  final int rntId;
  const CreateOrder({
    required this.o,
    required this.rntId,
  });
  @override
  List<Object> get props => [o, rntId];
}
