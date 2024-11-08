import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/color.style.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/dimens.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/shareprefs.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/auth/auth_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/auth/auth_state.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/widgets/buttons/button.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/widgets/snackbar/snackbar.dart';

class ChangePasswordBottomSheet extends StatefulWidget {
  final Map<String, dynamic> userData;

  const ChangePasswordBottomSheet({super.key, required this.userData});

  @override
  State<ChangePasswordBottomSheet> createState() =>
      _ChangePasswordBottomSheetState();
}

class _ChangePasswordBottomSheetState extends State<ChangePasswordBottomSheet> {
  String? oldPwd;
  String errOldPwd = "Wrong password";
  bool isErrorOldPwd = false;
  bool isErrorNewPwd = false;
  String errNewPwd = "Re-password is not match";
  final styleError =
      const TextStyle(color: Colors.red, fontWeight: FontWeight.bold);

  TextEditingController oldPassword = TextEditingController();
  TextEditingController newPassword = TextEditingController();
  TextEditingController rePassword = TextEditingController();

  bool oldPasswordObscured = true;
  bool newPasswordObscured = true;
  bool rePasswordObscured = true;
  @override
  void initState() {
    _getPassword();
    super.initState();
  }

  Future<void> _getPassword() async {
    oldPwd = await SharePrefs.getUserPassword();
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return BlocListener<AuthBloc, AuthState>(
      listener: (context, state) {
        if (state.authStatus == AuthStatus.success) {
          SnackbarUtils.showSuccessSnackBar(
              context, "Change Password Successfully");
          Navigator.pop(context);
        }
      },
      child: Container(
        padding: EdgeInsets.only(
            left: 16,
            right: 16,
            bottom: MediaQuery.of(context).viewInsets.bottom,
            top: 16),
        width: DimensionApp.screenWidth,
        decoration: const BoxDecoration(
          color: MyAppColors.white,
          borderRadius: BorderRadius.only(
            topLeft: Radius.circular(20),
            topRight: Radius.circular(20),
          ),
        ),
        height: DimensionApp.screenHeight * 0.75,
        child: SingleChildScrollView(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              Align(
                alignment: Alignment.topRight,
                child: GestureDetector(
                  onTap: () => Navigator.pop(context),
                  child: const Icon(
                    Icons.close,
                    size: 32,
                  ),
                ),
              ),
              const SizedBox(
                height: 20,
              ),
              TextField(
                controller: oldPassword,
                decoration: InputDecoration(
                  hintStyle: const TextStyle(color: MyAppColors.black),
                  labelStyle: const TextStyle(color: MyAppColors.black),
                  labelText: "Old Password",
                  error: !isErrorOldPwd
                      ? null
                      : Text(errOldPwd, style: styleError),
                  prefixIcon: const Icon(
                    Icons.lock,
                    color: MyAppColors.black,
                  ),
                  suffixIcon: IconButton(
                    icon: Icon(
                      oldPasswordObscured
                          ? Icons.visibility
                          : Icons.visibility_off,
                    ),
                    onPressed: () {
                      setState(() {
                        oldPasswordObscured = !oldPasswordObscured;
                      });
                    },
                  ),
                ),
                keyboardType: TextInputType.visiblePassword,
                obscureText: oldPasswordObscured,
                style: const TextStyle(fontSize: 17, color: MyAppColors.black),
              ),
              const SizedBox(
                height: 10,
              ),
              TextField(
                controller: newPassword,
                decoration: InputDecoration(
                  hintStyle: const TextStyle(color: MyAppColors.black),
                  labelStyle: const TextStyle(color: MyAppColors.black),
                  labelText: "New Password",
                  prefixIcon: const Icon(
                    Icons.lock,
                    color: MyAppColors.black,
                  ),
                  suffixIcon: IconButton(
                    icon: Icon(
                      newPasswordObscured
                          ? Icons.visibility
                          : Icons.visibility_off,
                    ),
                    onPressed: () {
                      setState(() {
                        newPasswordObscured = !newPasswordObscured;
                      });
                    },
                  ),
                ),
                keyboardType: TextInputType.visiblePassword,
                obscureText: newPasswordObscured,
                style: const TextStyle(fontSize: 17, color: MyAppColors.black),
              ),
              const SizedBox(height: 10),
              TextField(
                controller: rePassword,
                decoration: InputDecoration(
                  hintStyle: const TextStyle(color: MyAppColors.black),
                  error: !isErrorNewPwd
                      ? null
                      : Text(
                          errNewPwd,
                          style: styleError,
                        ),
                  labelStyle: const TextStyle(color: MyAppColors.black),
                  labelText: "Confirm Password",
                  prefixIcon: const Icon(
                    Icons.lock,
                    color: MyAppColors.black,
                  ),
                  suffixIcon: IconButton(
                    icon: Icon(
                      rePasswordObscured
                          ? Icons.visibility
                          : Icons.visibility_off,
                    ),
                    onPressed: () {
                      setState(() {
                        rePasswordObscured = !rePasswordObscured;
                      });
                    },
                  ),
                ),
                keyboardType: TextInputType.visiblePassword,
                obscureText: rePasswordObscured,
                style: const TextStyle(fontSize: 17, color: MyAppColors.black),
              ),
              const SizedBox(height: 32),
              ButtonWidget(
                  buttonText: "Change Password",
                  onPressed: () async {
                    if (oldPwd != oldPassword.text) {
                      setState(() {
                        isErrorOldPwd = true;
                      });
                    } else if (newPassword.text.isEmpty ||
                        rePassword.text.isEmpty) {
                    } else {
                      if (newPassword.text != rePassword.text) {
                        setState(() {
                          isErrorNewPwd = true;
                        });
                      } else {
                        setState(() {
                          isErrorNewPwd = false;
                          isErrorOldPwd = false;
                        });
                        await SharePrefs.setUserPassword(rePassword.text);
                        getIt<AuthBloc>().add(AuthChangePwd(
                            rePwd: rePassword.text, pwd: rePassword.text));
                      }
                    }
                  })
            ],
          ),
        ),
      ),
    );
  }
}
