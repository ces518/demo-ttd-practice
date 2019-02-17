package me.june.demottdpractice.service;

import me.june.demottdpractice.entity.Board;
import me.june.demottdpractice.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> findAll(Board board) {
        return boardRepository.findAll();
    }

    @Transactional
    public Board createBoard(Board board) {
        Board savedBoard = boardRepository.save(board);
        return savedBoard;
    }

    public Board findBoard(Long id) {
        return boardRepository.findById(id).orElse(new Board());
    }

    @Transactional
    public Board updateBoard(Board board){

        Board searchedBoard = boardRepository.findById(board.getSeq()).orElse(new Board());
        searchedBoard.setTitle(board.getTitle());
        searchedBoard.setContent(board.getContent());
        searchedBoard.setWriter(board.getWriter());
        return searchedBoard;
    }
}
