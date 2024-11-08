import 'package:equatable/equatable.dart';

class CommentEvent extends Equatable {
  const CommentEvent();

  @override
  List<Object> get props => [];
}

class GetComments extends CommentEvent {
  final int id;

  const GetComments({this.id = 0});
  @override
  List<Object> get props => [id];
}

class CreateComment extends CommentEvent {
  final String comment;

  final int rntId;
  const CreateComment({this.comment = "",  this.rntId = 0});

  @override
  List<Object> get props => [comment,  rntId];
}

class UpdateComment extends CommentEvent {
  final String comment;
  final int cmtId;

  final int rntId;
  const UpdateComment( {this.comment = "",this.cmtId=0,  this.rntId = 0});

  @override
  List<Object> get props => [comment,  rntId,cmtId];
}

class DeleteComment extends CommentEvent {
  final int id;

  const DeleteComment({this.id=0});
  @override
  List<Object> get props => [id];
}
