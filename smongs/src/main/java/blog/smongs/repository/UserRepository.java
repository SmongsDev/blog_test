package blog.smongs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.smongs.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
    
}
