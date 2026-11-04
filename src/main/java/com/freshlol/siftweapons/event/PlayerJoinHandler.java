package com.freshlol.siftweapons.event;

import com.freshlol.siftweapons.item.LevelledWeaponItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class PlayerJoinHandler {
    public static void onJoin(ServerPlayNetworkHandler handler, net.minecraft.server.MinecraftServer server) {
        ServerPlayerEntity player = handler.getPlayer();

        boolean hasMace = false;
        boolean hasSpear = false;
        for (ItemStack stack : player.getInventory().main) {
            if (stack.getItem() instanceof LevelledWeaponItem lwi) {
                if (lwi.isMace()) hasMace = true;
                else hasSpear = true;
            }
        }

        if (!hasMace) {
            ItemStack mace = LevelledWeaponItem.create(1, true);
            player.giveItemStack(mace);
        }
        if (!hasSpear) {
            ItemStack spear = LevelledWeaponItem.create(1, false);
            player.giveItemStack(spear);
        }

        if (!hasMace || !hasSpear) {
            player.sendMessage(Text.literal("You received Level 1 Spear & Mace. Get kills to upgrade!").formatted(Formatting.GREEN), false);
        }
    }
}
