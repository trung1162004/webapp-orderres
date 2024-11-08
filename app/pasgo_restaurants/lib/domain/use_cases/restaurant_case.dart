import 'package:pasgo_restaurants_ecommerce/domain/entities/restaurant/restaurant.dart';

abstract class RestaurantUseCase{
  Future<List<Restaurant>> getAllRestaurant();
  Future<List<Restaurant>> getAllFavouriteRestaurant();
  Future<void> actionWithRestaurant(bool isLikeState,int rntId);

}