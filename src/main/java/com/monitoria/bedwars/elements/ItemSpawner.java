package com.monitoria.bedwars.elements;

import com.monitoria.bedwars.BedWars;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ItemType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Timer;
import java.util.TimerTask;

public class ItemSpawner {

    public Location location;

    public Material itemType;

    public ItemSpawner(Location location, Material itemType) {
        this.location = location;
        this.itemType = itemType;
    }

    public void startTimer() {
        new BukkitRunnable() {
            @Override
            public void run() {
                spawn();
            }
        }.runTaskTimer(BedWars.instance, 0, 1000);
    }


    int switcher = 0;

    public void spawn() {
        if (itemType == null) {
            if (switcher % 2 == 0) {
                location.getWorld().dropItem(location, new ItemStack(Material.IRON_INGOT, 3));
            } else {
                location.getWorld().dropItem(location, new ItemStack(Material.GOLD_INGOT, 3));
            }
            switcher++;
            System.out.println("Spawnou item");
        } else {
            location.getWorld().dropItem(location, new ItemStack(itemType));
        }
    }

}
