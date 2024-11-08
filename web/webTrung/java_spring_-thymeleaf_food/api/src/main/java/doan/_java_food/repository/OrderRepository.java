package doan._java_food.repository;

import doan._java_food.models.Order;
import doan._java_food.models.Restaurant;
import doan._java_food.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "Select  u.* " +
            " from orders u " +
            " WHERE TRUE " +
            " AND (:name is null or :name = '' or u.receiver_name LIKE %:name%) " +
            " AND (:email is null or :email = '' or u.receiver_email LIKE %:email%) " +
            " AND (:restaurant_name is null or :restaurant_name = '' or u.restaurant_name LIKE %:restaurant_name%) " +
            " AND (:status is null or :status = '' or u.status= :status) " +
            " AND (:user_id is null or :user_id = '' or u.user_id= :user_id) " +
            " LIMIT :page_size OFFSET :page",nativeQuery = true)
    List<Order> findAndCountBy(
            @Param("page") int page,
            @Param("page_size")int page_size,
            @Param("status") String status,
            @Param("name") String name,
            @Param("email") String email,
            @Param("restaurant_name") String restaurant_name,
            @Param("user_id") String user_id


    );

    @Query(value = "Select count(*) " +
            " from orders u " +
            " WHERE TRUE " +
            " AND (:name is null or :name = '' or u.receiver_name LIKE %:name%) " +
            " AND (:email is null or :email = '' or u.receiver_email LIKE %:email%) " +
            " AND (:restaurant_name is null or :restaurant_name = '' or u.restaurant_name LIKE %:restaurant_name%) " +
            " AND (:status is null or :status = '' or u.status= :status) " +
            " AND (:user_id is null or :user_id = '' or u.user_id= :user_id) " ,nativeQuery = true)
    Integer countByCondition(
            @Param("status") String status,
            @Param("name") String name,
            @Param("email") String email,
            @Param("restaurant_name") String restaurant_name,
            @Param("user_id") String user_id
    );


    @Query(value = "Select sum(u.total_price) " +
            " from orders u " +
            " WHERE TRUE " +
            " AND (:status is null or :status = '' or u.status= :status) " +
            " AND (:type is null or :type = '' or u.type= :type) " ,nativeQuery = true)
    Float sumOrderByTypeAndStatus( @Param("status") String status, @Param("type") String type);


}
