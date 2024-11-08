package doan._java_food.service.Storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    void init();

    void store(MultipartFile file);
    String storeImage(MultipartFile file);

//    Stream<Path> loadAll();
//
//    Path load(String filename);
//
//    Resource loadAsResource(String filename);
//
    void deleteAll();
}
