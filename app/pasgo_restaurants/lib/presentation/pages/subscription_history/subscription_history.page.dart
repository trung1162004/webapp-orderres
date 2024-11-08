import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/shareprefs.dart';
import 'package:pasgo_restaurants_ecommerce/data/models/subscriptions/subscription_model2.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/subscription_history/items/ss.item.dart';

class SubscriptionHistoryPage extends StatefulWidget {
  const SubscriptionHistoryPage({super.key});

  @override
  State<SubscriptionHistoryPage> createState() =>
      _SubscriptionHistoryPageState();
}

class _SubscriptionHistoryPageState extends State<SubscriptionHistoryPage> {
  List<SubscriptionMM2> _subscriptions = [];
  bool _isLoading = true;
  String? _errorMessage;

  @override
  void initState() {
    super.initState();
    _loadSubscriptions();
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    _loadSubscriptions();
  }

  Future<void> _loadSubscriptions() async {
    try {
      List<SubscriptionMM2> subscriptions =
          await SharePrefs.getSubscriptionData();
      setState(() {
        _subscriptions = subscriptions;
        _isLoading = false;
      });
    } catch (e) {
      setState(() {
        _errorMessage = "Error loading subscriptions: $e";
        _isLoading = false;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    final int id = ModalRoute.of(context)?.settings.arguments as int;

    if (_isLoading) {
      return Scaffold(
        appBar: AppBar(title: const Text("Subscription History")),
        body: const Center(child: CircularProgressIndicator()),
      );
    }

    final filteredSubscriptions =
        _subscriptions.where((sub) => sub.uID == id).toList();

    return Scaffold(
      appBar: AppBar(title: const Text("Subscription History")),
      body: _errorMessage != null
          ? Center(child: Text(_errorMessage!))
          : filteredSubscriptions.isEmpty
              ? const Center(child: Text("No subscriptions found for this ID."))
              : ListView.builder(
                  itemCount: filteredSubscriptions.length,
                  itemBuilder: (context, index) {
                    final sub = filteredSubscriptions[index];
                    return SubscriptionItem2(subscription: sub);
                  },
                ),
    );
  }
}
