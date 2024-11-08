import 'package:injectable/injectable.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/path.configs.dart';
import 'package:pasgo_restaurants_ecommerce/data/models/order/order_model.dart';
import 'package:pasgo_restaurants_ecommerce/domain/entities/order/order.dart';
import 'package:pasgo_restaurants_ecommerce/domain/repositories/order.repository.dart';
import 'package:pasgo_restaurants_ecommerce/domain/use_cases/order_case.dart';

@LazySingleton(as: OrderUseCase)
class OrderUseCaseImpl extends OrderUseCase {
  final OrderRepository _orderRepository;
  OrderUseCaseImpl() : _orderRepository = getIt<OrderRepository>();

  @override
  Future<List<OrderM>> getAllOrders() async {

    List<OrderModel> orders = await _orderRepository.getAllOrders();

    return orders
        .map((e) => OrderM(
            id: e.id,
            code: e.code,
            status: e.status,
            type: e.type,
            // receiverAddress: e.receiverAddress,
            receiverEmail: e.receiverEmail,
            // totalPrice: e.totalPrice,
            restaurantName: e.restaurantName,
            // price: e.price,
            timeBooking: e.timeBooking,
            quantity: e.quantity,
            createdAt: e.createdAt,
            updatedAt: e.updatedAt,
            user: e.user,
        logo: e.logo != null ? e.logo! : ""))
        .toList();
  }
  
  @override
  Future<void> createOrder(OrderModel o, int rntId) async{
    await _orderRepository.createOrder(o,rntId);
  }
}
