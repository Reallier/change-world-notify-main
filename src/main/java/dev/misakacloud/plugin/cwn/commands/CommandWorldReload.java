package dev.misakacloud.plugin.cwn.commands;

import dev.misakacloud.plugin.cwn.ChangeWorldNotify;
import dev.misakacloud.plugin.cwn.TitleUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public final class CommandWorldReload
        implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        ChangeWorldNotify.instance.reloadConfig();
        sender.sendMessage(TitleUtil.ChatColor("&a重载成功!"));
        return false;
    }
}
