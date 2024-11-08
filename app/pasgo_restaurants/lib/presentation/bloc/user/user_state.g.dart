// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'user_state.dart';

// **************************************************************************
// CopyWithGenerator
// **************************************************************************

abstract class _$UserStateCWProxy {
  UserState u(Map<String, dynamic> u);

  UserState userStatus(UserStatus userStatus);

  /// This function **does support** nullification of nullable fields. All `null` values passed to `non-nullable` fields will be ignored. You can also use `UserState(...).copyWith.fieldName(...)` to override fields one at a time with nullification support.
  ///
  /// Usage
  /// ```dart
  /// UserState(...).copyWith(id: 12, name: "My name")
  /// ````
  UserState call({
    Map<String, dynamic>? u,
    UserStatus? userStatus,
  });
}

/// Proxy class for `copyWith` functionality. This is a callable class and can be used as follows: `instanceOfUserState.copyWith(...)`. Additionally contains functions for specific fields e.g. `instanceOfUserState.copyWith.fieldName(...)`
class _$UserStateCWProxyImpl implements _$UserStateCWProxy {
  final UserState _value;

  const _$UserStateCWProxyImpl(this._value);

  @override
  UserState u(Map<String, dynamic> u) => this(u: u);

  @override
  UserState userStatus(UserStatus userStatus) => this(userStatus: userStatus);

  @override

  /// This function **does support** nullification of nullable fields. All `null` values passed to `non-nullable` fields will be ignored. You can also use `UserState(...).copyWith.fieldName(...)` to override fields one at a time with nullification support.
  ///
  /// Usage
  /// ```dart
  /// UserState(...).copyWith(id: 12, name: "My name")
  /// ````
  UserState call({
    Object? u = const $CopyWithPlaceholder(),
    Object? userStatus = const $CopyWithPlaceholder(),
  }) {
    return UserState(
      u: u == const $CopyWithPlaceholder() || u == null
          ? _value.u
          // ignore: cast_nullable_to_non_nullable
          : u as Map<String, dynamic>,
      userStatus:
          userStatus == const $CopyWithPlaceholder() || userStatus == null
              ? _value.userStatus
              // ignore: cast_nullable_to_non_nullable
              : userStatus as UserStatus,
    );
  }
}

extension $UserStateCopyWith on UserState {
  /// Returns a callable class that can be used as follows: `instanceOfUserState.copyWith(...)` or like so:`instanceOfUserState.copyWith.fieldName(...)`.
  // ignore: library_private_types_in_public_api
  _$UserStateCWProxy get copyWith => _$UserStateCWProxyImpl(this);
}

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

UserState _$UserStateFromJson(Map<String, dynamic> json) => UserState(
      userStatus:
          $enumDecodeNullable(_$UserStatusEnumMap, json['userStatus']) ??
              UserStatus.initial,
      u: json['u'] as Map<String, dynamic>? ?? const {},
    );

Map<String, dynamic> _$UserStateToJson(UserState instance) => <String, dynamic>{
      'u': instance.u,
      'userStatus': _$UserStatusEnumMap[instance.userStatus]!,
    };

const _$UserStatusEnumMap = {
  UserStatus.initial: 'initial',
  UserStatus.success: 'success',
  UserStatus.failure: 'failure',
  UserStatus.erorRegister: 'erorRegister',
  UserStatus.successRegister: 'successRegister',
};
