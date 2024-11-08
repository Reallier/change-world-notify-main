package dev.misakacloud.plugin.cwn;

import dev.misakacloud.plugin.cwn.commands.CommandWorldReload;
import dev.misakacloud.plugin.cwn.listeners.PlayerChangeWorld;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class ChangeWorldNotify extends JavaPlugin {
    public static ChangeWorldNotify instance;
    public YamlConfiguration pluginConfig;
    public YamlConfiguration titleConfig;

    public File pluginHome = new File(String.valueOf(this.getDataFolder()));

    public void onEnable() {
        boolean Stats;
        instance = this;

        if (!pluginHome.exists()) {
            try {
                Stats = pluginHome.mkdir();
            } catch (Exception e) {
                TitleUtil.ColorLog("[MisakaCloud-ChangeWorldMessage] 插件数据文件夹创建失败! 原因: " + e.getMessage());
                Stats = false;
            }
            TitleUtil.ColorLog("[MisakaCloud-ChangeWorldMessage] 插件数据文件夹创建终了");
        }
        createConfigs();
        // 注册命令
        Objects.requireNonNull(this.getCommand("worldreload")).setExecutor(new CommandWorldReload());
        // 注册事件
        Bukkit.getPluginManager().registerEvents(new PlayerChangeWorld(), this);
        TitleUtil.ColorLog("&f============&6梦回东方-切换世界提示&f============");
        TitleUtil.ColorLog("&e插件启动完成!");
        TitleUtil.ColorLog("&f============&6梦回东方-切换世界提示&f============");
    }

    public void onDisable() {
        instance = null;
        TitleUtil.ColorLog("&f============&6梦回东方-切换世界提示&f============");
        TitleUtil.ColorLog("&e插件已卸载!");
        TitleUtil.ColorLog("&f============&6梦回东方-切换世界提示&f============");
    }

    public void createConfigs() {
        File configFile = new File(pluginHome, "config.yml");
        if (!configFile.exists()) {
            YamlFileUtil.SaveResource(this.getDataFolder().getPath(), "config.yml", "config.yml", false);
        }
        pluginConfig = YamlConfiguration.loadConfiguration(new File(pluginHome, "config.yml"));
        File titleFile = new File(pluginHome, "ChangeWorldTitle.yml");
        if (!titleFile.exists()) {
            YamlFileUtil.SaveResource(this.getDataFolder().getPath(), "ChangeWorldTitle.yml", "ChangeWorldTitle.yml", false);
        }
        titleConfig = YamlConfiguration.loadConfiguration(new File(pluginHome, "ChangeWorldTitle.yml"));
    }
}
