import 'package:pasgo_restaurants_ecommerce/data/models/order/order_model.dart';

abstract class OrderService{
  Future<List<OrderModel>> getAllOrders();
  Future<void> createOrder(OrderModel o, int rntId);

}