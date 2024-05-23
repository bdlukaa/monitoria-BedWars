package com.monitoria.bedwars.game;

import com.monitoria.bedwars.elements.ItemSpawner;
import com.monitoria.bedwars.elements.Team;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemType;

import java.util.ArrayList;
import java.util.List;

public class Game {

    List<Team> teams = new ArrayList<>();

    GameStatus status = GameStatus.iniciando;

    public int lobbyX = 0;
    public int lobbyZ = 0;
    public int lobbyY = 92;

    List<ItemSpawner> itemSpawnPoints = new ArrayList<>();

    public Game() {
        resetar();
    }

    public void iniciar() {
        if (status != GameStatus.iniciando) {
            System.out.println("Jogo já iniciado.");
            return;
        }
        Bukkit.broadcastMessage("Iniciando jogo!! Boa sorte");
        for (Team team : teams) {
            // Teletransportar jogares
            for (Player player : team.getPlayers()) {
                if (player != null) {
                    team.teleportPlayerToSpawn(player);
                    player.setGameMode(GameMode.SURVIVAL);
                }
            }

            // Começar o timer dos itemSpawners
            team.itemSpawner.startTimer();
        }

        for (ItemSpawner itemSpawner : itemSpawnPoints) {
            itemSpawner.startTimer();
        }
    }

    void finalizar() {

    }

    void ganhar(Team team) {
        Bukkit.broadcastMessage("Time " + team.color + " GANHOU!! PARABENS");
    }

    void resetar() {
        World world = Bukkit.getWorld("world");

        teams.clear();
        teams.add(new Team(Color.RED, -108, 52, -47, new ItemSpawner(new Location(world, -108, 50, -52), null)));
        teams.add(new Team(Color.PURPLE, -52, 52, -46, new ItemSpawner(new Location(world, -52, 50, -52), null)));
        teams.add(new Team(Color.BLACK, -106, 52, 47, new ItemSpawner(new Location(world, -106, 50, 54), null)));
        teams.add(new Team(Color.WHITE, 43, 52, 50, new ItemSpawner(new Location(world, 43, 50, 54), null)));
        teams.add(new Team(Color.BLUE, 99, 52, 50, new ItemSpawner(new Location(world, 99, 50, 54), null)));

        itemSpawnPoints.clear();
        itemSpawnPoints.add(new ItemSpawner(new Location(world, 69, 51, 0), Material.DIAMOND));
        itemSpawnPoints.add(new ItemSpawner(new Location(world, 127, 51, 1), Material.EMERALD));
        itemSpawnPoints.add(new ItemSpawner(new Location(world, 2, 45, -9), Material.EMERALD));
        itemSpawnPoints.add(new ItemSpawner(new Location(world, -12, 57, 10), Material.EMERALD));
        itemSpawnPoints.add(new ItemSpawner(new Location(world, -136, 51, 0), Material.EMERALD));
        itemSpawnPoints.add(new ItemSpawner(new Location(world, -78, 51, 1), Material.DIAMOND));
    }
}
