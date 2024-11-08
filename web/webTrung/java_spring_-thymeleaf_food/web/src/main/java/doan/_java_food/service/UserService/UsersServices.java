package doan._java_food.service.UserService;

import doan._java_food.models.UserService;

import java.util.List;

public interface UsersServices {
    Boolean create(UserService data);
    Boolean update(UserService data);
    List<UserService>getAll();
//    List<UserService>finAllByCondition(int page, int page_size, String user_id, String service_id, String status);
    Integer countByCondition( String user_id, String service_id, String status);
    UserService findById(Long id);
 // Trong UsersServices
//    List<UserService> findByUserId(Long id);
    
	
    List<UserService> getNearlyExpiredAndExpiredServices();
    
    UserService findByUserUsernameAndStatus(String username, String status);
    void updateStatusIfDeadline();
    Float sumTotalPrice(String status, String user_id);
    Boolean delete(Long id);
	List<UserService> findByUserId(Integer userId);
	Float getSalesThisMonth();
	Integer getPackagesThisMonth();
	List<UserService> findActiveServiceByUser(Integer userId);

}