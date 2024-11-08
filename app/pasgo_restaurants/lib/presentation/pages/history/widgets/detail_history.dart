import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pasgo_restaurants_ecommerce/color.style.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/dimens.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/format_date.dart';
import 'package:pasgo_restaurants_ecommerce/domain/entities/order/order.dart';

class DetailHistory extends StatelessWidget {
  final OrderM orderM;
  const DetailHistory({super.key, required this.orderM});
/*
    object
    

 */
  @override
  Widget build(BuildContext context) {
    final padding = MediaQuery.of(context).padding;

    return Container(
      width: DimensionApp.screenWidth,
      height: DimensionApp.screenHeight,
      padding: EdgeInsets.fromLTRB(16, padding.top + 40, 16, 16),
      decoration: const BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.vertical(
          top: Radius.circular(20),
        ),
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              const Text(
                "Order Details",
                style: TextStyle(
                  fontSize: 24,
                  fontWeight: FontWeight.bold,
                ),
              ),
              GestureDetector(
                onTap: () => Navigator.pop(context),
                child: const Icon(
                  color: MyAppColors.backgroundColor,
                  Icons.close,
                  size: 32,
                ),
              ),
            ],
          ),
          Padding(
            padding: EdgeInsets.fromLTRB(16, padding.top + 16, 16, 16),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: [
                ClipRRect(
                  borderRadius: BorderRadius.circular(15),
                  child: Image.network(
                    orderM.logo??"https://upload.wikimedia.org/wikipedia/commons/thumb/5/5f/Red_X.svg/2048px-Red_X.svg.png",
                    width: double.infinity,
                    height: 200,
                    fit: BoxFit.cover,
                    errorBuilder: (context, error, stackTrace) {
                      return const Icon(Icons.error, size: 100);
                    },
                  ),
                ),
                const SizedBox(height: 16),
                Text(
                  orderM.restaurantName ?? 'Unknown Restaurant',
                  style: const TextStyle(
                    fontSize: 24,
                    fontWeight: FontWeight.bold,
                    color: MyAppColors.backgroundColor,
                  ),
                  maxLines: 2,
                  overflow: TextOverflow.ellipsis,
                ),
                const SizedBox(height: 8),
                Row(
                  children: [
                    const Icon(Icons.schedule,
                        color: MyAppColors.backgroundColor),
                    const SizedBox(width: 8),
                    Text(
                      '${FormatDateApp.formatHour(orderM.timeBooking)} | ${FormatDateApp.formatDate(orderM.timeBooking)}',
                      style: TextStyle(
                        fontSize: 16,
                        color: Colors.grey[600],
                      ),
                    ),
                  ],
                ),
                const SizedBox(height: 16),
                const Text(
                  'Code:',
                  style: TextStyle(
                    fontSize: 18,
                    fontWeight: FontWeight.bold,
                    color: MyAppColors.backgroundColor,
                  ),
                ),
                Text(
                  orderM.code ?? '',
                  style: const TextStyle(fontSize: 16),
                ),
                // const SizedBox(height: 16),
                // const Text(
                //   'Address:',
                //   style: TextStyle(
                //     fontSize: 18,
                //     fontWeight: FontWeight.bold,
                //     color: MyAppColors.backgroundColor,
                //   ),
                // ),
                // Text(
                //   orderM.receiverAddress ?? 'No address provided',
                //   style: const TextStyle(fontSize: 16),
                // ),
                // const SizedBox(height: 16),
                // const Text(
                //   'Total Price:',
                //   style: TextStyle(
                //     fontSize: 18,
                //     fontWeight: FontWeight.bold,
                //     color: MyAppColors.backgroundColor,
                //   ),
                // ),
                // Text(
                //   "\$${orderM.price}",
                //   style: const TextStyle(fontSize: 16, color: Colors.green),
                // ),
                const SizedBox(height: 16),
                const Text(
                  'Quantity:',
                  style: TextStyle(
                    fontSize: 18,
                    fontWeight: FontWeight.bold,
                    color: MyAppColors.backgroundColor,
                  ),
                ),
                Text(
                  '${orderM.quantity.toStringAsFixed(0) ?? 0}',
                  style: const TextStyle(fontSize: 16),
                ),

                 const SizedBox(height: 16),
                const Text(
                  'Discount:',
                  style: TextStyle(
                    fontSize: 18,
                    fontWeight: FontWeight.bold,
                    color: MyAppColors.backgroundColor,
                  ),
                ),
                Text(
                  '${orderM.discount ?? 0}',
                  style: const TextStyle(fontSize: 16),
                ),

                const SizedBox(height: 16),
                const Text(
                  'Time:',
                  style: TextStyle(
                    fontSize: 18,
                    fontWeight: FontWeight.bold,
                    color: MyAppColors.backgroundColor,
                  ),
                ),
                Text(
                  orderM.timeBooking ?? 'No notes provided',
                  style: const TextStyle(fontSize: 16),
                ),
                const SizedBox(height: 20),
                Container(
                  padding: const EdgeInsets.symmetric(vertical: 15),
                  width: 150,
                  height: 60,
                  decoration: BoxDecoration(
                      color: orderM.status == "PENDING"
                            ? Colors.orange.shade600
                            : orderM.status == "APPROVED"
                                ? Colors.green
                                : Colors.red,
                      borderRadius: BorderRadius.circular(20)),
                  child:  Text(
                    orderM.status,
                    textAlign: TextAlign.center,
                    style: const TextStyle(
                        fontSize: 19,
                        fontWeight: FontWeight.bold,
                        color: MyAppColors.white),
                  ),
                ),
              ],
            ),
          )
        ],
      ),
    );
  }
}
