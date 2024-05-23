package com.monitoria.bedwars.game;

import com.monitoria.bedwars.elements.Team;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;

public class GameListener implements Listener {
    //Colocar limite de altura;
    //Colocar no modo Aventura quando entra e modo Survival quando inicia;
    //Lojinha e tals
    private final Game game;

    public GameListener(Game game) {
        this.game = game;
    }

    int playersJoined = 0;

    @EventHandler
    void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.teleport(new Location(player.getWorld(), game.lobbyX, game.lobbyY, game.lobbyZ));
        player.setGameMode(GameMode.ADVENTURE);

        playersJoined++;
        boolean added = false;
        for (int i = 0; i < game.teams.toArray().length; i++) {
            Team team = game.teams.get(i);
            if (team.player1 == null) {
                team.player1 = player;
                added = true;
                break;
            } else if (team.player2 == null) {
                team.player2 = player;
                added = true;
                break;
            } else if (team.player3 == null) {
                team.player3 = player;
                added = true;
                break;
            }
        }

        if (!added) {
            player.setGameMode(GameMode.SPECTATOR);
        }

        if (playersJoined >= 6) {
            game.iniciar();
        }
    }

    @EventHandler
    void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        for (Team team : game.teams) {
            for (Player teamPlayer : team.getPlayers()) {
                if (teamPlayer == player) {
                    if (team.isBedActive) {
                        team.teleportPlayerToSpawn(player);
                    } else {
                        player.setGameMode(GameMode.SPECTATOR);
                    }
                    break;
                }
            }
        }

        Team aliveTeam = null;
        int teamsAlives = 0;
        for (Team t : game.teams) {
            if (t.amountAlive() >= 1) {
                teamsAlives++;
                aliveTeam = t;
            }
        }
        if (teamsAlives == 1) {
            game.ganhar(aliveTeam);
        }
    }

    @EventHandler
    void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        for (Team team : game.teams) {
            if (block.getType() == team.getBedMaterial()) {
                team.isBedActive = false;
                event.setDropItems(false);
                Bukkit.broadcastMessage("A cama " + team.color.toString() " foi quebrada");
            }
        }
    }

}
