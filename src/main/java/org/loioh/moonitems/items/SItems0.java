package org.loioh.moonitems.items;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.loioh.moonitems.MoonItems;

public class SItems0 extends GItems {
    public SItems0(String id, ItemStack item, Boolean add_bm) throws CustomItemException {
        super(id, item, false);
        if(add_bm) {
            MoonItems.IM.add(this);
        }
    }
    public SItems0(String id, String name, Material material, int model, Boolean add_bm) throws CustomItemException {
        super(id,name,material,model,false);
        if(add_bm) {
            MoonItems.IM.add(this);
        }
    }

    public SItems0(String id, BaseComponent[] name, Material material, int model, Boolean add_bm) throws CustomItemException {
        super(id,name,material,model,false);
        if(add_bm) {
            MoonItems.IM.add(this);
        }
    }

    public static String[] path(String key){
        String[] path_g0 = {
                String.join(".", "SItems",key,"Item")
        };
        return path_g0;
    }

    public static void load_from_config(String key) throws CustomItemException {
        String[] paths = path(key);

        //ItemStack item = (ItemStack) MoonItems.items_c.get(paths[0]);

        //new SItems0(key,item,true);
    }
    @Override
    public void save_to_config() {
        String[] paths = path(get_id());
        //MoonItems.items_c.set(paths[0],get_item());
        //MoonItems.items_c.save();
    }
}
