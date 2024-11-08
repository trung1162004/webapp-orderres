import 'dart:async';

import 'package:bloc/bloc.dart';
import 'package:injectable/injectable.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/domain/entities/order/order.dart';
import 'package:pasgo_restaurants_ecommerce/domain/use_cases/order_case.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/order/order_event.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/order/order_state.dart';

@lazySingleton
class OrderBloc extends Bloc<OrderEvent, OrderState> {
  final OrderUseCase _orderUseCase;

  OrderBloc()
      : _orderUseCase = getIt<OrderUseCase>(),
        super(OrderInitial()) {
    on<GetOrders>(_getOrders);
    on<CreateOrder>(_createOrder);
  }

  Future<FutureOr<void>> _getOrders(
      GetOrders event, Emitter<OrderState> emit) async {
    try {
      emit(state.copyWith(orderStatus: OrderStatus.start));
      List<OrderM> restaurants = await _orderUseCase.getAllOrders();
      emit(state.copyWith(
          orders: restaurants, orderStatus: OrderStatus.success));
    } catch (e) {
      emit(state.copyWith(orderStatus: OrderStatus.fail));
    }
  }
  Future<FutureOr<void>> _createOrder(
      CreateOrder event, Emitter<OrderState> emit) async {
    try {
      emit(state.copyWith(createOrdertatus: OrderStatus.start));
     await _orderUseCase.createOrder(event.o,event.rntId);
      emit(state.copyWith( createOrdertatus: OrderStatus.success));
    } catch (e) {
      emit(state.copyWith(orderStatus: OrderStatus.fail));
    }
  }
}
