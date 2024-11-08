import 'package:intl/intl.dart';

class FormatDateApp {
  static String formatDate(String date) {
    if (date.isEmpty) return '';

    DateTime dateTime = DateTime.parse(date);

    String formattedDate = DateFormat('MM-dd-yyyy').format(dateTime);
    return formattedDate;
  }

  static String formatHour(String date) {
    if (date.isEmpty) return '';

    DateTime dateTime = DateTime.parse(date);
    DateTime vietnamTime = dateTime.toUtc().add(const Duration(hours: 7));

    String formattedHour = DateFormat('HH:mm').format(vietnamTime);
    return formattedHour;
  }
}
