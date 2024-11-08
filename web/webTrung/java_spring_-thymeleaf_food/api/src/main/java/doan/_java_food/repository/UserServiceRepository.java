package doan._java_food.repository;

import doan._java_food.models.Restaurant;
import doan._java_food.models.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserServiceRepository extends JpaRepository<UserService, Long> {
    @Query(value = "Select sum(u.price) " +
            " from users_services u " +
            " WHERE TRUE " +
            " AND (:status is null or :status = '' or u.status= :status) " +
            " AND (:user_id is null or :user_id = '' or u.user_id= :user_id) " ,nativeQuery = true)
     Float sumOrderByTypeAndStatus(@Param("status") String status, @Param("user_id") String user_id);


    @Query(value = "Select distinct (u.id) as data_id, u.* " +
            " from users_services u " +
            " WHERE TRUE " +
            " AND (:status is null or :status = '' or u.status= :status) " +
            " AND (:service_id is null or :service_id = '' or u.service_type= :service_id) " +
            " AND (:user_id is null or :user_id = '' or u.user_id= :user_id) " +
            " LIMIT :page_size OFFSET :page",nativeQuery = true)
    List<UserService> findAndCountBy(
            @Param("page") int page,
            @Param("page_size")int page_size,
            @Param("user_id") String user_id,
            @Param("service_id") String service_id,
            @Param("status") String status


    );

    @Query(value = "Select count(*) " +
            " from users_services u " +
            " WHERE TRUE " +
            " AND (:status is null or :status = '' or u.status= :status) " +
            " AND (:service_id is null or :service_id = '' or u.service_type= :service_id) " +
            " AND (:user_id is null or :user_id = '' or u.user_id= :user_id) ",nativeQuery = true)
     Integer countByCondition(
            @Param("user_id") String user_id,
            @Param("service_id") String service_id,
            @Param("status") String status
    );
}
