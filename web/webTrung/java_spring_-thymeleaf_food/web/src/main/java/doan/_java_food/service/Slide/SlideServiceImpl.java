package doan._java_food.service.Slide;

import doan._java_food.models.Slides;
import doan._java_food.repository.SlideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class SlideServiceImpl implements SlideService {

    @Autowired
    private SlideRepository repository;

    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));



    @Override
    public List<Slides> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Boolean create(Slides data, MultipartFile fileAvatar) {
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
                    data.setImage(fileAvatar.getOriginalFilename());
                }
            }
            this.repository.save(data);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Slides findById(Long id) {
        return this.repository.findById(id).get();
    }

    @Override
    public Boolean update(Slides data, MultipartFile fileAvatar) {
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
                    data.setImage(fileAvatar.getOriginalFilename());
                }
            }

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
