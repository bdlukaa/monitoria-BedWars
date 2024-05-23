package com.monitoria.bedwars.elements;

import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Team {

    public Player player1;
    public Player player2;
    public Player player3;

    public Player[] getPlayers() {
        return new Player[] {player1, player2, player3};
    }

    // Spawn points
    public int x;
    public int y;
    public int z;

    public Color color;

    public boolean isBedActive = true;

    public ItemSpawner itemSpawner;

    public Team(Color color, int x, int y, int z, ItemSpawner itemSpawner) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.z = z;
        this.itemSpawner = itemSpawner;
    }

    public void teleportPlayerToSpawn(Player player) {
        if (player != null) player.teleport(new Location(player.getWorld(), x, y, z));
    }

    // Ver a quantidade de jogadores do time vivos
    public int amountAlive() {
        int alive = 0;

        for (Player player : getPlayers()) {
            if (player != null && player.getGameMode() == GameMode.SURVIVAL) alive++;
        }

        return alive;
    }

    public Material getBedMaterial() {
        if (color.equals(Color.RED)) {
            return Material.RED_BED;
        } else if (color.equals(Color.WHITE)) {
            return Material.WHITE_BED;
        } else if (color.equals(Color.BLACK)) {
            return Material.BLACK_BED;
        } else if (color.equals(Color.BLUE)) {
            return Material.BLUE_BED;
        } else if (color.equals(Color.PURPLE)) {
            return Material.PURPLE_BED;
        }
        return Material.RED_BED;
    }

}
