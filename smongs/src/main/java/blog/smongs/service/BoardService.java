package blog.smongs.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import blog.smongs.config.auth.PrincipalDetail;
import blog.smongs.dto.ReplySaveRequestDto;
import blog.smongs.model.Board;
import blog.smongs.model.Reply;
import blog.smongs.model.User;
import blog.smongs.repository.BoardRepository;
import blog.smongs.repository.ReplyRepository;
import blog.smongs.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

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

    @Transactional
    public void boardUpdate(int id, Board requestBoard){
        Board board = boardRepository.findById(id)
            .orElseThrow(()->{
                return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
            });
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
    }

    // 댓글

    @Transactional
    public Reply replyDetail(int id){
        return replyRepository.findById(id)
            .orElseThrow(()->{
                return new IllegalArgumentException("댓글 찾기 실패 ");
            });
    } 

    @Transactional
    public void replyWrite(ReplySaveRequestDto replySaveRequestDto){

        User user = userRepository.findById(replySaveRequestDto.getUserId())
            .orElseThrow(()->{
                return new IllegalArgumentException("댓글 쓰기 실패 : 사용자 Id를 찾을 수 없습니다.");
            });

        Board board = boardRepository.findById(replySaveRequestDto.getBoardId())
            .orElseThrow(()->{
                return new IllegalArgumentException("댓글 쓰기 실패 : 게시글 id를 찾을 수 없습니다.");
            });

        Reply reply = new Reply();
        reply.update(user, board, replySaveRequestDto.getContent());

        replyRepository.save(reply);

        // replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
        // System.out.println("작성 댓글 개수 : " + result);
    }

    @Transactional
    public void replyDelete(int replyId){
        replyRepository.deleteById(replyId);
    }
}
