import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/color.style.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/locators.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/route.configs.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/dimens.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/shareprefs.dart';
import 'package:pasgo_restaurants_ecommerce/gen/assets.gen.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/user/user_bloc.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/bloc/user/user_state.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/editProfile/edit_profile.page.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/profile/widgets/change_password_bottom_sheets.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/pages/profile/widgets/item_option.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/widgets/bottomsheets/content.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/widgets/buttons/button.dart';

class ProfilePage extends StatefulWidget {
  final String? storedPwd;
  final Map<String, dynamic>? userData;
  const ProfilePage({super.key, this.userData, this.storedPwd});

  @override
  State<ProfilePage> createState() => _ProfilePageState();
}

class _ProfilePageState extends State<ProfilePage> {
  late List<String> options;

  @override
  void initState() {
    super.initState();
    getIt<UserBloc>().add(const GetUser());
    _updateOptions();
  }

  void _updateOptions() {
    final userType = widget.userData?["type"];
    if (userType == "RESTAURANT") {
      options = [
        "Edit Profile",
        "Change Password",
        "Favourite Restaurant",
        "Subscriptions"
      ];
    } else {
      options = [
        "Edit Profile",
        "Change Password",
        "Favourite Restaurant"
      ];
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16),
          child: BlocBuilder<UserBloc, UserState>(
            builder: (context, state) {
              _updateOptions(); // Update options based on current user data

              return Column(
                mainAxisAlignment: MainAxisAlignment.start,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  const SizedBox(height: 40),
                  _header(state),
                  const SizedBox(height: 20),
                  SizedBox(
                    height: DimensionApp.screenHeight * 0.5,
                    child: ListView.separated(
                      physics: const BouncingScrollPhysics(),
                      itemBuilder: (context, index) {
                        return ItemOption(
                          title: options[index],
                          onTap: () {
                            if (index == 0) {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                  builder: (context) => EditProfilePage(
                                      userData: state.u,
                                      storedPwd: widget.storedPwd),
                                ),
                              );
                            } else if (index == 2) {
                              Navigator.pushNamed(
                                  context, AppRouters.favouriteRestaurant);
                            } else if (index == 3) {
                              Navigator.pushNamed(
                                context, AppRouters.sch,
                                arguments: state.u["id"]
                              );
                            } else {
                              showAppModalBottomSheet(
                                context: context,
                                barrierColor: MyAppColors.black.withOpacity(0.2),
                                child: ChangePasswordBottomSheet(userData: state.u),
                              );
                            }
                          },
                        );
                      },
                      separatorBuilder: (context, index) => const SizedBox(height: 25),
                      itemCount: options.length,
                    ),
                  ),
                  if (widget.userData?["email"] != null)
                    ButtonWidget(
                      buttonText: "Log out",
                      onPressed: () async {
                        await SharePrefs.setUserData(null);
                        await SharePrefs.setUserPassword(null);
                        await SharePrefs.setUserAccessToken(null);
                        if (!context.mounted) return;
                        Navigator.pushNamedAndRemoveUntil(
                            context, AppRouters.home, (route) => false);
                      }
                    )
                ],
              );
            },
          ),
        ),
      ),
    );
  }

  Column _header(UserState state) => Column(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Align(
            alignment: Alignment.topCenter,
            child: Container(
              width: 150,
              height: 150,
              decoration: BoxDecoration(
                  color: Colors.red,
                  borderRadius: BorderRadius.circular(150 / 2)),
              child: ClipRRect(
                borderRadius: BorderRadius.circular(150 / 2),
                child: Image.asset(
                  Assets.images.trendAvatar1.path,
                  fit: BoxFit.cover,
                ),
              ),
            ),
          ),
          const SizedBox(height: 5),
          Text(
            widget.userData?["email"] != null? state.u["name"] ?? "":"",
            style: const TextStyle(
                fontSize: 22,
                fontWeight: FontWeight.w700,
                color: MyAppColors.black),
          ),
        ],
      );
}
