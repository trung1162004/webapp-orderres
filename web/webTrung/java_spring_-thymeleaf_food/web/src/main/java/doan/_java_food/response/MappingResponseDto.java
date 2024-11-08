package doan._java_food.response;


import doan._java_food.models.*;
import doan._java_food.response.Blog.BlogResponse;
import doan._java_food.response.Category.CategoryResponse;
import doan._java_food.response.Restauran.RestaurantResponse;
import doan._java_food.response.Slide.SlideResponse;
import doan._java_food.response.User.UserRelationResponse;
import doan._java_food.response.User.UserResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MappingResponseDto {

    public UserResponse getUserInfo(User data) {
        UserResponse newData = new UserResponse();
//        newData.setId(data.getId());
//        newData.setName(data.getName());
//        newData.setStatus(data.getStatus());
//        newData.setAvatar(data.getAvatar());
//        newData.setAddress(data.getAddress());
//        newData.setGender(data.getGender());
//        newData.setType(data.getType());
//        newData.setEmail(data.getEmail());
//        newData.setPhone(data.getPhone());
        BeanUtils.copyProperties(data, newData);
        return newData;
    }

    public CategoryResponse getCategoryInfo(Category data) {
        if(data != null) {
            CategoryResponse newData = new CategoryResponse();
//            newData.setId(data.getId());
//            newData.setName(data.getName());
//            newData.setStatus(data.getStatus());
            BeanUtils.copyProperties(data, newData);
            return newData;
        }
        return null;
    }
    public SlideResponse getSlideInfo(Slides data) {
        if(data != null) {
            SlideResponse newData = new SlideResponse();
            BeanUtils.copyProperties(data, newData);
            return newData;
        }
        return null;
    }
//
    public RestaurantResponse getRestaurantInfo(Restaurant data) {
        if(data == null) {
            return null;
        }
        RestaurantResponse newData = new RestaurantResponse();
        BeanUtils.copyProperties(data, newData);
        if(data.getUser() != null)  {
            newData.setUser(new UserRelationResponse(data.getUser().getId(),
                    data.getUser().getName(), data.getUser().getEmail(),
                    data.getUser().getGender(),
                    data.getUser().getAvatar()));
        }
        else {
            newData.setUser(null);
        }
        return newData;
    }

    public BlogResponse getBlogInfo(Blog data) {
        if(data == null) {
            return null;
        }
        BlogResponse newData = new BlogResponse();
        BeanUtils.copyProperties(data, newData);
        return newData;
    }
//
//    public CertificateResponse getInfoCertificate(Certificate data) {
//        if(data == null) {
//            return null;
//        }
//        CertificateResponse newData = new CertificateResponse();
//        newData.setId(data.getId());
//        newData.setName(data.getName());
//        newData.setDescription(data.getDescription());
//        newData.setStatus(data.getStatus());
//        newData.setCreated_at(data.getCreated_at());
//        newData.setUpdated_at(data.getUpdated_at());
//
//        UserRelationResponse u = new UserRelationResponse();
//        if(data.getUser() != null)  {
//            newData.setUser(new UserRelationResponse(data.getUser().getId(),
//                    data.getUser().getName(), data.getUser().getEmail(),
//                    data.getUser().getCode(), data.getUser().getGender(), data.getUser().getAvatar()));
//        }
//        else {
//            newData.setUser(null);
//        }
//        return newData;
//    }
//
//    public RoomResponse getInfoRoom(Room data) {
//        if(data == null) {
//            return null;
//        }
//        RoomResponse newData = new RoomResponse();
//        newData.setId(data.getId());
//        newData.setName(data.getName());
//        newData.setDescription(data.getDescription());
//        newData.setStatus(data.getStatus());
//        newData.setCreated_at(data.getCreated_at());
//        newData.setUpdated_at(data.getUpdated_at());
//
//        UserRelationResponse u = new UserRelationResponse();
//        if(data.getUser() != null)  {
//            newData.setUser(new UserRelationResponse(data.getUser().getId(),
//                    data.getUser().getName(), data.getUser().getEmail(),
//                    data.getUser().getCode(), data.getUser().getGender(), data.getUser().getAvatar()));
//        }
//        else {
//            newData.setUser(null);
//        }
//        return newData;
//    }
//
//    public SalaryResponse getInfoSalary(Salary data) {
//        if(data == null) {
//            return null;
//        }
//        SalaryResponse newData = new SalaryResponse();
//        newData.setId(data.getId());
//
//        newData.setAllowance(data.getAllowance());
//        newData.setReceive_salary(data.getReceive_salary());
//        newData.setFrom_date(data.getFrom_date());
//        newData.setTo_date(data.getTo_date());
//        newData.setWorkday(data.getWorkday());
//
//        newData.setSalary(data.getSalary());
//        newData.setStatus(data.getStatus());
//        newData.setCreated_at(data.getCreated_at());
//        newData.setUpdated_at(data.getUpdated_at());
//
//        if(data.getUser() != null)  {
//            newData.setUser(new UserRelationResponse(data.getUser().getId(),
//                    data.getUser().getName(), data.getUser().getEmail(),
//                    data.getUser().getCode(), data.getUser().getGender(), data.getUser().getAvatar()));
//        }
//        else {
//            newData.setUser(null);
//        }
//
//        if(data.getUpdatedBy() != null)  {
//            newData.setUpdated_by(new UserRelationResponse(data.getUpdatedBy().getId(),
//                    data.getUpdatedBy().getName(), data.getUpdatedBy().getEmail(),
//                    data.getUpdatedBy().getCode(), data.getUpdatedBy().getGender(), data.getUpdatedBy().getAvatar()));
//        }
//        else {
//            newData.setUpdated_by(null);
//        }
//
//        return newData;
//    }
//
//    public BonusResponse getInfoBonus(BonusAndDiscipline data) {
//        if(data == null) {
//            return null;
//        }
//        BonusResponse newData = new BonusResponse();
//        newData.setId(data.getId());
//        newData.setType(data.getType());
//        newData.setName(data.getName());
//        newData.setContent(data.getContent());
//        newData.setStatus(data.getStatus());
//        newData.setData_value(data.getDataValue());
//        newData.setCreated_at(data.getCreated_at());
//        newData.setUpdated_at(data.getUpdated_at());
//
//        if(data.getUser() != null)  {
//            newData.setUser(new UserRelationResponse(data.getUser().getId(),
//                    data.getUser().getName(), data.getUser().getEmail(),
//                    data.getUser().getCode(), data.getUser().getGender(), data.getUser().getAvatar()));
//        }
//        else {
//            newData.setUser(null);
//        }
//        if(data.getUpdatedBy() != null)  {
//            newData.setUpdated_by(new UserRelationResponse(data.getUpdatedBy().getId(),
//                    data.getUpdatedBy().getName(), data.getUpdatedBy().getEmail(),
//                    data.getUpdatedBy().getCode(), data.getUpdatedBy().getGender(), data.getUpdatedBy().getAvatar()));
//        }
//        return newData;
//    }
//
//    public WorkingTaskResponse getInfoWorkingTask(WorkingTasks data) {
//        if(data == null) {
//            return null;
//        }
//        WorkingTaskResponse newData = new WorkingTaskResponse();
//        newData.setId(data.getId());
//        newData.setBonus(data.getBonus());
//        newData.setName(data.getName());
//        newData.setDescription(data.getDescription());
//        newData.setStatus(data.getStatus());
//        newData.setTo_date(data.getTo_date());
//        newData.setFrom_date(data.getFrom_date());
//        newData.setCreated_at(data.getCreated_at());
//        newData.setUpdated_at(data.getUpdated_at());
//
//        UserRelationResponse u = new UserRelationResponse();
//        if(data.getUser() != null)  {
//            newData.setUser(new UserRelationResponse(data.getUser().getId(),
//                    data.getUser().getName(), data.getUser().getEmail(),
//                    data.getUser().getCode(), data.getUser().getGender(), data.getUser().getAvatar()));
//        }
//        else {
//            newData.setUser(null);
//        }
//        return newData;
//    }
}
