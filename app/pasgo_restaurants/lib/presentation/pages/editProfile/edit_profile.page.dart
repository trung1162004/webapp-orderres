import 'package:flutter/material.dart';
import 'package:pasgo_restaurants_ecommerce/color.style.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/shareprefs.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/validation.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/auth/auth_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/user/user_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/editProfile/widgets/item_input.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/widgets/buttons/button.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/widgets/snackbar/snackbar.dart';

class EditProfilePage extends StatefulWidget {
  final Map<String, dynamic>? userData;
  final String? storedPwd;

  const EditProfilePage({super.key, this.userData, this.storedPwd});

  @override
  State<EditProfilePage> createState() => _EditProfilePageState();
}

class _EditProfilePageState extends State<EditProfilePage> {
  late bool isFemale;
  late TextEditingController fullNameController;
  late TextEditingController usernameController;
  late TextEditingController emailController;
  late TextEditingController phoneController;
  bool isUpdated = false;

  String? fullNameError;
  String? emailError;
  String? phoneError;
  String? usrn;
  @override
  void initState() {
    _getusr();
    getIt<UserBloc>().add(const GetUser());
    super.initState();
    isFemale = (widget.userData?['gender'] ?? 'MALE') == 'FEMALE';
    fullNameController =
        TextEditingController(text: widget.userData?['name'] ?? '');
    usernameController =
        TextEditingController(text: usrn );
    emailController =
        TextEditingController(text: widget.userData?['email'] ?? '');
    phoneController =
        TextEditingController(text: widget.userData?['phone'] ?? '');

    fullNameController.addListener(_checkForUpdates);
    emailController.addListener(_checkForUpdates);
    phoneController.addListener(_checkForUpdates);
  }
  void _getusr()async{
    usrn=await SharePrefs.getUserName();
    setState(() {
      
    });
    // print(usrn);
  }

  void _checkForUpdates() {
    setState(() {
      isUpdated = (fullNameController.text != widget.userData?['name'] &&
              fullNameController.text.isNotEmpty) ||
          (emailController.text != widget.userData?['email'] &&
              emailController.text.isNotEmpty) ||
          (phoneController.text != widget.userData?['phone'] &&
              phoneController.text.isNotEmpty) ||
          (isFemale != ((widget.userData?['gender'] ?? 'MALE') == 'FEMALE'));
    });
  }

  void _validateInputs() {
    setState(() {
      fullNameError =
          fullNameController.text.isEmpty ? "Full Name is required" : null;
      emailError = !ValidationUtils.isValidEmail(emailController.text)
          ? "Invalid Email"
          : null;
      phoneError = !ValidationUtils.isValidPhoneNumber(phoneController.text)
          ? "Invalid Phone Number"
          : null;

      isUpdated =
          fullNameError == null && emailError == null && phoneError == null;
    });
  }

  @override
  void dispose() {
    fullNameController.dispose();
    usernameController.dispose();
    emailController.dispose();
    phoneController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return PopScope(
      onPopInvoked: (e) {
        // print("fetch");
        getIt<UserBloc>().add(const GetUser());
      },
      child: Scaffold(
        appBar: AppBar(
          title: const Text('Edit Profile'),
        ),
        body: SingleChildScrollView(
          physics: const BouncingScrollPhysics(),
          child: Padding(
            padding: const EdgeInsets.all(12),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                ItemInput(
                  title: "Full Name",
                  controller: fullNameController,
                  callBack: (e) {
                    _checkForUpdates();
                    _validateInputs();
                  },
                  errorText: fullNameError,
                ),
                ItemInput(
                  title: "Username",
                  controller: usernameController,
                  isReadonly: true,
                  hint: usrn,
                  callBack: (e) {},
                ),
                ItemInput(
                  title: "Email",
                  controller: emailController,
                  callBack: (e) {
                    _checkForUpdates();
                    _validateInputs();
                  },
                  errorText: emailError,
                ),
                ItemInput(
                  title: "Phone",
                  controller: phoneController,
                  callBack: (e) {
                    _checkForUpdates();
                    _validateInputs();
                  },
                  errorText: phoneError,
                ),
                Row(
                  children: [
                    const Expanded(child: Text("Gender")),
                    const Text("Male"),
                    Switch(
                      value: isFemale,
                      activeColor: MyAppColors.backgroundColor,
                      onChanged: (bool value) {
                        setState(() {
                          isFemale = value;
                          _checkForUpdates();
                        });
                      },
                    ),
                    const Text("Female"),
                  ],
                ),
                const SizedBox(height: 20),
                ButtonWidget(
                  buttonText: "Update",
                  onPressed: () {
                    if (isUpdated) {
                      // print(!isFemale ? "MALE" : 'FEMALE');
                      getIt<AuthBloc>().add(AuthUpdate(
                        fullName: fullNameController.text,
                        email: emailController.text,
                        phong: phoneController.text,
                        gender: !isFemale ? "MALE" : 'FEMALE',
                        pwd: widget.storedPwd!,
                      ));
                      SnackbarUtils.showSuccessSnackBar(
                          context, 'Update Successfully');
                    }
                  },
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
