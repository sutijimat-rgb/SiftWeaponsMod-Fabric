package com.freshlol.siftweapons.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class GodWeaponItem extends Item {
    private final boolean isMace;

    public GodWeaponItem(Settings settings, boolean isMace) {
        super(settings);
        this.isMace = isMace;
    }

    public boolean isMace() {
        return isMace;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.literal("ONLY 1 EXISTS").formatted(Formatting.DARK_RED, Formatting.BOLD));
        tooltip.add(Text.literal("Ultimate Freshlol God Weapon").formatted(Formatting.GOLD));
        if (isMace) {
            tooltip.add(Text.literal("Insane smash + special abilities").formatted(Formatting.LIGHT_PURPLE));
        } else {
            tooltip.add(Text.literal("Insane speed damage + special abilities").formatted(Formatting.LIGHT_PURPLE));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}
