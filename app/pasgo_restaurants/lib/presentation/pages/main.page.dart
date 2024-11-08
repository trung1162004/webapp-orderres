import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:pasgo_restaurants_ecommerce/color.style.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/shareprefs.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/order/order_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/order/order_event.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/history/history.page.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/home/home.page.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/profile/profile.page.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  int _selectedIndex = 0;
  String? data;
  String? pwd;
  Map<String, dynamic>? userData;

  Future<void> _initializeUserData() async {
    data = await SharePrefs.getUserData();
    pwd = await SharePrefs.getUserPassword();
    if (data != null) {
      userData = jsonDecode(data!);
    }
    setState(() {});
  }

  void _onItemTapped(int index) async {
    setState(() {
      _selectedIndex = index;
    });
    if (index == 2) {
      _initializeUserData();
    }
    if (index == 1) {
      String? token = await SharePrefs.getUserAccessToken();
      if (token != null) {
        getIt<OrderBloc>().add(const GetOrders());
      }
    }
  }

  @override
  void initState() {
    _initializeUserData();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      bottomNavigationBar: _bottomNav,
      body: IndexedStack(
        index: _selectedIndex,
        children: [
           HomeScreen(userData:userData),
           const HistoryPage(),
          ProfilePage(
            storedPwd: pwd,
            userData: userData,
          ),
        ],
      ),
    );
  }

  BottomNavigationBar get _bottomNav => BottomNavigationBar(
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Icon(Icons.home),
            label: 'Home',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.history),
            label: 'History',
          ),
          BottomNavigationBarItem(
            icon: Icon(
              Icons.person,
            ),
            label: 'Profile',
          ),
        ],
        currentIndex: _selectedIndex,
        unselectedItemColor: MyAppColors.black,
        selectedItemColor: MyAppColors.backgroundColor,
        onTap: _onItemTapped,
      );
}
