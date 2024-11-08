import 'dart:async';
import 'dart:convert';

import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';
import 'package:injectable/injectable.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/shareprefs.dart';
import 'package:pasgo_restaurants_ecommerce/domain/use_cases/user_case.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/user/user_state.dart';

part 'user_event.dart';

@lazySingleton
class UserBloc extends Bloc<UserEvent, UserState> {
  final UserUseCase _useCase;
  UserBloc()
      : _useCase = getIt<UserUseCase>(),
        super(const UserInitial()) {
    on<GetUser>(_getUserInfor);
  }

  Future<void> _getUserInfor(GetUser event, Emitter<UserState> emit) async {
    try {
      String? data = await SharePrefs.getUserData();
      Map<String, dynamic>? userData;
      if (data != null) {
        userData = jsonDecode(data);
      }
      // await SharePrefs.
      final user = await _useCase.get(userData!['id']);
      await SharePrefs.setUserFullName(user["data"]["name"]);
      emit(state.copyWith(u: user["data"], userStatus: UserStatus.success));
    } catch (e) {
      // print("error $e");
      emit(state.copyWith(userStatus: UserStatus.failure));
    }
  }
}
