package dev.misakacloud.plugin.cwn.listeners;

import dev.misakacloud.plugin.cwn.ChangeWorldNotify;
import dev.misakacloud.plugin.cwn.TitleUtil;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import java.io.File;

public final class PlayerChangeWorld
        implements Listener {
    @EventHandler
    public void onEvent(PlayerChangedWorldEvent event) {
        if (ChangeWorldNotify.instance.getConfig().getBoolean("ChangeWorldSettings.RunCommandSettings.Enable")) {
            for (String Command : ChangeWorldNotify.instance.getConfig().getStringList("ChangeWorldSettings.RunCommandSettings.CommandList")) {
                event.getPlayer().chat("/" + Command);
            }
        }
        if (ChangeWorldNotify.instance.getConfig().getBoolean("ChangeWorldSettings.TitleMessageSettings.Enable")) {
            if (ChangeWorldNotify.instance.getConfig().getBoolean("ChangeWorldSettings.TitleMessageSettings.DynamicTextMode")) {
                Bukkit.getScheduler().runTaskAsynchronously(ChangeWorldNotify.instance, () -> {
                    try {
                        int sleepTime = ChangeWorldNotify.instance.getConfig().getInt("ChangeWorldSettings.TitleMessageSettings.DynamicTextTime");
                        YamlConfiguration titleData =ChangeWorldNotify.instance.titleConfig;
                        for (String Titles : titleData.getStringList(event.getPlayer().getLocation().getWorld().getName())) {
                            String[] Title = Titles.split("\\|");
                            event.getPlayer().sendTitle(Title[0], Title[1], 0, 70, 20);
                            Thread.sleep(sleepTime);
                        }
                    }
                    catch (InterruptedException interruptedException) {
                        // empty catch block
                    }
                });
            } else {
                TitleUtil.RandomTitle(event.getPlayer());
            }
        }
    }
}