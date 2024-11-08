

import 'package:copy_with_extension/copy_with_extension.dart';
import 'package:equatable/equatable.dart';
import 'package:json_annotation/json_annotation.dart';
import 'package:pasgo_restaurants_ecommerce/domain/entities/restaurant/restaurant.dart';
part 'restaurant_state.g.dart';

enum RestaurantStatus { init, start, success, fail }

@JsonSerializable()
@CopyWith()
class RestaurantState extends Equatable {
  final List<Restaurant> restaurants;
  final List<Restaurant> restaurantsFav;
  final RestaurantStatus restaurantStatus;
  final RestaurantStatus createRestaurantStatus;
  final RestaurantStatus updateRestaurantStatus;
  final RestaurantStatus deleteRestaurantStatus;
  final RestaurantStatus actionFavRestaurantStatus;
  const RestaurantState({
    this.restaurants = const [],
    this.restaurantsFav = const [],
    this.restaurantStatus = RestaurantStatus.init,
    this.createRestaurantStatus = RestaurantStatus.init,
    this.updateRestaurantStatus = RestaurantStatus.init,
    this.deleteRestaurantStatus = RestaurantStatus.init,
    this.actionFavRestaurantStatus = RestaurantStatus.init,
  });

  @override
  List<Object> get props => [
        restaurants,
        restaurantsFav,
        restaurantStatus,
        createRestaurantStatus,
        updateRestaurantStatus,
        deleteRestaurantStatus,
        actionFavRestaurantStatus,
      ];
}

final class RestaurantInitial extends RestaurantState {}
