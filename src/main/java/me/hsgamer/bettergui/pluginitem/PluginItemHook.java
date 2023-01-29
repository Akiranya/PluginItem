package me.hsgamer.bettergui.pluginitem;

import me.hsgamer.bettergui.builder.ItemModifierBuilder;
import me.hsgamer.hscore.bukkit.addon.PluginAddon;

public final class PluginItemHook extends PluginAddon {
    @Override public void onEnable() {
        ItemModifierBuilder.INSTANCE.register(PluginItemModifier::new, "plugin-item");
    }
}
