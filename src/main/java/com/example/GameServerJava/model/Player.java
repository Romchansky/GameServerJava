package com.example.GameServerJava.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "players")
@Data
public class Player {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "")
    @SequenceGenerator(name = "lobby.players_id_seq", sequenceName = "lobby.players_id_seq", allocationSize = 1)
    private int id;

    @Column(nullable = false)
    private String email;

    @Column
    private String password;

    @Column
    private String nickName;

    @Column(name = "created_at")
    private LocalDate creationDate;


}
