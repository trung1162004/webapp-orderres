
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/color.style.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/route.configs.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/dimens.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/screen_options.dart';
import 'package:pasgo_restaurants_ecommerce/domain/enum/screen_option_enum.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/main_bloc.dart';

void main() {
  configureDependencies();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});
  @override
  Widget build(BuildContext context) {
    DimensionApp.init(context);
    ScreenUtils.setPreferredOrientations([ScreenOrientation.portraitUp]);
    SystemChrome.setSystemUIOverlayStyle(
      const SystemUiOverlayStyle(statusBarColor: MyAppColors.transparent),
    );
    return MultiBlocProvider(
      providers: MainBloc.allBlocs(),
      child: MaterialApp(
        theme: ThemeData(
          drawerTheme: const DrawerThemeData(
              scrimColor: Colors.transparent, elevation: 0.6),
        ),
        routes: AppRouters.routes,
        initialRoute: AppRouters.splash,
        debugShowCheckedModeBanner: false,
      ),
    );
  
  }
}
