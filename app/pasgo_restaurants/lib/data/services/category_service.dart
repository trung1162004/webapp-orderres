import 'dart:convert';

import 'package:injectable/injectable.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/path.configs.dart';
import 'package:pasgo_restaurants_ecommerce/data/models/category/category_model.dart';
import 'package:pasgo_restaurants_ecommerce/data/services/base_service.dart';
import 'package:pasgo_restaurants_ecommerce/domain/services/category.service.dart';

@LazySingleton(as: CategoryService)
class CategoryServiceImpl extends BaseService implements CategoryService {
  @override
  Future<List<CategoryModel>> getAllCategory() async {
    final response = await client.get(ApiPaths.categories["get"]!);
    if (response.statusCode == 200) {
      Map<String, dynamic> decodedResponse =
          json.decode(utf8.decode(response.bodyBytes));
      

      List<dynamic> data = decodedResponse["data"];
      

      List<CategoryModel> categories =
          data.map((item) => CategoryModel.fromJson(item)).toList(); 
      
      // print("Categories: ${categories.toString()}");

      return categories;
    } else {
      // print("Failed to load categories. Status code: ${response.statusCode}");
      // print("Response body: ${response.body}");
      
      throw Exception('Failed to load categories');
    }
  }
}

