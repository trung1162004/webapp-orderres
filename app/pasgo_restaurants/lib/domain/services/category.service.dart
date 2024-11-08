import 'package:pasgo_restaurants_ecommerce/data/models/category/category_model.dart';

abstract class CategoryService {
  Future<List<CategoryModel>> getAllCategory();
}
