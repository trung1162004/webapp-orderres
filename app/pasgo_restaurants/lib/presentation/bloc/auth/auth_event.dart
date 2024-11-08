part of 'auth_bloc.dart';

class AuthEvent extends Equatable {
  const AuthEvent();

  @override
  List<Object> get props => [];
}

class AuthLogin extends AuthEvent {
  final String username;
  final String password;

  const AuthLogin({this.username = '', this.password = ''});
  @override
  List<Object> get props => [username, password];
}

class AuthRegister extends AuthEvent {
  final String username;
  final String passwrod;
  final String name;
  final String email;
  final String phoneNumber;

  const AuthRegister(
      {this.username = '',
      this.passwrod = '',
      this.email = '',
      this.name = '',
      this.phoneNumber = ''});
  @override
  List<Object> get props => [username, passwrod, name, phoneNumber, email];
}

class AuthUpdate extends AuthEvent {
  final String fullName;
  final String email;
  final String phong;
  final String gender;
  final String pwd;

  const AuthUpdate({
    this.fullName = '',
    this.email = '',
    this.phong = '',
    this.gender = '',
    this.pwd = '',
  });
  
  @override
  List<Object> get props => [fullName, email, phong, gender, pwd];
}

class AuthChangePwd extends AuthEvent{
  final String rePwd;
  final String pwd;
  const AuthChangePwd({
    this.pwd="",
    this.rePwd=""
  });
}