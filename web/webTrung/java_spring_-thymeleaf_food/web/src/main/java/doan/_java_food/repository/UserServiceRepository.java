package doan._java_food.repository;

import doan._java_food.models.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface UserServiceRepository extends JpaRepository<UserService, Long> {

    @Query(value = "SELECT SUM(u.price) " +
                   "FROM users_services u " +
                   "WHERE TRUE " +
                   "AND (:status IS NULL OR :status = '' OR u.status = :status) " +
                   "AND (:user_id IS NULL OR :user_id = '' OR u.user_id = :user_id)", nativeQuery = true)
    Float sumOrderByTypeAndStatus(@Param("status") String status, @Param("user_id") String user_id);

    @Query(value = "SELECT DISTINCT (u.id) AS data_id, u.* " +
                   "FROM users_services u " +
                   "WHERE TRUE " +
                   "AND (:status IS NULL OR :status = '' OR u.status = :status) " +
                   "AND (:service_id IS NULL OR :service_id = '' OR u.service_type = :service_id) " +
                   "AND (:user_id IS NULL OR :user_id = '' OR u.user_id = :user_id) " +
                   "LIMIT :page_size OFFSET :page", nativeQuery = true)
    List<UserService> findAndCountBy(@Param("page") int page,
                                     @Param("page_size") int page_size,
                                     @Param("user_id") String user_id,
                                     @Param("service_id") String service_id,
                                     @Param("status") String status);

    @Query(value = "SELECT COUNT(*) " +
                   "FROM users_services u " +
                   "WHERE TRUE " +
                   "AND (:status IS NULL OR :status = '' OR u.status = :status) " +
                   "AND (:service_id IS NULL OR :service_id = '' OR u.service_type = :service_id) " +
                   "AND (:user_id IS NULL OR :user_id = '' OR u.user_id = :user_id)", nativeQuery = true)
    Integer countByCondition(@Param("user_id") String user_id,
                             @Param("service_id") String service_id,
                             @Param("status") String status);

    @Query("SELECT us FROM UserService us WHERE us.user.id = :userId")
    List<UserService> findByUserId(@Param("userId") Integer userId);

    
    
    UserService findByUserUsernameAndStatus(String username, String status);
    List<UserService> findByDeadlineBefore(LocalDateTime deadline);
    
    //dashboas nhà hàng theo tháng
    @Query("SELECT COUNT(u) FROM UserService u WHERE FUNCTION('MONTH', u.createdAt) = FUNCTION('MONTH', CURRENT_DATE) AND FUNCTION('YEAR', u.createdAt) = FUNCTION('YEAR', CURRENT_DATE)")
    Integer countPackagesThisMonth();
@Query("SELECT SUM(u.price) FROM UserService u WHERE FUNCTION('MONTH', u.createdAt) = FUNCTION('MONTH', CURRENT_DATE) AND FUNCTION('YEAR', u.createdAt) = FUNCTION('YEAR', CURRENT_DATE)")
    Float sumSalesThisMonth();
    
    //xuất file pdf
    @Query("SELECT u FROM UserService u WHERE (u.status = :status1 AND u.deadline BETWEEN :now AND :tenDaysLater) OR (u.status = :status2 AND u.deadline < :now)")
    List<UserService> findNearlyExpiredAndExpiredServices(@Param("status1") String status1, @Param("now") LocalDateTime now, @Param("tenDaysLater") LocalDateTime tenDaysLater, @Param("status2") String status2);
   
    // Thêm phương thức để tìm các gói dịch vụ hết hạn
//    @Query("SELECT us FROM UserService us WHERE us.expried < :today AND us.status = 'APPROVED'")
//    List<UserService> findDeadlineServices(@Param("today") LocalDateTime today);
    
    List<UserService> findByUserIdAndStatus(Integer userId, String status);

  
    
    @Query("SELECT us FROM UserService us WHERE us.deadline < :today AND us.status = 'Expired'")
    List<UserService> findDeadlineServices(@Param("today") LocalDateTime today);
}