import 'package:injectable/injectable.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/data/models/comment/comment_model.dart';
import 'package:pasgo_restaurants_ecommerce/domain/entities/comment/comment.dart';
import 'package:pasgo_restaurants_ecommerce/domain/repositories/comment.repository.dart';
import 'package:pasgo_restaurants_ecommerce/domain/use_cases/comment_case.dart';

@LazySingleton(as: CommentUseCase)
class CommentUseCaseImpl extends CommentUseCase {
  final CommentRepository _commentRepository;
  CommentUseCaseImpl() : _commentRepository = getIt<CommentRepository>();

  @override
  Future<List<Comment>> getAllComment(int id) async {
    List<CommentModel> comments = await _commentRepository.getAllComment(id);

    return comments
        .map((e) => Comment(
            id: e.id,
            name: e.name,
            status: e.status,
            // voteNumber: e.voteNumber ?? 0,
            phone: e.phone ?? "",
            content: e.content,
            createdAt: e.createdAt ?? "",
            updatedAt: e.updatedAt ?? "",
            user: e.user,
            restaurant: e.restaurant,
            email: e.email??""))
        .toList();
  }

  @override
  Future<void> createComment(String content, int rntId) async{
    return _commentRepository.createComment(content, rntId);
  }
  
  @override
  Future<void> updateComment(String content,  int rntId,int cmtId)async{
    return _commentRepository.updateComment(content, rntId,cmtId);
  }
  
  @override
  Future<void> deleteComment(int cmtId) async{
    return _commentRepository.deleteComment(cmtId);
  }
}
