package doan._java_food.service.Restaurant;

import doan._java_food.models.Location;
import doan._java_food.models.Restaurant;
import doan._java_food.models.RestaurantCategory;
import doan._java_food.models.Slides;
import doan._java_food.repository.LocationRepository;
import doan._java_food.repository.RestaurantCategoryRepository;
import doan._java_food.repository.RestaurantRepository;
import doan._java_food.repository.SlideRepository;
import doan._java_food.service.Slide.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private RestaurantCategoryRepository restaurantCategoryRepository;

    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));



    @Override
    public List<Restaurant> getAll() {
        return this.repository.findAll();
    }

    @Override
    public List<Location> getAllLocation() {
        return this.locationRepository.findAll();
    }
    @Override
    public void createOrUpdateLocation(String name) {
        Location location = this.locationRepository.getLocationsByName(name);
        if(location == null) {
            Location l = new Location();
            l.setName(name);
            this.locationRepository.save(l);
        }
    }


    @Override
    public List<Restaurant> getAllByCondition(int page, int page_size, String status,
                                              String name, String email, String type,
                                              String user_id, String category_id, String hot, String discount,
                                              String price_from, String price_to
                                              ) {

        System.out.println("query-----> " + page * page_size);
        System.out.println(page + " " + page_size + " " + status +" " + name +" " + email +" " + type);
        return this.repository.findAndCountBy(page * page_size, page_size, status, name, email,
                type, user_id, category_id, hot, discount, price_from, price_to);
    }

    @Override
    public Integer countAllByCondition(String status, String name,
                                       String email, String type,
                                       String user_id, String category_id,
                                       String hot, String discount,
                                       String price_from, String price_to
                                       ) {
        return this.repository.countByCondition(status, name, email,  type, user_id,
                category_id, hot, discount, price_from, price_to);
    }

    @Override
    public Boolean create(Restaurant data, MultipartFile fileAvatar) {
        try{
            Path staticPath = Paths.get("static");
            Path imagePath = Paths.get("images");
            if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
                Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
            }
            Path image = CURRENT_FOLDER.resolve(staticPath)
                    .resolve(imagePath).resolve(Objects.requireNonNull(fileAvatar.getOriginalFilename()));

            if (!fileAvatar.isEmpty()) {
                try (OutputStream os = Files.newOutputStream(image)) {
                    os.write(fileAvatar.getBytes());
                    data.setLogo(fileAvatar.getOriginalFilename());
                }
            }
            data.setCreatedAt(new Date());
            data.setUpdatedAt(new Date());
            if(data.getType() == null) {
                data.setType("RESTAURANT");
            }
            this.repository.save(data);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Restaurant findById(Long id) {
        return this.repository.findById(id).get();
    }

    @Override
    public Boolean update(Restaurant data, MultipartFile fileAvatar) {
        try{
            Path staticPath = Paths.get("static");
            Path imagePath = Paths.get("images");

            System.out.println("FILE ==> : " +  fileAvatar);
            System.out.println("career ==> : " +  data);

            if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
                Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
            }
            Path image = CURRENT_FOLDER.resolve(staticPath)
                    .resolve(imagePath).resolve(Objects.requireNonNull(fileAvatar.getOriginalFilename()));
            System.out.println("IMG: " +  image);
            System.out.println("imagePath: " +  imagePath);
            System.out.println("==> FILE: " + Arrays.toString(fileAvatar.getBytes()));

            if (!fileAvatar.isEmpty()) {
                try (OutputStream os = Files.newOutputStream(image)) {
                    os.write(fileAvatar.getBytes());
                    data.setLogo(fileAvatar.getOriginalFilename());
                }
            }
            data.setUpdatedAt(new Date());

            this.repository.save(data);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean updateType(Restaurant data) {
        this.repository.save(data);
        return true;
    }

    @Override
    public Boolean delete(Long id) {
        try{
            this.repository.delete(findById(id));
            this.restaurantCategoryRepository.deleteByRestaurantId(id);

            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return  false;
    }
}
