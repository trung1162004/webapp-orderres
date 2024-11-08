import 'package:injectable/injectable.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/data/models/comment/comment_model.dart';
import 'package:pasgo_restaurants_ecommerce/domain/repositories/comment.repository.dart';

import 'package:pasgo_restaurants_ecommerce/domain/services/comment.service.dart';

@LazySingleton(as: CommentRepository)
class CommentRepositoryImpl extends CommentRepository {
  final CommentService _commentService;
  CommentRepositoryImpl() : _commentService = getIt<CommentService>();

  @override
  Future<List<CommentModel>> getAllComment(int id) async {
    List<CommentModel> comments = await _commentService.getAllComment(id);

    return comments;
  }

  @override
  Future<void> createComment(String content, int rntId) async {
    return await _commentService.createComment(content, rntId);
  }

  @override
  Future<void> updateComment(
      String content, int rntId, int cmtId) async {
    return await _commentService.updateComment(content,  rntId, cmtId);
  }

  @override
  Future<void> deleteComment(int cmtId) async {
    return await _commentService.deleteComment(cmtId);
  }
}
