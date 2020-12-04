package com.example.GameServerJava.controller;

import com.example.GameServerJava.entity.PlayerEntity;
import com.example.GameServerJava.entity.Players;
import com.example.GameServerJava.model.Player;
import com.example.GameServerJava.repository.PlayerRepository;
import com.example.GameServerJava.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;
    private final PlayerRepository playerRepository;

    @GetMapping
    public ResponseEntity<List<Player>> getPlayers() {
        return new ResponseEntity<>(playerService.getPlayers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable(value = "id") Integer id) {
        Player player = playerService.getPlayerById(id);
        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody PlayerEntity player) {
        return new ResponseEntity<>(playerService.saveNewPlayer(player), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@RequestBody PlayerEntity player,
                                               @PathVariable(value = "id") Integer id) {
        return new ResponseEntity<>(playerService.updatePlayer(id, player), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deletePlayer(@PathVariable(value = "id") Integer id) {
        playerService.deletePlayer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/new")
    public String showSignUpForm(Model model) {
        model.addAttribute("player", new Player());
        return "newPlayer";
    }

    @PostMapping ("/add")
    public String addPlayer(@Valid Player player,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "newPlayer";
        }
        playerRepository.save(player);
        Iterable<Player> players = playerRepository.findAll();
        model.addAttribute("players", players);
        return "lobby";
    }
}
