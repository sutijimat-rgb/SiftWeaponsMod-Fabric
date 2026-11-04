package com.freshlol.siftweapons.event;

import com.freshlol.siftweapons.item.LevelledWeaponItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class KillUpgradeHandler {
    public static void onDeath(LivingEntity entity, DamageSource source) {
        if (!(source.getAttacker() instanceof ServerPlayerEntity player)) return;

        ItemStack main = player.getMainHandStack();
        if (!(main.getItem() instanceof LevelledWeaponItem weapon)) return;

        int current = LevelledWeaponItem.getLevel(main);
        if (current >= 14) {
            player.sendMessage(Text.literal("Weapon is already max level (14)!").formatted(Formatting.GOLD), true);
            return;
        }

        int next = current + 1;
        LevelledWeaponItem.setLevel(main, next);
        LevelledWeaponItem.applyLevelEnchants(main, next, weapon.isMace());

        player.sendMessage(Text.literal((weapon.isMace() ? "Mace" : "Spear") + " upgraded to Level " + next + "!").formatted(Formatting.AQUA), true);

        if (next == 5) {
            player.sendMessage(Text.literal("Mid-tier abilities unlocked!").formatted(Formatting.GREEN), false);
        } else if (next == 10) {
            player.sendMessage(Text.literal("High-tier abilities unlocked!").formatted(Formatting.LIGHT_PURPLE), false);
        } else if (next == 14) {
            player.sendMessage(Text.literal("MAX LEVEL! You can now aim for the God version.").formatted(Formatting.GOLD), false);
        }
    }
}
