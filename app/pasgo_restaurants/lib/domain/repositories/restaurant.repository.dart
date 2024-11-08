import 'package:pasgo_restaurants_ecommerce/data/models/restaurant/restaurant_model.dart';

abstract class RestaurantRepository{
    Future<List<RestaurantModel>> getAllRestaurant();
  Future<List<RestaurantModel>> getAllFavouriteRestaurant();
  Future<void> actionWithRestaurant(bool isLikeState,int rntId);

}