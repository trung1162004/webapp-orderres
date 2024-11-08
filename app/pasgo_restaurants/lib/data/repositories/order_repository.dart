import 'package:injectable/injectable.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/data/models/order/order_model.dart';
import 'package:pasgo_restaurants_ecommerce/domain/repositories/order.repository.dart';
import 'package:pasgo_restaurants_ecommerce/domain/services/order_service.dart';

@LazySingleton(as: OrderRepository)
class OrderRepositoryImpl extends OrderRepository {
  final OrderService _orderService;
  OrderRepositoryImpl() : _orderService = getIt<OrderService>();
  @override
  Future<List<OrderModel>> getAllOrders() async {
    return await _orderService.getAllOrders();
  }
  
  @override
  Future<void> createOrder(OrderModel o, int rntId) async{
        await _orderService.createOrder(o,rntId);

  }
}
