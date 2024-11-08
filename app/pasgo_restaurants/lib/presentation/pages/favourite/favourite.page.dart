import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/dimens.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/restaurant/restaurant_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/restaurant/restaurant_event.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/restaurant/restaurant_state.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/favourite/widgets/item_favourite.dart';
import 'package:pasgo_restaurants_ecommerce/domain/entities/restaurant/restaurant.dart';
class FavouritePage extends StatefulWidget {
  const FavouritePage({super.key});

  @override
  State<FavouritePage> createState() => _FavouritePageState();
}

class _FavouritePageState extends State<FavouritePage> {
  @override
  void initState() {
    super.initState();
    getIt<RestaurantBloc>().add(const GetFavouriteRestaurants());
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Favourite"),
      ),
      body: BlocBuilder<RestaurantBloc, RestaurantState>(
        builder: (context, state) {
          final sortedRestaurants = List<Restaurant>.from(state.restaurantsFav)
            ..sort((a, b) {
              final updatedAtA = _parseDateTime(a.updatedAt) ?? DateTime(0);
              final updatedAtB = _parseDateTime(b.updatedAt) ?? DateTime(0);
              return updatedAtA.compareTo(updatedAtB);
            });

          if (sortedRestaurants.isEmpty) {
            return const Center(
              child: Text("No data"),
            );
          }
          return Container(
            padding: const EdgeInsets.symmetric(vertical: 20, horizontal: 8),
            width: DimensionApp.screenWidth,
            height: DimensionApp.screenHeight,
            child: ListView.separated(
              physics: const BouncingScrollPhysics(),
              itemBuilder: (context, index) {
                return ItemFavourite(
                  restaurantsFav: sortedRestaurants,
                  rnt: sortedRestaurants[index],
                );
              },
              separatorBuilder: (context, index) => const SizedBox(height: 25),
              itemCount: sortedRestaurants.length,
            ),
          );
        },
      ),
    );
  }

  DateTime? _parseDateTime(String? dateTimeString) {
    if (dateTimeString == null) return null;
    try {
      return DateTime.parse(dateTimeString);
    } catch (e) {
      print("Failed to parse date: $e");
      return null;
    }
  }
}
