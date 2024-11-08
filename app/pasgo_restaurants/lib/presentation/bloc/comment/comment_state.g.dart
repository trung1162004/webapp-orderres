// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'comment_state.dart';

// **************************************************************************
// CopyWithGenerator
// **************************************************************************

abstract class _$CommentStateCWProxy {
  CommentState commentStatus(CommentStatus commentStatus);

  CommentState comments(List<Comment> comments);

  CommentState createCommentStatus(CommentStatus createCommentStatus);

  CommentState deleteCommentStatus(CommentStatus deleteCommentStatus);

  CommentState updateCommentStatus(CommentStatus updateCommentStatus);

  /// This function **does support** nullification of nullable fields. All `null` values passed to `non-nullable` fields will be ignored. You can also use `CommentState(...).copyWith.fieldName(...)` to override fields one at a time with nullification support.
  ///
  /// Usage
  /// ```dart
  /// CommentState(...).copyWith(id: 12, name: "My name")
  /// ````
  CommentState call({
    CommentStatus? commentStatus,
    List<Comment>? comments,
    CommentStatus? createCommentStatus,
    CommentStatus? deleteCommentStatus,
    CommentStatus? updateCommentStatus,
  });
}

/// Proxy class for `copyWith` functionality. This is a callable class and can be used as follows: `instanceOfCommentState.copyWith(...)`. Additionally contains functions for specific fields e.g. `instanceOfCommentState.copyWith.fieldName(...)`
class _$CommentStateCWProxyImpl implements _$CommentStateCWProxy {
  final CommentState _value;

  const _$CommentStateCWProxyImpl(this._value);

  @override
  CommentState commentStatus(CommentStatus commentStatus) =>
      this(commentStatus: commentStatus);

  @override
  CommentState comments(List<Comment> comments) => this(comments: comments);

  @override
  CommentState createCommentStatus(CommentStatus createCommentStatus) =>
      this(createCommentStatus: createCommentStatus);

  @override
  CommentState deleteCommentStatus(CommentStatus deleteCommentStatus) =>
      this(deleteCommentStatus: deleteCommentStatus);

  @override
  CommentState updateCommentStatus(CommentStatus updateCommentStatus) =>
      this(updateCommentStatus: updateCommentStatus);

  @override

  /// This function **does support** nullification of nullable fields. All `null` values passed to `non-nullable` fields will be ignored. You can also use `CommentState(...).copyWith.fieldName(...)` to override fields one at a time with nullification support.
  ///
  /// Usage
  /// ```dart
  /// CommentState(...).copyWith(id: 12, name: "My name")
  /// ````
  CommentState call({
    Object? commentStatus = const $CopyWithPlaceholder(),
    Object? comments = const $CopyWithPlaceholder(),
    Object? createCommentStatus = const $CopyWithPlaceholder(),
    Object? deleteCommentStatus = const $CopyWithPlaceholder(),
    Object? updateCommentStatus = const $CopyWithPlaceholder(),
  }) {
    return CommentState(
      commentStatus:
          commentStatus == const $CopyWithPlaceholder() || commentStatus == null
              ? _value.commentStatus
              // ignore: cast_nullable_to_non_nullable
              : commentStatus as CommentStatus,
      comments: comments == const $CopyWithPlaceholder() || comments == null
          ? _value.comments
          // ignore: cast_nullable_to_non_nullable
          : comments as List<Comment>,
      createCommentStatus:
          createCommentStatus == const $CopyWithPlaceholder() ||
                  createCommentStatus == null
              ? _value.createCommentStatus
              // ignore: cast_nullable_to_non_nullable
              : createCommentStatus as CommentStatus,
      deleteCommentStatus:
          deleteCommentStatus == const $CopyWithPlaceholder() ||
                  deleteCommentStatus == null
              ? _value.deleteCommentStatus
              // ignore: cast_nullable_to_non_nullable
              : deleteCommentStatus as CommentStatus,
      updateCommentStatus:
          updateCommentStatus == const $CopyWithPlaceholder() ||
                  updateCommentStatus == null
              ? _value.updateCommentStatus
              // ignore: cast_nullable_to_non_nullable
              : updateCommentStatus as CommentStatus,
    );
  }
}

extension $CommentStateCopyWith on CommentState {
  /// Returns a callable class that can be used as follows: `instanceOfCommentState.copyWith(...)` or like so:`instanceOfCommentState.copyWith.fieldName(...)`.
  // ignore: library_private_types_in_public_api
  _$CommentStateCWProxy get copyWith => _$CommentStateCWProxyImpl(this);
}

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

CommentState _$CommentStateFromJson(Map<String, dynamic> json) => CommentState(
      comments: (json['comments'] as List<dynamic>?)
              ?.map((e) => Comment.fromJson(e as Map<String, dynamic>))
              .toList() ??
          const [],
      commentStatus:
          $enumDecodeNullable(_$CommentStatusEnumMap, json['commentStatus']) ??
              CommentStatus.init,
      createCommentStatus: $enumDecodeNullable(
              _$CommentStatusEnumMap, json['createCommentStatus']) ??
          CommentStatus.init,
      updateCommentStatus: $enumDecodeNullable(
              _$CommentStatusEnumMap, json['updateCommentStatus']) ??
          CommentStatus.init,
      deleteCommentStatus: $enumDecodeNullable(
              _$CommentStatusEnumMap, json['deleteCommentStatus']) ??
          CommentStatus.init,
    );

Map<String, dynamic> _$CommentStateToJson(CommentState instance) =>
    <String, dynamic>{
      'comments': instance.comments,
      'commentStatus': _$CommentStatusEnumMap[instance.commentStatus]!,
      'createCommentStatus':
          _$CommentStatusEnumMap[instance.createCommentStatus]!,
      'updateCommentStatus':
          _$CommentStatusEnumMap[instance.updateCommentStatus]!,
      'deleteCommentStatus':
          _$CommentStatusEnumMap[instance.deleteCommentStatus]!,
    };

const _$CommentStatusEnumMap = {
  CommentStatus.init: 'init',
  CommentStatus.start: 'start',
  CommentStatus.success: 'success',
  CommentStatus.fail: 'fail',
};
