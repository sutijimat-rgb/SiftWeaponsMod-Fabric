package com.freshlol.siftweapons.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class SiftMaceItem extends Item {
    public SiftMaceItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient) {
            Vec3d look = user.getRotationVector();
            double power = 2.8;
            user.addVelocity(look.x * power, 1.35 + look.y * 0.6, look.z * power);
            user.velocityModified = true;

            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ENTITY_WIND_CHARGE_WIND_BURST, SoundCategory.PLAYERS, 1.0f, 0.8f);
        }
        return TypedActionResult.success(stack, world.isClient());
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.literal("Only 2 can exist").formatted(Formatting.RED));
        tooltip.add(Text.literal("Right-click: Powerful Dash").formatted(Formatting.AQUA));
        tooltip.add(Text.literal("In inventory: Strength III + Speed III").formatted(Formatting.GREEN));
        tooltip.add(Text.literal("Breach IV · Density V · Fire Aspect III · Unbreakable").formatted(Formatting.GRAY));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
