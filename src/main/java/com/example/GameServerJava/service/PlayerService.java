package com.example.GameServerJava.service;


import com.example.GameServerJava.entity.PlayerEntity;
import com.example.GameServerJava.model.Player;
import com.example.GameServerJava.repository.PlayerRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
@Data
public class PlayerService {

    public final PlayerRepository playerRepository;


    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayerByEmail(String email) {
        return playerRepository.findByEmail(email);
    }

    public Player getPlayerById(Integer id) {
        return playerRepository.findById(id).orElse(null);
    }

    public Player saveNewPlayer(PlayerEntity player) {
        Player newPlayer = new Player();
        newPlayer.setEmail(player.getEmail());
        newPlayer.setPassword(player.getPassword());
        newPlayer.setNickName(player.getNickName());
        newPlayer.setCreationDate(LocalDate.now());
        return playerRepository.save(newPlayer);
    }

    public Player updatePlayer(Integer id, PlayerEntity updatePlayer) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Player not found in Database"));
        player.setEmail(updatePlayer.getEmail());
        player.setPassword(updatePlayer.getPassword());
        player.setNickName(updatePlayer.getNickName());
        return playerRepository.save(player);
    }

    public void deletePlayer(Integer id) {
        playerRepository.deleteById(id);
    }
}
