import 'dart:convert';

import 'package:injectable/injectable.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/path.configs.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/shareprefs.dart';
import 'package:pasgo_restaurants_ecommerce/data/models/comment/comment_model.dart';
import 'package:pasgo_restaurants_ecommerce/data/services/base_service.dart';
import 'package:pasgo_restaurants_ecommerce/domain/services/comment.service.dart';

@LazySingleton(as: CommentService)
class CommentServiceImpl extends BaseService implements CommentService {
  @override
  Future<List<CommentModel>> getAllComment(int id) async {
    print('--------------getAllComment-------------------');
    try {
      final response = await client
          .get("${ApiPaths.comment["get"]}restaurant_id=$id&status");
      print('--------------response-------------------${response.statusCode}');
      if (response.statusCode == 200) {
        
        Map<String, dynamic> decodedResponse =
            json.decode(utf8.decode(response.bodyBytes));
        List<dynamic> data = decodedResponse["data"];
        print('-------------------${data}');
        List<CommentModel> comments =
            data.map((item) => CommentModel.fromJson(item)).toList();
        print('--------kkkkkkkkkk-----------${comments}');
        return comments;
      } else {
        throw Exception('Failed to load restaurants');
      }
    } catch (e) {
      // print("e $e");
      throw Exception('Failed to load restaurants');
    }
  }

  @override
  Future<void> createComment(String content,  int rntId) async {
    try {
      String? data;
      Map<String, dynamic>? userData;
      String? token = await SharePrefs.getUserAccessToken();
      Map<String, String> defaultHeaders = {
        'Content-Type': 'application/json',
        "Authorization": "Bearer $token"
      };
      data = await SharePrefs.getUserData();
      if (data != null) {
        userData = jsonDecode(data);
      }
      final body = json.encode({
        "name": userData!["name"],
        "status": "APPROVED",
        "user_id": userData["id"],
        "email": userData["email"],
        "content": content,
        "restaurant_id": rntId
      });
      // print(body.toString());
      final response = await client.post(ApiPaths.comment["create"]!,
          body: body, currentHeaders: defaultHeaders);
      // print(utf8.decode(response.bodyBytes));
    } catch (e) {
      // print("e $e");
      throw Exception('Failed to load restaurants');
    }
  }

  @override
  Future<void> updateComment(
      String content, int rntId, int cmtId) async {
    try {
      String? data;
      Map<String, dynamic>? userData;

      data = await SharePrefs.getUserData();
      if (data != null) {
        userData = jsonDecode(data);
      }
      final body = json.encode({
        "name": userData!["name"],
        "status": "APPROVED",
        "user_id": userData["id"],
        "content": content,
        "email": userData["email"],
        "restaurant_id": rntId
      });
      String? token = await SharePrefs.getUserAccessToken();
      Map<String, String> defaultHeaders = {
        'Content-Type': 'application/json',
        "Authorization": "Bearer $token"
      };
      final response = await client.put("${ApiPaths.comment["update"]}/$cmtId",
          body: body, customHeaders: defaultHeaders);
      // print(utf8.decode(response.bodyBytes));
    } catch (e) {
      // print("e $e");
      throw Exception('Failed to load restaurants');
    }
  }

  @override
  Future<void> deleteComment(int cmtId) async {
    try {
      await client.delete("${ApiPaths.comment["delete"]}/$cmtId");
    } catch (e) {
      throw Exception('Failed to load restaurants $e');
    }
  }
}
