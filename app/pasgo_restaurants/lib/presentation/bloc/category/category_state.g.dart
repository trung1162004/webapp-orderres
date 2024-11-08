// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'category_state.dart';

// **************************************************************************
// CopyWithGenerator
// **************************************************************************

abstract class _$CategoryStateCWProxy {
  CategoryState categories(List<Category> categories);

  CategoryState categoryStatus(CategoryStatus categoryStatus);

  CategoryState createCategoryStatus(CategoryStatus createCategoryStatus);

  CategoryState deleteCategoryStatus(CategoryStatus deleteCategoryStatus);

  CategoryState updateCategoryStatus(CategoryStatus updateCategoryStatus);

  /// This function **does support** nullification of nullable fields. All `null` values passed to `non-nullable` fields will be ignored. You can also use `CategoryState(...).copyWith.fieldName(...)` to override fields one at a time with nullification support.
  ///
  /// Usage
  /// ```dart
  /// CategoryState(...).copyWith(id: 12, name: "My name")
  /// ````
  CategoryState call({
    List<Category>? categories,
    CategoryStatus? categoryStatus,
    CategoryStatus? createCategoryStatus,
    CategoryStatus? deleteCategoryStatus,
    CategoryStatus? updateCategoryStatus,
  });
}

/// Proxy class for `copyWith` functionality. This is a callable class and can be used as follows: `instanceOfCategoryState.copyWith(...)`. Additionally contains functions for specific fields e.g. `instanceOfCategoryState.copyWith.fieldName(...)`
class _$CategoryStateCWProxyImpl implements _$CategoryStateCWProxy {
  final CategoryState _value;

  const _$CategoryStateCWProxyImpl(this._value);

  @override
  CategoryState categories(List<Category> categories) =>
      this(categories: categories);

  @override
  CategoryState categoryStatus(CategoryStatus categoryStatus) =>
      this(categoryStatus: categoryStatus);

  @override
  CategoryState createCategoryStatus(CategoryStatus createCategoryStatus) =>
      this(createCategoryStatus: createCategoryStatus);

  @override
  CategoryState deleteCategoryStatus(CategoryStatus deleteCategoryStatus) =>
      this(deleteCategoryStatus: deleteCategoryStatus);

  @override
  CategoryState updateCategoryStatus(CategoryStatus updateCategoryStatus) =>
      this(updateCategoryStatus: updateCategoryStatus);

  @override

  /// This function **does support** nullification of nullable fields. All `null` values passed to `non-nullable` fields will be ignored. You can also use `CategoryState(...).copyWith.fieldName(...)` to override fields one at a time with nullification support.
  ///
  /// Usage
  /// ```dart
  /// CategoryState(...).copyWith(id: 12, name: "My name")
  /// ````
  CategoryState call({
    Object? categories = const $CopyWithPlaceholder(),
    Object? categoryStatus = const $CopyWithPlaceholder(),
    Object? createCategoryStatus = const $CopyWithPlaceholder(),
    Object? deleteCategoryStatus = const $CopyWithPlaceholder(),
    Object? updateCategoryStatus = const $CopyWithPlaceholder(),
  }) {
    return CategoryState(
      categories:
          categories == const $CopyWithPlaceholder() || categories == null
              ? _value.categories
              // ignore: cast_nullable_to_non_nullable
              : categories as List<Category>,
      categoryStatus: categoryStatus == const $CopyWithPlaceholder() ||
              categoryStatus == null
          ? _value.categoryStatus
          // ignore: cast_nullable_to_non_nullable
          : categoryStatus as CategoryStatus,
      createCategoryStatus:
          createCategoryStatus == const $CopyWithPlaceholder() ||
                  createCategoryStatus == null
              ? _value.createCategoryStatus
              // ignore: cast_nullable_to_non_nullable
              : createCategoryStatus as CategoryStatus,
      deleteCategoryStatus:
          deleteCategoryStatus == const $CopyWithPlaceholder() ||
                  deleteCategoryStatus == null
              ? _value.deleteCategoryStatus
              // ignore: cast_nullable_to_non_nullable
              : deleteCategoryStatus as CategoryStatus,
      updateCategoryStatus:
          updateCategoryStatus == const $CopyWithPlaceholder() ||
                  updateCategoryStatus == null
              ? _value.updateCategoryStatus
              // ignore: cast_nullable_to_non_nullable
              : updateCategoryStatus as CategoryStatus,
    );
  }
}

extension $CategoryStateCopyWith on CategoryState {
  /// Returns a callable class that can be used as follows: `instanceOfCategoryState.copyWith(...)` or like so:`instanceOfCategoryState.copyWith.fieldName(...)`.
  // ignore: library_private_types_in_public_api
  _$CategoryStateCWProxy get copyWith => _$CategoryStateCWProxyImpl(this);
}

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

CategoryState _$CategoryStateFromJson(Map<String, dynamic> json) =>
    CategoryState(
      categories: (json['categories'] as List<dynamic>?)
              ?.map((e) => Category.fromJson(e as Map<String, dynamic>))
              .toList() ??
          const [],
      categoryStatus: $enumDecodeNullable(
              _$CategoryStatusEnumMap, json['categoryStatus']) ??
          CategoryStatus.init,
      createCategoryStatus: $enumDecodeNullable(
              _$CategoryStatusEnumMap, json['createCategoryStatus']) ??
          CategoryStatus.init,
      updateCategoryStatus: $enumDecodeNullable(
              _$CategoryStatusEnumMap, json['updateCategoryStatus']) ??
          CategoryStatus.init,
      deleteCategoryStatus: $enumDecodeNullable(
              _$CategoryStatusEnumMap, json['deleteCategoryStatus']) ??
          CategoryStatus.init,
    );

Map<String, dynamic> _$CategoryStateToJson(CategoryState instance) =>
    <String, dynamic>{
      'categories': instance.categories,
      'categoryStatus': _$CategoryStatusEnumMap[instance.categoryStatus]!,
      'createCategoryStatus':
          _$CategoryStatusEnumMap[instance.createCategoryStatus]!,
      'updateCategoryStatus':
          _$CategoryStatusEnumMap[instance.updateCategoryStatus]!,
      'deleteCategoryStatus':
          _$CategoryStatusEnumMap[instance.deleteCategoryStatus]!,
    };

const _$CategoryStatusEnumMap = {
  CategoryStatus.init: 'init',
  CategoryStatus.start: 'start',
  CategoryStatus.success: 'success',
  CategoryStatus.fail: 'fail',
};
