package org.loioh.moonitems.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.loioh.moonitems.MoonItems;
import org.loioh.moonitems.items.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class item_get implements CommandExecutor, TabCompleter {
        static JavaPlugin plugin;
        public item_get(JavaPlugin plugin) {
            this.plugin = plugin;
        }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(!(sender instanceof Player)) return true;

        Player player = (Player) sender;


        if(command.getName().equalsIgnoreCase("mitem_get") || command.getName().equalsIgnoreCase("mi_get")) {
            //player.sendMessage(ChatColor.GREEN+"command start");
            if (player.isOp()) {
                if (args.length == 1) {
                    String i1 = args[0];
                    if (MoonItems.IM.get_ids().contains(i1)) {
                        Object i = MoonItems.IM.find_id(i1);
                        if (i instanceof SItems0){
                            player.getInventory().addItem(((SItems0)i).give_new_item());
                        }else if(i instanceof Staffs0){
                            player.getInventory().addItem(((Staffs0)i).give_new_item());
                        }//else if (i instanceof Usable0){
                        //    player.getInventory().addItem(((Usable0)i).give_new_item());
                        //}
                    } else if(i1.equals("ALL")){
                        List<String> keys = MoonItems.IM.get_ids();
                        for(String key:keys) {
                            Object i = MoonItems.IM.find_id(key);
                            if (i instanceof SItems0) {
                                player.getInventory().addItem(((SItems0) i).give_new_item());
                            } else if (i instanceof Staffs0) {
                                player.getInventory().addItem(((Staffs0) i).give_new_item());
                            }
                        }
                        return true;
                    }else{
                        player.sendMessage(ChatColor.RED + "you must send item name (lunar_staff or ALL)");
                        return true;
                    }

                } else {
                    player.sendMessage(ChatColor.RED + "you must send item name (/mi_get lunar_staff)");
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
            l.add("ALL");
            l.addAll(MoonItems.IM.get_ids());
            return l;
        }
        return new ArrayList<>();
    }
    public static final String author="loioh";










    public static void init(){
        //items
        ItemStack celestial_guardian_helmet = ((SItems0)MoonItems.IM.find_id("celestial_guardian_helmet")).give_craft_item();
        ItemStack celestial_guardian_chestplate = ((SItems0)MoonItems.IM.find_id("celestial_guardian_chestplate")).give_craft_item();
        ItemStack celestial_guardian_leggings = ((SItems0)MoonItems.IM.find_id("celestial_guardian_leggings")).give_craft_item();
        ItemStack celestial_guardian_boots = ((SItems0)MoonItems.IM.find_id("celestial_guardian_boots")).give_craft_item();

        ItemStack moonstone = ((SItems0)MoonItems.IM.find_id("moonstone")).give_craft_item();

        ItemStack compacted_end_stone = ((SItems0)MoonItems.IM.find_id("compacted_end_stone")).give_craft_item();

        ItemStack moon_crystal = ((SItems0)MoonItems.IM.find_id("moon_crystal")).give_craft_item();
        ItemStack stardust = ((SItems0)MoonItems.IM.find_id("stardust")).give_craft_item();
        ItemStack moondust = ((SItems0)MoonItems.IM.find_id("moondust")).give_craft_item();
        ItemStack lunar_dust = ((SItems0)MoonItems.IM.find_id("lunar_dust")).give_craft_item();
        ItemStack enchanted_silk = ((SItems0)MoonItems.IM.find_id("enchanted_silk")).give_craft_item();
        ItemStack moon_leather = ((SItems0)MoonItems.IM.find_id("moon_leather")).give_craft_item();
        ItemStack glowing_essence = ((SItems0)MoonItems.IM.find_id("glowing_essence")).give_craft_item();
        ItemStack moonforged_plates = ((SItems0)MoonItems.IM.find_id("moonforged_plates")).give_craft_item();
        ItemStack enchanted_stick = ((SItems0)MoonItems.IM.find_id("enchanted_stick")).give_craft_item();

        ItemStack lynar_staff = ((Staffs0)MoonItems.IM.find_id("lynar_staff")).give_craft_item();

        //crafts
        //armor
        ShapedRecipe recipe1 = new ShapedRecipe(Objects.requireNonNull(new NamespacedKey(plugin,"key201")), celestial_guardian_boots);
        recipe1.shape("CDC","BAB","EFE");
        recipe1.setIngredient('A', Material.NETHERITE_BOOTS);
        recipe1.setIngredient('B', enchanted_silk);
        recipe1.setIngredient('C', lunar_dust);
        recipe1.setIngredient('D', moon_leather);
        recipe1.setIngredient('E', moonforged_plates);
        recipe1.setIngredient('F', moon_crystal);
        Bukkit.getServer().addRecipe(recipe1);

        ShapedRecipe recipe2 = new ShapedRecipe(Objects.requireNonNull(new NamespacedKey(plugin,"key202")), celestial_guardian_leggings);
        recipe2.shape("CDC","BAB","EFE");
        recipe2.setIngredient('A', Material.NETHERITE_LEGGINGS);
        recipe2.setIngredient('B', moonstone);
        recipe2.setIngredient('C', moonforged_plates);
        recipe2.setIngredient('D', stardust);
        recipe2.setIngredient('E', moon_crystal);
        recipe2.setIngredient('F', glowing_essence);
        Bukkit.getServer().addRecipe(recipe2);

        ShapedRecipe recipe3 = new ShapedRecipe(Objects.requireNonNull(new NamespacedKey(plugin,"key203")), celestial_guardian_chestplate);
        recipe3.shape("CDC","BAB","EFE");
        recipe3.setIngredient('A', Material.NETHERITE_CHESTPLATE);
        recipe3.setIngredient('B', moonstone);
        recipe3.setIngredient('C', moonforged_plates);
        recipe3.setIngredient('D', moon_crystal);
        recipe3.setIngredient('E', enchanted_silk);
        recipe3.setIngredient('F', stardust);
        Bukkit.getServer().addRecipe(recipe3);

        ShapedRecipe recipe4 = new ShapedRecipe(Objects.requireNonNull(new NamespacedKey(plugin,"key204")), celestial_guardian_helmet);
        recipe4.shape("CDC","<A>","EFE");
        recipe4.setIngredient('A', Material.NETHERITE_HELMET);
        recipe4.setIngredient('<', lunar_dust);
        recipe4.setIngredient('>', moondust);
        recipe4.setIngredient('C', moon_crystal);
        recipe4.setIngredient('D', moonforged_plates);
        recipe4.setIngredient('E', glowing_essence);
        recipe4.setIngredient('F', moonstone);
        Bukkit.getServer().addRecipe(recipe4);


        //another
        ItemStack moondust4 = moondust.clone();
        moondust4.setAmount(4);
        ShapedRecipe recipe101 = new ShapedRecipe(Objects.requireNonNull(new NamespacedKey(plugin,"key101")), moondust4);
        recipe101.shape(" A ","ABA"," A ");
        recipe101.setIngredient('A', compacted_end_stone);
        recipe101.setIngredient('B', lunar_dust);
        Bukkit.getServer().addRecipe(recipe101);

        ShapedRecipe recipe102 = new ShapedRecipe(Objects.requireNonNull(new NamespacedKey(plugin,"key102")), glowing_essence);
        recipe102.shape("CDC","BAB","CDC");
        recipe102.setIngredient('A', Material.DRAGON_BREATH);
        recipe102.setIngredient('B', Material.CHORUS_FRUIT);
        recipe102.setIngredient('C', Material.DIAMOND);
        recipe102.setIngredient('D', Material.GLOWSTONE);
        Bukkit.getServer().addRecipe(recipe102);

        ShapedRecipe recipe103 = new ShapedRecipe(Objects.requireNonNull(new NamespacedKey(plugin,"key103")), moon_crystal);
        recipe103.shape("CBC","BAB","CBC");
        recipe103.setIngredient('A', moon_leather);
        recipe103.setIngredient('B', moonstone);
        recipe103.setIngredient('C', stardust);
        Bukkit.getServer().addRecipe(recipe103);

        ShapedRecipe recipe104 = new ShapedRecipe(Objects.requireNonNull(new NamespacedKey(plugin,"key104")), lynar_staff);
        recipe104.shape("CDC","BAB","CAC");
        recipe104.setIngredient('A', enchanted_stick);
        recipe104.setIngredient('B', Material.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE);
        recipe104.setIngredient('D', Material.AMETHYST_BLOCK);
        recipe104.setIngredient('C', moon_crystal);
        Bukkit.getServer().addRecipe(recipe104);

        ShapedRecipe recipe105 = new ShapedRecipe(Objects.requireNonNull(new NamespacedKey(plugin,"key105")), moonforged_plates);
        recipe105.shape("CBC","BAB","CBC");
        recipe105.setIngredient('A', glowing_essence);
        recipe105.setIngredient('B', lunar_dust);
        recipe105.setIngredient('C', moonstone);
        Bukkit.getServer().addRecipe(recipe105);

    }
}
