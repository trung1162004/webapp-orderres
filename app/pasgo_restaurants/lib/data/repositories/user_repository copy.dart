import 'package:injectable/injectable.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';

import 'package:pasgo_restaurants_ecommerce/domain/repositories/user.repository.dart';
import 'package:pasgo_restaurants_ecommerce/domain/services/user.service.dart';

@LazySingleton(as: UserRepository)
class UserRepositoryImpl extends UserRepository {
  final UserService _userService;
  UserRepositoryImpl() : _userService = getIt<UserService>();

  @override
    Future<Map<String,dynamic>> get(int id) async {
    return await _userService.get(id);
  }
}
