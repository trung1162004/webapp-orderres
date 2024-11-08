
import 'package:pasgo_restaurants_ecommerce/data/models/comment/comment_model.dart';

abstract class CommentService {
  Future<List<CommentModel>> getAllComment(int id);
  Future<void> createComment(String content, int rntId);
  Future<void> updateComment(String content, int rntId,int cmtId);
  Future<void> deleteComment(int cmtId);

}
