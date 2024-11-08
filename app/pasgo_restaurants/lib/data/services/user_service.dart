import 'dart:convert';

import 'package:injectable/injectable.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/path.configs.dart';
import 'package:pasgo_restaurants_ecommerce/data/services/base_service.dart';
import 'package:pasgo_restaurants_ecommerce/domain/services/user.service.dart';

@LazySingleton(as: UserService)
class UserServiceImpl extends BaseService implements UserService {
  @override
  Future<Map<String, dynamic>> get(int id) async {
    final response = await client.get('${ApiPaths.user['get']!}/$id');
    if (response.statusCode == 200) {
   
      return json.decode(utf8.decode(response.bodyBytes));
    } else {
      throw Exception(
          'Failed to login users ${utf8.decode(response.bodyBytes)}');
    }
  }
}
