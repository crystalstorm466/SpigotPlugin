package me.David.SpigotPlugin;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

public class CommandHandler implements CommandExecutor {

    private volatile boolean chatEnabled = true;
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        if(!chatEnabled) {
            event.setCancelled(true);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] strings) {
       if (sender instanceof Player) {
           Player player = (Player) sender;
           if (cmd.getName().equalsIgnoreCase("kit")) {
               ItemStack diamond = new ItemStack(Material.DIAMOND);
               ItemStack netherStar = new ItemStack(Material.NETHER_STAR);

               netherStar.setAmount(64);
               netherStar.addEnchantment(Enchantment.DAMAGE_ALL, 200);
               netherStar.addEnchantment(Enchantment.KNOCKBACK, 10);

               diamond.setAmount(128);

               player.getInventory().addItem(diamond, netherStar);

           }
           if (cmd.getName().equalsIgnoreCase("mutechat")) {
               chatEnabled = !chatEnabled;
               sender.sendMessage(ChatColor.GREEN + (chatEnabled ? "unmuted the chat" : "Muted the Chat"));
           }
           return true;
       }
       return true;
    }



}
