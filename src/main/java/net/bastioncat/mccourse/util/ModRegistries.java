package net.bastioncat.mccourse.util;

import net.bastioncat.mccourse.MCCourseMod;
import net.bastioncat.mccourse.item.ModItems;
import net.fabricmc.fabric.api.registry.FuelRegistry;

public class ModRegistries {
    public static void registerModStuffs() {
    registerFuels();
    }

    private static void registerFuels() {
        System.out.println("Registering Fuels for + " + MCCourseMod.Mod_ID);
        FuelRegistry registry = FuelRegistry.INSTANCE;


        registry.add(ModItems.COAL_SLIVER, 400);
    }
}
