import 'package:diacritic/diacritic.dart';
import 'package:flutter/material.dart';
import 'package:pasgo_restaurants_ecommerce/color.style.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/route.configs.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/dimens.dart';
import 'package:pasgo_restaurants_ecommerce/domain/entities/restaurant/restaurant.dart';

class SearchBottomSheet extends StatefulWidget {
  final List<Restaurant> listRnt;
  const SearchBottomSheet({super.key, required this.listRnt});

  @override
  State<SearchBottomSheet> createState() => _SearchBottomSheetState();
}

class _SearchBottomSheetState extends State<SearchBottomSheet> {
  TextEditingController searchController = TextEditingController();
  List<Restaurant> filteredList = [];

  @override
  void initState() {
    super.initState();
    filteredList = widget.listRnt;
    searchController.addListener(_filterRestaurants);
  }

  @override
  void dispose() {
    searchController.removeListener(_filterRestaurants);
    searchController.dispose();
    super.dispose();
  }

  void _filterRestaurants() {
    final query = removeDiacritics(searchController.text.toLowerCase());
    setState(() {
      filteredList = widget.listRnt
          .where((restaurant) => removeDiacritics(restaurant.name.toLowerCase()).contains(query))
          .toList();
    });
  }

  @override
  Widget build(BuildContext context) {
    final padding = MediaQuery.of(context).padding;
    return Container(
      padding: EdgeInsets.only(
        left: 8,
        right: 8,
        top: padding.top + 40,
        bottom: 8,
      ),
      height: DimensionApp.screenHeight,
      width: DimensionApp.screenWidth,
      color: MyAppColors.white,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          Align(
            alignment: Alignment.topRight,
            child: GestureDetector(
              onTap: () => Navigator.pop(context),
              child: const Icon(Icons.close, size: 32),
            ),
          ),
          const SizedBox(height: 10),
          TextField(
            controller: searchController,
            decoration: const InputDecoration(
              hintText: 'Search restaurants...',
              border: OutlineInputBorder(),
              prefixIcon: Icon(Icons.search),
            ),
          ),
          const SizedBox(height: 10),
          Expanded(
            child: ListView.builder(

              itemCount: filteredList.length,
              itemBuilder: (context, index) {
                final restaurant = filteredList[index];
                return Padding(
                  padding: const EdgeInsets.symmetric(vertical: 10),
                  child: ListTile(
                    title: Text(restaurant.name),
                    leading: ClipRRect(
                      borderRadius: BorderRadius.circular(5),
                      child: Image.network(
                        restaurant.logo.isEmpty
                            ? "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5f/Red_X.svg/2048px-Red_X.svg.png"
                            : restaurant.logo,
                        width: 50,
                        height: 50,
                        fit: BoxFit.cover,
                      ),
                    ),
                    onTap: () {
                      Navigator.pushNamed(
                        context,
                        AppRouters.detail,
                        arguments: restaurant,
                      );
                    },
                  ),
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}
