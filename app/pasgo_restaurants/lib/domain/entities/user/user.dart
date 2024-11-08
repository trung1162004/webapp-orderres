import 'package:json_annotation/json_annotation.dart';

part 'user.g.dart';

@JsonSerializable()
class User {
  final int id;
  final String name;
  final String username;
  final String status;
  final String email;
  final String gender;
  final String? phone;
  final String? avatar;
  final String type;
  final String? address;

  User({
    required this.id,
    required this.name,
    required this.username,
    required this.status,
    required this.email,
    required this.gender,
    this.phone,
    this.avatar,
    required this.type,
    this.address,
  });

  factory User.fromJson(Map<String, dynamic> json) =>
      _$UserFromJson(json);

  Map<String, dynamic> toJson() => _$UserToJson(this);

  // Map<String, dynamic> toJson() => {
  //       'fullName': fullName,
  //       'username': username,
  //       'phoneNumber': phoneNumber,
  //       'avatar': avatar,
  //       'dateOfBirth': dateOfBirth,
  //       'description': description,
  //       'role': role,
  //       'status': status
  //     };

  // factory UserModel.fromJson(Map<String, dynamic> json) => UserModel(
  //       fullName: json['fullName'],
  //       id: json['id'],
  //       username: json['username'],
  //       phoneNumber: json['phoneNumber'],
  //       avatar: json['avatar'],
  //       dateOfBirth: json['dateOfBirth'],
  //       description: json['description'],
  //       role: json['role'],
  //       status: json['status'],
  //     );
}
