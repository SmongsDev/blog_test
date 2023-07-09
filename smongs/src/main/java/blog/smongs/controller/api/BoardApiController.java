package blog.smongs.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import blog.smongs.config.auth.PrincipalDetail;
import blog.smongs.dto.ResponseDto;
import blog.smongs.model.Board;
import blog.smongs.service.BoardService;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;
    
    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
        boardService.write(board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
    
    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id, @AuthenticationPrincipal PrincipalDetail principal){
        boardService.boardDelete(id, principal);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
