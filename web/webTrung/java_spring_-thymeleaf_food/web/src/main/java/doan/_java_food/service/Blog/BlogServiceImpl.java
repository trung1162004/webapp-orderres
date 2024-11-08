package doan._java_food.service.Blog;

import doan._java_food.models.Blog;
import doan._java_food.models.Category;
import doan._java_food.repository.BlogRepository;
import doan._java_food.repository.CategoryRepository;
import doan._java_food.service.Category.CategoryService;
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
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository repository;

    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));



    @Override
    public List<Blog> getAll() {
        return this.repository.findAll();
    }

    @Override
    public List<Blog> findAndCount(int page, int page_size, String status, String name,
                                   String author, String hot) {
        return this.repository.findAndCountBy(
                page * page_size, page_size,
                status, name, author,hot);
    }

    @Override
    public Integer countByCondition(String status, String name, String author, String hot) {
       return this.repository.countByCondition(status, name, author,hot);
    }

    @Override
    public Boolean create(Blog data, MultipartFile fileAvatar) {
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
                    data.setAvatar(fileAvatar.getOriginalFilename());
                }
            }
            data.setCreatedAt(new Date());
            data.setUpdatedAt(new Date());
            this.repository.save(data);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Blog findById(Long id) {
        return this.repository.findById(id).get();
    }

    @Override
    public Boolean update(Blog data, MultipartFile fileAvatar) {
        try{
            Path staticPath = Paths.get("static");
            Path imagePath = Paths.get("images");

            System.out.println("FILE ==> : " +  fileAvatar);

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
                    data.setAvatar(fileAvatar.getOriginalFilename());
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
    public Boolean delete(Long id) {
        try{
            this.repository.delete(findById(id));
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return  false;
    }
}
