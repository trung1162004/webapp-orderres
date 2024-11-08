import 'package:injectable/injectable.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/data/models/category/category_model.dart';
import 'package:pasgo_restaurants_ecommerce/domain/repositories/category.repository.dart';

import 'package:pasgo_restaurants_ecommerce/domain/services/category.service.dart';

@LazySingleton(as: CategoryRepository)
class CategoryRepositoryImpl extends CategoryRepository {
  final CategoryService _categoryService;
  CategoryRepositoryImpl() : _categoryService = getIt<CategoryService>();

  @override
  Future<List<CategoryModel>> getAllCategory() async {
    return await _categoryService.getAllCategory();
  }
}
