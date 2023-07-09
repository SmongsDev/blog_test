package blog.smongs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import blog.smongs.config.auth.PrincipalDetail;
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

    @Transactional(readOnly = true)
    public Page<Board> boardList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board boardDetail(int id){
        return boardRepository.findById(id)
        .orElseThrow(() ->{
            return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
        });
    }

    @Transactional
    public void boardDelete(int id, PrincipalDetail principal){
        Board board = boardRepository.findById(id)
        .orElseThrow(()->{
            return new IllegalArgumentException("글 찾기 실패 : 해당 글이 존재하지 않습니다.");
        });

        if(board.getUser().getId() != principal.getUser().getId()){
            throw new IllegalAccessError("글 삭제 실패 : 해당 글을 삭제할 권한이 없습니다.");
        }
        boardRepository.deleteById(id);
    }
}
