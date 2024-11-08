import 'dart:convert';

import 'package:carousel_slider/carousel_slider.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/color.style.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/path.configs.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/shareprefs.dart';
import 'package:pasgo_restaurants_ecommerce/domain/entities/restaurant/restaurant.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/restaurant/restaurant_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/restaurant/restaurant_event.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/restaurant/restaurant_state.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/details/widgets/body.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/order/order_page.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/widgets/buttons/button.dart';

class DetailRestaurantPage extends StatefulWidget {
  const DetailRestaurantPage({super.key});

  @override
  State<DetailRestaurantPage> createState() => _DetailRestaurantPageState();
}

class _DetailRestaurantPageState extends State<DetailRestaurantPage> {
  Map<String, dynamic>? userData;

  bool stateLike = false;
  @override
  void initState() {
    _getUserId();
    super.initState();
    getIt<RestaurantBloc>().add(const GetFavouriteRestaurants());
    Future.delayed(const Duration(milliseconds: 1), () {
      setState(() {
        stateLike = context.read<RestaurantBloc>().state.restaurantsFav.any(
            (restaurant) =>
                restaurant.id ==
                (ModalRoute.of(context)?.settings.arguments as Restaurant).id);
      });
    });
  }

  Future<void> _getUserId() async {
    String? data = await SharePrefs.getUserData();
    if (data != null) {
      userData = jsonDecode(data);
    }
  }

  @override
  Widget build(BuildContext context) {
    final Restaurant rnt =
        ModalRoute.of(context)?.settings.arguments as Restaurant;
    return PopScope(
      onPopInvoked: (e) {
        getIt<RestaurantBloc>().add(const GetFavouriteRestaurants());
      },
      child: Scaffold(
        body: Stack(
          children: [
            CustomScrollView(
              slivers: [
                SliverAppBar(
                  expandedHeight: 380,
                  pinned: true,
                  flexibleSpace: FlexibleSpaceBar(
                    background: (rnt.images?.isEmpty ?? true)
                        ? Image.network(
                            rnt.logo ?? '',
                            fit: BoxFit.cover,
                          )
                        : CarouselSlider(
                            items: rnt.images!.map((image) {
                              return Builder(
                                builder: (BuildContext context) {
                                  return Image.network(
                                      ApiConfig.baseUrlImage + image,
                                      fit: BoxFit.cover);
                                },
                              );
                            }).toList(),
                            options: CarouselOptions(
                              height: 450.0,
                              autoPlay: false,
                              enlargeCenterPage: true,
                              aspectRatio: 1 / 1,
                              viewportFraction: 1.0,
                            ),
                          ),
                  ),
                  leading: GestureDetector(
                    onTap: () => Navigator.pop(context),
                    child: const Icon(
                      Icons.west,
                      color: MyAppColors.backgroundColor,
                      size: 42,
                    ),
                  ),
                  actions: [
                    BlocBuilder<RestaurantBloc, RestaurantState>(
                      builder: (context, state) {
                        bool isLiked = state.restaurantsFav
                            .any((restaurant) => restaurant.id == rnt.id);
                        return GestureDetector(
                          onTap: () {
                            setState(() {
                              stateLike = !stateLike;
                            });
                            getIt<RestaurantBloc>().add(AddFavouriteRestaurants(
                              rntId: rnt.id,
                              isLikeState: !isLiked,
                            ));
                          },
                          child: Icon(
                            stateLike ? Icons.favorite : Icons.favorite_border,
                            color: stateLike ? Colors.red : Colors.grey,
                            size: 42,
                          ),
                        );
                      },
                    ),
                  ],
                ),
                SliverToBoxAdapter(
                  child: BodyDetail(rnt: rnt!),
                ),
              ],
            ),
            Align(
              alignment: Alignment.bottomCenter,
              child: Container(
                padding: const EdgeInsets.all(8),
                height: 80,
                width: MediaQuery.of(context).size.width,
                decoration: const BoxDecoration(color: MyAppColors.white),
                child: Row(
                  crossAxisAlignment: CrossAxisAlignment.center,
                  mainAxisAlignment: MainAxisAlignment.end,
                  children: [
                    const SizedBox(width: 20),
                    ButtonWidget(
                      buttonText: "Order now",
                      onPressed: () {
                        Navigator.of(context).push(MaterialPageRoute(
                            builder: (context) => OrderPage(
                                  orderData: rnt,
                                //  totalPrice: rnt!.price,
                                )));
                      },
                      width: 140,
                    ),
                  ],
                ),
              ),
            )
          ],
        ),
      ),
    );
  }
}
