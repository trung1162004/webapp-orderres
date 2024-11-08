import 'package:pasgo_restaurants_ecommerce/data/models/order/order_model.dart';
import 'package:pasgo_restaurants_ecommerce/domain/entities/order/order.dart';

abstract class OrderUseCase{
  Future<List<OrderM>> getAllOrders();
  Future<void> createOrder(OrderModel o, int rntId);

}