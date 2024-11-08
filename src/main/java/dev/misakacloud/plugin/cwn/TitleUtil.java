package dev.misakacloud.plugin.cwn;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

public final class TitleUtil {

    public static String ChatColor(String Message) {
        return ChatColor.translateAlternateColorCodes('&', Message);
    }

    public static void ColorLog(String Message) {
        ConsoleCommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage(TitleUtil.ChatColor(Message));
    }

    public static void RandomTitle(Player player) {
        
        List<String> TitleList = ChangeWorldNotify.instance.titleConfig.getStringList(player.getLocation().getWorld().getName());
        Random random = new Random();
        int RandomInt = random.nextInt(TitleList.size());
        String[] Title = ((String) TitleList.get(RandomInt)).split("\\|");
        player.sendTitle(TitleUtil.ChatColor(Title[0]), TitleUtil.ChatColor(Title[1]), 10, 70, 20);
    }
}
