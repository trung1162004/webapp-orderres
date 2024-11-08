import 'package:auto_size_text/auto_size_text.dart';
import 'package:flutter/material.dart';
import 'package:pasgo_restaurants_ecommerce/color.style.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/format_date.dart';
import 'package:pasgo_restaurants_ecommerce/domain/entities/order/order.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/history/widgets/detail_history.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/widgets/bottomsheets/content.dart';

class ItemHistory extends StatelessWidget {
  final OrderM order;
  const ItemHistory({super.key, required this.order});

  @override
  Widget build(BuildContext context) {

    return GestureDetector(
      onTap: () {
        showAppModalBottomSheet(
            context: context, child: DetailHistory(orderM: order));
      },
      child: Container(
        margin: const EdgeInsets.symmetric(horizontal: 12),
        padding: const EdgeInsets.all(8),
        width: double.infinity,
        decoration: BoxDecoration(
          color: Colors.grey[200],
          borderRadius: BorderRadius.circular(20),
          boxShadow: [
            BoxShadow(
              color: Colors.grey.withOpacity(0.5),
              spreadRadius: 2,
              blurRadius: 5,
              offset: const Offset(0, 3),
            ),
          ],
        ),
        child: Row(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            ClipRRect(
              borderRadius: BorderRadius.circular(10),
              child: SizedBox(
                width: 120,
                height: 140,
                child: Image.network(
                  (order.logo?.isEmpty ?? true)
                      ? "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5f/Red_X.svg/2048px-Red_X.svg.png"
                      : order.logo!,
                  fit: BoxFit.cover,
                ),
              ),
            ),
            const SizedBox(
              height: 5,
            ),
            Expanded(
              child: Padding(
                padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 10),
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  mainAxisSize: MainAxisSize.max,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    AutoSizeText(
                      order.restaurantName!,
                      style: const TextStyle(
                        fontWeight: FontWeight.w700,
                      ),
                      maxLines: 2,
                      overflow: TextOverflow.ellipsis,
                    ),
                    const SizedBox(
                      height: 5,
                    ),
                    // Row(
                    //   children: [
                    //     const Icon(Icons.where_to_vote),
                    //     const SizedBox(
                    //       width: 8,
                    //     ),
                    //     Text(
                    //       order.receiverAddress,
                    //       style: const TextStyle(
                    //           fontSize: 18, fontWeight: FontWeight.w700),
                    //     ),
                    //   ],
                    // ),
                    // const SizedBox(
                    //   height: 5,
                    // ),
                    Row(
                      children: [
                        const Icon(Icons.schedule),
                        const SizedBox(
                          width: 8,
                        ),
                        Text(
                          "${FormatDateApp.formatHour(order.timeBooking)} | ${FormatDateApp.formatDate(order.timeBooking)}",
                          style: const TextStyle(
                              fontSize: 18, fontWeight: FontWeight.w700),
                        ),
                      ],
                    ),
                    const SizedBox(
                      height: 10,
                    ),
                    Container(
                      padding: const EdgeInsets.all(5),
                      decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(8),
                           color: order.status == "PENDING"
                              ? Colors.orange.shade600
                              : order.status == "APPROVED"
                                  ? Colors.green
                                  : Colors.red),
                      child: Text(order.status,
                          style: const TextStyle(
                              color: MyAppColors.white,
                              fontWeight: FontWeight.bold)),
                    )
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
