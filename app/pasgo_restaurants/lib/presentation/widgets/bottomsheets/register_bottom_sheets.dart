import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/color.style.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/spacing_unit.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/validation.dart';
import 'package:pasgo_restaurants_ecommerce/domain/enum/validation_enum.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/auth/auth_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/auth/auth_state.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/widgets/buttons/button.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/widgets/snackbar/snackbar.dart';

class RegisterBottomSheet extends StatefulWidget {
  const RegisterBottomSheet({super.key});

  @override
  State<RegisterBottomSheet> createState() => _RegisterBottomSheetState();
}

class _RegisterBottomSheetState extends State<RegisterBottomSheet> {
  bool errorFullName = false;
  bool errorEmail = false;
  bool errorPhone = false;
  bool errorUsername = false;
  bool _hasEnteredPassword = false;
  bool _passwordError = false;
  bool _hasEnteredRePassword = false;
  bool _rePasswordError = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: MyAppColors.white,
      body: SingleChildScrollView(
        child: Container(
          padding: const EdgeInsets.symmetric(horizontal: SpacingUnit.x3),
          constraints: BoxConstraints(
            minHeight: MediaQuery.of(context).size.height,
          ),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              Align(
                alignment: Alignment.topLeft,
                child: Padding(
                  padding: const EdgeInsets.only(left: SpacingUnit.x2),
                  child: GestureDetector(
                    onTap: () => Navigator.pop(context),
                    child: const Row(
                      children: [
                        Icon(
                          Icons.west,
                          size: SpacingUnit.x9,
                          color: MyAppColors.black,
                        ),
                        SizedBox(
                          width: 15,
                        ),
                        Text(
                          "Register",
                          textAlign: TextAlign.left,
                          style: TextStyle(
                              color: MyAppColors.backgroundColor,
                              fontSize: SpacingUnit.x8_5,
                              fontWeight: FontWeight.bold),
                        ),
                      ],
                    ),
                  ),
                ),
              ),
              const SizedBox(height: SpacingUnit.x8),
              _formLogin(context),
            ],
          ),
        ),
      ),
    );
  }

  BlocConsumer<AuthBloc, AuthState> _formLogin(BuildContext context) =>
      BlocConsumer<AuthBloc, AuthState>(
        listener: (context, state) {
          if (state.authStatus == AuthStatus.erorRegister) {
            ScaffoldMessenger.of(context).showSnackBar(
              const SnackBar(
                content: Text('Account already exists.'),
              ),
            );
          } else if (state.authStatus == AuthStatus.successRegister) {
            SnackbarUtils.showSuccessSnackBar(context, 'Register Successfully');
            Navigator.pop(context);
          }
          context.read<AuthBloc>().resetAuthState();
        },
        builder: (context, state) {
          return Column(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              TextField(
                onChanged: (e) {
                  context.read<AuthBloc>().onChangeFullname(e);
                  setState(() {
                    errorFullName = e.isEmpty;
                  });
                },
                decoration: InputDecoration(
                    hintStyle: const TextStyle(color: MyAppColors.black),
                    labelStyle: const TextStyle(color: MyAppColors.black),
                    labelText: "Fullname",
                    errorText: errorFullName ? "Field is empty" : null,
                    prefixIcon: const Icon(
                      Icons.people,
                      color: MyAppColors.black,
                    )),
                keyboardType: TextInputType.text,
                style: const TextStyle(fontSize: 17, color: MyAppColors.black),
              ),
              const SizedBox(height: 30),
              TextField(
                onChanged: (e) {
                  context.read<AuthBloc>().onChangeEmail(e);
                  setState(() {
                    errorEmail = !ValidationUtils.isValidEmail(e) && e.isNotEmpty;
                  });
                },
                decoration: InputDecoration(
                    hintStyle: const TextStyle(color: MyAppColors.black),
                    labelStyle: const TextStyle(color: MyAppColors.black),
                    labelText: "Email",
                    errorText: errorEmail ? "Email is invalid" : null,
                    prefixIcon: const Icon(
                      Icons.email,
                      color: MyAppColors.black,
                    )),
                keyboardType: TextInputType.emailAddress,
                style: const TextStyle(fontSize: 17, color: MyAppColors.black),
              ),
              const SizedBox(height: 30),
              TextField(
                onChanged: (e) {
                  context.read<AuthBloc>().onChangePhoneNumber(e);
                  setState(() {
                    errorPhone = !ValidationUtils.isValidPhoneNumber(e) && e.isNotEmpty;
                  });
                },
                decoration: InputDecoration(
                    hintStyle: const TextStyle(color: MyAppColors.black),
                    labelStyle: const TextStyle(color: MyAppColors.black),
                    labelText: "Phone number",
                    errorText: errorPhone ? "Phone is invalid" : null,
                    prefixIcon: const Icon(
                      Icons.phone,
                      color: MyAppColors.black,
                    )),
                keyboardType: TextInputType.phone,
                style: const TextStyle(fontSize: 17, color: MyAppColors.black),
              ),
              const SizedBox(height: 30),
              TextField(
                onChanged: (e) {
                  context.read<AuthBloc>().onChangeUsername(e);
                  setState(() {
                    errorUsername = e.isEmpty;
                  });
                },
                decoration: InputDecoration(
                    hintStyle: const TextStyle(color: MyAppColors.black),
                    labelStyle: const TextStyle(color: MyAppColors.black),
                    labelText: "Username",
                    errorText: errorUsername ? "Username is empty" : null,
                    prefixIcon: const Icon(
                      Icons.person,
                      color: MyAppColors.black,
                    )),
                keyboardType: TextInputType.text,
                style: const TextStyle(fontSize: 17, color: MyAppColors.black),
              ),
              const SizedBox(height: 30),
              TextField(
                obscureText: state.isObscureText,
                onChanged: (e) {
                  context.read<AuthBloc>().onChangePassword(e);
                  setState(() {
                    _hasEnteredPassword = e.isNotEmpty;
                    _passwordError = e.length < 8 && e.isNotEmpty;
                  });
                },
                decoration: InputDecoration(
                    hintStyle: const TextStyle(color: MyAppColors.black),
                    labelStyle: const TextStyle(color: MyAppColors.black),
                    labelText: "Password",
                    errorText: _hasEnteredPassword && _passwordError
                        ? "Password must be greater than 8 characters"
                        : null,
                    prefixIcon: const Icon(
                      Icons.lock,
                      color: MyAppColors.black,
                    ),
                    suffixIcon: IconButton(
                        onPressed: () {
                          context.read<AuthBloc>().changeObsecureText();
                        },
                        icon: Icon(
                          state.isObscureText
                              ? Icons.visibility_off
                              : Icons.visibility,
                          color: MyAppColors.black,
                        ))),
                style: const TextStyle(fontSize: 17, color: MyAppColors.black),
              ),
              const SizedBox(height: 40),
              TextField(
                style: const TextStyle(fontSize: 17, color: MyAppColors.black),
                obscureText: state.isObscureRepassText,
                onChanged: (e) {
                  context.read<AuthBloc>().onChangeRePassword(e);
                  setState(() {
                    _hasEnteredRePassword = e.isNotEmpty;
                    _rePasswordError = e != state.passwrod && e.isNotEmpty;
                  });
                },
                decoration: InputDecoration(
                  hintStyle: const TextStyle(color: MyAppColors.black),
                  labelStyle: const TextStyle(color: MyAppColors.black),
                  prefixIcon: const Icon(
                    Icons.lock_outline,
                    color: MyAppColors.black,
                  ),
                  labelText: "Re-Password",
                  errorText: _hasEnteredRePassword && _rePasswordError
                      ? "Passwords do not match"
                      : null,
                  suffixIcon: IconButton(
                    onPressed: () {
                      context.read<AuthBloc>().changeObscureRePasswordText();
                    },
                    icon: Icon(
                      state.isObscureRepassText
                          ? Icons.visibility_off
                          : Icons.visibility,
                      color: MyAppColors.black,
                    ),
                  ),
                ),
              ),
              const SizedBox(height: 40),
              ButtonWidget(
                  buttonText: "Register",
                  onPressed: () {
                    int flag = context.read<AuthBloc>().onValidateReg();
                    if (flag == ValidationTypes.match.index) {
                      getIt<AuthBloc>().add(AuthRegister(
                          username: state.username,
                          passwrod: state.passwrod,
                          name: state.name,
                          email: state.email,
                          phoneNumber: state.phoneNumber));
                    } else if (flag == ValidationTypes.error.index) {
                      ScaffoldMessenger.of(context).showSnackBar(
                        const SnackBar(
                          content: Text('Fields cannot be empty!'),
                        ),
                      );
                    } else if (flag == ValidationTypes.notMatch.index) {
                      ScaffoldMessenger.of(context).showSnackBar(
                        const SnackBar(
                          content: Text('Repassword not match!'),
                        ),
                      );
                    } else {
                      ScaffoldMessenger.of(context).showSnackBar(
                        const SnackBar(
                          content: Text('Account already exists'),
                        ),
                      );
                    }
                  }),
              const SizedBox(height: 20),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  const Text(
                    "Already have an account? ",
                    style: TextStyle(fontSize: 16, color: MyAppColors.black),
                  ),
                  GestureDetector(
                    onTap: () {
                      Navigator.pop(context);
                    },
                    child: const Text(
                      "Sign in",
                      style: TextStyle(
                          fontSize: 16,
                          color: MyAppColors.hintTextColor,
                          fontWeight: FontWeight.bold),
                    ),
                  )
                ],
              ),
            ],
          );
        },
      );
}
