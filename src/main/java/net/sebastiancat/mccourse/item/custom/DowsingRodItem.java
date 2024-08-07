package net.sebastiancat.mccourse.item.custom;

import net.sebastiancat.mccourse.item.ModItems;
import net.sebastiancat.mccourse.util.InventoryUtil;
import net.sebastiancat.mccourse.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class DowsingRodItem extends Item {
    public DowsingRodItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getWorld().isClient()) {
            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            boolean foundBlock = false;

            for(int i = 0; i <= positionClicked.getY(); i++) {
                Block blockBelow = context.getWorld().getBlockState(positionClicked.down(i)).getBlock();

                if(isValuableBlock(blockBelow)) {
                    outputValuableCoordinates(positionClicked.add(0, -i, 0), player, blockBelow);
                    foundBlock = true;

                    if(InventoryUtil.hasPlayerStackInInventory(player, ModItems.DATA_TABLET)) {
                        addNbtToDataTablet(player, positionClicked.add(0, -i, 0), blockBelow);
                    }

                    break;
                }
            }

            if(!foundBlock) {
                player.sendMessage(new TranslatableText("item.mccourse.dowsing_rod.no_valuables"), false);
            }

        }

        context.getStack().damage(1, context.getPlayer(),
                (player) -> player.sendToolBreakStatus(player.getActiveHand()));

        return super.useOnBlock(context);
    }

    private void addNbtToDataTablet(PlayerEntity player, BlockPos pos, Block blockBelow) {
        ItemStack dataTablet =
                player.getInventory().getStack(InventoryUtil.getFirstInventoryIndex(player, ModItems.DATA_TABLET));

        NbtCompound nbtData = new NbtCompound();
        nbtData.putInt("posX", pos.getX());
        nbtData.putInt("posY", pos.getY());
        nbtData.putInt("posZ", pos.getZ());
        nbtData.putString("oreType", blockBelow.asItem().getName().getString());
        dataTablet.setNbt(nbtData);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslatableText("item.mccourse.dowsing_rod.tooltip.shift"));
        } else {
            tooltip.add(new TranslatableText("item.mccourse.dowsing_rod.tooltip"));
        }

    }

    private static void outputValuableCoordinates(BlockPos blockPos, PlayerEntity player, Block blockBelow) {
        player.sendMessage(new LiteralText(
                "Found " + blockBelow.asItem().getName().getString() + " at " +
                "(" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"), false);
    }

    private boolean isValuableBlock(Block block) {
        return ModTags.Blocks.DOWSING_ROD_DETECTABLE_BLOCKS.contains(block);
    }


}
