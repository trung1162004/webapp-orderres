import 'dart:convert';

import 'package:injectable/injectable.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/path.configs.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/shareprefs.dart';
import 'package:pasgo_restaurants_ecommerce/data/models/restaurant/restaurant_model.dart';
import 'package:pasgo_restaurants_ecommerce/data/services/base_service.dart';
import 'package:pasgo_restaurants_ecommerce/domain/services/restaurant.service.dart';

@LazySingleton(as: RestaurantService)
class RestaurantServiceImpl extends BaseService implements RestaurantService {
  @override
  Future<List<RestaurantModel>> getAllRestaurant() async {
    try {
      final response = await client.get(ApiPaths.restaurant["get"]!);
      //  print(json.decode(utf8.decode(response.bodyBytes)));
         if (response.statusCode == 200) {
        Map<String, dynamic> decodedResponse =
            json.decode(utf8.decode(response.bodyBytes));
        List<dynamic> data = decodedResponse["data"];
        List<RestaurantModel> restaurants =
            data.map((item) => RestaurantModel.fromJson(item)).toList();
        print(restaurants.length);
        return restaurants;
      } else {
        // print(json.decode(utf8.decode(response.bodyBytes)));
        throw Exception('Failed to load restaurants');
      }
    } catch (e) {
      print("e $e");
      throw Exception('Failed to load restaurants');
    }
  }

  @override
  Future<List<RestaurantModel>> getAllFavouriteRestaurant() async {
    try {
      String? token = await SharePrefs.getUserAccessToken();
      Map<String, String> defaultHeaders = {
        'Content-Type': 'application/json',
        "Authorization": "Bearer $token"
      };

      final response = await client.get(ApiPaths.restaurant["getFavourite"]!,
          currentHeaders: defaultHeaders);
      if (response.statusCode == 200) {
        // print(json.decode(utf8.decode(response.bodyBytes)));
        Map<String, dynamic> decodedResponse =
            json.decode(utf8.decode(response.bodyBytes));
        List<dynamic> data = decodedResponse["data"];
        List<RestaurantModel> restaurants =
            data.map((item) => RestaurantModel.fromJson(item)).toList();
        return restaurants;
      } else {
        throw Exception('Failed to load restaurants');
      }
    } catch (e) {
      // print("e $e");
      throw Exception('Failed to load restaurants');
    }
  }

  @override
  Future<void> actionWithRestaurant(bool isLikeState, int rntId) async {
    try {
      String? token = await SharePrefs.getUserAccessToken();
      Map<String, String> defaultHeaders = {
        'Content-Type': 'application/json',
        "Authorization": "Bearer $token"
      };
      String type = !isLikeState ? "LIKE" : "UNLIKE";
      final body = json.encode({"restaurant_id": rntId, "type": type});
      final response = await client.post(ApiPaths.restaurant['addToFav']!,
          body: body, currentHeaders: defaultHeaders);
      if (response.statusCode == 200) {
        // print("login successfully ${utf8.decode(response.bodyBytes)}");
        return json.decode(utf8.decode(response.bodyBytes));
      } else {
        // print("login eeeee ${utf8.decode(response.bodyBytes)}");
        throw Exception(
            'Failed to login users ${utf8.decode(response.bodyBytes)}');
      }
    } catch (e) {
      // print("login eeeee $e");
    }
  }
}
