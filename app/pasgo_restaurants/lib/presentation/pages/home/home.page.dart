import 'package:auto_size_text/auto_size_text.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/color.style.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/route.configs.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/shareprefs.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/spacing_unit.dart';
import 'package:pasgo_restaurants_ecommerce/data/models/restaurant/restaurant_model.dart';
import 'package:pasgo_restaurants_ecommerce/domain/entities/restaurant/restaurant.dart';
import 'package:pasgo_restaurants_ecommerce/gen/assets.gen.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/category/category_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/category/category_event.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/category/category_state.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/restaurant/restaurant_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/restaurant/restaurant_event.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/restaurant/restaurant_state.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/home/widgets/item_app_bar.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/widgets/bottomsheets/content.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/widgets/bottomsheets/search_bottom_sheets.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/widgets/bottomsheets/subscription_package_bottomsheets.dart';

class HomeScreen extends StatefulWidget {
  final Map<String, dynamic>? userData;

  const HomeScreen({super.key, this.userData});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  late List<Restaurant> listRnt = [];

  @override
  void initState() {
    super.initState();
    getIt<RestaurantBloc>().add(const GetRestaurants());
    getIt<CategoryBloc>().add(const GetCategory());
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leadingWidth: 150,
        leading: const Padding(
          padding: EdgeInsets.only(left: 8, top: 4),
          child: Text(
            "Eat&Eat",
            style: TextStyle(
              color: MyAppColors.backgroundColor,
              fontSize: SpacingUnit.x8,
              fontWeight: FontWeight.bold,
            ),
          ),
        ),
        actions: [
          if (widget.userData?["type"] == "RESTAURANT")
            ItemAppBar(
              isUsingImage: true,
              onTap: () {
                showAppModalBottomSheet(
                  context: context,
                  child: const SubscriptionPackageBottomSheet(),
                );
              },
              assets: Assets.images.subscriptions24dp.path,
              icon: Icons.search,
            ),
          const SizedBox(width: 15),
          ItemAppBar(
            onTap: () {
              showAppModalBottomSheet(
                context: context,
                child: SearchBottomSheet(listRnt: listRnt),
              );
            },
            icon: Icons.search,
          ),
          const SizedBox(width: 8),
        ],
      ),
      body: BlocBuilder<CategoryBloc, CategoryState>(
        builder: (context, stateCategories) {
          if (stateCategories.categoryStatus == CategoryStatus.start) {
            return const Center(child: CircularProgressIndicator());
          } else if (stateCategories.categoryStatus == CategoryStatus.success) {
            return ListView.builder(
              padding: const EdgeInsets.all(20),
              itemCount: stateCategories.categories.length,
              itemBuilder: (context, index) {
                final category = stateCategories.categories[index];
                return Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Container(
                      margin: const EdgeInsets.symmetric(vertical: 5),
                      child: Text(
                        category.name,
                        style: const TextStyle(
                          color: MyAppColors.backgroundColor,
                          fontWeight: FontWeight.w900,
                          fontSize: 17,
                        ),
                      ),
                    ),
                    BlocBuilder<RestaurantBloc, RestaurantState>(
                      builder: (context, state) {
                        if (state.restaurantStatus == RestaurantStatus.start) {
                          return const Center(
                            child: CircularProgressIndicator(),
                          );
                        } else if (state.restaurantStatus ==
                            RestaurantStatus.success) {
                          final categoryID = category.id;
                          final filteredRestaurants = state.restaurants
                              .where((restaurant) =>
                                  restaurant.status == 'ACTIVE' &&
                                      (restaurant.categories?.any(
                                          (cat) => cat.id == categoryID) ??
                                  false))
                              .toList();

                          filteredRestaurants.sort((a, b) {
                            final aUpdateAt = (a).updatedAt;
                            final bUpdateAt = (b).updatedAt;

                            final aDateTime = DateTime.parse(aUpdateAt!);
                            final bDateTime = DateTime.parse(bUpdateAt!);

                            return bDateTime.compareTo(aDateTime);
                          });

                          listRnt = state.restaurants;
                          if (filteredRestaurants.isEmpty) {
                            return const Text("No restaurant");
                          }
                          return SizedBox(
                            height: 340,
                            child: ListView.separated(
                              physics: const BouncingScrollPhysics(),
                              scrollDirection: Axis.horizontal,
                              itemBuilder: (context, indexR) {
                                final restaurant = filteredRestaurants[indexR];
                                return GestureDetector(
                                  onTap: () async {
                                    String? data =
                                        await SharePrefs.getUserData();
                                    if (data == null ||
                                        data == '{}' ||
                                        data.isEmpty) {
                                      if (!context.mounted) return;
                                      Navigator.pushNamed(
                                          context, AppRouters.login);
                                    } else {
                                      if (!context.mounted) return;
                                      Navigator.pushNamed(
                                        context,
                                        AppRouters.detail,
                                        arguments: restaurant,
                                      );
                                    }
                                  },
                                  child: SizedBox(
                                    width: 200,
                                    child: Column(
                                      mainAxisSize: MainAxisSize.min,
                                      crossAxisAlignment:
                                          CrossAxisAlignment.start,
                                      children: [
                                        ClipRRect(
                                          borderRadius:
                                              BorderRadius.circular(10),
                                          child: SizedBox(
                                            width: 185,
                                            height: 210,
                                            child: Image.network(
                                              //test
                                              // !restaurant.logo.isEmpty
                                              //release
                                              restaurant.logo.isEmpty
                                                  ? "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5f/Red_X.svg/2048px-Red_X.svg.png"
                                                  : restaurant.logo,
                                              fit: BoxFit.cover,
                                            ),
                                          ),
                                        ),
                                        const SizedBox(height: 5),
                                        AutoSizeText(
                                          restaurant.name,
                                          overflow: TextOverflow.visible,
                                          style: const TextStyle(
                                            fontWeight: FontWeight.w700,
                                          ),
                                        ),
                                        Row(
                                          children: [
                                            Text(
                                                "Price approx: ${restaurant.price.toString()} \$",
                                                style: const TextStyle(
                                                    color: MyAppColors.black,
                                                    fontWeight:
                                                        FontWeight.w700)),
                                          ],
                                        ),
                                        Text(
                                          "${restaurant.discount == null ? "" : "(-${restaurant.discount}%)"}",
                                          style: const TextStyle(
                                              fontSize: 18,
                                              fontWeight: FontWeight.w700,
                                              color: Colors.red),
                                        ),
                                      ],
                                    ),
                                  ),
                                );
                              },
                              separatorBuilder:
                                  (BuildContext context, int index) =>
                                      const SizedBox(width: SpacingUnit.x2),
                              itemCount: filteredRestaurants.length,
                            ),
                          );
                        }
                        return const SizedBox();
                      },
                    ),
                  ],
                );
              },
            );
          }
          return const SizedBox();
        },
      ),
    );
  }
}
