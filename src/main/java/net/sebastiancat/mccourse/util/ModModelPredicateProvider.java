package net.sebastiancat.mccourse.util;

import net.sebastiancat.mccourse.MCCourseMod;
import net.sebastiancat.mccourse.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

@SuppressWarnings("UnreachableCode")
public class ModModelPredicateProvider {
    public static void registerModModels() {
        FabricModelPredicateProviderRegistry.register(ModItems.DATA_TABLET, new Identifier(MCCourseMod.Mod_ID, "on"),
                ((stack, world, entity, seed) -> stack.hasNbt() ? 1f: 0f));
        FabricModelPredicateProviderRegistry.register(ModItems.PERSONAL_SPEED_CONTROLLER, new Identifier(MCCourseMod.Mod_ID, "speed"),
                (stack, world, entity, seed) -> stack.hasNbt() ? (float) stack.getNbt().getInt("speed")/4: 0f);
        FabricModelPredicateProviderRegistry.register(ModItems.PERSONAL_SPEED_CONTROLLER, new Identifier(MCCourseMod.Mod_ID, "on"),
                (stack, world, entity, seed) -> stack.hasNbt() ? stack.getNbt().getBoolean("on") ? 1f: 0f: 0f);
    }
}
