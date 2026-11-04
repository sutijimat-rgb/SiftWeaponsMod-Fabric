package com.freshlol.siftweapons.data;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;

public class WeaponState extends PersistentState {
    private int siftMaceCount = 0;
    private int godMaceCount = 0;
    private int godSpearCount = 0;

    public static WeaponState get(ServerWorld world) {
        PersistentStateManager manager = world.getPersistentStateManager();
        return manager.getOrCreate(TYPE, "siftweapons_state");
    }

    private static final Type<WeaponState> TYPE = new Type<>(
            WeaponState::new,
            WeaponState::fromNbt,
            null
    );

    public WeaponState() {}

    public static WeaponState fromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup lookup) {
        WeaponState state = new WeaponState();
        state.siftMaceCount = nbt.getInt("SiftMaceCount");
        state.godMaceCount = nbt.getInt("GodMaceCount");
        state.godSpearCount = nbt.getInt("GodSpearCount");
        return state;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup lookup) {
        nbt.putInt("SiftMaceCount", siftMaceCount);
        nbt.putInt("GodMaceCount", godMaceCount);
        nbt.putInt("GodSpearCount", godSpearCount);
        return nbt;
    }

    public boolean canCraftSiftMace() {
        return siftMaceCount < 2;
    }

    public void incrementSiftMace() {
        siftMaceCount++;
        markDirty();
    }

    public boolean canCraftGodMace() {
        return godMaceCount < 1;
    }

    public void incrementGodMace() {
        godMaceCount++;
        markDirty();
    }

    public boolean canCraftGodSpear() {
        return godSpearCount < 1;
    }

    public void incrementGodSpear() {
        godSpearCount++;
        markDirty();
    }

    public int getSiftMaceCount() { return siftMaceCount; }
    public int getGodMaceCount() { return godMaceCount; }
    public int getGodSpearCount() { return godSpearCount; }
}
