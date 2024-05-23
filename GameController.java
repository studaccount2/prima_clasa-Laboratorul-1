package ro.uaic.Lab11.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.quartz.QuartzTransactionManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.uaic.Lab11.entities.Game;
import ro.uaic.Lab11.entities.Player;
import ro.uaic.Lab11.services.GameService;

import java.util.List;

/**
 * Controller for game-related endpoints.
 */
@RestController
@RequestMapping("/api/v1/games")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityRequirement(name = "bearerAuth")
    @Operation(tags = {"Games"})
    public void createGame(String gameName) {
        gameService.createGame(gameName);
    }

    @PostMapping("/join")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(tags = {"Games"})
    public void joinGame(String gameName) {
        gameService.joinGame(gameName);
    }

    @GetMapping(produces = "application/json")
    @Operation(tags = {"Games"})
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/minimal-group")
    @Operation(tags = {"Games"})
    public List<Player> getMinimalGroup() {
        return gameService.getMinimalGroup();
    }
}
