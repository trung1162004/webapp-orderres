import 'package:flutter/material.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/comment/comment.page.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/confirm_payment/confirm_payment.page.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/details/detail.page.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/editProfile/edit_profile.page.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/favourite/favourite.page.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/fgp/fgp.page.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/main.page.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/regulations/regulation.page.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/splash.page.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/subscription_history/subscription_history.page.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/widgets/bottomsheets/login_bottom_sheets.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/widgets/bottomsheets/register_bottom_sheets.dart';

class AppRouters {
  AppRouters._();

  static const String splash = '/';
  static const String home = '/home';
  static const String login = '/login';
  static const String register = '/register';
  static const String fgp = '/fgp';
  static const String editProfile = '/editProfile';
  static const String favouriteRestaurant = '/favouriteRestaurant';
  static const String changePassword = '/changePassword';
  static const String detail = '/detail';
  static const String comments = '/comment';
  static const String regulations = '/regulations';
  static const String confirms = '/confirms';
  static const String sch = '/sch';
  static final routes = <String, WidgetBuilder>{
    splash: (context) => const SplashPage(),
    home: (context) => const HomePage(),
    login: (context) => const LoginBottomSheet(),
    register: (context) => const RegisterBottomSheet(),
    fgp: (context) => const ForgotPasswordPage(),
    editProfile: (context) => const EditProfilePage(),
    favouriteRestaurant: (context) => const FavouritePage(),
    detail: (context) => const DetailRestaurantPage(),
    comments: (context) => const CommentPage(),
    regulations: (context) => const RegulationPage(),
    confirms: (context) => const ConfirmPaymentPage(),
    sch: (context) => const SubscriptionHistoryPage(),
  };
}
