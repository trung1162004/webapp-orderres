import 'package:injectable/injectable.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/domain/repositories/auth.repository.dart';
import 'package:pasgo_restaurants_ecommerce/domain/services/auth.service.dart';

@LazySingleton(as: AuthRepository)
class AuthRepositoryImpl extends AuthRepository {
  final AuthService _authService;
  AuthRepositoryImpl() : _authService = getIt<AuthService>();

  @override
  Future<bool> register(String name, String username, String email,
      String phone, String password, String gender, String type) async {
    return await _authService.register(
        name, username, email, phone, password, gender, type);
  }

  @override
  Future<Map<String, dynamic>> login(String u, String p) async {
    return await _authService.login(u, p);
  }

  @override
  Future<void> update(String fullName, String email, String phong,
      String gender, String pwd) async {
    return await _authService.update(fullName, email, phong, gender, pwd);
  }
  
  @override
  Future<void> updatePwd(String rePwd, String pwd) async{
      return await _authService.updatePwd(rePwd, pwd);

  }
}
