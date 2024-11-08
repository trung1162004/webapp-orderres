import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_widget_from_html/flutter_widget_from_html.dart';
import 'package:pasgo_restaurants_ecommerce/color.style.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/path.configs.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/dimens.dart';
import 'package:intl/intl.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/shareprefs.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/validation.dart';

import 'package:pasgo_restaurants_ecommerce/data/models/order/order_model.dart';
import 'package:pasgo_restaurants_ecommerce/data/models/restaurant/uu_model.dart';
import 'package:pasgo_restaurants_ecommerce/domain/entities/restaurant/restaurant.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/order/order_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/order/order_event.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/widgets/buttons/button.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/widgets/snackbar/snackbar.dart';

class OrderPage extends StatefulWidget {
  final Restaurant orderData;

  const OrderPage({
    super.key,
    required this.orderData,
  });

  @override
  State<OrderPage> createState() => _OrderPageState();
}

class _OrderPageState extends State<OrderPage> {
  final _fullNameController = TextEditingController();
  final _noteController = TextEditingController();
  final _emailController = TextEditingController();
  final _phoneController = TextEditingController();

  bool errorEmail = false;
  bool errorPhone = false;
  bool errorDate = false;
  bool errorTime = false;

  Map<String, dynamic>? uData;
  String? data;
  final styleError =
      const TextStyle(color: Colors.red, fontWeight: FontWeight.bold);
  String? fName;
  int count = 0;
  DateTime? _selectedDate;
  TimeOfDay? _selectedTime;

  @override
  void initState() {
    super.initState();
    _getUserData();
  }

  Future<void> _getUserData() async {
    data = await SharePrefs.getUserData();
    fName = await SharePrefs.getUserFullName();
    if (data != null) {
      setState(() {
        uData = jsonDecode(data!);
        _fullNameController.text = fName ?? '';
        _emailController.text = uData?["email"] ?? '';
      });
    }
  }

  Future<void> _selectDate(BuildContext context) async {
    final DateTime now = DateTime.now();
    final DateTime oneYearFromNow = now.add(Duration(days: 365));

    final DateTime? picked = await showDatePicker(
      context: context,
      initialDate: _selectedDate ?? now,
      firstDate: now,
      lastDate: oneYearFromNow,
    );

    if (picked != null && picked != _selectedDate) {
      setState(() {
        _selectedDate = picked;
        errorDate = false;
      });
    }
  }

  Future<void> _selectTime(BuildContext context) async {
    final TimeOfDay? picked = await showTimePicker(
      context: context,
      initialTime: _selectedTime ?? TimeOfDay.now(),
    );

    if (picked != null && picked != _selectedTime) {
      setState(() {
        _selectedTime = picked;
        errorTime = false;
      });
    }
  }

  String _formatDateTime() {
    if (_selectedDate == null || _selectedTime == null) {
      return '';
    }
    final DateTime dateTime = DateTime(
      _selectedDate!.year,
      _selectedDate!.month,
      _selectedDate!.day,
      _selectedTime!.hour,
      _selectedTime!.minute,
    );
    return DateFormat('yyyy-MM-ddTHH:mm:ss.SSS').format(dateTime);
  }

  bool _validateInputs() {
    setState(() {
      errorEmail = !ValidationUtils.isValidEmail(_emailController.text);
      errorPhone = !ValidationUtils.isValidPhoneNumber(_phoneController.text);
      errorDate = _selectedDate == null;
      errorTime = _selectedTime == null;
    });
    return 
        !errorEmail &&
        !errorPhone &&
        !errorDate &&
        !errorTime &&
        count > 0;
  }

  @override
  Widget build(BuildContext context) {
    final padding = MediaQuery.of(context).padding;

    return Scaffold(
      appBar: AppBar(
        title: Text("Order"),
      ),
      body: SingleChildScrollView(
        child: Container(
          width: DimensionApp.screenWidth,
          padding: EdgeInsets.symmetric(horizontal: 16, vertical: padding.top),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              ClipRRect(
                borderRadius: BorderRadius.circular(10),
                child: widget.orderData != null
                    ? Image.network(
                        widget.orderData.logo,
                        width: double.infinity,
                        height: 200,
                        fit: BoxFit.cover,
                      )
                    : Image.network(
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5f/Red_X.svg/2048px-Red_X.svg.png",
                        width: double.infinity,
                        height: 200,
                        fit: BoxFit.cover,
                      ),
              ),
              const SizedBox(height: 16),
              Text(
                widget.orderData.name,
                style: const TextStyle(
                  fontSize: 24,
                  fontWeight: FontWeight.bold,
                  color: MyAppColors.backgroundColor,
                ),
                maxLines: 2,
                overflow: TextOverflow.ellipsis,
              ),
              // HtmlWidget(ValidationUtils.cleanedHtmlText(
              //     widget.orderData.fit ?? "<h1>No data</h1>"
              //     )),
              const SizedBox(height: 16),
              const Text("Full Name: "),
              TextField(
                controller: _fullNameController,
                decoration: const InputDecoration(
                  hintText: "We'll use your name",
                  border: OutlineInputBorder(),
                ),
              ),
              const SizedBox(height: 16),
              const Text("Email: "),
              TextField(
                controller: _emailController,
                keyboardType: TextInputType.emailAddress,
                decoration: InputDecoration(
                    border: const OutlineInputBorder(),
                    errorText: errorEmail ? "Email is invalid" : null),
                onChanged: (value) {
                  setState(() {
                    errorEmail = !ValidationUtils.isValidEmail(value);
                  });
                },
              ),
              const SizedBox(height: 16),
              const Text("Phone: "),
              TextField(
                controller: _phoneController,
                keyboardType: TextInputType.phone,
                decoration: InputDecoration(
                    border: const OutlineInputBorder(),
                    errorText: errorPhone ? "Phone number is invalid" : null),
                onChanged: (value) {
                  setState(() {
                    errorPhone = !ValidationUtils.isValidPhoneNumber(value);
                  });
                },
              ),
              const SizedBox(height: 16),
              // const Text("Address: "),
              // TextField(
              //   controller: _addressController,
              //   decoration: InputDecoration(
              //     border: const OutlineInputBorder(),
              //     errorText: errorAddress ? "Address is required" : null,
              //   ),
              //   onChanged: (value) {
              //     setState(() {
              //       errorAddress = _addressController.text.isEmpty;
              //     });
              //   },
              // ),
              const SizedBox(height: 16),
              Row(
                children: [
                  const Text("Number of People: "),
                  const SizedBox(width: 20),
                  Row(
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      IconButton(
                        onPressed: () {
                          setState(() {
                            if (count > 0) count--;
                          });
                        },
                        icon: const Icon(Icons.remove),
                      ),
                      const SizedBox(width: 10),
                      Text("$count"),
                      const SizedBox(width: 10),
                      IconButton(
                        onPressed: () {
                          setState(() {
                            count++;
                          });
                        },
                        icon: const Icon(Icons.add),
                      ),
                    ],
                  ),
                ],
              ),
              const SizedBox(height: 16),
              Row(
                children: [
                  Expanded(
                    child: GestureDetector(
                      onTap: () => _selectDate(context),
                      child: InputDecorator(
                        decoration: InputDecoration(
                          border: const OutlineInputBorder(),
                          labelText: 'Select Date',
                          errorText: errorDate ? 'Date is required' : null,
                        ),
                        child: Text(
                          _selectedDate == null
                              ? 'Choose a date'
                              : DateFormat('yyyy-MM-dd').format(_selectedDate!),
                        ),
                      ),
                    ),
                  ),
                  const SizedBox(width: 16),
                  Expanded(
                    child: GestureDetector(
                      onTap: () => _selectTime(context),
                      child: InputDecorator(
                        decoration: InputDecoration(
                          border: const OutlineInputBorder(),
                          labelText: 'Select Time',
                          errorText: errorTime ? 'Time is required' : null,
                        ),
                        child: Text(
                          _selectedTime == null
                              ? 'Choose a time'
                              : _selectedTime!.format(context),
                        ),
                      ),
                    ),
                  ),
                ],
              ),
              const SizedBox(height: 16),
              ButtonWidget(
                buttonText: "Order",
                onPressed: () {
                  if (_validateInputs()) {
                    final formattedDateTime = _formatDateTime();
                    getIt<OrderBloc>().add(CreateOrder(
                      o: OrderModel(
                        id: 0,
                        code: "code",
                        status: "status",
                        type: "type",
                        note: _noteController.text,
                        // receiverAddress: widget.orderData.address??"",
                        receiverEmail: _emailController.text,
                        receiverName: fName,
                        receiverPhone: _phoneController.text,
                        // totalPrice: widget.orderData.price * count,
                        restaurantName: widget.orderData.name,
                        logo: widget.orderData.logo,
                        // price: widget.orderData.price??"",
                        timeBooking: formattedDateTime,
                        quantity: count.toDouble(),
                        createdAt: formattedDateTime,
                        updatedAt: formattedDateTime,
                        user: u,
                      ),
                      rntId: widget.orderData.id,
                    ));
                    SnackbarUtils.showSuccessSnackBar(
                        context, "Order Successfully");
                    Navigator.pop(context);
                  } else {
                    setState(() {});
                  }
                },
              ),
            ],
          ),
        ),
      ),
    );
  }

  final u =
      const UUserModel(id: 0, name: "You", email: "abba", username: "username");
}
