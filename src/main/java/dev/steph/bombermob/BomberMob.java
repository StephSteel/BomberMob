package dev.steph.bombermob;

import dev.steph.bombermob.command.BomberSpawnEggCommand;
import dev.steph.bombermob.listener.InteractListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class BomberMob extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("givebomberspawnegg").setExecutor(new BomberSpawnEggCommand());

        Bukkit.getPluginManager().registerEvents(new InteractListener(this),this);
    }

}
