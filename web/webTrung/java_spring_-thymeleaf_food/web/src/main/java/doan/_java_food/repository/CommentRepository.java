package doan._java_food.repository;

import doan._java_food.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "Select  u.* " +
            " from comments u " +
            " WHERE TRUE " +
            " AND (:name is null or :name = '' or u.name LIKE %:name%) " +
            " AND (:email is null or :email = '' or u.email LIKE %:email%) " +
            " AND (:status is null or :status = '' or u.status= :status) " +
            " AND (:user_id is null or :user_id = '' or u.user_id= :user_id) " +
            " AND (:restaurant_id is null or :restaurant_id = '' or u.restaurant_id= :restaurant_id) " +
            " LIMIT :page_size OFFSET :page",nativeQuery = true)
    List<Comment> findAndCountBy(
               @Param("page") int page,
               @Param("page_size")int page_size,
               @Param("restaurant_id") String restaurant_id,
               @Param("user_id") String user_id,
               @Param("status") String status,
               @Param("name") String name,
                @Param("email") String email


    );

    @Query(value = "Select count(*) " +
            " from comments u " +
            " WHERE TRUE " +
            " AND (:name is null or :name = '' or u.name LIKE %:name%) " +
            " AND (:email is null or :email = '' or u.email LIKE %:email%) " +
            " AND (:status is null or :status = '' or u.status= :status) " +
            " AND (:user_id is null or :user_id = '' or u.user_id= :user_id) " +
            " AND (:restaurant_id is null or :restaurant_id = '' or u.phone= :restaurant_id) ",nativeQuery = true)
    Long countByCondition(
            @Param("restaurant_id") String restaurant_id,
            @Param("user_id") String user_id,
            @Param("status") String status,
            @Param("name") String name,
            @Param("email") String email
    );

    @Modifying
    @Query(value = "DELETE FROM comments ur where ur.restaurant_id = :id", nativeQuery = true)
    @Transactional
    void deleteByRestaurantId(@Param("id")long id);

    @Modifying
    @Query(value = "DELETE FROM comments ur where ur.user_id = :user_id and ur.restaurant_id=:restaurant_id", nativeQuery = true)
    @Transactional
    void deleteByUserId(@Param("user_id")long user_id, @Param("restaurant_id")long restaurant_id);




}
