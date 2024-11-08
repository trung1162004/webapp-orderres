import 'package:equatable/equatable.dart';
import 'package:copy_with_extension/copy_with_extension.dart';
import 'package:json_annotation/json_annotation.dart';

part 'auth_state.g.dart';

enum AuthStatus { initial, success, failure,erorRegister,successRegister }

@JsonSerializable()
@CopyWith()
class AuthState extends Equatable {
  final String username;
  final String name;
  final String passwrod;
  final String email;
  final String Repasswrod;
  final String gender;
  final String phoneNumber;
  final bool isObscureText;
  final bool isObscureRepassText;
  final AuthStatus authStatus;

  const AuthState(
      {this.username = '',
      this.isObscureText = true,
      this.isObscureRepassText = true,
      this.name = '',
      this.email = '',
      this.gender = '',
      this.passwrod = '',
      this.Repasswrod = '',
      this.authStatus = AuthStatus.initial,
      this.phoneNumber = ''});

  @override
  List<Object> get props => [
        username,
        name,
        passwrod,
        phoneNumber,
        isObscureText,
        isObscureRepassText,
        Repasswrod,
        authStatus,email,gender
      ];
}

final class AuthInitial extends AuthState {
  const AuthInitial()
      : super(
          username: '',
          passwrod: '',
          Repasswrod: '',
          email: '',
          phoneNumber: '',
          isObscureText: true,
          isObscureRepassText: true,
          authStatus: AuthStatus.initial,

        );
}
