package blog.smongs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.smongs.model.User;

// @Repository 생략 가능, 자동 Bean 등록
public interface UserRepository extends JpaRepository<User,Integer>{
    Optional<User> findByUsername(String username);
}

    // JPA Naming 쿼리 전략
    // select * from user where username = ?1 and password = ?2;
    // User findByUsernameAndPassword(String username, String password);