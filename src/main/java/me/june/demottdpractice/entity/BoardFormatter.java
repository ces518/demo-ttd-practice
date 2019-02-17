package me.june.demottdpractice.entity;

import ch.qos.logback.classic.pattern.MessageConverter;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

public class BoardFormatter implements Formatter<Board> {

    @Override
    public Board parse(String s, Locale locale) throws ParseException {
        Board board = new Board();
        board.setSeq(Long.parseLong(s));
        return board;
    }

    @Override
    public String print(Board board, Locale locale) {
        return null;
    }
}
