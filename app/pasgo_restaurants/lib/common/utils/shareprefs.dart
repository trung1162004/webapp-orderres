import 'dart:convert';

import 'package:pasgo_restaurants_ecommerce/data/models/subscriptions/subscription_model2.dart';
import 'package:shared_preferences/shared_preferences.dart';

class SharePrefs {
  static const String _sharedPreferenceUserData = "userData";
  static const String _sharedPreferenceUserAccessToken = "userAccessToken";
  static const String _sharedPreferenceUserPassword = "userPassword";
  static const String _sharedPreferenceUserName = "userName";
  static const String _sharedPreferenceUserFullName = "userFullName";
  static const String _sharedPreferenceSubscriptionData = "subscriptionData";

  static Future<void> setUserData(Map<String, dynamic>? userData) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    if (userData == null) {
      await prefs.remove(_sharedPreferenceUserData);
    } else {
      String jsonString = jsonEncode(userData);
      await prefs.setString(_sharedPreferenceUserData, jsonString);
    }
  }

  static Future<String?> getUserData() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    return prefs.getString(_sharedPreferenceUserData);
  }

  static Future setUserPassword(String? value) async {
    SharedPreferences rs = await SharedPreferences.getInstance();
    if (value == null) {
      await rs.remove(_sharedPreferenceUserPassword);
    } else {
      await rs.setString(_sharedPreferenceUserPassword, value);
    }
  }

  static Future<String?> getUserPassword() async {
    SharedPreferences preferences = await SharedPreferences.getInstance();
    return preferences.getString(_sharedPreferenceUserPassword);
  }

  static Future setUserName(String? value) async {
    SharedPreferences rs = await SharedPreferences.getInstance();
    if (value == null) {
      await rs.remove(_sharedPreferenceUserName);
    } else {
      await rs.setString(_sharedPreferenceUserName, value);
    }
  }

  static Future<String?> getUserName() async {
    SharedPreferences preferences = await SharedPreferences.getInstance();
    return preferences.getString(_sharedPreferenceUserName);
  }

  static Future setUserFullName(String? value) async {
    SharedPreferences rs = await SharedPreferences.getInstance();
    if (value == null) {
      await rs.remove(_sharedPreferenceUserFullName);
    } else {
      await rs.setString(_sharedPreferenceUserFullName, value);
    }
  }

  static Future<String?> getUserFullName() async {
    SharedPreferences preferences = await SharedPreferences.getInstance();
    return preferences.getString(_sharedPreferenceUserFullName);
  }

  static Future setUserAccessToken(String? value) async {
    SharedPreferences rs = await SharedPreferences.getInstance();
    if (value == null) {
      await rs.remove(_sharedPreferenceUserAccessToken);
    } else {
      await rs.setString(_sharedPreferenceUserAccessToken, value);
    }
  }

  static Future<String?> getUserAccessToken() async {
    SharedPreferences preferences = await SharedPreferences.getInstance();
    return preferences.getString(_sharedPreferenceUserAccessToken);
  }

  static Future<void> addSubscriptions(
      List<SubscriptionMM2> newSubscriptions) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? jsonString = prefs.getString(_sharedPreferenceSubscriptionData);

    List<SubscriptionMM2> existingSubscriptions = [];
    if (jsonString != null) {
      List<dynamic> decodedData = jsonDecode(jsonString);
      existingSubscriptions =
          decodedData.map((item) => SubscriptionMM2.fromJson(item)).toList();
    }

    existingSubscriptions.addAll(newSubscriptions);
    String updatedJsonString =
        jsonEncode(existingSubscriptions.map((e) => e.toJson()).toList());
    await prefs.setString(_sharedPreferenceSubscriptionData, updatedJsonString);
  }

  static Future<List<SubscriptionMM2>> getSubscriptionData() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? jsonString = prefs.getString(_sharedPreferenceSubscriptionData);

    if (jsonString != null) {
      List<dynamic> jsonData = jsonDecode(jsonString);
      return jsonData.map((e) => SubscriptionMM2.fromJson(e)).toList();
    } else {
      return [];
    }
  }
}
