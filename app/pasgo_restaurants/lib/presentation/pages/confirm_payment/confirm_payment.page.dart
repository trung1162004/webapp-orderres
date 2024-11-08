import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_paypal_payment/flutter_paypal_payment.dart';
import 'package:pasgo_restaurants_ecommerce/color.style.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/route.configs.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/shareprefs.dart';
import 'package:pasgo_restaurants_ecommerce/data/models/subscriptions/subscription_model.dart';
import 'package:pasgo_restaurants_ecommerce/data/models/subscriptions/subscription_model2.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/editProfile/widgets/item_input.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/widgets/buttons/button.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/widgets/snackbar/snackbar.dart';
import 'package:url_launcher/url_launcher.dart';

class ConfirmPaymentPage extends StatefulWidget {
  const ConfirmPaymentPage({super.key});

  @override
  State<ConfirmPaymentPage> createState() => _ConfirmPaymentPageState();
}

class _ConfirmPaymentPageState extends State<ConfirmPaymentPage> {
  String? data;
  Map<String, dynamic>? userData;

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

  String getCurrentDateTime() {
    final now = DateTime.now();
    return now.toIso8601String();
  }

  String calculateEndDate(int months) {
    final now = DateTime.now();
    final endDate = DateTime(now.year, now.month + months, now.day);
    return endDate.toIso8601String();
  }

  @override
  Widget build(BuildContext context) {
    final SubscriptionModel s =
        ModalRoute.of(context)?.settings.arguments as SubscriptionModel;

    return Scaffold(
      appBar: AppBar(
        title: const Text("Subscription"),
      ),
      body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(
          children: [
            ItemInput(
              title: "Full Name",
              controller: TextEditingController(text: userData?["name"]),
              callBack: (e) {},
              isReadonly: true,
              errorText: null,
            ),
            ItemInput(
              title: "Email",
              controller: TextEditingController(text: userData?["email"]),
              callBack: (e) {},
              isReadonly: true,
              errorText: null,
            ),
            ItemInput(
              title: "Phone",
              controller: TextEditingController(text: userData?["phone"]),
              callBack: (e) {},
              isReadonly: true,
              errorText: null,
            ),
            Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                const Row(
                  children: [
                    Icon(Icons.room_service),
                    Text("Service Pack"),
                  ],
                ),
                TextField(
                  controller: TextEditingController(
                      text: "${s.price}\$ - ${s.month} months"),
                  style: const TextStyle(
                    color: Colors.black,
                    fontWeight: FontWeight.w400,
                    fontSize: 14,
                  ),
                  readOnly: true,
                  decoration: InputDecoration(
                    filled: true,
                    fillColor: const Color.fromARGB(197, 214, 214, 214),
                    hintStyle: const TextStyle(
                      color: Colors.black,
                      fontWeight: FontWeight.bold,
                    ),
                    border: OutlineInputBorder(
                      borderSide: BorderSide.none,
                      borderRadius: BorderRadius.circular(8),
                    ),
                  ),
                ),
                const SizedBox(
                  height: 20,
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.start,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: [
                    Checkbox(
                      value: true,
                      onChanged: (e) {},
                    ),
                    const Text(
                      "Paypal",
                      style: TextStyle(fontSize: 16),
                    )
                  ],
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.start,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: [
                    Checkbox(
                      value: true,
                      onChanged: (e) {},
                    ),
                    const Text(
                      "I agree to the ",
                      style: TextStyle(fontSize: 16),
                    ),
                    GestureDetector(
                      onTap: () async {
                        final Uri url = Uri.parse(
                            'https://www.freeprivacypolicy.com/live/baced082-d054-4675-a3a8-37b9c8de3e42');
                        if (!await launchUrl(url)) {
                          throw Exception('Could not launch $url');
                        }
                      },
                      child: const Text(
                        "terms and conditions",
                        style: TextStyle(
                            color: Colors.blue,
                            fontWeight: FontWeight.w500,
                            fontSize: 16),
                      ),
                    )
                  ],
                ),
                ButtonWidget(
                  buttonText: "Buy now",
                  onPressed: () {
                    Navigator.of(context).push(MaterialPageRoute(
                      builder: (BuildContext context) => PaypalCheckoutView(
                        sandboxMode: true,
                        clientId:
                            "AW8D9LxDyHCmn6xZiGtFkKvFmsEARGVOBfIze2DjiaX3eKFFBX7gjYHW61Ai92MF-9GCZPJvULSb9XpT",
                        secretKey:
                            "ELRVWYZmLmoog975X4F78s_Fn34K8DaZ7Fdo5Bzj2vsXVbgElPr7ljZ-VTv3ae3KGfPZ_BVziKthRot_",
                        transactions: [
                          {
                            "amount": {
                              "total": "${s.price}",
                              "currency": "USD",
                              "details": {
                                "subtotal": "${s.price}",
                                "shipping": '0',
                                "shipping_discount": 0
                              }
                            },
                            "description":
                                "The payment transaction description.",
                            "item_list": {
                              "items": [
                                {
                                  "name": "Subscriptions",
                                  "quantity": 1,
                                  "price": "${s.price}",
                                  "currency": "USD"
                                },
                              ],
                            }
                          }
                        ],
                        note: "Contact us for any questions on your order.",
                        onSuccess: (Map params) async {
                          print("========================${userData?['id']}");
                          List<SubscriptionMM2> newSubscriptions = [
                            SubscriptionMM2(
                              uID: userData!['id'],
                              name: userData!["name"],
                              phone: userData?["phone"]??"",
                              email: userData?["email"],
                              createdAt: getCurrentDateTime(),
                              endAt: calculateEndDate(s.month),
                              smd: [s],
                            ),
                          ];

                          await SharePrefs.addSubscriptions(newSubscriptions);
                          if (!context.mounted) return;
                          SnackbarUtils.showSuccessSnackBar(
                              context, "Register Subscription Successfully");

                          Navigator.popUntil(
                              context, ModalRoute.withName(AppRouters.home));
                        },
                        onError: (error) {
                          print("onError: $error");
                          Navigator.pop(context);
                        },
                        onCancel: () {
                          print('cancelled:');
                          Navigator.pop(context);
                        },
                      ),
                    ));
                  },
                )
              ],
            )
          ],
        ),
      ),
    );
  }
}
