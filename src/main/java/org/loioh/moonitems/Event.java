package org.loioh.moonitems;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.*;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitScheduler;
import org.loioh.moonitems.items.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Random;

public class Event implements Listener {
    static JavaPlugin plugin;
    public Event(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void BBE(BlockBreakEvent e){
        Block b = e.getBlock();
        if(b.getType().equals(Material.END_STONE) || b.getType().equals(Material.END_STONE_BRICKS)) {
            BlockState n_data = e.getBlock().getState();
            Location loc = e.getBlock().getLocation().add(0.5, 0, 0.5);
            if (n_data.hasMetadata("custom_id")) {
                if(n_data.getMetadata("custom_id").get(0).asString().equals("moonstone")) {
                    ItemsManager.remove_block_data(loc);
                    if (rd() < 0.05) {
                        e.setDropItems(false);
                        b.getLocation().getWorld().dropItem(b.getLocation(), ((SItems0) MoonItems.IM.find_id("lunar_dust")).give_craft_item());
                    } else {
                        e.setDropItems(false);
                        b.getLocation().getWorld().dropItem(b.getLocation(), ((SItems0) MoonItems.IM.find_id("moonstone")).give_craft_item());
                    }
                }else if(n_data.getMetadata("custom_id").get(0).asString().equals("compacted_end_stone")) {
                    ItemsManager.remove_block_data(loc);
                    e.setDropItems(false);
                    b.getLocation().getWorld().dropItem(b.getLocation(), ((SItems0) MoonItems.IM.find_id("compacted_end_stone")).give_craft_item());
                }
            }else if (rd() < 0.003) {
                e.setDropItems(false);
                b.getLocation().getWorld().dropItem(b.getLocation(),((SItems0) MoonItems.IM.find_id("moonstone")).give_craft_item());
            }
        }
    }
    @EventHandler
    public void PIE(PlayerInteractEvent e) {
        ItemStack i_item = e.getItem();
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (isItem(i_item)) {
                ItemStack item_0 = e.getItem().clone();
                String ci = ItemsManager.get_item_id(item_0);
                if (ci != null) {
                    if(MoonItems.IM.find_id(ci) instanceof Staffs0) {
                        Staffs0 g0 = (Staffs0) MoonItems.IM.find_id(ci);
                        if (g0 != null) {
                            g0.PreShoot(p, i_item);
                        }
                    }
                    //else if(MoonItems.IM.find_id(ci) instanceof Usable0){
                    //                        Usable0 u0 = (Usable0) MoonItems.IM.find_id(ci);
                    //                        if (u0 != null) {
                    //                            u0.PreUse(p);
                    //                            if(u0.get_remove()){
                    //                                if(e.getItem().equals(p.getInventory().getItemInMainHand())){
                    //                                    p.getInventory().setItemInMainHand(remove(e.getItem(),1));
                    //                                }else if(e.getItem().equals(p.getInventory().getItemInOffHand())){
                    //                                    p.getInventory().setItemInOffHand(remove(e.getItem(),1));
                    //                                }
                    //                            }
                    //                        }
                    //                    }
                    if(!(MoonItems.IM.find_id(ci) instanceof SItems0)) {
                        e.setCancelled(true);
                    }else{
                        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                            if (ci.equals("moonstone")) {
                                Block b = e.getClickedBlock().getRelative(e.getBlockFace(),1);
                                b.setType(Material.END_STONE);
                                Location loc = b.getLocation().clone().add(0.5,0,0.5);
                                ItemsManager.set_block_data(loc,"moonstone");
                                if(e.getItem().equals(p.getInventory().getItemInMainHand())){
                                    p.getInventory().setItemInMainHand(remove(e.getItem(),1));
                                }else if(e.getItem().equals(p.getInventory().getItemInOffHand())){
                                    p.getInventory().setItemInOffHand(remove(e.getItem(),1));
                                }
                            }else if(ci.equals("compacted_end_stone")){
                                Block b = e.getClickedBlock().getRelative(e.getBlockFace(),1);
                                b.setType(Material.END_STONE_BRICKS);
                                Location loc = b.getLocation().clone().add(0.5,0,0.5);
                                ItemsManager.set_block_data(loc,"compacted_end_stone");
                                if(e.getItem().equals(p.getInventory().getItemInMainHand())){
                                    p.getInventory().setItemInMainHand(remove(e.getItem(),1));
                                }else if(e.getItem().equals(p.getInventory().getItemInOffHand())){
                                    p.getInventory().setItemInOffHand(remove(e.getItem(),1));
                                }
                            }
                        }
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
    @EventHandler
    public void ICE(InventoryClickEvent e) {
        final Player clicker = (Player) e.getWhoClicked();
        String[] s = {"locked_item"};
        if (e.getInventory() != null && e.getClickedInventory() != null && e.getClickedInventory().getType() != null) {
            //String title = e.getView().getTitle();
            //clicker.sendMessage("getView(1)(click): "+title);
            if (e.getClickedInventory().getType() == InventoryType.WORKBENCH) {
                if (e.getClick() == ClickType.DOUBLE_CLICK) {
                    e.setCancelled(true);
                }
                if (e.getAction() == InventoryAction.DROP_ONE_SLOT || e.getAction() == InventoryAction.DROP_ALL_SLOT) {
                    e.setCancelled(true);
                }
                ItemStack current = e.getCurrentItem().clone();

                if(isItem(current)) {
                    ItemStack[] wbc = e.getClickedInventory().getContents();
                    if(e.getSlot() == 0);
                    String ci = ItemsManager.get_item_id(current);
                    if(ci!= null && ci.contains("compacted_end_stone")){
                        for(int i=1;i< wbc.length;i++) {
                            ItemStack ii = wbc[i].clone();
                            ii.setAmount(ii.getAmount()-3);
                            e.getClickedInventory().setItem(i,ii);
                            //MoonItems.IM.msg(i + ": " + wbc[i]);

                            //BukkitScheduler bs = plugin.getServer().getScheduler();
                            //    bs.runTaskLater(plugin, () -> {
                            //        if (!isItem(e.getCurrentItem())){
                            //            e.setCurrentItem(current);
                            //        }
                            //}, 10);
                        }
                        clicker.getInventory().addItem(current);
                    }
                }
            }
        }
    }
    @EventHandler
    public void PHE(ProjectileHitEvent e) {
        Projectile p = e.getEntity();
        if(p.hasMetadata("Staff_ID")) {
            p.remove();
        }
    }
    @EventHandler
    public void EDBEE(EntityDamageByEntityEvent e){
        if(e.getDamager().hasMetadata("Damage") ){
            double damage = e.getDamager().getMetadata("Damage").get(0).asDouble();
            e.setDamage(damage);
        }else if(e.getEntity() instanceof Player){
            Player p = (Player)e.getEntity();
            if(ItemsManager.Wear(p,"celestial_guardian_chestplate")){
                if(rd() < 0.5){
                    if(e.getDamager() instanceof LivingEntity) {
                        ((LivingEntity)e.getDamager()).damage(e.getDamage());
                    }
                    e.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void ESE(EntitySpawnEvent e){
        if(e.getEntity() instanceof Spider ){
            if(rd() < 0.1) {
                e.getEntity().setCustomName(ChatColor.WHITE + "Lunar Spider");
                e.getEntity().setCustomNameVisible(true);
            }
        }else if (e.getEntity() instanceof Cow){
            if(rd() < 0.03) {//
                e.getEntity().setCustomName(ChatColor.WHITE + "Moon Cowhide");
                e.getEntity().setCustomNameVisible(true);
            }
        }
    }
    @EventHandler
    public void EDE(EntityDeathEvent e){
        if(e.getEntity() instanceof Spider ){
            if(e.getEntity().isCustomNameVisible() && e.getEntity().getCustomName().equals(ChatColor.WHITE + "Lunar Spider")) {
                e.getDrops().clear();
                if(rd() < 0.01) {
                    e.getDrops().add(((SItems0)MoonItems.IM.find_id("enchanted_silk")).give_craft_item());
                }
            }
        }else if (e.getEntity() instanceof Cow){
            if(e.getEntity().isCustomNameVisible() && e.getEntity().getCustomName().equals(ChatColor.WHITE + "Moon Cowhide")) {
                e.getDrops().clear();
                if(rd() < 0.03) {
                    e.getDrops().add(((SItems0)MoonItems.IM.find_id("moon_leather")).give_craft_item());
                }
            }
        }else if (e.getEntity() instanceof Enderman){
            if(rd() < 0.0002) {
                e.getDrops().add(((SItems0) MoonItems.IM.find_id("moon_leather")).give_craft_item());
            }
        }
        else if (e.getEntity() instanceof EnderDragon){
            if(rd() < 0.02) {
                e.getDrops().add(((SItems0) MoonItems.IM.find_id("moon_leather")).give_craft_item());
            }
        }
    }
    @EventHandler
    public void PIDE(PlayerItemDamageEvent e){
        //MoonItems.IM.msg("item:"+e.getItem());
        //MoonItems.IM.msg("item:"+e.getItem().getType().getMaxDurability());
        //MoonItems.IM.msg("od:"+e.getOriginalDamage());
        //MoonItems.IM.msg("d:"+e.getDamage());

        if(isItem(e.getItem())){
            String id = ItemsManager.get_item_id(e.getItem());
            if(id!= null && ItemsManager.armor_set.contains(id)) {
                Boolean a = false;
                if(id.equals(ItemsManager.armor[0])){
                    a=durabulity_2xPh(e.getPlayer());
                }else if(id.equals(ItemsManager.armor[1])){
                    a=durabulity_2xPc(e.getPlayer());
                }else if(id.equals(ItemsManager.armor[2])){
                    a=durabulity_2xPl(e.getPlayer());
                }else if(id.equals(ItemsManager.armor[3])){
                    a=durabulity_2xPb(e.getPlayer());
                }
                e.setCancelled(a);
            }
        }
    }
    private HashMap<Player,Integer> Ph = new HashMap<>();
    private HashMap<Player,Integer> Pc = new HashMap<>();
    private HashMap<Player,Integer> Pl = new HashMap<>();
    private HashMap<Player,Integer> Pb = new HashMap<>();
    private Boolean durabulity_2xPh(Player p){
        Boolean a= false;
        int v;

        if(!Ph.containsKey(p)){
            v=1;
        }else{
            v = Ph.get(p);
            if(v>=3){
                v=1;
            }else{
                v+=1;
                a = true;
            }
        }
        Ph.put(p,v);
        return a;
    }
    private Boolean durabulity_2xPc(Player p){
        Boolean a= false;
        int v;

        if(!Pc.containsKey(p)){
            v=1;
        }else{
            v = Pc.get(p);
            if(v>=3){
                v=1;
            }else{
                v+=1;
                a = true;
            }
        }
        Pc.put(p,v);
        return a;
    }
    private Boolean durabulity_2xPl(Player p){
        Boolean a= false;
        int v;

        if(!Pl.containsKey(p)){
            v=1;
        }else{
            v = Pl.get(p);
            if(v>=3){
                v=1;
            }else{
                v+=1;
                a = true;
            }
        }
        //MoonItems.IM.msg("v:"+v);
        Pl.put(p,v);
        return a;
    }
    private Boolean durabulity_2xPb(Player p){
        Boolean a= false;
        int v;

        if(!Pb.containsKey(p)){
            v=1;
        }else{
            v = Pb.get(p);
            if(v>=3){
                v=1;
            }else{
                v+=1;
                a = true;
            }
        }
        //MoonItems.IM.msg("v:"+v);
        Pb.put(p,v);
        return a;
    }



    @EventHandler
    public void PAE(PrepareAnvilEvent e){
        //MoonItems.IM.msg("item1:"+e.getInventory().getFirstItem());
        //MoonItems.IM.msg("item2:"+e.getInventory().getSecondItem());
        //MoonItems.IM.msg("result:"+e.getInventory().getResult());
        //MoonItems.IM.msg("exp cost:"+e.getInventory().getRepairCost());
        //MoonItems.IM.msg("EResult:"+e.getResult());
        if(isItem(e.getResult())){
            String id = ItemsManager.get_item_id(e.getResult());
            if(id!= null && ItemsManager.armor_set.contains(id)){
                e.getInventory().setRepairCost(-1);
                e.setResult(null);
                e.getInventory().setResult(null);
            }
        }
    }



    public static Boolean isItem(ItemStack a){
        if( a != null && a.getType() != null && a.getType() != Material.AIR){
            return true;
        }
        return false;
    }
    public static ItemStack remove(ItemStack a,int amount){
        if(a != null) {
            if (a.getAmount() > amount) {
                a.setAmount(a.getAmount() - amount);
            } else {
                a.setAmount(0);
                a = null;
            }
        }
        return a;
    }
    public static double rd(){
        return (new Random()).nextDouble();
    }
}
