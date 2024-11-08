import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/color.style.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/shareprefs.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/spacing_unit.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/order/order_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/order/order_state.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/history/widgets/item_history.dart';

class HistoryPage extends StatefulWidget {
  const HistoryPage({super.key});

  @override
  State<HistoryPage> createState() => _HistoryPageState();
}

class _HistoryPageState extends State<HistoryPage> {
  Map<String, dynamic>? userData;
  String? data;

  Future<void> _initializeUserData() async {
    data = await SharePrefs.getUserData();
    if (data != null) {
      userData = jsonDecode(data!);
    }
    setState(() {});
  }

  @override
  void initState() {
    _initializeUserData();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    final padding = MediaQuery.of(context).padding;

    return Scaffold(
      body: Padding(
        padding: EdgeInsets.only(top: padding.top + 16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const Padding(
              padding: EdgeInsets.only(left: 12),
              child: Text(
                "History Order",
                style: TextStyle(
                  color: MyAppColors.backgroundColor,
                  fontSize: SpacingUnit.x8,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
            Expanded(
              child: BlocBuilder<OrderBloc, OrderState>(
                builder: (context, state) {
                  final ordersFiltered = state.orders
                      .where(
                          (order) => order.receiverEmail == userData?["email"])
                      .toList()..sort((a,b)=>b.createdAt.compareTo(a.createdAt));
                  if (ordersFiltered.isEmpty) {
                    return const Center(child: Text("No data"));
                  }

                  return ListView.separated(
                    physics: const BouncingScrollPhysics(),
                    itemBuilder: (context, index) {
                      return ItemHistory(order: ordersFiltered[index]);
                    },
                    separatorBuilder: (context, index) =>
                        const SizedBox(height: 20),
                    itemCount: ordersFiltered.length,
                  );
                },
              ),
            ),
          ],
        ),
      ),
    );
  }
}
