package doan._java_food.repository;

import doan._java_food.models.Category;
import doan._java_food.models.RestaurantCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RestaurantCategoryRepository extends JpaRepository<RestaurantCategory, Long> {
    @Modifying
    @Query(value = "DELETE FROM restaurant_categories ur where ur.category_id = :id", nativeQuery = true)
    @Transactional
    void deleteByCategoryId(@Param("id")long id);

    @Modifying
    @Query(value = "DELETE FROM restaurant_categories ur where ur.restaurant_id = :id", nativeQuery = true)
    @Transactional
    void deleteByRestaurantId(@Param("id")long id);
}
