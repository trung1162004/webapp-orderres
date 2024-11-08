import 'package:flutter/material.dart';
import 'package:flutter_widget_from_html/flutter_widget_from_html.dart';
import 'package:pasgo_restaurants_ecommerce/common/utils/validation.dart';

class RegulationPage extends StatelessWidget {
  const RegulationPage({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    final String? content = ModalRoute.of(context)?.settings.arguments as String?;
    return Scaffold(
      appBar: AppBar(
        title: const Text("Regulations"),
      ),
      body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: SingleChildScrollView(
          child: HtmlWidget( content??"<h1>No data</h1>"),
        ),
      ),
    );
  }
}
