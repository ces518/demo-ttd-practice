package me.june.demottdpractice.repository;

import me.june.demottdpractice.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
}
