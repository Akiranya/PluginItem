package me.hsgamer.bettergui.pluginitem;

import cc.mewcraft.mewcore.item.api.PluginItem;
import cc.mewcraft.mewcore.item.api.PluginItemRegistry;
import me.hsgamer.hscore.bukkit.item.ItemModifier;
import me.hsgamer.hscore.common.interfaces.StringReplacer;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;

public class PluginItemModifier implements ItemModifier {
    private String name = "";

    @Override public String getName() {
        return "plugin-item";
    }

    @Override public ItemStack modify(final ItemStack itemStack, final UUID uuid, final Map<String, StringReplacer> map) {
        String replaced = StringReplacer.replace(name, uuid, map.values());
        PluginItem<?> pluginItem = PluginItemRegistry.get().fromReferenceNullable(replaced);
        if (pluginItem == null)
            return null;
        ItemStack newItemStack = pluginItem.createItemStack();
        return newItemStack == null ? itemStack : newItemStack;
    }

    @Override public Object toObject() {
        return name;
    }

    @Override public void loadFromObject(final Object o) {
        this.name = String.valueOf(o);
    }

    @Override public void loadFromItemStack(final ItemStack itemStack) {
        PluginItem<?> pluginItem = PluginItemRegistry.get().fromItemStackNullable(itemStack);
        if (pluginItem != null) {
            this.name = pluginItem.asReference();
        }
    }

    @Override public boolean compareWithItemStack(final ItemStack itemStack, final UUID uuid, final Map<String, StringReplacer> map) {
        String replaced = StringReplacer.replace(name, uuid, map.values());
        String key = PluginItemRegistry.get().asReference(itemStack);
        return key != null && key.equals(replaced);
    }
}
