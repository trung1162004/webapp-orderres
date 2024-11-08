

import 'package:copy_with_extension/copy_with_extension.dart';
import 'package:equatable/equatable.dart';
import 'package:json_annotation/json_annotation.dart';
import 'package:pasgo_restaurants_ecommerce/domain/entities/comment/comment.dart';
part 'comment_state.g.dart';

enum CommentStatus { init, start, success, fail }

@JsonSerializable()
@CopyWith()
class CommentState extends Equatable {
  final List<Comment> comments;
  final CommentStatus commentStatus;
  final CommentStatus createCommentStatus;
  final CommentStatus updateCommentStatus;
  final CommentStatus deleteCommentStatus;
  const CommentState({
    this.comments = const [],
    this.commentStatus = CommentStatus.init,
    this.createCommentStatus = CommentStatus.init,
    this.updateCommentStatus = CommentStatus.init,
    this.deleteCommentStatus = CommentStatus.init,
  });

  @override
  List<Object> get props => [
        comments,
        commentStatus,
        createCommentStatus,
        updateCommentStatus,
        deleteCommentStatus
      ];
}

final class CommentInitial extends CommentState {}
