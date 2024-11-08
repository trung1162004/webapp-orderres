// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'auth_state.dart';

// **************************************************************************
// CopyWithGenerator
// **************************************************************************

abstract class _$AuthStateCWProxy {
  AuthState Repasswrod(String Repasswrod);

  AuthState authStatus(AuthStatus authStatus);

  AuthState email(String email);

  AuthState gender(String gender);

  AuthState isObscureRepassText(bool isObscureRepassText);

  AuthState isObscureText(bool isObscureText);

  AuthState name(String name);

  AuthState passwrod(String passwrod);

  AuthState phoneNumber(String phoneNumber);

  AuthState username(String username);

  /// This function **does support** nullification of nullable fields. All `null` values passed to `non-nullable` fields will be ignored. You can also use `AuthState(...).copyWith.fieldName(...)` to override fields one at a time with nullification support.
  ///
  /// Usage
  /// ```dart
  /// AuthState(...).copyWith(id: 12, name: "My name")
  /// ````
  AuthState call({
    String? Repasswrod,
    AuthStatus? authStatus,
    String? email,
    String? gender,
    bool? isObscureRepassText,
    bool? isObscureText,
    String? name,
    String? passwrod,
    String? phoneNumber,
    String? username,
  });
}

/// Proxy class for `copyWith` functionality. This is a callable class and can be used as follows: `instanceOfAuthState.copyWith(...)`. Additionally contains functions for specific fields e.g. `instanceOfAuthState.copyWith.fieldName(...)`
class _$AuthStateCWProxyImpl implements _$AuthStateCWProxy {
  final AuthState _value;

  const _$AuthStateCWProxyImpl(this._value);

  @override
  AuthState Repasswrod(String Repasswrod) => this(Repasswrod: Repasswrod);

  @override
  AuthState authStatus(AuthStatus authStatus) => this(authStatus: authStatus);

  @override
  AuthState email(String email) => this(email: email);

  @override
  AuthState gender(String gender) => this(gender: gender);

  @override
  AuthState isObscureRepassText(bool isObscureRepassText) =>
      this(isObscureRepassText: isObscureRepassText);

  @override
  AuthState isObscureText(bool isObscureText) =>
      this(isObscureText: isObscureText);

  @override
  AuthState name(String name) => this(name: name);

  @override
  AuthState passwrod(String passwrod) => this(passwrod: passwrod);

  @override
  AuthState phoneNumber(String phoneNumber) => this(phoneNumber: phoneNumber);

  @override
  AuthState username(String username) => this(username: username);

  @override

  /// This function **does support** nullification of nullable fields. All `null` values passed to `non-nullable` fields will be ignored. You can also use `AuthState(...).copyWith.fieldName(...)` to override fields one at a time with nullification support.
  ///
  /// Usage
  /// ```dart
  /// AuthState(...).copyWith(id: 12, name: "My name")
  /// ````
  AuthState call({
    Object? Repasswrod = const $CopyWithPlaceholder(),
    Object? authStatus = const $CopyWithPlaceholder(),
    Object? email = const $CopyWithPlaceholder(),
    Object? gender = const $CopyWithPlaceholder(),
    Object? isObscureRepassText = const $CopyWithPlaceholder(),
    Object? isObscureText = const $CopyWithPlaceholder(),
    Object? name = const $CopyWithPlaceholder(),
    Object? passwrod = const $CopyWithPlaceholder(),
    Object? phoneNumber = const $CopyWithPlaceholder(),
    Object? username = const $CopyWithPlaceholder(),
  }) {
    return AuthState(
      Repasswrod:
          Repasswrod == const $CopyWithPlaceholder() || Repasswrod == null
              ? _value.Repasswrod
              // ignore: cast_nullable_to_non_nullable
              : Repasswrod as String,
      authStatus:
          authStatus == const $CopyWithPlaceholder() || authStatus == null
              ? _value.authStatus
              // ignore: cast_nullable_to_non_nullable
              : authStatus as AuthStatus,
      email: email == const $CopyWithPlaceholder() || email == null
          ? _value.email
          // ignore: cast_nullable_to_non_nullable
          : email as String,
      gender: gender == const $CopyWithPlaceholder() || gender == null
          ? _value.gender
          // ignore: cast_nullable_to_non_nullable
          : gender as String,
      isObscureRepassText:
          isObscureRepassText == const $CopyWithPlaceholder() ||
                  isObscureRepassText == null
              ? _value.isObscureRepassText
              // ignore: cast_nullable_to_non_nullable
              : isObscureRepassText as bool,
      isObscureText:
          isObscureText == const $CopyWithPlaceholder() || isObscureText == null
              ? _value.isObscureText
              // ignore: cast_nullable_to_non_nullable
              : isObscureText as bool,
      name: name == const $CopyWithPlaceholder() || name == null
          ? _value.name
          // ignore: cast_nullable_to_non_nullable
          : name as String,
      passwrod: passwrod == const $CopyWithPlaceholder() || passwrod == null
          ? _value.passwrod
          // ignore: cast_nullable_to_non_nullable
          : passwrod as String,
      phoneNumber:
          phoneNumber == const $CopyWithPlaceholder() || phoneNumber == null
              ? _value.phoneNumber
              // ignore: cast_nullable_to_non_nullable
              : phoneNumber as String,
      username: username == const $CopyWithPlaceholder() || username == null
          ? _value.username
          // ignore: cast_nullable_to_non_nullable
          : username as String,
    );
  }
}

extension $AuthStateCopyWith on AuthState {
  /// Returns a callable class that can be used as follows: `instanceOfAuthState.copyWith(...)` or like so:`instanceOfAuthState.copyWith.fieldName(...)`.
  // ignore: library_private_types_in_public_api
  _$AuthStateCWProxy get copyWith => _$AuthStateCWProxyImpl(this);
}

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

AuthState _$AuthStateFromJson(Map<String, dynamic> json) => AuthState(
      username: json['username'] as String? ?? '',
      isObscureText: json['isObscureText'] as bool? ?? true,
      isObscureRepassText: json['isObscureRepassText'] as bool? ?? true,
      name: json['name'] as String? ?? '',
      email: json['email'] as String? ?? '',
      gender: json['gender'] as String? ?? '',
      passwrod: json['passwrod'] as String? ?? '',
      Repasswrod: json['Repasswrod'] as String? ?? '',
      authStatus:
          $enumDecodeNullable(_$AuthStatusEnumMap, json['authStatus']) ??
              AuthStatus.initial,
      phoneNumber: json['phoneNumber'] as String? ?? '',
    );

Map<String, dynamic> _$AuthStateToJson(AuthState instance) => <String, dynamic>{
      'username': instance.username,
      'name': instance.name,
      'passwrod': instance.passwrod,
      'email': instance.email,
      'Repasswrod': instance.Repasswrod,
      'gender': instance.gender,
      'phoneNumber': instance.phoneNumber,
      'isObscureText': instance.isObscureText,
      'isObscureRepassText': instance.isObscureRepassText,
      'authStatus': _$AuthStatusEnumMap[instance.authStatus]!,
    };

const _$AuthStatusEnumMap = {
  AuthStatus.initial: 'initial',
  AuthStatus.success: 'success',
  AuthStatus.failure: 'failure',
  AuthStatus.erorRegister: 'erorRegister',
  AuthStatus.successRegister: 'successRegister',
};
