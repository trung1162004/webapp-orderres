package doan._java_food.repository;

import doan._java_food.models.Blog;
import doan._java_food.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {

    Location getLocationsByName(String name);
}
