package doan._java_food.service.Storage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileSystemStorageService implements StorageService{

    private final Path rootLocation;

    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

    public FileSystemStorageService() {
        this.rootLocation = Paths.get("src/main/resources/static/uploads");
    }
    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {

        }
    }

    @Override
    public void store(MultipartFile file) {
        try {
            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String storeImage(MultipartFile file) {
        try {
            Path staticPath = Paths.get("static");
            Path imagePath = Paths.get("images");
            if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
                Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
            }
            Path image = CURRENT_FOLDER.resolve(staticPath)
                    .resolve(imagePath).resolve(Objects.requireNonNull(file.getOriginalFilename()));

            if (!file.isEmpty()) {
                try (OutputStream os = Files.newOutputStream(image)) {
                    os.write(file.getBytes());
                    return file.getOriginalFilename();
                }
            }
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
