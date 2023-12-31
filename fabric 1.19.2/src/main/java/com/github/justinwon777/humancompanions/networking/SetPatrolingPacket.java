package com.github.justinwon777.humancompanions.networking;

import com.github.justinwon777.humancompanions.entity.AbstractHumanCompanionEntity;
import me.pepperbell.simplenetworking.C2SPacket;
import me.pepperbell.simplenetworking.S2CPacket;
import me.pepperbell.simplenetworking.SimpleChannel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.util.thread.BlockableEventLoop;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.util.function.Supplier;

public class SetPatrolingPacket implements S2CPacket, C2SPacket {
    private final int entityId;

    public SetPatrolingPacket(int entityId) {
        this.entityId = entityId;
    }

    public static SetPatrolingPacket decode(FriendlyByteBuf buf) {
        return new SetPatrolingPacket(buf.readInt());
    }

    public static void encode(SetPatrolingPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.entityId);
    }

    public int getEntityId() {
        return this.entityId;
    }

    public static void handle(SetPatrolingPacket msg, BlockableEventLoop<?> loop, Player player) {
        loop.execute(() -> {
            if (msg != null) {
                loop.execute(() -> {
                    if (player != null && player.level instanceof ServerLevel) {
                        Entity entity = player.level.getEntity(msg.getEntityId());
                        if (entity instanceof AbstractHumanCompanionEntity) {
                            AbstractHumanCompanionEntity companion = (AbstractHumanCompanionEntity) entity;
                            if (companion.isFollowing()) {
                                companion.setPatrolling(true);
                                companion.setFollowing(false);
                                companion.setGuarding(false);
                                companion.setPatrolPos(companion.blockPosition());
                            } else if (companion.isPatrolling()) {
                                companion.setPatrolling(false);
                                companion.setFollowing(false);
                                companion.setGuarding(true);
                                companion.setPatrolPos(companion.blockPosition());
                            } else {
                                companion.setPatrolling(false);
                                companion.setFollowing(true);
                                companion.setGuarding(false);
                            }
                        }
                    }
                });
            }
        });
    }

    @Override
    public void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl listener, PacketSender responseSender, SimpleChannel channel) {
        handle(this, server, player);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void handle(Minecraft client, ClientPacketListener listener, PacketSender responseSender, SimpleChannel channel) {
        handle(this, client, client.player);
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        encode(this, buf);
    }
}
