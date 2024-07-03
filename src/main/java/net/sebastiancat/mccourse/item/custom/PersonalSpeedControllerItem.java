package net.sebastiancat.mccourse.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;


public class PersonalSpeedControllerItem extends Item {

    public PersonalSpeedControllerItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (stack.hasNbt()) {
            if (stack.getNbt().getBoolean("on")) {
                ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, stack.getNbt().getInt("speed") - 1));
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        if (nbtCompound == null) {
            nbtCompound.putBoolean("on", true);
            nbtCompound.putInt("speed", 1);
        }
        System.out.println(nbtCompound.getBoolean("on"));


        if (user.isSneaking()) {
            nbtCompound.putBoolean("on", !nbtCompound.getBoolean("on"));
        } else {
            int speed = stack.getNbt().getInt("speed");
            int newSpeed;
            if (speed < 4) {
                newSpeed = speed + 1;
            } else {
                newSpeed = 1;
            }

            nbtCompound.putInt("speed", newSpeed);
        }
        return super.use(world, user, hand);
    }
}