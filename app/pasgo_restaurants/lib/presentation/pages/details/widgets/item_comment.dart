import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:pasgo_restaurants_ecommerce/color.style.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/route.configs.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/dimens.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/format_date.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/shareprefs.dart';
import 'package:pasgo_restaurants_ecommerce/domain/entities/comment/comment.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/details/widgets/item_no_comment.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/widgets/text/truncated_text.dart';

class ItemComment extends StatefulWidget {
  final List<Comment>? listComment;
  final bool isCommentPage;
  final int? rntId;
  final Comment? cmt;

  final Function(int)? onDelete;
  final Function(int, String)? updateCallback; 
  final Function(dynamic)? callBack;
  const ItemComment(
      {super.key,
      this.cmt,
      this.listComment,
      this.isCommentPage = false,
      this.rntId,
      this.callBack, this.updateCallback, this.onDelete});

  @override
  State<ItemComment> createState() => _ItemCommentState();
}

class _ItemCommentState extends State<ItemComment> {
  int? id;
  TextEditingController _controller = TextEditingController();

  Future<void> _getUserId() async {
    String? data = await SharePrefs.getUserData();
    Map<String, dynamic>? userData;
    if (data != null) {
      userData = jsonDecode(data);
    }
    setState(() {
      id = userData!['id'];
    });
  }

  @override
  void initState() {
    super.initState();
    _getUserId();
    _controller.text = widget.cmt?.content ?? '';
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

    void _deleteComment() {
    if (widget.onDelete != null && widget.cmt != null) {
      widget.onDelete!(widget.cmt!.id);
    }
  }
  void _showPopupMenu(BuildContext context, Offset position) {
    showMenu(
      context: context,
      position: RelativeRect.fromLTRB(position.dx, position.dy, 16, 16),
      items: [
        const PopupMenuItem(
          value: 'edit',
          child: ListTile(
            leading: Icon(Icons.edit),
            title: Text('Edit'),
          ),
        ),
        const PopupMenuItem(
          value: 'delete',
          child: ListTile(
            leading: Icon(Icons.delete, color: Colors.red),
            title: Text('Delete', style: TextStyle(color: Colors.red)),
          ),
        ),
      ],
    ).then((value) {
      if (value == "edit") {
        widget.callBack!(widget.cmt!.content);
      }
      else if(value=="delete"){
        _deleteComment();
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    final cmt = widget.cmt;
    if (cmt == null) {
      return  ItemNoComment(rntId: widget.rntId!);
    }else if(cmt.status=="status"){
      return SizedBox();
    }
    return GestureDetector(
      onLongPress: () {
        if (widget.isCommentPage && id == widget.cmt!.user.id) {
          RenderBox renderBox = context.findRenderObject() as RenderBox;
          Offset position = renderBox.localToGlobal(Offset.zero);
          double itemHeight = renderBox.size.height;
          _showPopupMenu(
              context,
              Offset(DimensionApp.screenWidth - 5,
                  (position.dy + itemHeight) - 20));
        }
      },
      onTap: () {
        if (!widget.isCommentPage) {
          Navigator.pushNamed(
            context,
            AppRouters.comments,
            arguments: {
              'listComment': widget.listComment,
              'rntId': widget.rntId,
              "userId": id,
              "noCmt":false
            },
          );
        }
      },
      child: Container(
          width: DimensionApp.screenWidth,
          padding: const EdgeInsets.all(10),
          decoration: BoxDecoration(
              color: Colors.grey.withOpacity(0.4),
              borderRadius: BorderRadius.circular(8)),
          child: Row(
            children: [
              Expanded(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.stretch,
                  children: [
                    Text(
                      id == widget.cmt!.user.id
                          ? "You "
                          : widget.cmt!.user.name,
                      style: const TextStyle(
                          fontSize: 17,
                          fontWeight: FontWeight.w800,
                          color: MyAppColors.backgroundColor),
                    ),
                    !widget.isCommentPage
                        ? TruncatedText(
                            text: widget.cmt?.content??"",
                            maxLength: 100,
                          )
                        :             Text(widget.cmt?.content ?? ''),

                    const SizedBox(
                      height: 5,
                    ),
                    Text(
                      widget.cmt?.createdAt?.isEmpty ?? true
                          ? ""
                          : "${FormatDateApp.formatHour(widget.cmt!.createdAt!)}, ${FormatDateApp.formatDate(widget.cmt!.createdAt!)}",
                      textAlign: TextAlign.end,
                      style: const TextStyle(fontSize: 14, color: Colors.grey),
                    )
                  ],
                ),
              ),
              if (!widget.isCommentPage) const Icon(Icons.chevron_right)
            ],
          )),
    );
  }
}
