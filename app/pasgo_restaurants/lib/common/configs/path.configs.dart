class ApiConfig {
  static String baseUrl = 'http://192.168.1.3:3014/api/v1';
  static String fgpUrl = 'http://192.168.1.3/forgot-password';
  static String baseUrlImage ="http://192.168.1.3:3013/images/";
}

class ApiEndpoints {
  static String authEndPoint = '/auth';
  static String userEndPoint = '/user';
  static String restaurantEndPoint = '/restaurant';
  static String categoriesEndPoint = '/category';
  static String commentsEndPoint = '/comment';
  static String ordersEndPoint = '/order';
}

class ApiPaths {
  static final Map<String, String> auth = {
    'login': '${ApiConfig.baseUrl}${ApiEndpoints.authEndPoint}/login',
    'register': '${ApiConfig.baseUrl}${ApiEndpoints.authEndPoint}/register',
    "update": '${ApiConfig.baseUrl}${ApiEndpoints.authEndPoint}/profile',
    "changePassword":"${ApiConfig.baseUrl}${ApiEndpoints.authEndPoint}/change-password"
  };
  static final Map<String, String> user = {
    'get': '${ApiConfig.baseUrl}${ApiEndpoints.userEndPoint}/show'
  };

/*sorry bc i'm so lazy*/
  static final Map<String, String> restaurant = {
    'get':
        '${ApiConfig.baseUrl}${ApiEndpoints.restaurantEndPoint}/list?page=1&page_size=20&name&email&user_id&status',
        
    'getFavourite':
        '${ApiConfig.baseUrl}${ApiEndpoints.restaurantEndPoint}/like?page=1&page_size=20',
        'addToFav':
        '${ApiConfig.baseUrl}${ApiEndpoints.restaurantEndPoint}/like-or-unlike',
        
  };

  static final Map<String, String> categories = {
    'get':
        '${ApiConfig.baseUrl}${ApiEndpoints.categoriesEndPoint}/list'
  };

  /*sorry bc i'm so lazy*/
  static final Map<String, String> comment = {
    'get':
        '${ApiConfig.baseUrl}${ApiEndpoints.commentsEndPoint}/list?page=1&page_size=20&name=&email=&user_id=&',

    'create':"${ApiConfig.baseUrl}${ApiEndpoints.commentsEndPoint}/store",
    'update':"${ApiConfig.baseUrl}${ApiEndpoints.commentsEndPoint}/update",
    'delete':"${ApiConfig.baseUrl}${ApiEndpoints.commentsEndPoint}/delete",

  };

 static final Map<String, String> orders = {
    'get':
        '${ApiConfig.baseUrl}${ApiEndpoints.ordersEndPoint}/list',
    'create':
        '${ApiConfig.baseUrl}${ApiEndpoints.ordersEndPoint}/store',
  };

}
