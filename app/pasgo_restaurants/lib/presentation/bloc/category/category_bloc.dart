import 'dart:async';

import 'package:bloc/bloc.dart';
import 'package:injectable/injectable.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/domain/entities/category/category.dart';
import 'package:pasgo_restaurants_ecommerce/domain/use_cases/category_case.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/category/category_event.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/category/category_state.dart';

@lazySingleton
class CategoryBloc extends Bloc<CategoryEvent, CategoryState> {
  final CategoryUseCase _categoryUseCase;

  CategoryBloc()
      : _categoryUseCase = getIt<CategoryUseCase>(),
        super(CategoryInitial()) {
    on<GetCategory>(_getCategory);
  }

  Future<FutureOr<void>> _getCategory(
      GetCategory event, Emitter<CategoryState> emit) async {
    try {
      emit(state.copyWith(categoryStatus: CategoryStatus.start));
      List<Category> categories = await _categoryUseCase.getAllCategory();
      // print("================${categories.runtimeType}");
      emit(state.copyWith(
          categories: categories, categoryStatus: CategoryStatus.success));
    } catch (e) {
      emit(state.copyWith(categoryStatus: CategoryStatus.fail));
    }
  }
}
