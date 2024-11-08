// GENERATED CODE - DO NOT MODIFY BY HAND

// **************************************************************************
// InjectableConfigGenerator
// **************************************************************************

// ignore_for_file: type=lint
// coverage:ignore-file

// ignore_for_file: no_leading_underscores_for_library_prefixes
import 'package:get_it/get_it.dart' as _i1;
import 'package:injectable/injectable.dart' as _i2;

import '../../data/repositories/auth_repository.dart' as _i5;
import '../../data/repositories/category_repository.dart' as _i12;
import '../../data/repositories/comment_repository.dart' as _i19;
import '../../data/repositories/order_repository.dart' as _i27;
import '../../data/repositories/restaurant_repository.dart' as _i34;
import '../../data/repositories/user_repository%20copy.dart' as _i41;
import '../../data/services/auth_service.dart' as _i7;
import '../../data/services/base_service.dart' as _i24;
import '../../data/services/category_service.dart' as _i14;
import '../../data/services/comment_service.dart' as _i21;
import '../../data/services/order_service.dart' as _i29;
import '../../data/services/restaurant_service.dart' as _i36;
import '../../data/services/user_service.dart' as _i43;
import '../../data/use_cases/auth_case.impl.dart' as _i9;
import '../../data/use_cases/category_case.impl.dart' as _i16;
import '../../data/use_cases/comment_case.impl.dart' as _i23;
import '../../data/use_cases/order_case.impl.dart' as _i31;
import '../../data/use_cases/restaurant_case.impl.dart' as _i38;
import '../../data/use_cases/user_case.impl.dart' as _i45;
import '../../domain/repositories/auth.repository.dart' as _i4;
import '../../domain/repositories/category.repository.dart' as _i11;
import '../../domain/repositories/comment.repository.dart' as _i18;
import '../../domain/repositories/order.repository.dart' as _i26;
import '../../domain/repositories/restaurant.repository.dart' as _i33;
import '../../domain/repositories/user.repository.dart' as _i40;
import '../../domain/services/auth.service.dart' as _i6;
import '../../domain/services/category.service.dart' as _i13;
import '../../domain/services/comment.service.dart' as _i20;
import '../../domain/services/order_service.dart' as _i28;
import '../../domain/services/restaurant.service.dart' as _i35;
import '../../domain/services/user.service.dart' as _i42;
import '../../domain/use_cases/auth_case.dart' as _i8;
import '../../domain/use_cases/category_case.dart' as _i15;
import '../../domain/use_cases/comment_case.dart' as _i22;
import '../../domain/use_cases/order_case.dart' as _i30;
import '../../domain/use_cases/restaurant_case.dart' as _i37;
import '../../domain/use_cases/user_case.dart' as _i44;
import '../../presentation/bloc/auth/auth_bloc.dart' as _i3;
import '../../presentation/bloc/category/category_bloc.dart' as _i10;
import '../../presentation/bloc/comment/comment_bloc.dart' as _i17;
import '../../presentation/bloc/order/order_bloc.dart' as _i25;
import '../../presentation/bloc/restaurant/restaurant_bloc.dart' as _i32;
import '../../presentation/bloc/user/user_bloc.dart' as _i39;

extension GetItInjectableX on _i1.GetIt {
// initializes the registration of main-scope dependencies inside of GetIt
  _i1.GetIt init({
    String? environment,
    _i2.EnvironmentFilter? environmentFilter,
  }) {
    final gh = _i2.GetItHelper(
      this,
      environment,
      environmentFilter,
    );
    gh.lazySingleton<_i3.AuthBloc>(() => _i3.AuthBloc());
    gh.lazySingleton<_i4.AuthRepository>(() => _i5.AuthRepositoryImpl());
    gh.lazySingleton<_i6.AuthService>(() => _i7.AuthServiceImpl());
    gh.lazySingleton<_i8.AuthUseCase>(() => _i9.AuthUseCaseImpl());
    gh.lazySingleton<_i10.CategoryBloc>(() => _i10.CategoryBloc());
    gh.lazySingleton<_i11.CategoryRepository>(
        () => _i12.CategoryRepositoryImpl());
    gh.lazySingleton<_i13.CategoryService>(() => _i14.CategoryServiceImpl());
    gh.lazySingleton<_i15.CategoryUseCase>(() => _i16.CategoryUseCaseImpl());
    gh.lazySingleton<_i17.CommentBloc>(() => _i17.CommentBloc());
    gh.lazySingleton<_i18.CommentRepository>(
        () => _i19.CommentRepositoryImpl());
    gh.lazySingleton<_i20.CommentService>(() => _i21.CommentServiceImpl());
    gh.lazySingleton<_i22.CommentUseCase>(() => _i23.CommentUseCaseImpl());
    gh.lazySingleton<_i24.CustomClient>(() => _i24.CustomClient());
    gh.lazySingleton<_i25.OrderBloc>(() => _i25.OrderBloc());
    gh.lazySingleton<_i26.OrderRepository>(() => _i27.OrderRepositoryImpl());
    gh.lazySingleton<_i28.OrderService>(() => _i29.OrderServiceImpl());
    gh.lazySingleton<_i30.OrderUseCase>(() => _i31.OrderUseCaseImpl());
    gh.lazySingleton<_i32.RestaurantBloc>(() => _i32.RestaurantBloc());
    gh.lazySingleton<_i33.RestaurantRepository>(
        () => _i34.RestaurantRepositoryImpl());
    gh.lazySingleton<_i35.RestaurantService>(
        () => _i36.RestaurantServiceImpl());
    gh.lazySingleton<_i37.RestaurantUseCase>(
        () => _i38.RestaurantUseCaseImpl());
    gh.lazySingleton<_i39.UserBloc>(() => _i39.UserBloc());
    gh.lazySingleton<_i40.UserRepository>(() => _i41.UserRepositoryImpl());
    gh.lazySingleton<_i42.UserService>(() => _i43.UserServiceImpl());
    gh.lazySingleton<_i44.UserUseCase>(() => _i45.UserUseCaseImpl());
    return this;
  }
}
