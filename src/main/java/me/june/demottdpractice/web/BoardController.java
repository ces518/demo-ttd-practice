package me.june.demottdpractice.web;

import me.june.demottdpractice.entity.Board;
import me.june.demottdpractice.service.BoardService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/boards")
    public ResponseEntity<List<Board>> list(
            Board board
    ){
        List<Board> list = boardService.findAll(new Board());
        return ResponseEntity.ok(list);
    }

    @PostMapping("/boards")
    public ResponseEntity<Board> create(
            Board board
    ){
        Board createdBoard = boardService.createBoard(board);

        if(createdBoard == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(createdBoard);
    }

    @GetMapping("/boards/{id}")
    public ResponseEntity<Board> getBoard(
            @PathVariable("id") Board board
    ){
        if(board.getSeq() == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(board);
    }

    @PutMapping("/boards/{id}")
    public ResponseEntity<Board> update(
            @PathVariable Long id
            ,Board board
    ){
        board.setSeq(id);
        if(board.getSeq() == null){
            return ResponseEntity.badRequest().build();
        }

        Board result = boardService.updateBoard(board);

        if(result.getSeq() == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result);
    }
}
