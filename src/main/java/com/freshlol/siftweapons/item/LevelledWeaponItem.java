package com.freshlol.siftweapons.item;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class LevelledWeaponItem extends Item {
    private final boolean isMace;

    public LevelledWeaponItem(Settings settings, boolean isMace) {
        super(settings);
        this.isMace = isMace;
    }

    public boolean isMace() {
        return isMace;
    }

    public static int getLevel(ItemStack stack) {
        NbtComponent custom = stack.get(DataComponentTypes.CUSTOM_DATA);
        if (custom == null) return 1;
        NbtCompound nbt = custom.copyNbt();
        return Math.max(1, Math.min(14, nbt.getInt("WeaponLevel")));
    }

    public static void setLevel(ItemStack stack, int level) {
        level = Math.max(1, Math.min(14, level));
        NbtCompound nbt = new NbtCompound();
        nbt.putInt("WeaponLevel", level);
        stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));

        String base = stack.getItem() instanceof LevelledWeaponItem lwi && lwi.isMace() ? "Mace" : "Spear";
        stack.set(DataComponentTypes.CUSTOM_NAME, Text.literal(base + " (Level " + level + ")").formatted(Formatting.GOLD));
    }

    public static ItemStack create(int level, boolean mace) {
        ItemStack stack = new ItemStack(mace ? ModItems.LEVELLED_MACE : ModItems.LEVELLED_SPEAR);
        setLevel(stack, level);
        applyLevelEnchants(stack, level, mace);
        return stack;
    }

    public static void applyLevelEnchants(ItemStack stack, int level, boolean mace) {
        NbtCompound nbt = stack.getOrDefault(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT).copyNbt();
        nbt.putInt("WeaponLevel", level);
        nbt.putBoolean("IsMace", mace);
        stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        int level = getLevel(stack);
        tooltip.add(Text.literal("Level: " + level + " / 14").formatted(Formatting.AQUA));
        if (level >= 10) {
            tooltip.add(Text.literal("High tier abilities unlocked").formatted(Formatting.LIGHT_PURPLE));
        }
        if (level >= 14) {
            tooltip.add(Text.literal("MAX LEVEL – Ready for God form").formatted(Formatting.GOLD));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}
