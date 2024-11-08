import 'package:equatable/equatable.dart';
import 'package:copy_with_extension/copy_with_extension.dart';
import 'package:json_annotation/json_annotation.dart';

part 'user_state.g.dart';

enum UserStatus { initial, success, failure, erorRegister, successRegister }

@JsonSerializable()
@CopyWith()
class UserState extends Equatable {
  final Map<String, dynamic> u;
  final UserStatus userStatus;

  const UserState({this.userStatus = UserStatus.initial, this.u = const {}});
  @override
  List<Object> get props => [userStatus, u];
}

final class UserInitial extends UserState {
  const UserInitial() : super();
}
