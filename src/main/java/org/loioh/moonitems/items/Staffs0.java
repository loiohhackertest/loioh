package org.loioh.moonitems.items;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.loioh.moonitems.MoonItems;

import java.util.List;

import static java.lang.System.currentTimeMillis;

public class Staffs0 extends SItems0 {
    private int shoot_t;
    private double speed;
    private double damage;
    private List<String> f_command;
    public Staffs0(String id, ItemStack item, Boolean add_bm, int shoot_t, double speed, double damage,List<String> s_type) throws CustomItemException {
        super(id,item,false);
        this.shoot_t = shoot_t;
        this.speed = speed;
        this.damage = damage;
        this.f_command = s_type;
        if(add_bm) {
            MoonItems.IM.add(this);
        }
    }
    public Staffs0(String id, String name, Material material, int model, Boolean add_bm, int shoot_t, double speed, double damage,List<String> s_type) throws CustomItemException {
        super(id,name,material,model,false);
        this.shoot_t = shoot_t;
        this.speed = speed;
        this.damage = damage;
        this.f_command = s_type;
        if(add_bm) {
            MoonItems.IM.add(this);
        }
    }
    public Staffs0(String id, BaseComponent[] name, Material material, int model, Boolean add_bm, int shoot_t, double speed, double damage, List<String> s_type) throws CustomItemException {
        super(id,name,material,model,false);
        this.shoot_t = shoot_t;
        this.speed = speed;
        this.damage = damage;
        this.f_command = s_type;
        if(add_bm) {
            MoonItems.IM.add(this);
        }
    }
    //get
    public double get_speed() {
        return speed;
    }
    public double get_damage() {
        return damage;
    }
    //method

    @Override
    public ItemStack give_new_item(){
        ItemStack ti = get_item().clone();

        ItemMeta item_m = ti.getItemMeta();
        List<BaseComponent[]> lore = item_m.getLoreComponents();
        if(lore != null && !lore.isEmpty()) {
            if(lore.size() >1) {
                lore.remove(lore.size() - 1);
            }
            item_m.setLoreComponents(lore);
        }
        ti.setItemMeta(item_m);

        ItemsManager.set_item_id(ti,get_id());
        ItemsManager.set_uuid_item(ti);
        return ti;
    }

    @Override
    public ItemStack give_craft_item(){
        ItemStack ti = get_item().clone();

        ItemMeta item_m = ti.getItemMeta();
        List<BaseComponent[]> lore = item_m.getLoreComponents();
        if(lore != null && !lore.isEmpty()) {
            if(lore.size() >1) {
                lore.remove(lore.size() - 1);
            }
            item_m.setLoreComponents(lore);
        }
        ti.setItemMeta(item_m);

        ItemsManager.set_item_id(ti,get_id());
        return ti;
    }

    public Boolean not_reloading(Player p,ItemStack item,String uuid){
        if(uuid != null) {
            if (ItemsManager.Shoot.containsKey(uuid)) {
                long l = currentTimeMillis() - ItemsManager.Shoot.get(uuid);
                if (l < 0 || l > shoot_t) {
                    return true;
                }
            } else {
                return true;
            }
        }else if(item.getAmount() == 1){
            ItemsManager.set_uuid_item(item);
        }else{
            p.sendMessage("You must use 1 staff in hand for shoot");
        }
        return false;
    }
    public void PreShoot(Player p,ItemStack item) {
        String uuid = ItemsManager.get_uuid_item(item);
        if(uuid != null){
            if(not_reloading(p,item,uuid)){
                uuid = ItemsManager.get_uuid_item(item);
                Shoot(p, item,uuid);
            }
        }else{
            if(item.equals(p.getInventory().getItemInMainHand())) {
                ItemsManager.set_uuid_item(item);
                p.getInventory().setItemInMainHand(item);
            }else if(item.equals(p.getInventory().getItemInOffHand())) {
                ItemsManager.set_uuid_item(item);
                p.getInventory().setItemInOffHand(item);
            }
            PreShoot(p,item);
        }
    }
    public void Shoot(Player p,ItemStack item,String uuid){
        ItemsManager.Shoot.put(uuid,currentTimeMillis());

        ItemsManager.shoot(p,get_speed(),get_damage(),this,get_id());
    }
    public void f_command(Player p) {
        if(f_command != null) {
            for (String c : f_command) {
                if (c.contains("%player%")) {
                    c = c.replace("%player%", p.getName());
                }
                CommandSender s = Bukkit.getConsoleSender();
                //s = null;
                boolean completed = Bukkit.dispatchCommand(s, c);
                if (!completed) {
                    MoonItems.IM.msg("error at:/" + c);
                    break;
                }
            }
        }
    }

    //config
    public static String[] path(String key){
        String[] path_g0 = {
                String.join(".", "Staffs",key,"Item"),
                String.join(".", "Staffs",key,"Shoot_Cooldown"),
                String.join(".", "Staffs",key,"Speed"),
                String.join(".", "Staffs",key,"Damage"),
                String.join(".", "Staffs",key,"Commands_At_Shoot")
        };
        return path_g0;
    }

    public static void load_from_config(String key) throws CustomItemException {
        String[] paths = path(key);

        //ItemStack item = (ItemStack) MoonItems.items_c.get(paths[0]);
        //int shoot_t = (int) MoonItems.items_c.get(paths[1]);
        //double speed = (double) MoonItems.items_c.get(paths[2]);
        //double damage = (double) MoonItems.items_c.get(paths[3]);
        //List<String> s_type = (List<String>) MoonItems.items_c.getList(paths[4]);

        //new Staffs0(key,item,true,shoot_t,speed,damage,s_type);
    }
    @Override
    public void save_to_config() {
        String[] paths = path(get_id());
        //MoonItems.items_c.set(paths[0], get_item());
        //MoonItems.items_c.set(paths[1],shoot_t);
        //MoonItems.items_c.set(paths[2],speed);
        //MoonItems.items_c.set(paths[3],damage);
        //MoonItems.items_c.set(paths[4],f_command);

        //MoonItems.items_c.save();
    }
}
