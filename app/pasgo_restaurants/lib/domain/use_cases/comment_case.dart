import 'package:pasgo_restaurants_ecommerce/domain/entities/comment/comment.dart';

abstract class CommentUseCase {
  Future<List<Comment>> getAllComment(int id);
  Future<void> createComment(String content, int rntId);
  Future<void> updateComment(String content, int rntId,int cmtId);
  Future<void> deleteComment(int cmtId);
}
