package org.loioh.moonitems.items;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.loioh.moonitems.Event;
import org.loioh.moonitems.MoonItems;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.currentTimeMillis;

public class ItemsManager {
    public static String[] armor = {
            "celestial_guardian_helmet",
            "celestial_guardian_chestplate",
            "celestial_guardian_leggings",
            "celestial_guardian_boots"
    };
    public static List<String> armor_set = Arrays.stream(armor).collect(Collectors.toList());





    public static HashMap<String, Long> Shoot = new HashMap<>();
    static JavaPlugin plugin;
    private static List<? super GItems> items;

    public ItemsManager(JavaPlugin plugin) {
        this.plugin = plugin;
        items = new ArrayList<>();
    }

    public void msg(String m) {
        plugin.getLogger().warning(m);
    }

    public void add(GItems item) {
        items.add(item);
        //item.save_to_config();
    }

    public void remove(GItems item) {
        if (items.contains(item)) {
            items.remove(item);
        }
    }

    public GItems find_i(int model) {
        for (Object b : items) {
            if (b instanceof GItems) {
                GItems i0 = (GItems) b;
                if (i0.get_model() == model) {
                    return i0;
                }
            }
        }
        return null;
    }

    public GItems find_i(String name) {
        for (Object i : items) {
            if (i instanceof GItems) {
                GItems i0 = (GItems) i;
                if (i0.get_name().equals(name)) {
                    return i0;
                }
            }
        }
        return null;
    }

    public GItems find_id(String id) {
        if(id == null){
            return null;
        }
        for (Object i : items) {
            if (i instanceof GItems) {
                GItems i0 = (GItems) i;
                if (i0.get_id().equals(id)) {
                    return i0;
                }
            }
        }
        return null;
    }

    public List<String> get_ids() {
        List<String> ids = new ArrayList<>();
        for (Object i : items) {
            GItems i0 = (GItems) i;
            ids.add(i0.get_id());
        }
        return ids;
    }
    public HashMap<String,String> get_Mids() {
        HashMap<String,String> ids = new HashMap<>();
        ids.put("lunar_spider",ChatColor.WHITE + "Lunar Spider");

        ids.put("moon_cowhide",ChatColor.WHITE + "Moon Cowhide");
        return ids;
    }

    public GItems find_i(ItemStack item) {
        ItemStack a = item.clone();
        for (Object b : items) {
            if (b instanceof GItems) {
                GItems b0 = (GItems) b;
                a.setAmount(b0.get_item().getAmount());
                if (b0.get_item().equals(a) || b0.get_item() == a) {
                    return b0;
                }
            }
        }
        return null;
    }
    public static final String site="https://www.fiverr.com/s/3j4g7m";
    public static void set_item_id(ItemStack item, String custom_id) {
        if (Event.isItem(item)){
            ItemMeta meta = item.getItemMeta();
            assert meta != null;
            meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "custom_id"), PersistentDataType.STRING, custom_id);
            item.setItemMeta(meta);
        }
    }
    public static String get_item_id(ItemStack i_item) {
        if (Event.isItem(i_item) && i_item.hasItemMeta()) {
            if(i_item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, "custom_id"))) {
                String id = i_item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "custom_id"), PersistentDataType.STRING);
                return id;
            }
        }
        return null;
    }

    //guns
    public static String get_uuid_item(ItemStack i_item) {
        if (Event.isItem(i_item) && i_item.hasItemMeta()) {
            String uuid = i_item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "UUID"), PersistentDataType.STRING);
            return uuid;
        }
        return null;
    }
    public static UUID get_uuid_item_U(ItemStack i_item) {
        if (Event.isItem(i_item) && i_item.hasItemMeta()) {
            return UUID.fromString(i_item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "UUID"), PersistentDataType.STRING));
        }
        return null;
    }
    public static void set_uuid_item(ItemStack i_item) {
        ItemMeta meta = i_item.getItemMeta();
        UUID uuid = UUID.randomUUID();
        assert meta != null;
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "UUID"), PersistentDataType.STRING, uuid.toString());
        i_item.setItemMeta(meta);
    }
    public static ItemStack create_armor(BaseComponent[] name,Material material, int model,Attribute at,EquipmentSlot slot,double armor) {
        if (material != null) {
            ItemStack item0 = new ItemStack(material);
            ItemMeta im = item0.getItemMeta();
            if (model != -1) {
                im.setCustomModelData(model);
            }
            if (name != null) {
                im.setDisplayNameComponent(name);
            }
            if(armor > 0) {
                AttributeModifier aas = new AttributeModifier(UUID.randomUUID(), "generic.Armor1", armor, AttributeModifier.Operation.ADD_NUMBER, slot);
                im.addAttributeModifier(at, aas);
            }

            item0.setItemMeta(im);
            return item0;
        }
        return null;
    }
    public static ItemStack create_armor(BaseComponent[] name,Material material, int model,Attribute at,EquipmentSlot slot,double armor,Attribute at2,EquipmentSlot slot2,double armor2) {
        if (material != null) {
            ItemStack item0 = new ItemStack(material);
            ItemMeta im = item0.getItemMeta();
            if (model != -1) {
                im.setCustomModelData(model);
            }
            if (name != null) {
                im.setDisplayNameComponent(name);
            }
            if(armor > 0) {
                AttributeModifier aas = new AttributeModifier(UUID.randomUUID(), "generic.Armor1", armor, AttributeModifier.Operation.ADD_NUMBER, slot);
                im.addAttributeModifier(at, aas);
            }
            if(armor2 > 0) {
                AttributeModifier aas = new AttributeModifier(UUID.randomUUID(), "generic.Armor2", armor2, AttributeModifier.Operation.ADD_NUMBER, slot2);
                im.addAttributeModifier(at2, aas);
            }

            item0.setItemMeta(im);
            return item0;
        }
        return null;
    }
    public static ItemStack create_item(BaseComponent[] name, Material material, int model, List<BaseComponent[]> lore) {
        if (material != null) {
            ItemStack item0 = new ItemStack(material);
            ItemMeta im = item0.getItemMeta();
            if (model != -1) {
                im.setCustomModelData(model);
            }
            if (name != null) {
                im.setDisplayNameComponent(name);
            }
            if(lore !=null){
                im.setLoreComponents(lore);
            }
            item0.setItemMeta(im);
            return item0;
        }
        return null;
    }
    public static void shoot(Player player1,double speed,double damage,Staffs0 gun,String g_id) {
        //MoonItems.IM.msg("projectile ");
        Location eye = player1.getEyeLocation();
        Vector direct = eye.getDirection();

        Arrow arrow = player1.getWorld().spawnArrow(eye, direct, (float) (speed), 0);
        double d =damage;
        if(CGComplect(player1)) {
            d = (d*1.5);
        }
        arrow.setMetadata("Damage", new FixedMetadataValue(plugin, d));
        arrow.setMetadata("Staff_ID", new FixedMetadataValue(plugin, g_id));
        arrow.setShooter(player1);

        if(gun != null) {
            gun.f_command(player1);
        }
    }
    public static Boolean CGComplect(Player p) {
        ItemStack[] Armor = p.getInventory().getArmorContents();
        int s =0;
        for(ItemStack i: Armor) {
            String ci = get_item_id(i);
            if (ci!=null && armor_set.contains(ci)){
                s+=1;
            }
        }
        //MoonItems.IM.msg("S: "+s);
        if(s>=4){
            return true;
        }
        return false;
    }
    public static Boolean Wear(Player p,String s) {
        ItemStack[] Armor = p.getInventory().getArmorContents();
        for(ItemStack i: Armor) {
            String ci = get_item_id(i);
            if (ci!=null && ci.equals(s)){
                return true;
            }
        }
        return false;
    }

    public static void draw_line(Location from,Location to,int particle_count,Particle particle) {
        double dis = from.distance(to);
        Vector dir = to.toVector().subtract(from.toVector()).normalize();
        double dis0 = dis/(particle_count+1);
        for (int i = 0; i < particle_count; i++) {
            Location pLoc = from.clone().add(dir.clone().multiply(dis0*i)); // loc
            from.getWorld().spawnParticle(particle, pLoc,1,0,0,0,0);
        }
    }

    public static ItemStack enchant_i(ItemStack i) {
        if(Event.isItem(i)) {
            ItemStack item = i.clone();
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.addEnchant(Enchantment.DURABILITY,1,false);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(itemMeta);
            return item;
        }
        return null;
    }
    public static void set_block_data(Location loc_m, String custom_id) {
        BlockState block = loc_m.getBlock().getState();
        block.setMetadata("custom_id", new FixedMetadataValue(plugin, custom_id));
        block.update();
    }
    public static void remove_block_data(Location loc_m) {
        BlockState block = loc_m.getBlock().getState();
        if(block != null && block.hasMetadata("custom_id")) {
            //block.setMetadata("custom_id", null);
            //block.set
        }
        //block.update();
    }
}
