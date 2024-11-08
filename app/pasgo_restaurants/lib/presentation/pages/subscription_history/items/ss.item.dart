// ignore_for_file: deprecated_member_use

import 'package:flutter/material.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/format_date.dart';
import 'package:pasgo_restaurants_ecommerce/data/models/subscriptions/subscription_model2.dart';

class SubscriptionItem2 extends StatelessWidget {
  final SubscriptionMM2 subscription;

  const SubscriptionItem2({super.key, required this.subscription});

  @override
  Widget build(BuildContext context) {
    return Card(
      elevation: 5,
      margin: const EdgeInsets.symmetric(vertical: 10, horizontal: 15),
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(12),
      ),
      child: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              subscription.name,
              style: Theme.of(context).textTheme.titleLarge?.copyWith(
                    fontWeight: FontWeight.bold,
                  ),
            ),
            const SizedBox(height: 8),
            if (subscription.phone.isNotEmpty)
              Text(
                "Phone: ${subscription.phone}",
                style: Theme.of(context).textTheme.bodyLarge,
              ),
            const SizedBox(height: 4),
            Text(
              "Email: ${subscription.email}",
              style: Theme.of(context).textTheme.bodyLarge,
            ),
            const SizedBox(height: 16),
            Text(
              "Created At: ${FormatDateApp.formatDate(subscription.createdAt)} - ${FormatDateApp.formatHour(subscription.createdAt)}",
              style: Theme.of(context).textTheme.bodyLarge?.copyWith(
                    color: Colors.grey[600],
                  ),
            ),
            const SizedBox(height: 4),
            Text(
              "End At: ${FormatDateApp.formatDate(subscription.endAt)} - ${FormatDateApp.formatHour(subscription.endAt)}",
              style: Theme.of(context).textTheme.bodyLarge?.copyWith(
                    color: Colors.grey[600],
                  ),
            ),
            const SizedBox(height: 16),
            const Divider(thickness: 1, color: Colors.grey),
            const SizedBox(height: 16),
            Text(
              "Subscription Details",
              style: Theme.of(context).textTheme.titleMedium?.copyWith(
                    fontWeight: FontWeight.bold,
                  ),
            ),
            const SizedBox(height: 8),
            ...subscription.smd?.map((model) => Padding(
                      padding: const EdgeInsets.only(bottom: 8),
                      child: Text(
                        "${model.price}\$ - ${model.month} months\nBenefits: ${model.benefits.join(', ')}",
                        style: Theme.of(context).textTheme.bodyMedium,
                      ),
                    )) ??
                [],
          ],
        ),
      ),
    );
  }
}
