package blog.smongs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.smongs.model.Board;

public interface BoardRepository extends JpaRepository<Board,Integer>{
    
}