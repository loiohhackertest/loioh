package org.loioh.moonitems;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.EquipmentSlot;
import org.loioh.moonitems.commands.item_get;
import org.loioh.moonitems.items.*;

import java.util.ArrayList;
import java.util.List;

public class _Init {
    public static void init() throws CustomItemException {
        initI();
        //item_get.init();
    }

    public static void initI() throws CustomItemException {
        //String[] path = {String.join(".", "SItems"),
        //        String.join(".", "Staffs"),};
        //if (!MoonItems.items_c.contains(path[0]) ) {
        //} else {
        //}
        //for (int i = 0; i < path.length; i++) {
            //if (!MoonItems.items_c.contains(path[i])) {
                //MoonItems.IM.msg("[items.yml] creating...");
                //switch (i) {
                    //case 0:
                        //armor

        new SItems0("celestial_guardian_helmet", ItemsManager.create_armor(new BaseComponent[]{sn("Celestial Guardian Helmet", ChatColor.GRAY)}, Material.DIAMOND_HELMET, 8001, Attribute.GENERIC_ARMOR, EquipmentSlot.FEET, 6, Attribute.GENERIC_ARMOR_TOUGHNESS, EquipmentSlot.FEET,4), true);
        new SItems0("celestial_guardian_chestplate", ItemsManager.create_armor(new BaseComponent[]{sn("Celestial Guardian ChestPlate", ChatColor.GRAY)}, Material.DIAMOND_CHESTPLATE, 8002, Attribute.GENERIC_ARMOR, EquipmentSlot.FEET, 16, Attribute.GENERIC_ARMOR_TOUGHNESS, EquipmentSlot.FEET,4), true);
        new SItems0("celestial_guardian_leggings", ItemsManager.create_armor(new BaseComponent[]{sn("Celestial Guardian Leggings", ChatColor.GRAY)}, Material.DIAMOND_LEGGINGS, 8003, Attribute.GENERIC_ARMOR, EquipmentSlot.FEET,12, Attribute.GENERIC_ARMOR_TOUGHNESS, EquipmentSlot.FEET,4), true);
        new SItems0("celestial_guardian_boots", ItemsManager.create_armor(new BaseComponent[]{sn("Celestial Guardian Boots", ChatColor.GRAY)}, Material.DIAMOND_BOOTS, 8004, Attribute.GENERIC_ARMOR, EquipmentSlot.FEET,6,Attribute.GENERIC_ARMOR_TOUGHNESS, EquipmentSlot.FEET,4), true);
        //B drop
        new SItems0("moonstone", ItemsManager.enchant_i(ItemsManager.create_item(new BaseComponent[]{sn("Moonstone", ChatColor.WHITE)}, Material.END_STONE, 8011, null)), true);
        new SItems0("compacted_end_stone", ItemsManager.enchant_i(ItemsManager.create_item(new BaseComponent[]{sn("Compacted End Stone", ChatColor.WHITE)}, Material.END_STONE_BRICKS, 8012, null)), true);
        //Items
        new SItems0("moon_crystal", ItemsManager.create_item(new BaseComponent[]{sn("Moon Crystal", ChatColor.WHITE)}, Material.END_CRYSTAL, 8100, null), true);
        new SItems0("stardust", ItemsManager.create_item(new BaseComponent[]{sn("Stardust", ChatColor.WHITE)}, Material.NETHER_STAR, 8101, null), true);
        new SItems0("moondust", ItemsManager.create_item(new BaseComponent[]{sn("Moondust", ChatColor.WHITE)}, Material.GUNPOWDER, 8102, null), true);
        new SItems0("lunar_dust", ItemsManager.enchant_i(ItemsManager.create_item(new BaseComponent[]{sn("Lunar Dust", ChatColor.WHITE)}, Material.REDSTONE, 8103, null)), true);
        new SItems0("enchanted_silk", ItemsManager.enchant_i(ItemsManager.create_item(new BaseComponent[]{sn("Enchanted Silk", ChatColor.WHITE)}, Material.COBWEB, 8104, null)), true);
        new SItems0("moon_leather", ItemsManager.enchant_i(ItemsManager.create_item(new BaseComponent[]{sn("Moon Leather", ChatColor.WHITE)}, Material.LEATHER, 8105, null)), true);
        new SItems0("glowing_essence", ItemsManager.enchant_i(ItemsManager.create_item(new BaseComponent[]{sn("Glowing Essence", ChatColor.WHITE)}, Material.DRAGON_BREATH, 8106, null)), true);
        new SItems0("moonforged_plates", ItemsManager.create_item(new BaseComponent[]{sn("Moonforged Plates", ChatColor.YELLOW)}, Material.LEGACY_GOLD_PLATE, 8107, null), true);
        new SItems0("enchanted_stick", ItemsManager.enchant_i(ItemsManager.create_item(new BaseComponent[]{sn("Enchanted Stick", ChatColor.BLUE)}, Material.STICK, 8108, null)), true);


        new Staffs0("lynar_staff", ItemsManager.create_item(new BaseComponent[]{sn("Lunar Staff", ChatColor.GRAY)}, Material.STICK, 8000, null), true,1000,5,10,null);
    }


    public static TranslatableComponent tn(String name) {
        TranslatableComponent textC = new TranslatableComponent(name);
        textC.setColor(net.md_5.bungee.api.ChatColor.WHITE);
        textC.setItalic(false);
        return textC;
    }

    public static TextComponent sn(String name) {
        TextComponent textC = new TextComponent(name);
        textC.setItalic(false);
        return textC;
    }

    public static TextComponent sn(String name, ChatColor color) {
        TextComponent textC = new TextComponent(name);
        textC.setItalic(false);
        textC.setColor(color);
        return textC;
    }

    public static TranslatableComponent tn(String name, ChatColor color) {
        TranslatableComponent textC = new TranslatableComponent(name);
        textC.setColor(color);
        textC.setItalic(false);
        return textC;
    }
    public static TextComponent hl_text(String text,String hover_text,String link){
        TextComponent text1 = new TextComponent(text);
        Text h_text = new Text(hover_text);
        text1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,h_text));
        text1.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,link));
        return text1;
    }
}