import 'package:injectable/injectable.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/path.configs.dart';
import 'package:pasgo_restaurants_ecommerce/data/models/restaurant/restaurant_model.dart';
import 'package:pasgo_restaurants_ecommerce/domain/entities/restaurant/restaurant.dart';
import 'package:pasgo_restaurants_ecommerce/domain/repositories/restaurant.repository.dart';
import 'package:pasgo_restaurants_ecommerce/domain/use_cases/restaurant_case.dart';

@LazySingleton(as: RestaurantUseCase)
class RestaurantUseCaseImpl extends RestaurantUseCase {
  final RestaurantRepository _restaurantRepository;
  RestaurantUseCaseImpl()
      : _restaurantRepository = getIt<RestaurantRepository>();

  @override
  Future<List<Restaurant>> getAllRestaurant() async {
    List<RestaurantModel> restaurants =
        await _restaurantRepository.getAllRestaurant();

    return restaurants
        .map((e) => Restaurant(
            id: e.id,
            name: e.name,
            logo: ApiConfig.baseUrlImage+e.logo,
            status: e.status,
            email: e.email,
            // description: e.description??"",
            address: e.address??"",
            // content: e.content??"",
            phone: e.phone,
            type: e.type,
            quantity: e.quantity,
            price: e.price,
            work_time_close: e.work_time_close,
            work_time_open: e.work_time_open,
            fit: e.fit,
            specialDish: e.specialDish,
            space: e.space,
            parking: e.parking,
            feature: e.feature,
            // utilities: e.utilities ?? [],
            depositRegulation: e.depositRegulation,
            discountRegulation: e.discountRegulation,
            reservationTimeRegulation: e.reservationTimeRegulation,
            pasgoTimeRegulation: e.pasgoTimeRegulation,
            holdTimeRegulation: e.holdTimeRegulation,
            mininumGuestRegulation: e.mininumGuestRegulation,
            invoiceRegulation: e.invoiceRegulation,
            serviceFeeRegulation: e.serviceFeeRegulation,
            bringInFeeRegulation: e.bringInFeeRegulation,


            // createdAt: e.createdAt,
            updatedAt: e.updatedAt,
            user: e.user,
            categories: e.categories, images: e.images, discount: e.discount))
        .toList();
  }

  @override
  Future<List<Restaurant>> getAllFavouriteRestaurant() async {
        final url="${ApiConfig.baseUrl}/upload/file/";
List<RestaurantModel> restaurants =
        await _restaurantRepository.getAllFavouriteRestaurant();

    return restaurants
        .map((e) => Restaurant(
            id: e.id,
            name: e.name,
            logo: ApiConfig.baseUrlImage+e.logo,
            status: e.status,
            email: e.email,
            // description: e.description,
            address: e.address,
            // content: e.content,
            work_time_close: e.work_time_close,
            work_time_open: e.work_time_open,
            phone: e.phone,
            type: e.type,
            // createdAt: e.createdAt,
            updatedAt: e.updatedAt,
            quantity: e.quantity,
            price: e.price,
            fit: e.fit,
            specialDish: e.specialDish,
            space: e.space,
            parking: e.parking,
            feature: e.feature,
            // utilities: e.utilities ?? [],
            depositRegulation: e.depositRegulation,
            discountRegulation: e.discountRegulation,
            pasgoTimeRegulation: e.pasgoTimeRegulation,
            reservationTimeRegulation: e.reservationTimeRegulation,
            holdTimeRegulation: e.holdTimeRegulation,
            mininumGuestRegulation: e.mininumGuestRegulation,
            invoiceRegulation: e.invoiceRegulation,
            serviceFeeRegulation: e.serviceFeeRegulation,
            bringInFeeRegulation: e.bringInFeeRegulation,

            user: e.user,
            categories: e.categories, images: e.images, discount: e.discount))
        .toList();
  }

  @override
  Future<void> actionWithRestaurant(bool isLikeState, int rntId) async {
    await _restaurantRepository.actionWithRestaurant(isLikeState, rntId);
  }
}
