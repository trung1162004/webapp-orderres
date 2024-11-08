import 'dart:convert';

import 'package:injectable/injectable.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/path.configs.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/shareprefs.dart';
import 'package:pasgo_restaurants_ecommerce/data/services/base_service.dart';
import 'package:pasgo_restaurants_ecommerce/domain/services/auth.service.dart';

@LazySingleton(as: AuthService)
class AuthServiceImpl extends BaseService implements AuthService {
  @override
  Future<Map<String, dynamic>> login(String u, String p) async {
    final body = json.encode({"email": u, "password": p});
    final response = await client.post(ApiPaths.auth['login']!, body: body);
    if (response.statusCode == 200) {
      // print("login successfully ${utf8.decode(response.bodyBytes)}");
      // Map<String, dynamic> a =  json.decode(utf8.decode(response.bodyBytes));
      // print(a['data']['user']['id']);
      return json.decode(utf8.decode(response.bodyBytes));
    } else {
      throw Exception(
          'Failed to login users ${utf8.decode(response.bodyBytes)}');
    }
  }

  @override
  Future<bool> register(String name, String username, String email,
      String phone, String password, String gender, String type) async {
    final body = json.encode({
      "name": name,
      "email": email,
      "username": username,
      "password": password,
      "gender": gender,
      "phone": phone,
      "type": type
    });
    final response = await client.post(ApiPaths.auth['register']!, body: body);
    if (response.statusCode == 200) {
      final responseBody = utf8.decode(response.bodyBytes);
      final responseJson = json.decode(responseBody);
      final metaCode = responseJson['meta']['code'];

      if (metaCode == 'Success200') {
        await SharePrefs.setUserName(username);
        return true;
      } else {
        return false;
      }
    } else {
      // print(utf8.decode(response.bodyBytes));
      throw Exception(
          'Failed to register users ${utf8.decode(response.bodyBytes)}');
    }
  }

  @override
  Future<void> update(String fullName, String email, String phong,
      String gender, String pwd) async {
    final body = json.encode({
      "name": fullName,
      "email": email,
      "password": pwd,
      "gender": gender,
      "phone": phong,
      "type": "CUSTOMER",
      "status": "ACTIVE"
    });
    String? data = await SharePrefs.getUserAccessToken();
    Map<String, String> defaultHeaders = {
      'Content-Type': 'application/json',
      "Authorization": "Bearer $data"
    };
    final response = await client.put(ApiPaths.auth['update']!,
        body: body, customHeaders: defaultHeaders);
    if (response.statusCode == 200) {
      // print(
      //     "login successfully ${json.decode(utf8.decode(response.bodyBytes))}");

      return json.decode(utf8.decode(response.bodyBytes));
    } else {
      // print("update error ${utf8.decode(response.bodyBytes)} ----------------");
      throw Exception(
          'Failed to login users ${utf8.decode(response.bodyBytes)}');
    }
  }

  @override
  Future<void> updatePwd(String rePwd, String pwd) async {
    final body = json.encode({
      "confirm_password": rePwd,
      "password": pwd,
    });
    String? data = await SharePrefs.getUserAccessToken();
    Map<String, String> defaultHeaders = {
      'Content-Type': 'application/json',
      "Authorization": "Bearer $data"
    };
    final response = await client.put(ApiPaths.auth['changePassword']!,
        body: body, customHeaders: defaultHeaders);
    if (response.statusCode == 200) {
      // print(
      //     "login successfully ${json.decode(utf8.decode(response.bodyBytes))}");

      return json.decode(utf8.decode(response.bodyBytes));
    } else {
      // print("update error ${utf8.decode(response.bodyBytes)} ----------------");
      throw Exception(
          'Failed to login users ${utf8.decode(response.bodyBytes)}');
    }
  }
}
