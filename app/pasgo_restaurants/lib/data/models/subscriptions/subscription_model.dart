class SubscriptionModel {
  final int price;
  final int month;
  final List<String> benefits;

  const SubscriptionModel({
    required this.price,
    required this.month,
    required this.benefits,
  });

  factory SubscriptionModel.fromJson(Map<String, dynamic> json) {
    return SubscriptionModel(
      price: json['price'] as int,
      month: json['month'] as int,
      benefits: List<String>.from(json['benefits'] as List<dynamic>),
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'price': price,
      'month': month,
      'benefits': benefits,
    };
  }
}

List<SubscriptionModel> listSubscriptions = [
  const SubscriptionModel(price: 500, month: 3, benefits: [
    "Basic support",
    "Access to standard features",
    "Email support"
  ]),
  const SubscriptionModel(price: 1000, month: 8, benefits: [
    "Priority support",
    "Access to all features",
    "Phone & Email support"
  ]),
  const SubscriptionModel(price: 1200, month: 12, benefits: [
    "Delicated support",
    "All premium features",
    "24/7 support"
  ]),
];
