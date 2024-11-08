// ignore_for_file: invalid_use_of_visible_for_testing_member

import 'dart:convert';

import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';
import 'package:injectable/injectable.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/shareprefs.dart';
import 'package:pasgo_restaurants_ecommerce/domain/enum/validation_enum.dart';
import 'package:pasgo_restaurants_ecommerce/domain/use_cases/auth_case.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/auth/auth_state.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/user/user_bloc.dart';
part 'auth_event.dart';

@lazySingleton
class AuthBloc extends Bloc<AuthEvent, AuthState> {
  final AuthUseCase _authCase;
  AuthBloc()
      : _authCase = getIt<AuthUseCase>(),
        super(const AuthInitial()) {
    on<AuthLogin>(_login);
    on<AuthRegister>(_register);
    on<AuthUpdate>(_update);
    on<AuthChangePwd>(_changePwd);
  }
  late String data;

  void changeObsecureText() {
    emit(state.copyWith(isObscureText: !state.isObscureText));
  }

  void changeObscureRePasswordText() {
    emit(state.copyWith(isObscureRepassText: !state.isObscureRepassText));
  }

  void onChangeUsername(String txt) {
    emit(state.copyWith(username: txt));
  }

  void resetAuthState() {
    emit(state.copyWith(authStatus: AuthStatus.initial));
  }

  void onChangeFullname(String txt) {
    emit(state.copyWith(name: txt));
  }

  void onChangeEmail(String txt) {
    emit(state.copyWith(email: txt));
  }

  void onChangePhoneNumber(String txt) {
    emit(state.copyWith(phoneNumber: txt));
  }

  void onChangePassword(String txt) {
    emit(state.copyWith(passwrod: txt));
  }

  void onChangeRePassword(String txt) {
    emit(state.copyWith(Repasswrod: txt));
  }

  bool onValidate() => state.username.isEmpty || state.passwrod.isEmpty ;
  
  int onValidateReg() {
    if (state.username.isEmpty ||
        state.passwrod.isEmpty ||
        state.name.isEmpty ||
        state.Repasswrod.isEmpty) {
      return ValidationTypes.error.index;
    } else if (state.passwrod != state.Repasswrod) {
      return ValidationTypes.notMatch.index;
    } else {
      return ValidationTypes.match.index;
    }
  }

  Future<void> _login(AuthLogin event, Emitter<AuthState> emit) async {
    try {
      Map<String, dynamic> users =
          await _authCase.login(state.username, state.passwrod);
      var userData = users['data']['user'];
      var accessToken = users['data']['access_token'];
      await SharePrefs.setUserPassword(state.passwrod);
      await SharePrefs.setUserData(userData);
      await SharePrefs.setUserAccessToken(accessToken);
      data = jsonEncode(userData).toString();

      emit(state.copyWith(
          authStatus: userData['email'].isNotEmpty
              ? AuthStatus.success
              : AuthStatus.failure));
      // print(state.authStatus);
    } catch (e) {
      // print("ERROR $e");
      emit(state.copyWith(authStatus: AuthStatus.failure));
    }
  }

  Future<void> _register(AuthRegister event, Emitter<AuthState> emit) async {
    try {
      bool flag = await _authCase.register(state.name, state.username,
          state.email, event.phoneNumber, state.passwrod, "MALE", "CUSTOMER");
      emit(state.copyWith(
          authStatus:
              flag ? AuthStatus.successRegister : AuthStatus.erorRegister));
    } catch (e) {
      emit(state.copyWith(authStatus: AuthStatus.erorRegister));
    }
  }

  Future<void> _update(AuthUpdate event, Emitter<AuthState> emit) async {
    try {
      await _authCase.update(event.fullName, event.email, event.phong,
          event.gender, event.pwd);
      emit(state.copyWith(authStatus: AuthStatus.initial));
      getIt<UserBloc>().add(const GetUser());
    } catch (e) {}
  }
  Future<void> _changePwd(AuthChangePwd event, Emitter<AuthState> emit) async {
    try {
      await _authCase.updatePwd(event.rePwd, event.pwd);
      emit(state.copyWith(authStatus: AuthStatus.success));
      getIt<UserBloc>().add(const GetUser());
    } catch (e) {}
  }

  void resetState() {
    emit(const AuthInitial());
  }
}
