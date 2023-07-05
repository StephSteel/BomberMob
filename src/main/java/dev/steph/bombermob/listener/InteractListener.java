package dev.steph.bombermob.listener;

import dev.steph.bombermob.BomberMob;
import dev.steph.bombermob.mob.Bomber;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class InteractListener implements Listener {

    private final BomberMob plugin;

    public InteractListener(BomberMob plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(e.getHand() != null && e.getHand() == EquipmentSlot.HAND){
                if(e.getItem() != null && e.getItem().getItemMeta() != null
                && e.getItem().getItemMeta().getLore() != null &&
                e.getItem().getItemMeta().getLore().contains(ChatColor.GRAY + "Spawns a bomber mob")){
                    Location spawnLocation;
                    if(e.getClickedBlock().isPassable()){
                        spawnLocation = e.getClickedBlock().getLocation().add(0.5 , 0 , 0.5);
                    } else{
                        spawnLocation = e.getClickedBlock().getRelative(e.getBlockFace()).getLocation().add(0.5 , 0 , 0.5);
                    }
                    new Bomber(spawnLocation, plugin);
                    if(e.getPlayer().getGameMode() != GameMode.CREATIVE){
                        e.getItem().setAmount(e.getItem().getAmount() - 1);
                    }
                    e.setCancelled(true);
                }
            }
        }
    }

}
