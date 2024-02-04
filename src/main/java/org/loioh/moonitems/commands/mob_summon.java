package org.loioh.moonitems.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.loioh.moonitems.MoonItems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class mob_summon implements CommandExecutor, TabCompleter {
        static JavaPlugin plugin;
        public mob_summon(JavaPlugin plugin) {
            this.plugin = plugin;
        }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(!(sender instanceof Player)) return true;

        Player player = (Player) sender;


        if(command.getName().equalsIgnoreCase("mmob_summon") || command.getName().equalsIgnoreCase("mm_summon")) {
            //player.sendMessage(ChatColor.GREEN+"command start");
            if (player.isOp()) {
                if (args.length == 1) {
                    String i1 = args[0];
                    HashMap<String,String> mobs = MoonItems.IM.get_Mids();
                    if (mobs.containsKey(i1)) {
                        Entity mob = null;
                        if(i1.toLowerCase().contains("cow")){
                            mob = player.getWorld().spawnEntity(player.getLocation(), EntityType.COW);
                        }else if(i1.toLowerCase().contains("spider")){
                            mob = player.getWorld().spawnEntity(player.getLocation(), EntityType.SPIDER);
                        }
                        mob.setCustomName(mobs.get(i1));
                        mob.setCustomNameVisible(true);
                    }else{
                        player.sendMessage(ChatColor.RED + "you must send mob id (moon_cowhide or lunar_spider)");
                        return true;
                    }

                } else {
                    player.sendMessage(ChatColor.RED + "you must send item name (/mm_summon moon_cowhide)");
                }
            } else {
                player.sendMessage(ChatColor.RED + "you haven't permission");
                return true;
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete( CommandSender sender, Command command, String s, String[] args) {
        if(args.length == 1){
            List<String> l = new ArrayList<>();
            l.addAll(MoonItems.IM.get_Mids().keySet());
            return l;
        }
        return new ArrayList<>();
    }
}
