package ro.uaic.Lab11.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.Lab11.dtos.AuthenticationResponse;
import ro.uaic.Lab11.entities.Player;
import ro.uaic.Lab11.services.PlayerService;

import java.util.List;

/**
 * Controller for player-related endpoints.
 */
@RestController
@RequestMapping("/api/v1/players")
@RequiredArgsConstructor
@Log4j2
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping
    @Operation(tags = {"Players"})
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(tags = {"Players"})
    public ResponseEntity<AuthenticationResponse> addPlayer(@RequestParam("name") String name) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.addPlayer(name));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @SecurityRequirement(name = "bearerAuth")
    @Operation(tags = {"Players"})
    public void updatePlayer(@RequestParam("newName") String newName) {
        playerService.updatePlayer(newName);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @SecurityRequirement(name = "bearerAuth")
    @Operation(tags = {"Players"})
    public void deletePlayer() {
        playerService.deletePlayer();
    }

}
