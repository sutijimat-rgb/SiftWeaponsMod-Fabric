package com.freshlol.siftweapons.event;

import com.freshlol.siftweapons.item.ModItems;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

public class SiftMaceHandler {
    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                boolean hasSift = false;
                for (ItemStack stack : player.getInventory().main) {
                    if (stack.isOf(ModItems.SIFT_MACE)) {
                        hasSift = true;
                        break;
                    }
                }
                if (!hasSift) {
                    ItemStack off = player.getOffHandStack();
                    if (off.isOf(ModItems.SIFT_MACE)) hasSift = true;
                }

                if (hasSift) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 40, 2, true, false, true));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 40, 2, true, false, true));
                }
            }
        });
    }
}
