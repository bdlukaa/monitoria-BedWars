package com.monitoria.bedwars;

import com.monitoria.bedwars.commands.GameStart;
import com.monitoria.bedwars.game.Game;
import com.monitoria.bedwars.game.GameListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;



public final class BedWars extends JavaPlugin {

    public static Game game;
    public static BedWars instance;

    @Override
    public void onEnable() {
        Bukkit.getLogger().info(ChatColor.GREEN + "Enabled " + this.getName());

        instance = this;
        game = new Game();
        getServer().getPluginManager().registerEvents(new GameListener(game), this);

        this.getCommand("bedwars_start").setExecutor(new GameStart());
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(ChatColor.RED + "Disabled " + this.getName());
    }
}
