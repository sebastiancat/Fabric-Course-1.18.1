package net.bastioncat.mccourse.util;

import net.bastioncat.mccourse.MCCourseMod;
import net.bastioncat.mccourse.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class ModModelPredicateProvider {
    public static void registerModModels() {
        FabricModelPredicateProviderRegistry.register(ModItems.DATA_TABLET, new Identifier(MCCourseMod.Mod_ID, "on"),
                ((stack, world, entity, seed) -> stack.hasNbt() ? 1f: 0f));
    }
}
