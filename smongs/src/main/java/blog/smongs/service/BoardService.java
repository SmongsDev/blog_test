package blog.smongs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import blog.smongs.model.Board;
import blog.smongs.model.User;
import blog.smongs.repository.BoardRepository;

@Service
public class BoardService {
    
    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void write(Board board, User user){
        board.setCout(0);
        board.setUser(user);
        boardRepository.save(board);
    }
}
