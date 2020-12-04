package com.example.GameServerJava.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "players")
public class Players {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Players players;
}
