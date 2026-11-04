package com.freshlol.siftweapons.item;

import com.freshlol.siftweapons.SiftWeaponsMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item LEVELLED_MACE = register("levelled_mace",
            new LevelledWeaponItem(new Item.Settings().maxCount(1), true));
    public static final Item LEVELLED_SPEAR = register("levelled_spear",
            new LevelledWeaponItem(new Item.Settings().maxCount(1), false));
    public static final Item SIFT_MACE = register("sift_mace",
            new SiftMaceItem(new Item.Settings().maxCount(1).fireproof()));
    public static final Item GOD_MACE = register("god_mace",
            new GodWeaponItem(new Item.Settings().maxCount(1).fireproof(), true));
    public static final Item GOD_SPEAR = register("god_spear",
            new GodWeaponItem(new Item.Settings().maxCount(1).fireproof(), false));

    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(SiftWeaponsMod.MOD_ID, name), item);
    }

    public static void register() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(LEVELLED_MACE);
            entries.add(LEVELLED_SPEAR);
            entries.add(SIFT_MACE);
            entries.add(GOD_MACE);
            entries.add(GOD_SPEAR);
        });
        SiftWeaponsMod.LOGGER.info("Registered Sift Weapons items");
    }
}
