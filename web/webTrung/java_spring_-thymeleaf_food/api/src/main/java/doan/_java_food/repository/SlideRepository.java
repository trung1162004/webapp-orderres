package doan._java_food.repository;

import doan._java_food.models.Slides;
import doan._java_food.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SlideRepository extends JpaRepository<Slides, Long> {

}
