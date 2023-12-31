package com.github.justinwon777.humancompanions;

import com.github.justinwon777.humancompanions.client.event.ClientModEvents;
import com.github.justinwon777.humancompanions.core.*;
import com.github.justinwon777.humancompanions.core.event.CommonModEvents;
import com.github.justinwon777.humancompanions.entity.CompanionEvents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HumanCompanions implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "humancompanions";

    @Override
    public void onInitialize() {
        EntityInit.ENTITIES.register();
        ItemInit.ITEMS.register();
        StructureInit.DEFERRED_REGISTRY_STRUCTURE.register();
        PacketHandler.register();
        Config.register();

        ConfiguredStructures.registerConfiguredStructures();

        CommonModEvents.init();
        CompanionEvents.init();

        PacketHandler.INSTANCE.initServerListener();

        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            ClientModEvents.init();
            PacketHandler.INSTANCE.initClientListener();
        }
    }
}
