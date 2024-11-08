package doan._java_food.repository;

import doan._java_food.models.Blog;
import doan._java_food.models.UserRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRestaurantRepository extends JpaRepository<UserRestaurant, Long> {
    @Query(value = "SELECT * FROM users_restaurants uj where uj.restaurant_id = :restaurant_id and uj.user_id = :user_id", nativeQuery = true)
    UserRestaurant findByUserRestaurant(@Param("restaurant_id") Long restaurant_id, @Param("user_id") Integer user_id);

    @Query(value = "SELECT * FROM users_restaurants uj where uj.user_id = :user_id LIMIT :page_size OFFSET :page ", nativeQuery = true)
    List<UserRestaurant> getByUser(@Param("page") Integer page,@Param("page_size") Integer page_size,@Param("user_id") Integer user_id);

    @Query(value = "SELECT count(*) FROM users_restaurants uj where uj.user_id = :user_id  ", nativeQuery = true)
    Long countByUserId(@Param("user_id") Integer user_id);

    @Modifying
    @Query(value = "DELETE FROM users_restaurants ur where ur.user_id = :id", nativeQuery = true)
    @Transactional
    void deleteByUserId(@Param("id")Integer id);
}
