import 'package:copy_with_extension/copy_with_extension.dart';
import 'package:equatable/equatable.dart';
import 'package:json_annotation/json_annotation.dart';
import 'package:pasgo_restaurants_ecommerce/domain/entities/category/category.dart';
part 'category_state.g.dart';

enum CategoryStatus { init, start, success, fail }

@JsonSerializable()
@CopyWith()
class CategoryState extends Equatable {
  final List<Category> categories;
  final CategoryStatus categoryStatus;
  final CategoryStatus createCategoryStatus;
  final CategoryStatus updateCategoryStatus;
  final CategoryStatus deleteCategoryStatus;
  const CategoryState({
    this.categories = const [],
    this.categoryStatus = CategoryStatus.init,
    this.createCategoryStatus = CategoryStatus.init,
    this.updateCategoryStatus = CategoryStatus.init,
    this.deleteCategoryStatus = CategoryStatus.init,
  });

  @override
  List<Object> get props => [
        categories,
        categoryStatus,
        createCategoryStatus,
        updateCategoryStatus,
        deleteCategoryStatus
      ];
}

final class CategoryInitial extends CategoryState {}
