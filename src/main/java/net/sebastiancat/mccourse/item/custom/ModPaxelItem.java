package net.sebastiancat.mccourse.item.custom;

import net.sebastiancat.mccourse.util.ModTags;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;

public class ModPaxelItem extends MiningToolItem {
    public ModPaxelItem(ToolMaterial material, float attackDamage, float attackSpeed,  Settings settings) {
        super(attackDamage, attackSpeed, material, ModTags.Blocks.PAXEL_MINEABLE, settings);
    }
}
