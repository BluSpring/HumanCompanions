package com.github.justinwon777.humancompanions.core;

import com.github.justinwon777.humancompanions.HumanCompanions;
import com.github.justinwon777.humancompanions.client.CompanionScreen;
import com.github.justinwon777.humancompanions.container.CompanionContainer;
import com.github.justinwon777.humancompanions.entity.AbstractHumanCompanionEntity;
import com.github.justinwon777.humancompanions.networking.*;
import me.pepperbell.simplenetworking.SimpleChannel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class PacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE =
            new SimpleChannel(new ResourceLocation(HumanCompanions.MOD_ID, "main"));

    public static void register() {
        int id = 0;
        INSTANCE.registerC2SPacket(OpenInventoryPacket.class, id++, OpenInventoryPacket::decode);
        INSTANCE.registerS2CPacket(OpenInventoryPacket.class, id++, OpenInventoryPacket::decode);

        INSTANCE.registerC2SPacket(SetAlertPacket.class, id++, SetAlertPacket::decode);
        INSTANCE.registerS2CPacket(SetAlertPacket.class, id++, SetAlertPacket::decode);

        INSTANCE.registerC2SPacket(SetHuntingPacket.class, id++, SetHuntingPacket::decode);
        INSTANCE.registerS2CPacket(SetHuntingPacket.class, id++, SetHuntingPacket::decode);

        INSTANCE.registerC2SPacket(SetPatrolingPacket.class, id++, SetPatrolingPacket::decode);
        INSTANCE.registerS2CPacket(SetPatrolingPacket.class, id++, SetPatrolingPacket::decode);

        INSTANCE.registerC2SPacket(ClearTargetPacket.class, id++, ClearTargetPacket::decode);
        INSTANCE.registerS2CPacket(ClearTargetPacket.class, id++, ClearTargetPacket::decode);

        INSTANCE.registerC2SPacket(SetStationeryPacket.class, id++, SetStationeryPacket::decode);
        INSTANCE.registerS2CPacket(SetStationeryPacket.class, id++, SetStationeryPacket::decode);

        INSTANCE.registerC2SPacket(ReleasePacket.class, id++, ReleasePacket::decode);
        INSTANCE.registerS2CPacket(ReleasePacket.class, id++, ReleasePacket::decode);
    }

    @SuppressWarnings("resource")
    @Environment(EnvType.CLIENT)
    public static void openInventory(OpenInventoryPacket packet) {
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            Entity entity = player.level.getEntity(packet.getEntityId());
            if (entity instanceof AbstractHumanCompanionEntity) {
                AbstractHumanCompanionEntity companion = (AbstractHumanCompanionEntity) entity;
                LocalPlayer clientplayerentity = Minecraft.getInstance().player;
                CompanionContainer container = new CompanionContainer(packet.getId(), player.getInventory(), companion.inventory);
                clientplayerentity.containerMenu = container;
                Minecraft.getInstance().setScreen(new CompanionScreen(container, player.getInventory(), companion));
            }
        }
    }
}
