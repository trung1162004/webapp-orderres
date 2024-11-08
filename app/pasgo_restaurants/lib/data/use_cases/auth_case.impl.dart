import 'package:injectable/injectable.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/domain/repositories/auth.repository.dart';
import 'package:pasgo_restaurants_ecommerce/domain/use_cases/auth_case.dart';

@LazySingleton(as: AuthUseCase)
class AuthUseCaseImpl extends AuthUseCase {
  final AuthRepository _authRepository;
  AuthUseCaseImpl() : _authRepository = getIt<AuthRepository>();
  
  @override
  Future<Map<String, dynamic>> login(String u, String p) async{
  return await _authRepository.login(u, p);
  }
  
  @override
  Future<bool> register(
    String name, String username, String email, String phone, String password,String gender,String type) async{
   return  await _authRepository.register(name, username, email, phone, password,gender,type);
  }
  
  @override
  Future<void> update(String fullName, String email, String phong, String gender,String pwd) async{
    return await _authRepository.update(fullName, email, phong, gender,pwd);
  }
  
  @override
  Future<void> updatePwd(String rePwd, String pwd) async{
    return await _authRepository.updatePwd(rePwd,pwd);
  }

}
