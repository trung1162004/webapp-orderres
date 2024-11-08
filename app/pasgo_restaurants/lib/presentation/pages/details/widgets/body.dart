import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_widget_from_html/flutter_widget_from_html.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/route.configs.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/validation.dart';
import 'package:pasgo_restaurants_ecommerce/domain/entities/restaurant/restaurant.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/comment/comment_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/comment/comment_event.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/comment/comment_state.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/details/widgets/item_comment.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/details/widgets/item_no_comment.dart';

class BodyDetail extends StatefulWidget {
  final Restaurant rnt;
  const BodyDetail({super.key, required this.rnt});

  @override
  State<BodyDetail> createState() => _BodyDetailState();
}

class _BodyDetailState extends State<BodyDetail> {
  @override
  void initState() {
    getIt<CommentBloc>().add(GetComments(id: widget.rnt.id));
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(top: 16, left: 8, right: 8, bottom: 120),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(
            widget.rnt.name,
            style: const TextStyle(fontSize: 22, fontWeight: FontWeight.w700),
          ),
          const SizedBox(
            height: 6,
          ),
          Row(
            children: [
              const Icon(
                Icons.where_to_vote,
                size: 28,
              ),
              const SizedBox(width: 8),
              Expanded(
                child: Text(
                  widget.rnt.address ?? "",
                  style: const TextStyle(
                      fontSize: 17, fontWeight: FontWeight.w700),
                  overflow: TextOverflow.visible,
                ),
              ),
            ],
          ),
          const SizedBox(
            height: 6,
          ),
          Row(
            children: [
              Text(
                "Price approx:",
                style: TextStyle(fontWeight: FontWeight.w800, fontSize: 17),
              ),
              const SizedBox(
                width: 8,
              ),
              Text(
                "${widget.rnt.price} USD/People",
                style:
                    const TextStyle(fontSize: 18, fontWeight: FontWeight.w700),
              ),
              Text(
                " ${widget.rnt.discount == null ? "" : "(-${widget.rnt.discount}%)"}",
                style: const TextStyle(
                    fontSize: 18,
                    fontWeight: FontWeight.w700,
                    color: Colors.red),
              ),
            ],
          ),
          const SizedBox(
            height: 6,
          ),
          Row(
            children: [
              const Icon(
                Icons.phone,
                size: 28,
              ),
              const SizedBox(
                width: 8,
              ),
              Text(
                widget.rnt.phone,
                style:
                    const TextStyle(fontSize: 18, fontWeight: FontWeight.w700),
              ),
            ],
          ),
          const SizedBox(
            height: 6,
          ),
          Row(
            children: [
              const Icon(
                Icons.schedule,
                size: 28,
              ),
              const SizedBox(
                width: 8,
              ),
              Text(
                widget.rnt.work_time_open != null &&
                        widget.rnt.work_time_close != null
                    ? "Work Time: ${widget.rnt.work_time_open} - ${widget.rnt.work_time_close}"
                    : "Work Time: Not available",
                style: const TextStyle(
                    fontSize: 18,
                    color: Colors.green,
                    fontWeight: FontWeight.w700),
              ),
            ],
          ),
          const SizedBox(
            height: 7,
          ),

          const Divider(
            color: Colors.grey,
          ),
          // HtmlWidget( widget.rnt.content.replaceAll('\\r\\n', ' ')),

          Text(
                "Summary",
                style: TextStyle(fontWeight: FontWeight.w800, fontSize: 25),
              ),
          Text(
                "Fit:",
                style: TextStyle(fontWeight: FontWeight.w800, fontSize: 17),
              ),
          HtmlWidget(
              ValidationUtils.cleanedHtmlText(widget.rnt.fit ?? "<h2>No</h2>")),
          const SizedBox(
            height: 20,
          ),
          Text(
                "Special dishes:",
                style: TextStyle(fontWeight: FontWeight.w800, fontSize: 17),
              ),
          HtmlWidget(ValidationUtils.cleanedHtmlText(
              widget.rnt.specialDish ?? "<h2>No</h2>")),
          const SizedBox(
            height: 20,
          ),
          Text(
                "Space:",
                style: TextStyle(fontWeight: FontWeight.w800, fontSize: 17),
              ),
          HtmlWidget(ValidationUtils.cleanedHtmlText(
              widget.rnt.space ?? "<h2>No</h2>")),
          const SizedBox(
            height: 20,
          ),
          Text(
                "Parking:",
                style: TextStyle(fontWeight: FontWeight.w800, fontSize: 17),
              ),
          HtmlWidget(ValidationUtils.cleanedHtmlText(
              widget.rnt.parking ?? "<h2>No</h2>")),
          const SizedBox(
            height: 20,
          ),
          Text(
                "Feature:",
                style: TextStyle(fontWeight: FontWeight.w800, fontSize: 17),
              ),
          HtmlWidget(ValidationUtils.cleanedHtmlText(
              widget.rnt.feature ?? "<h2>No</h2>")),
          const SizedBox(
            height: 20,
          ),
          
          Text(
                "Regulations",
                style: TextStyle(fontWeight: FontWeight.w800, fontSize: 25),
              ),
          Text(
                "1. Deposit regulations:",
                style: TextStyle(fontWeight: FontWeight.w800, fontSize: 17),
              ),
          HtmlWidget(
              ValidationUtils.cleanedHtmlText(widget.rnt.depositRegulation ?? "<h2>No</h2>")),
          const SizedBox(
            height: 20,
          ),
          Text(
                "2. Regulations on incentives:",
                style: TextStyle(fontWeight: FontWeight.w800, fontSize: 17),
              ),
          HtmlWidget(ValidationUtils.cleanedHtmlText(
              widget.rnt.discountRegulation ?? "<h2>No</h2>")),
          const SizedBox(
            height: 20,
          ),
          Text(
                "3. Regulations on Eat&Eat guest reception time:",
                style: TextStyle(fontWeight: FontWeight.w800, fontSize: 17),
              ),
          HtmlWidget(ValidationUtils.cleanedHtmlText(
              widget.rnt.pasgoTimeRegulation ?? "<h2>No</h2>")),
          const SizedBox(
            height: 20,
          ),
          Text(
                "4. Regulations on Reservation Time:",
                style: TextStyle(fontWeight: FontWeight.w800, fontSize: 17),
              ),
          HtmlWidget(ValidationUtils.cleanedHtmlText(
              widget.rnt.reservationTimeRegulation ?? "<h2>No</h2>")),
          const SizedBox(
            height: 20,
          ),
          Text(
                "5. Regulations on Maximum Reservation Time:",
                style: TextStyle(fontWeight: FontWeight.w800, fontSize: 17),
              ),
          HtmlWidget(ValidationUtils.cleanedHtmlText(
              widget.rnt.holdTimeRegulation ?? "<h2>No</h2>")),
          const SizedBox(
            height: 20,
          ),
          Text(
                "6. Regulations on minimum number of guests per table booking:",
                style: TextStyle(fontWeight: FontWeight.w800, fontSize: 17),
              ),
          HtmlWidget(ValidationUtils.cleanedHtmlText(widget.rnt.mininumGuestRegulation ?? "<h2>No</h2>")),
          const SizedBox(
            height: 20,
          ),
          Text(
                "7. Regulations on Invoice:",
                style: TextStyle(fontWeight: FontWeight.w800, fontSize: 17),
              ),
          HtmlWidget(ValidationUtils.cleanedHtmlText(
              widget.rnt.invoiceRegulation ?? "<h2>No</h2>")),
          const SizedBox(
            height: 20,
          ),
          Text(
                "8. Regulations on Service Fees:",
                style: TextStyle(fontWeight: FontWeight.w800, fontSize: 17),
              ),
          HtmlWidget(ValidationUtils.cleanedHtmlText(
              widget.rnt.serviceFeeRegulation ?? "<h2>No</h2>")),
          const SizedBox(
            height: 20,
          ),
          Text(
                "9. Private Room Fee Regulations:",
                style: TextStyle(fontWeight: FontWeight.w800, fontSize: 17),
              ),
          HtmlWidget(ValidationUtils.cleanedHtmlText(
              widget.rnt.bringInFeeRegulation ?? "<h2>No</h2>")),
          const SizedBox(
            height: 20,
          ),
          

          const SizedBox(
            height: 20,
          ),
          const Text(
            "Comments: ",
            style: TextStyle(fontSize: 18, fontWeight: FontWeight.w700),
          ),
          BlocBuilder<CommentBloc, CommentState>(
            builder: (context, state) {
              if (state.commentStatus == CommentStatus.success) {
                if (state.comments.isEmpty) {
                  return ItemNoComment(
                    rntId: widget.rnt.id,
                  );
                } else {
                  return ItemComment(
                    rntId: widget.rnt.id,
                    cmt: state.comments[0],
                    listComment: state.comments,
                  );
                }
              } else {
                return const CircularProgressIndicator();
              }
            },
          ),
        ],
      ),
    );
  }
}
