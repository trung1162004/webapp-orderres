import 'package:injectable/injectable.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/domain/repositories/user.repository.dart';
import 'package:pasgo_restaurants_ecommerce/domain/use_cases/user_case.dart';

@LazySingleton(as: UserUseCase)
class UserUseCaseImpl extends UserUseCase {
  final UserRepository _userRepository;
  UserUseCaseImpl() : _userRepository = getIt<UserRepository>();

  @override
  Future<Map<String, dynamic>> get(int id) async {
    return await _userRepository.get(id);
  }
}
