package net.sebastiancat.mccourse.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class SpeedyLevelBlock extends Block {
    public static final IntProperty SPEED = IntProperty.of("speed", 0, 4);

    public SpeedyLevelBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(SPEED, 0));
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if(!world.isClient()) {
            if(entity instanceof LivingEntity) {
                if(state.get(SPEED) != 0) {
                    LivingEntity livingEntity = ((LivingEntity) entity);
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, state.get(SPEED) - 1));
                }
            }
        }
        super.onSteppedOn(world, pos, state, entity);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        boolean bl = world.isReceivingRedstonePower(pos);
        if(bl) {
            world.setBlockState(pos, state.cycle(SPEED));
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        if(!world.isClient && hand == Hand.MAIN_HAND) {
            world.setBlockState(pos, state.cycle(SPEED));
        }

        return ActionResult.PASS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SPEED);
    }


}
