package com.freshlol.siftweapons;

import com.freshlol.siftweapons.data.WeaponState;
import com.freshlol.siftweapons.event.KillUpgradeHandler;
import com.freshlol.siftweapons.event.PlayerJoinHandler;
import com.freshlol.siftweapons.event.SiftMaceHandler;
import com.freshlol.siftweapons.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SiftWeaponsMod implements ModInitializer {
    public static final String MOD_ID = "siftweapons";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Sift Weapons (Freshlol style) loading...");

        ModItems.register();

        // Player joins → give Level 1 Spear + Level 1 Mace
        ServerPlayConnectionEvents.JOIN.register(PlayerJoinHandler::onJoin);

        // Kill with spear/mace → upgrade
        ServerLivingEntityEvents.AFTER_DEATH.register(KillUpgradeHandler::onDeath);

        // Sift Mace: dash on use + Strength/Speed while in inventory
        SiftMaceHandler.register();

        // Keep global counters loaded
        ServerTickEvents.START_SERVER_TICK.register(server -> {
            if (server.getOverworld() != null) {
                WeaponState.get(server.getOverworld());
            }
        });

        LOGGER.info("Sift Weapons ready. 14 levels, limited God/Sift weapons.");
    }
}
