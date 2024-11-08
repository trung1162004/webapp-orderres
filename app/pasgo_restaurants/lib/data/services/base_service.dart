import 'dart:convert';
import 'package:http/http.dart';
import 'package:injectable/injectable.dart';

abstract class BaseService {
  static final CustomClient defaultClient = CustomClient();
  static GetErrorMessage? _getErrorMessage;
  static GetErrorMessage get getErrorMessage =>
      _getErrorMessage ?? (err) => '$err';
  static set setErrorMessage(GetErrorMessage message) =>
      _getErrorMessage = message;

  late final CustomClient client;
  BaseService({CustomClient? client}) {
    this.client = client ?? defaultClient;
  }
}

Map<String, String> _defaultHeaders = {
  'Content-Type': 'application/json',
};

@lazySingleton
class CustomClient {
  final Client client = Client();
  // final _cacheManager = DefaultDataCacheManager.instance;
  Map<String, String> _addDefaultPropertiesToHeader(
      Map<String, String>? currentHeaders) {
    final headers = currentHeaders ?? {};
    return {
      ..._defaultHeaders,
      ...headers,
      //if has token write here
    };
  }

  Uri getUrlWithParams(String url, Map<String, dynamic>? params) {
    final uri = Uri.parse(url);
    if (params == null) return uri;
    final params0 = <String, dynamic>{};
    params0.addAll(uri.queryParameters);
    params0.addAll(params.map((key, value) => MapEntry(key, value.toString())));
    if (uri.scheme == 'https') {
      return Uri.https(uri.authority, uri.path, params0);
    }
    final result = Uri.http(uri.authority, uri.path, params0);
    return result;
  }

  Future<Response> get(String url,
      {Map<String, String>? currentHeaders, Map<String, dynamic>? params}) {
    final headers = _addDefaultPropertiesToHeader(currentHeaders);
    final urlWithParams = getUrlWithParams(url, params);
    return client.get(urlWithParams, headers: headers);
  }

  Future<Response> post(String url,
      {Map<String, String>? currentHeaders,
      body,
      Map<String, String>? params,
      Encoding? encoding}) {
    final headers = _addDefaultPropertiesToHeader(currentHeaders);
    final urlWithParams = getUrlWithParams(url, params);
    return client.post(urlWithParams,
        headers: headers, body: body, encoding: encoding);
  }

  Future<Response> patch(String url,
      {Map<String, String>? customHeaders,
      Map<String, String>? params,
      body,
      Encoding? encoding}) {
    final headers = _addDefaultPropertiesToHeader(customHeaders);
    final urlWithParams = getUrlWithParams(url, params);
    return client.patch(urlWithParams,
        headers: headers, body: body, encoding: encoding);
  }

  Future<Response> put(String url,
      {Map<String, String>? customHeaders,
      Map<String, String>? params,
      body,
      Encoding? encoding}) {
    final headers = _addDefaultPropertiesToHeader(customHeaders);
    final urlWithParams = getUrlWithParams(url, params);
    return client.put(urlWithParams,
        headers: headers, body: body, encoding: encoding);
  }

  Future<Response> delete(String url,
      {Map<String, String>? customHeaders,
      Map<String, String>? params,
      body,
      Encoding? encoding}) {
    final headers = _addDefaultPropertiesToHeader(customHeaders);
    final urlWithParams = getUrlWithParams(url, params);
    return client.delete(urlWithParams, headers: headers);
  }
}

typedef GetErrorMessage = String Function(Exception err);
