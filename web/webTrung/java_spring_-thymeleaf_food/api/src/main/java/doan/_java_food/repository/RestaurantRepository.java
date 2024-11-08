package doan._java_food.repository;

import doan._java_food.models.Restaurant;
import doan._java_food.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query(value = "Select  u.* " +
            " from restaurants u " +
            " WHERE TRUE " +
            " AND (:name is null or :name = '' or u.name LIKE %:name%) " +
            " AND (:email is null or :email = '' or u.email LIKE %:email%) " +
            " AND (:type is null or :type = '' or u.type= :type) " +

            " AND (:location_id is null or :location_id = '' or u.location_id= :location_id) " +
            " AND (:hotDiscount is null or :hotDiscount = '' or u.hot_discount= :hotDiscount) " +
            " AND (:price_from is null or :price_from = '' or u.price>=:price_from)" +
            " AND (:price_to is null or :price_to = '' or u.price<:price_to)" +

            " AND (:status is null or :status = '' or u.status= :status) " +
            " AND (:user_id is null or :user_id = '' or u.user_id= :user_id) " +
            " ORDER BY u.id DESC LIMIT :page_size OFFSET :page",nativeQuery = true)
    List<Restaurant> findAndCountBy(
               @Param("page") int page,
               @Param("page_size")int page_size,
               @Param("status") String status,
               @Param("name") String name,
                @Param("email") String email,
                @Param("type") String type,
                @Param("user_id") String user_id,
               @Param("location_id") String location_id,
               @Param("hotDiscount") String hotDiscount,
               @Param("price_from") String price_from,
               @Param("price_to") String price_to


    );

    @Query(value = "Select count(*) " +
            " from restaurants u " +
            " WHERE TRUE " +
            " AND (:name is null or :name = '' or u.name LIKE %:name%) " +
            " AND (:email is null or :email = '' or u.email LIKE %:email%) " +
            " AND (:type is null or :type = '' or u.type= :type) " +

            " AND (:location_id is null or :location_id = '' or u.location_id= :location_id) " +
            " AND (:hotDiscount is null or :hotDiscount = '' or u.hot_discount= :hotDiscount) " +
            " AND (:price_from is null or :price_from = '' or u.price>=:price_from)" +
            " AND (:price_to is null or :price_to = '' or u.price<:price_to)" +

            " AND (:user_id is null or :user_id = '' or u.user_id= :user_id) " +
            " AND (:status is null or :status = '' or u.status= :status) ",nativeQuery = true)
    Long countByCondition(
            @Param("status") String status,
            @Param("name") String name,
            @Param("email") String email,
            @Param("type") String type,
            @Param("user_id") String user_id,
            @Param("location_id") String location_id,
            @Param("hotDiscount") String hotDiscount,
            @Param("price_from") String price_from,
            @Param("price_to") String price_to
    );

    @Query(value = "Select distinct (u.id) as data_id, u.* " +
            " from restaurants u " +
            "LEFT JOIN restaurant_categories rc ON u.id = rc.restaurant_id " +
            " WHERE TRUE " +
            " AND (:name is null or :name = '' or u.name LIKE %:name%) " +
            " AND (:email is null or :email = '' or u.email LIKE %:email%) " +
            " AND u.type = 'RESTAURANT' " +
            " AND u.status = 'ACTIVE' " +
            " AND (:user_id is null or :user_id = '' or u.user_id= :user_id) " +
            " AND rc.category_id=:category_id " +
            " LIMIT :page_size OFFSET :page",nativeQuery = true)
    List<Restaurant> findAndCountByCategory(
            @Param("page") int page,
            @Param("page_size")int page_size,
            @Param("name") String name,
            @Param("email") String email,
            @Param("user_id") String user_id,
            @Param("category_id") Integer category_id


    );

    @Query(value = "Select count(distinct (u.id)) " +
            " from restaurants u " +
            "LEFT JOIN restaurant_categories rc ON u.id = rc.restaurant_id " +
            " WHERE TRUE " +
            " AND (:name is null or :name = '' or u.name LIKE %:name%) " +
            " AND (:email is null or :email = '' or u.email LIKE %:email%) " +
            " AND u.type = 'RESTAURANT' " +
            " AND u.status = 'ACTIVE' " +
            " AND (:user_id is null or :user_id = '' or u.user_id= :user_id) " +
            " AND rc.category_id=:category_id ",nativeQuery = true)
    Integer countByConditionByCategory(
            @Param("name") String name,
            @Param("email") String email,
            @Param("user_id") String user_id,
            @Param("category_id") Integer category_id
    );


}
