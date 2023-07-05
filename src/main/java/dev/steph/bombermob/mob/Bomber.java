package dev.steph.bombermob.mob;

import dev.steph.bombermob.BomberMob;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Bomber {

    public Bomber(Location location, BomberMob plugin){
        Zombie zombie = (Zombie) location.getWorld().spawnEntity(location, EntityType.ZOMBIE);

        zombie.setCustomName(ChatColor.RED + "Bomber");
        zombie.setCustomNameVisible(true);
        zombie.setBaby();
        zombie.getEquipment().setItemInMainHand(new ItemStack(Material.FLINT_AND_STEEL, 1));
        zombie.getEquipment().setHelmet(new ItemStack(Material.TNT,1 ));
        Attributable zombieAt = zombie;
        AttributeInstance attribute = zombieAt.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        attribute.setBaseValue(100);
        zombie.setHealth(100);

        new BukkitRunnable(){
            @Override
            public void run() {
                if(zombie.isDead()){
                    return;
                }

                if(zombie.getTarget() == null){
                    for(Entity entity : zombie.getNearbyEntities(10,10,10)){
                        if(entity instanceof Player){
                            zombie.setTarget((Player) entity);
                        }
                    }
                }

                zombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED , 120, 2));
                zombie.getLocation().getWorld().spawnEntity(zombie.getLocation(), EntityType.PRIMED_TNT);
            }
        }.runTaskTimer(plugin, 100L, 100L);


    }

}
