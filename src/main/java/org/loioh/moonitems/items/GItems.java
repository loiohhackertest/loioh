package org.loioh.moonitems.items;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.loioh.moonitems.Event;
import org.loioh.moonitems.MoonItems;

abstract class GItems {
    private ItemStack item;
    private String id;
    //set
    public GItems(String id, ItemStack item, Boolean add_bm) throws CustomItemException {
        if (Event.isItem(item) && id != null) {
            this.item = item;
            this.id = id;
            if(add_bm) {
                MoonItems.IM.add(this);
            }
        }else{
            throw new CustomItemException("CustomItemAdd Error!!!");
        }
    }
    public GItems(String id, String name,Material material, int model, Boolean add_bm) throws CustomItemException {
        if(id != null && material != null && material != Material.AIR) {
            ItemStack item0 = new ItemStack(material);
            ItemMeta im = item0.getItemMeta();
            if(model != -1) {
                im.setCustomModelData(model);
            }
            if(name != null) {
                im.setDisplayName(name);
            }
            item0.setItemMeta(im);
            this.item = item0;
            this.id = id;
            if(add_bm) {
                MoonItems.IM.add(this);
            }
        }else{
            throw new CustomItemException("CustomBlockAdd Error!!!");
        }
    }
    public GItems(String id, BaseComponent[] name, Material material, int model, Boolean add_bm) throws CustomItemException {
        if(id != null && material != null && material != Material.AIR) {
            ItemStack item0 = new ItemStack(material);
            ItemMeta im = item0.getItemMeta();
            if(model != -1) {
                im.setCustomModelData(model);
            }
            if(name != null) {
                im.setDisplayNameComponent(name);
            }
            item0.setItemMeta(im);
            this.item = item0;
            this.id = id;
            if(add_bm) {
                MoonItems.IM.add(this);
            }
        }else{
            throw new CustomItemException("CustomBlockAdd Error!!!");
        }
    }

    //get
    public int get_model(){
        if(Event.isItem(item)){
            if(item.hasItemMeta() && item.getItemMeta().hasCustomModelData()){
                return item.getItemMeta().getCustomModelData();
            }
        }
        return -1;
    }
    public String get_name(){
        if(Event.isItem(item)){
            if(item.hasItemMeta() && item.getItemMeta().hasDisplayName()){
                return item.getItemMeta().getDisplayName();
            }
        }
        return null;
    }
    public String get_id(){
        return id;
    }
    public Material get_itype(){
        if(Event.isItem(item)){
            return item.getType();
        }
        return null;
    }
    public ItemStack get_item(){
        if(Event.isItem(item)){
            return item.clone();
        }
        return null;
    }
    //get 2
    public ItemStack give_new_item() {
        ItemStack a = get_item().clone();
        ItemsManager.set_item_id(a,get_id());
        return a;
    }
    public ItemStack give_craft_item(){
        ItemStack a = get_item();
        ItemsManager.set_item_id(a,get_id());
        return a;
    }


    public static String[] path(String key){
        String[] path_g0 = {
                String.join(".", "GItems",key,"Item")
        };
        return path_g0;
    }

    public static void load_from_config(String key) throws CustomItemException {
        String[] paths = path(key);

        //ItemStack item = (ItemStack) MoonItems.items_c.get(paths[0]);

        //new GItems(key,item,true);
    }
    public void save_to_config() {
        String[] paths = path(id);
        //MoonItems.items_c.set(paths[0],get_item());
        //MoonItems.items_c.save();
    }

    //methods

}
