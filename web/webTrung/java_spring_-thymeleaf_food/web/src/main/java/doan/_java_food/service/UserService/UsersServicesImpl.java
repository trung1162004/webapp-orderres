package doan._java_food.service.UserService;

import doan._java_food.models.UserService;
import doan._java_food.repository.UserServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class UsersServicesImpl implements UsersServices {

    @Autowired
    private UserServiceRepository repository;

    @Override
    public Boolean create(UserService data) {
        try {
            data.setCreatedAt(new Date());
            data.setUpdatedAt(new Date());

            this.repository.save(data);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(UserService data) {
        try {
            data.setUpdatedAt(new Date());
            this.repository.save(data);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<UserService> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Integer countByCondition(String user_id, String service_id, String status) {
        System.out.println(user_id + status);
        return this.repository.countByCondition(user_id, service_id, status);
    }

    @Override
    public UserService findById(Long id) {
        return this.repository.getById(id);
    }
    
    

    @Override
    public Float sumTotalPrice(String status, String user_id) {
        return this.repository.sumOrderByTypeAndStatus(status, user_id);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            this.repository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<UserService> findByUserId(Integer userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public UserService findByUserUsernameAndStatus(String username, String status) {
        return repository.findByUserUsernameAndStatus(username, status);
    }

    // Thêm phương thức để kiểm tra và cập nhật trạng thái của gói dịch vụ hết hạn
    
    
    
    @Override
    public void updateStatusIfDeadline() {
        List<UserService> allServices = repository.findAll();
        LocalDateTime now = LocalDateTime.now();

        for (UserService service : allServices) {
            if (service.getDeadline() != null && service.getDeadline().isBefore(now) && "Still valid".equals(service.getStatus())) {
                service.setStatus("Expired");
                update(service);
            }
        }
    }

    public Integer getPackagesThisMonth() {
        return repository.countPackagesThisMonth();
    }

    public Float getSalesThisMonth() {
        return repository.sumSalesThisMonth();
    }

    @Override
    public List<UserService> findActiveServiceByUser(Integer userId) {
        return repository.findByUserIdAndStatus(userId, "Still valid");
    }

	
    @Override
    public List<UserService> getNearlyExpiredAndExpiredServices() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tenDaysLater = now.plusDays(10);
        return repository.findNearlyExpiredAndExpiredServices("Still valid", now, tenDaysLater, "Expired");
    }
	
}
