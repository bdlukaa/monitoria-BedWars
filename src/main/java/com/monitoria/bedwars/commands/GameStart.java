package com.monitoria.bedwars.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.monitoria.bedwars.BedWars;

public class GameStart implements CommandExecutor {

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        BedWars.game.iniciar();
        return true;
    }
}
