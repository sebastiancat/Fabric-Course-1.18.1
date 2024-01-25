package net.bastioncat.mccourse.item;

import net.bastioncat.mccourse.MCCourseMod;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup COURSE = FabricItemGroupBuilder.build(new Identifier(MCCourseMod.Mod_ID, "course"),
    () -> new ItemStack(ModItems.ORICHALCUM_INGOT));
}
