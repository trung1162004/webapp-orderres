
import 'package:pasgo_restaurants_ecommerce/domain/entities/category/category.dart';

abstract class CategoryUseCase {
  Future<List<Category>> getAllCategory();
}
