import 'dart:async';

import 'package:bloc/bloc.dart';
import 'package:injectable/injectable.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/domain/entities/comment/comment.dart';
import 'package:pasgo_restaurants_ecommerce/domain/use_cases/comment_case.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/comment/comment_event.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/comment/comment_state.dart';

@lazySingleton
class CommentBloc extends Bloc<CommentEvent, CommentState> {
  final CommentUseCase _commentUseCase;

  CommentBloc()
      : _commentUseCase = getIt<CommentUseCase>(),
        super(CommentInitial()) {
    on<GetComments>(_getComments);
    on<CreateComment>(_createComment);
    on<UpdateComment>(_updateComment);
    on<DeleteComment>(_deleteComment);
  }

  Future<FutureOr<void>> _getComments(
      GetComments event, Emitter<CommentState> emit) async {
    try {
      emit(state.copyWith(commentStatus: CommentStatus.start));
      List<Comment> comments = await _commentUseCase.getAllComment(event.id);
      emit(state.copyWith(
          comments: comments, commentStatus: CommentStatus.success));
    } catch (e) {
      emit(state.copyWith(commentStatus: CommentStatus.fail));
    }
  }

  Future<FutureOr<void>> _createComment(
      CreateComment event, Emitter<CommentState> emit) async {
    try {
      emit(state.copyWith(createCommentStatus: CommentStatus.start));
      await _commentUseCase.createComment(event.comment,  event.rntId);
      emit(state.copyWith(createCommentStatus: CommentStatus.success));
    } catch (e) {
      emit(state.copyWith(createCommentStatus: CommentStatus.fail));
    }
  }
   Future<FutureOr<void>> _updateComment(
      UpdateComment event, Emitter<CommentState> emit) async {
    try {
      emit(state.copyWith(updateCommentStatus: CommentStatus.start));
      await _commentUseCase.updateComment(event.comment, event.rntId,event.cmtId);
      emit(state.copyWith(updateCommentStatus: CommentStatus.success));

    } catch (e) {
      emit(state.copyWith(updateCommentStatus: CommentStatus.fail));
    }
  }
    Future<FutureOr<void>> _deleteComment(
      DeleteComment event, Emitter<CommentState> emit) async {
    try {
      emit(state.copyWith(deleteCommentStatus: CommentStatus.start));
      await _commentUseCase.deleteComment(event.id);
      emit(state.copyWith(deleteCommentStatus: CommentStatus.success));

    } catch (e) {
      emit(state.copyWith(deleteCommentStatus: CommentStatus.fail));
    }
  }
}
