package com.github.justinwon777.humancompanions.core;

import com.github.justinwon777.humancompanions.HumanCompanions;
import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.LazySpawnEggItem;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.minecraft.core.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class ItemInit {

    public static final LazyRegistrar<Item> ITEMS = LazyRegistrar.create(Registry.ITEM,
            HumanCompanions.MOD_ID);

    public static final RegistryObject<LazySpawnEggItem> Arbalist_Spawn_Egg = ITEMS.register("arbalist_spawn_egg",
            () -> new LazySpawnEggItem(EntityInit.Arbalist,0xE8AF5A, 0xFF0000,
                    new Item.Properties().stacksTo(64).tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<LazySpawnEggItem> Archer_Spawn_Egg = ITEMS.register("archer_spawn_egg",
            () -> new LazySpawnEggItem(EntityInit.Archer,0xE8AF5A, 0x0000FF,
                    new Item.Properties().stacksTo(64).tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<LazySpawnEggItem> Axeguard_Spawn_Egg = ITEMS.register("axeguard_spawn_egg",
            () -> new LazySpawnEggItem(EntityInit.Axeguard,0xE8AF5A, 0x00FF00,
                    new Item.Properties().stacksTo(64).tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<LazySpawnEggItem> Knight_Spawn_Egg = ITEMS.register("knight_spawn_egg",
            () -> new LazySpawnEggItem(EntityInit.Knight,0xE8AF5A, 0xFFFF00,
                    new Item.Properties().stacksTo(64).tab(CreativeModeTab.TAB_MISC)));
}
