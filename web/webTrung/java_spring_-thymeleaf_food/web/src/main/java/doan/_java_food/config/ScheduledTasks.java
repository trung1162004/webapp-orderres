package doan._java_food.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import doan._java_food.service.UserService.UsersServicesImpl;

@Component
public class ScheduledTasks {

    @Autowired
    private UsersServicesImpl usersServices;

    @Scheduled(fixedRate = 20000)  // Thực hiện mỗi 20 giây
    public void checkAndUpdateDeadlineServices() {
        usersServices.updateStatusIfDeadline();
    }
}
