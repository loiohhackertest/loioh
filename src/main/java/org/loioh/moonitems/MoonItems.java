package org.loioh.moonitems;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.loioh.moonitems.commands.mob_summon;
import org.loioh.moonitems.items.*;
import org.loioh.moonitems.commands.item_get;
import org.bukkit.plugin.java.JavaPlugin;

import static java.lang.System.currentTimeMillis;

public final class MoonItems extends JavaPlugin {
    public static ItemsManager IM;
    public static PotionEffect nv = new PotionEffect(PotionEffectType.NIGHT_VISION,20,2);
    public static PotionEffect sp = new PotionEffect(PotionEffectType.SPEED,9,0);
    public static PotionEffect jp = new PotionEffect(PotionEffectType.JUMP,9,1);
    @Override
    public void onEnable() {
        if(!(getDescription().getAuthors().contains(item_get.author) && getDescription().getWebsite().equals(ItemsManager.site))){
            getServer().getPluginManager().disablePlugin(this);
        }



        //getLogger().warning("author: "+item_get.author);
        //getLogger().warning("website: "+ItemsManager.site);
        Bukkit.broadcast(new BaseComponent[]{_Init.hl_text(ChatColor.YELLOW+"[MoonItems]: Welcome","Author: "+item_get.author+"\n"+"click to watch website.",ItemsManager.site)});

        try {
            init();
        }catch(CustomItemException e){
            getServer().getPluginManager().disablePlugin(this);
        }


        getServer().getPluginManager().registerEvents(new Event(this),this);
        item_get cig = new item_get(this);
        mob_summon cms = new mob_summon(this);

        getServer().getPluginCommand("mitem_get").setExecutor(cig);
        getServer().getPluginCommand("mitem_get").setTabCompleter(cig);
        getServer().getPluginCommand("mmob_summon").setExecutor(cms);
        getServer().getPluginCommand("mmob_summon").setTabCompleter(cms);
        item_get.init();

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            loop();
        }, 0L,2L);
    }
    private void init() throws CustomItemException {
        IM = new ItemsManager(this);

        _Init.init();
    }
    private void loop(){
        //time resets
        if(!ItemsManager.Shoot.keySet().isEmpty()) {
            for (String uuid : ItemsManager.Shoot.keySet()) {
                if (uuid != null) {
                    long l = currentTimeMillis() - ItemsManager.Shoot.get(uuid);
                    if (l < 0 || l > (60*1000)) {
                        ItemsManager.Shoot.remove(uuid);
                    }
                }
            }
        }
        for(Player p:Bukkit.getOnlinePlayers()){
            if(p.getOpenInventory() != null && p.getOpenInventory().getType() == InventoryType.WORKBENCH){
                Inventory wb = p.getOpenInventory().getTopInventory();
                //WoInventory i = (AnvilInventory) wb;
                ItemStack[] wbc = wb.getContents();
                Boolean able = true;
                for(int i=1;i< wbc.length;i++) {
                    if (!(Event.isItem(wbc[i]) && wbc[i].getType().equals(Material.END_STONE) &&  wbc[i].getAmount() >2)) {
                        able = false;
                        break;
                    }
                }
                if(able){
                    ItemStack r = wbc[0];
                    //IM.msg("Alast: "+r);
                    if(!Event.isItem(r)){
                        ItemStack result1 = ((SItems0)MoonItems.IM.find_id("compacted_end_stone")).give_craft_item();
                        p.getOpenInventory().getTopInventory().setItem(0,result1);
                    }else{
                        String ci = ItemsManager.get_item_id(r);
                        if(!(ci!= null && ci.contains("compacted_end_stone"))){
                            ItemStack result1 = ((SItems0)MoonItems.IM.find_id("compacted_end_stone")).give_craft_item();
                            p.getOpenInventory().getTopInventory().setItem(0,result1);
                        }
                    }
                }else{
                    ItemStack r = wbc[0];
                    //IM.msg("Nlast: "+r);
                    if(Event.isItem(r)){
                        String ci = ItemsManager.get_item_id(r);
                        if(ci!= null && ci.contains("compacted_end_stone")){
                            p.getOpenInventory().getTopInventory().setItem(0,null);
                        }
                    }
                }
            }
            if(ItemsManager.Wear(p,"celestial_guardian_helmet")){
                p.addPotionEffect(nv);
            }
            if(ItemsManager.Wear(p,"celestial_guardian_leggings")){
                long t = p.getWorld().getTime();
                if(t>13000 && t<23000) {
                    p.addPotionEffect(sp);
                }
            }
            if(ItemsManager.Wear(p,"celestial_guardian_boots")){
                long t = p.getWorld().getTime();
                if(t>13000 && t<23000) {
                    Boolean isClear = (p.getWorld().isClearWeather());
                    Boolean isMoon = ((p.getWorld().getFullTime()/24000)%8)!=0;

                    //IM.msg("night");
                    //IM.msg("clear: "+isClear);
                    //IM.msg("moon: "+isMoon);
                    if(isMoon && isClear) {
                        p.addPotionEffect(jp);
                    }
                }
            }
        }
    }
}
