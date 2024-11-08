package doan._java_food.repository;

import doan._java_food.models.Blog;
import doan._java_food.models.Restaurant;
import doan._java_food.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    @Query(value = "Select u.* " +
            " from blogs u " +
            " WHERE TRUE " +
            " AND (:name is null or :name = '' or u.name LIKE %:name%) " +
            " AND (:author is null or :author = '' or u.author LIKE %:author%) " +
            " AND (:status is null or :status = '' or u.status= :status) " +
            " AND (:hot is null or :hot = '' or u.hot= :hot) " +
            " LIMIT :page_size OFFSET :page",nativeQuery = true)
    List<Blog> findAndCountBy(
            @Param("page") int page,
            @Param("page_size")int page_size,
            @Param("status") String status,
            @Param("name") String name,
            @Param("author") String author,
            @Param("hot") String hot


    );

    @Query(value = "Select u.* " +
            " from blogs u " +
            " WHERE TRUE " +
            " AND (:name is null or :name = '' or u.name LIKE %:name%) " +
            " AND (:author is null or :author = '' or u.author LIKE %:author%) " +
            " AND (:status is null or :status = '' or u.status= :status) " +
            " AND (:hot is null or :hot = '' or u.hot= :hot) ",nativeQuery = true)
    Integer countByCondition(
            @Param("status") String status,
            @Param("name") String name,
            @Param("author") String author,
            @Param("hot") String hot
    );
}
