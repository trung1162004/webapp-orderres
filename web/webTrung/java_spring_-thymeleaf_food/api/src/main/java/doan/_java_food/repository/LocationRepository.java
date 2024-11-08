package doan._java_food.repository;

import doan._java_food.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {

    Location getLocationsByName(String name);
}
