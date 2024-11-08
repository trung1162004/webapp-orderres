
abstract class AuthUseCase {
  Future<Map<String, dynamic>> login(String u, String p);
  Future<bool> register(
    String name, String username, String email, String phone, String password,String gender, String type
  );
  Future<void> update(String fullName, String email,String phong, String gender,String pwd);
  Future<void> updatePwd(String rePwd,String pwd);
 
}
