package com.github.justinwon777.humancompanions.core;

import com.github.justinwon777.humancompanions.HumanCompanions;
import com.github.justinwon777.humancompanions.world.CompanionHouseStructure;
import com.mojang.serialization.Codec;
import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;

public class StructureInit {
    public static final LazyRegistrar<StructureType<?>> DEFERRED_REGISTRY_STRUCTURE =
            LazyRegistrar.create(Registry.STRUCTURE_TYPE_REGISTRY, HumanCompanions.MOD_ID);

    public static final RegistryObject<StructureType<CompanionHouseStructure>> COMPANION_HOUSE =
            DEFERRED_REGISTRY_STRUCTURE.register("companion_house", () -> typeConvert(CompanionHouseStructure.CODEC));

    private static <S extends Structure> StructureType<S> typeConvert(Codec<S> codec) {
        return () -> codec;
    }
}
