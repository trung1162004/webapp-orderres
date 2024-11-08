import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/auth/auth_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/category/category_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/comment/comment_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/order/order_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/restaurant/restaurant_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/user/user_bloc.dart';

class MainBloc {
  static List<BlocProvider> allBlocs() => [
        BlocProvider<AuthBloc>(create: (_) => getIt<AuthBloc>()),
        BlocProvider<UserBloc>(create: (_) => getIt<UserBloc>()),
        BlocProvider<CategoryBloc>(create: (_) => getIt<CategoryBloc>()),
        BlocProvider<RestaurantBloc>(create: (_) => getIt<RestaurantBloc>()),
        BlocProvider<CommentBloc>(create: (_) => getIt<CommentBloc>()),
        BlocProvider<OrderBloc>(create: (_) => getIt<OrderBloc>()),
      ];
}
