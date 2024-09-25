package com.happysg.firefight.platform;


import com.happysg.firefight.Constants;
import com.happysg.firefight.network.MultiloaderPacket;
import com.happysg.firefight.platform.services.INetworkingHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import java.util.function.Consumer;

/**
 * NeoForge service implementation for GeckoLib's networking functionalities
 */
public class NeoForgeNetworking implements INetworkingHelper {
    private static PayloadRegistrar registrar = null;

    public static void init(IEventBus modBus) {
        modBus.addListener((Consumer<RegisterPayloadHandlersEvent>) event -> {
            registrar = event.registrar(Constants.MOD_ID);
            INetworkingHelper.init();
            registrar = null;
        });
    }

    /**
     * <p>
     * <b><u>FOR GECKOLIB USE ONLY</u></b>
     */
    @Override
    public <B extends FriendlyByteBuf, P extends MultiloaderPacket> void registerPacketInternal(CustomPacketPayload.Type<P> payloadType, StreamCodec<B, P> codec, boolean isClientBound) {
        if (isClientBound) {
            registrar.playToClient(payloadType, (StreamCodec<FriendlyByteBuf, P>) codec, (packet, context) -> packet.receiveMessage(context.player(), context::enqueueWork));
        } else {
            registrar.playToServer(payloadType, (StreamCodec<FriendlyByteBuf, P>) codec, (packet, context) -> packet.receiveMessage(context.player(), context::enqueueWork));
        }
    }

    /**
     * Send a packet to all players currently tracking a given entity
     * <p>
     * Good as a shortcut for sending a packet to all players that may have an interest in a given entity or its dealings
     * <p>
     * Will also send the packet to the entity itself if the entity is also a player
     */
    @Override
    public void sendToAllPlayersTrackingEntity(MultiloaderPacket packet, Entity trackingEntity) {
        PacketDistributor.sendToPlayersTrackingEntityAndSelf(trackingEntity, packet);
    }

    /**
     * Send a packet to all players tracking a given block position
     */
    @Override
    public void sendToAllPlayersTrackingBlock(MultiloaderPacket packet, ServerLevel level, BlockPos pos) {
        PacketDistributor.sendToPlayersTrackingChunk(level, new ChunkPos(pos), packet);
    }

    /**
     * Send a packet to the given player
     */
    @Override
    public void sendToPlayer(MultiloaderPacket packet, ServerPlayer player) {
        PacketDistributor.sendToPlayer(player, packet);
    }

    @Override
    public void sendToAllPlayer(MultiloaderPacket packet, ServerLevel level) {
        PacketDistributor.sendToAllPlayers(packet);
    }

    @Override
    public void sendToServer(MultiloaderPacket packet) {
        PacketDistributor.sendToServer(packet);
    }
}
