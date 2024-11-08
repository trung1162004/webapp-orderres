import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/color.style.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/route.configs.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/spacing_unit.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/validation.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/auth/auth_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/auth/auth_state.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/widgets/buttons/button.dart';
class LoginBottomSheet extends StatefulWidget {
  const LoginBottomSheet({super.key});

  @override
  State<LoginBottomSheet> createState() => _LoginBottomSheetState();
}

class _LoginBottomSheetState extends State<LoginBottomSheet> {
  bool _errorEmail = false;
  bool _errorPassword = false;
  bool _hasEnteredEmail = false;
  bool _hasEnteredPassword = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: MyAppColors.white,
      body: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 20),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            _header(context),
            _formLogin(),
            Align(
                alignment: Alignment.bottomCenter,
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    const Text("Don't have an account?"),
                    const SizedBox(
                      width: 5,
                    ),
                    GestureDetector(
                      onTap: () =>
                          Navigator.pushNamed(context, AppRouters.register),
                      child: const Text(
                        "Sign up",
                        style: TextStyle(
                            fontSize: 16,
                            fontWeight: FontWeight.w700,
                            color: MyAppColors.backgroundColor),
                      ),
                    )
                  ],
                )),
          ],
        ),
      ),
    );
  }

  Column _header(BuildContext ctx) => const Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          Text(
            "Pasgo",
            textAlign: TextAlign.left,
            style: TextStyle(
                color: MyAppColors.backgroundColor,
                fontSize: SpacingUnit.x8_5,
                fontWeight: FontWeight.bold),
          ),
          Text(
            "Login to use the features!",
            textAlign: TextAlign.left,
            style: TextStyle(
                color: MyAppColors.backgroundColor,
                fontSize: SpacingUnit.x3_5,
                fontWeight: FontWeight.bold),
          ),
        ],
      );

  BlocConsumer<AuthBloc, AuthState> _formLogin() =>
      BlocConsumer<AuthBloc, AuthState>(
        listener: (context, state) async {
          if (state.authStatus == AuthStatus.failure) {
            ScaffoldMessenger.of(context).showSnackBar(
              const SnackBar(
                content: Text('Login failed! Please try again.'),
              ),
            );
          } else if (state.authStatus == AuthStatus.success) {
            Navigator.pushNamedAndRemoveUntil(
                context, AppRouters.home, (route) => false);
          }
          context.read<AuthBloc>().resetAuthState();
        },
        builder: (context, state) {
          return Column(
            children: [
              TextField(
                onChanged: (e) {
                  context.read<AuthBloc>().onChangeUsername(e);
                  setState(() {
                    _hasEnteredEmail = e.isNotEmpty;
                    _errorEmail = !ValidationUtils.isValidEmail(e) && _hasEnteredEmail;
                  });
                },
                decoration: InputDecoration(
                    hintStyle: const TextStyle(color: MyAppColors.black),
                    labelStyle: const TextStyle(color: MyAppColors.black),
                    labelText: "Email",
                    errorText: _errorEmail ? "Email is invalid" : null,
                    prefixIcon: const Icon(
                      Icons.email,
                      color: MyAppColors.black,
                    )),
                keyboardType: TextInputType.emailAddress,
                style: const TextStyle(fontSize: 17, color: MyAppColors.black),
              ),
              const SizedBox(height: 30),
              TextField(
                obscureText: state.isObscureText,
                onChanged: (e) {
                  context.read<AuthBloc>().onChangePassword(e);
                  setState(() {
                    _hasEnteredPassword = e.isNotEmpty;
                    _errorPassword = e.length < 8 && _hasEnteredPassword;
                  });
                },
                decoration: InputDecoration(
                    hintStyle: const TextStyle(color: MyAppColors.black),
                    labelStyle: const TextStyle(color: MyAppColors.black),
                    labelText: "Password",
                    errorText: _errorPassword ? "Password must be at least 8 characters" : null,
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
                      ),
                    )),
                style: const TextStyle(fontSize: 17, color: MyAppColors.black),
              ),
              const SizedBox(height: 15),
              GestureDetector(
                onTap: () => Navigator.pushNamed(context, AppRouters.fgp),
                child: const Align(
                  alignment: Alignment.centerRight,
                  child: Text(
                    "Forgot Password?",
                  ),
                ),
              ),
              const SizedBox(height: 30),
              ButtonWidget(
                buttonText: "Login",
                onPressed: () {
                  bool flag = context.read<AuthBloc>().onValidate();
                  if (!_errorEmail && !_errorPassword) {
                    if (!flag) {
                      getIt<AuthBloc>().add(AuthLogin(
                        username: state.username,
                        password: state.passwrod,
                      ));
                    } else {
                      ScaffoldMessenger.of(context).showSnackBar(
                        const SnackBar(
                          content: Text('Username and Password cannot be empty!'),
                        ),
                      );
                    }
                  } else {
                    ScaffoldMessenger.of(context).showSnackBar(
                      const SnackBar(
                        content: Text('Please correct the errors above!'),
                      ),
                    );
                  }
                },
              ),
            ],
          );
        },
      );
}
