import 'package:injectable/injectable.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/data/models/restaurant/restaurant_model.dart';

import 'package:pasgo_restaurants_ecommerce/domain/repositories/restaurant.repository.dart';
import 'package:pasgo_restaurants_ecommerce/domain/services/restaurant.service.dart';

@LazySingleton(as: RestaurantRepository)
class RestaurantRepositoryImpl extends RestaurantRepository {
  final RestaurantService _restaurantService;
  RestaurantRepositoryImpl() : _restaurantService = getIt<RestaurantService>();

  @override
  Future<List<RestaurantModel>> getAllRestaurant() async {
    List<RestaurantModel> restaurants =
        await _restaurantService.getAllRestaurant();

    return restaurants;
  }
  
  @override
  Future<List<RestaurantModel>> getAllFavouriteRestaurant() async {
    List<RestaurantModel> restaurants =
        await _restaurantService.getAllFavouriteRestaurant();

    return restaurants;
  }
  
  @override
  Future<void> actionWithRestaurant(bool isLikeState, int rntId) async{
    await _restaurantService.actionWithRestaurant(isLikeState, rntId);
  }
}
