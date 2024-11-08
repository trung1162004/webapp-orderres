import 'dart:async';

import 'package:bloc/bloc.dart';
import 'package:injectable/injectable.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/domain/entities/restaurant/restaurant.dart';
import 'package:pasgo_restaurants_ecommerce/domain/use_cases/restaurant_case.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/restaurant/restaurant_event.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/restaurant/restaurant_state.dart';

@lazySingleton
class RestaurantBloc extends Bloc<RestaurantEvent, RestaurantState> {
  final RestaurantUseCase _restaurantUseCase;

  RestaurantBloc()
      : _restaurantUseCase = getIt<RestaurantUseCase>(),
        super(RestaurantInitial()) {
    on<GetRestaurants>(_getRestaurants);
    on<GetFavouriteRestaurants>(_getFavouriteRestaurant);
    on<AddFavouriteRestaurants>(_addFavouriteRestaurants);
  }

  Future<FutureOr<void>> _getRestaurants(
      GetRestaurants event, Emitter<RestaurantState> emit) async {
    try {
      emit(state.copyWith(restaurantStatus: RestaurantStatus.start));
      List<Restaurant> restaurants =
          await _restaurantUseCase.getAllRestaurant();
      emit(state.copyWith(
          restaurants: restaurants,
          restaurantStatus: RestaurantStatus.success));
    } catch (e) {
      emit(state.copyWith(restaurantStatus: RestaurantStatus.fail));
    }
  }

  Future<FutureOr<void>> _getFavouriteRestaurant(
      GetFavouriteRestaurants event, Emitter<RestaurantState> emit) async {
    try {
      emit(state.copyWith(restaurantStatus: RestaurantStatus.start));
      List<Restaurant> restaurantsFav =
          await _restaurantUseCase.getAllFavouriteRestaurant();
      emit(state.copyWith(
          restaurantsFav: restaurantsFav,
          restaurantStatus: RestaurantStatus.success));
    } catch (e) {
      emit(state.copyWith(restaurantStatus: RestaurantStatus.fail));
    }
  }

  Future<FutureOr<void>> _addFavouriteRestaurants(
      AddFavouriteRestaurants event, Emitter<RestaurantState> emit) async {
    try {
      emit(state.copyWith(actionFavRestaurantStatus: RestaurantStatus.start));
      await _restaurantUseCase.actionWithRestaurant(
          event.isLikeState, event.rntId);
      emit(state.copyWith(actionFavRestaurantStatus: RestaurantStatus.success));
    } catch (e) {
      emit(state.copyWith(actionFavRestaurantStatus: RestaurantStatus.fail));
    }
  }
}
