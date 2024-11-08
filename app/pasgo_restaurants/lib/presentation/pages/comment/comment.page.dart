import 'package:flutter/material.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/data/models/restaurant/uu_model.dart';
import 'package:pasgo_restaurants_ecommerce/domain/entities/comment/comment.dart';
import 'package:pasgo_restaurants_ecommerce/domain/entities/restaurant/restaurant.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/comment/comment_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/comment/comment_event.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/details/widgets/item_comment.dart';

class CommentPage extends StatefulWidget {
  const CommentPage({super.key});

  @override
  State<CommentPage> createState() => _CommentPageState();
}

class _CommentPageState extends State<CommentPage> {
  TextEditingController commentController = TextEditingController();
  int? userId;
  List<Comment> listComment = [];
  int? rntId;
  bool? noCmt;
  late FocusNode _focusNode;
  int? cmtId;
  bool isNotUpdate = false;

  @override
  void initState() {
    _focusNode = FocusNode();
    super.initState();
  }

  @override
  void dispose() {
    _focusNode.dispose();
    commentController.dispose();
    super.dispose();
  }

  void updateCommentCallback(int commentId, String newContent) {
    setState(() {
      for (int i = 0; i < listComment.length; i++) {
        if (listComment[i].id == commentId) {
          listComment[i].content = newContent;
          break;
        }
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    final arguments =
        ModalRoute.of(context)?.settings.arguments as Map<String, dynamic>;
    listComment = arguments['listComment'] as List<Comment>? ?? [];
    rntId = arguments['rntId'] as int?;
    noCmt = arguments['noCmt'] as bool?;
    userId = arguments['userId'] as int?;

    return PopScope(
      onPopInvoked: (e){
        getIt<CommentBloc>().add(GetComments(id: rntId!));
      },
      child: Scaffold(
        appBar: AppBar(
          title: const Text("Comment"),
        ),
        body: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            Expanded(
              child: Padding(
                padding:
                    const EdgeInsets.symmetric(horizontal: 8.0, vertical: 16),
                child: listComment.isEmpty
                    ? const Center(child: Text('No comments yet.'))
                    : ListView.separated(
                        physics: const BouncingScrollPhysics(),
                        itemBuilder: (context, index) {
                          return ItemComment(
                            callBack: (e) {
                              setState(() {
                                commentController.text = e;
                                isNotUpdate = true;
                                cmtId = listComment[index].id;
                                _focusNode.requestFocus();
                              });
                            },
                            onDelete: (id) {
                              setState(() {
                                listComment
                                    .removeWhere((comment) => comment.id == id);
                              });
                              getIt<CommentBloc>().add(DeleteComment(id: id));
                            },
                            updateCallback: updateCommentCallback,
                            cmt: listComment[index],
                            isCommentPage: true,
                          );
                        },
                        separatorBuilder: (context, index) =>
                            const SizedBox(height: 5),
                        itemCount: listComment.length,
                      ),
              ),
            ),
            Container(
              padding: const EdgeInsets.symmetric(horizontal: 16),
              child: Row(
                children: [
                  Expanded(
                    child: TextField(
                      focusNode: _focusNode,
                      controller: commentController,
                      decoration: const InputDecoration(
                        hintText: 'Enter your comment...',
                        border: OutlineInputBorder(),
                      ),
                      maxLines: null,
                      keyboardType: TextInputType.multiline,
                    ),
                  ),
                  const SizedBox(width: 10),
                  GestureDetector(
                    onTap: () {
                      if (commentController.text.isNotEmpty && !isNotUpdate) {
                        final newComment = Comment(
                          id: listComment.isNotEmpty
                              ? listComment.last.id + 1
                              : 1,
                          name: 'New User',
                          status: 'APPROVED',
                          email: 'new@example.com',
                          content: commentController.text,
                          user: u,
                          restaurant: r,
                          createdAt: DateTime.now().toString(),
                        );
      
                        setState(() {
                          listComment.add(newComment);
                        });
      
                        getIt<CommentBloc>().add(CreateComment(
                            comment: commentController.text, rntId: rntId!));
                      } else if (isNotUpdate) {
                        getIt<CommentBloc>().add(UpdateComment(
                            comment: commentController.text,
                            rntId: rntId!,
                            cmtId: cmtId!));
                        updateCommentCallback(cmtId!, commentController.text);
                      }
      
                      FocusScope.of(context).unfocus();
                      commentController.clear();
                      setState(() {
                        isNotUpdate = false;
                        cmtId = null;
                      });
                    },
                    child: const Icon(Icons.send),
                  ),
                ],
              ),
            ),
            const SizedBox(
              height: 16,
            ),
          ],
        ),
      ),
    );
  }

  final u =
      const UUserModel(id: 0, name: "You", email: "abba", username: "username");
  final r = const Restaurant(
      id: 0,
      name: "name",
      logo: "logo",
      status: "status",
      email: "email",
      // description: "description",
      address: "address",
      // content: "content",
      phone: "phone",
      type: "type",
      quantity: 1,
      price: "price",
      // createdAt: '2024-07-19T03:58:55.410+00:00',
      updatedAt: "2024-07-19T03:58:55.410+00:00", images: [], discount: 10);
}
