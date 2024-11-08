import 'package:equatable/equatable.dart';

class RestaurantEvent extends Equatable {
  const RestaurantEvent();

  @override
  List<Object> get props => [];
}

class GetRestaurants extends RestaurantEvent {
  const GetRestaurants();
  @override
  List<Object> get props => [];
}

class GetFavouriteRestaurants extends RestaurantEvent{
  const GetFavouriteRestaurants();
  @override
  List<Object> get props => [];
}

class AddFavouriteRestaurants extends RestaurantEvent{
  final bool isLikeState;
  final int rntId;
  const AddFavouriteRestaurants( {this.isLikeState=true,this.rntId=0});
  @override
  List<Object> get props => [];
}





