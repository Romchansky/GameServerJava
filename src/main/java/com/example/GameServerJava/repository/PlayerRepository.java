package com.example.GameServerJava.repository;

import com.example.GameServerJava.model.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {


    List<Player> findAll();

    Player findByEmail(String email);

    @Query("select p from Player p where p.creationDate between :startDate and :endDate")
    Stream<Player> findUsersByPeriod(@Param("startDate") LocalDate a1, @Param("endDate") LocalDate a2);

}
