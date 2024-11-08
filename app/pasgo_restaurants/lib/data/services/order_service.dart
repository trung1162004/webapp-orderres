import 'dart:convert';

import 'package:injectable/injectable.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/path.configs.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/shareprefs.dart';
import 'package:pasgo_restaurants_ecommerce/data/models/order/order_model.dart';
import 'package:pasgo_restaurants_ecommerce/data/services/base_service.dart';
import 'package:pasgo_restaurants_ecommerce/domain/services/order_service.dart';

@LazySingleton(as: OrderService)
class OrderServiceImpl extends BaseService implements OrderService {
  @override
  Future<List<OrderModel>> getAllOrders() async {
    try {
      String? token = await SharePrefs.getUserAccessToken();
      Map<String, String> defaultHeaders = {
        'Content-Type': 'application/json',
        "Authorization": "Bearer $token"
      };

      final response = await client.get(ApiPaths.orders["get"]!,
          currentHeaders: defaultHeaders);
      if (response.statusCode == 200) {
        Map<String, dynamic> decodedResponse =
            json.decode(utf8.decode(response.bodyBytes));
        List<dynamic> data = decodedResponse["data"];
        // print(json.decode(utf8.decode(response.bodyBytes)));
        List<OrderModel> restaurants =
            data.map((item) => OrderModel.fromJson(item)).toList();

        return restaurants;
      } else {
        // print(json.decode(utf8.decode(response.bodyBytes)));
        throw Exception('Failed to load restaurants');
      }
    } catch (e) {
      // print("e $e");
      throw Exception('Failed to load restaurants');
    }
  }

  @override
  Future<void> createOrder(OrderModel o, int rntId) async {
    try {
      String? token = await SharePrefs.getUserAccessToken();
      Map<String, String> defaultHeaders = {
        'Content-Type': 'application/json',
        "Authorization": "Bearer $token"
      };
      print(o.restaurantName.runtimeType);
      final body = json.encode({
        "status": "PENDING",
        "image": o.logo,
        "type": "BOOKING",
        // "receiverAddress": o.receiverAddress,
        "receiverEmail": o.receiverEmail,
        "receiverName": o.receiverName,
        "receiverPhone": o.receiverPhone,
        // "totalPrice": o.totalPrice,
        "quantity": o.quantity,
        // "price": o.price,
        "restaurantName": o.restaurantName,
        "restaurantId": rntId,
        "logo": o.logo,
        "timeBooking": o.timeBooking
      });
      print(body.toString());
      final response = await client.post(ApiPaths.orders["create"]!,
          body: body, currentHeaders: defaultHeaders);
      // print(token);
      // print(ApiPaths.orders["create"]!);
      // print(utf8.decode(response.bodyBytes));
    } catch (e) {
      // print("e $e");
      throw Exception('Failed to load restaurants');
    }
  }
}
