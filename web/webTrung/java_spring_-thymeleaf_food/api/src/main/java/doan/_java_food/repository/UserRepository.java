package doan._java_food.repository;

import doan._java_food.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Integer countUserByType(String type);

    List<User> findUserByType(String type);

    @Query(value = "SELECT * FROM users u where u.email = :email", nativeQuery = true)
    User findByUserEmail(@Param("email") String email);

//    List<User> findAllByTypeAndStatus(String type, Integer status);


    Optional<User> findByEmail(String username);
    User findUserByAccessToken(String accessToken);



    @Query(value = "Select u.* " +
            " from users u " +

            " WHERE TRUE " +
            " AND (:name is null or :name = '' or u.name LIKE %:name%) " +
            " AND (:email is null or :email = '' or u.email LIKE %:email%) " +
            " AND (:status is null or :status = '' or u.status= :status) " +
            " AND (:user_type is null or :user_type = '' or u.type= :user_type) " +
            " AND (:username is null or :username = '' or u.username= :username) " +

            " ORDER BY u.id desc LIMIT :page_size OFFSET :page",nativeQuery = true)
    List<User> findAndCountApi(@Param("page") int page,
                            @Param("page_size")int page_size,
                            @Param("status") String status,
                            @Param("name") String name,
                            @Param("email") String email,
                            @Param("user_type") String user_type,
                               @Param("username") String username

    );

    Optional<User> findByUsername(String username);



    @Query(value = "Select count(*) from users u " +
            " WHERE TRUE  " +
            " AND (:name is null or :name = '' or u.name LIKE %:name%) " +
            " AND (:email is null or :email = '' or u.email LIKE %:email%) " +
            " AND (:status is null or :status = '' or u.status= :status) " +
            " AND (:username is null or :username = '' or u.username LIKE %:username%) " +
            " AND (:user_type is null or :user_type = '' or u.type= :user_type) ",nativeQuery = true)
    Integer countByConditionsApi(
            @Param("status") String status,
            @Param("name") String name,
            @Param("email") String email,
            @Param("user_type") String user_type,
            @Param("username") String username
    );
}
