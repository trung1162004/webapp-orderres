import 'package:flutter/material.dart';
import 'package:pasgo_restaurants_ecommerce/color.style.dart';
import 'package:pasgo_restaurants_ecommerce/common/configs/path.configs.dart';
import 'package:pasgo_restaurants_ecommerce/presentation/widgets/buttons/button.dart';
import 'package:webview_flutter/webview_flutter.dart';

class ForgotPasswordPage extends StatefulWidget {
  const ForgotPasswordPage({super.key});

  @override
  State<ForgotPasswordPage> createState() => _ForgotPasswordPageState();
}

class _ForgotPasswordPageState extends State<ForgotPasswordPage> {
  late final WebViewController _controller;

  @override
  void initState() {
    super.initState();
    _loadWebViewContent();
  }

  Future<void> _loadWebViewContent() async {
     _controller = WebViewController()
      ..setJavaScriptMode(JavaScriptMode.unrestricted)
      ..loadRequest(
        Uri.parse(ApiConfig.fgpUrl),
      );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leadingWidth: 250,
        leading: Row(
          children: [
            const SizedBox(
              width: 10,
            ),
            GestureDetector(
              onTap: () => Navigator.pop(context),
              child: const Icon(Icons.west),
            ),
          ],
        ),
      ),
      body: WebViewWidget(
        controller: _controller,
      ),
    );
  }
}
