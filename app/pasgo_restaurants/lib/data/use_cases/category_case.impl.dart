import 'package:injectable/injectable.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/data/models/category/category_model.dart';
import 'package:pasgo_restaurants_ecommerce/domain/repositories/category.repository.dart';
import 'package:pasgo_restaurants_ecommerce/domain/use_cases/category_case.dart';
import 'package:pasgo_restaurants_ecommerce/domain/entities/category/category.dart';

@LazySingleton(as: CategoryUseCase)
class CategoryUseCaseImpl extends CategoryUseCase {
  final CategoryRepository _categoryRepository;
  CategoryUseCaseImpl() : _categoryRepository = getIt<CategoryRepository>();

  @override
  Future<List<Category>> getAllCategory() async {
    List<CategoryModel> categories = await _categoryRepository.getAllCategory();
    return categories
        .map((e) => Category(
            id: e.id,
            name: e.name,
            status: e.status,
            image: e.image))
        .toList();
  }
}
