package com.happysg.firefight.platform;


import com.happysg.firefight.FabricClientMod;
import com.happysg.firefight.network.MultiloaderPacket;
import com.happysg.firefight.platform.services.INetworkingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

/**
 * Fabric service implementation for networking functionalities
 * Credit GeckoLib
 */
public final class FabricNetworking implements INetworkingHelper {
    /**
     * Register a GeckoLib packet for use
     * <p>
     * <b><u>FOR GECKOLIB USE ONLY</u></b>
     */
    @Override
    public <B extends FriendlyByteBuf, P extends MultiloaderPacket> void registerPacketInternal(CustomPacketPayload.Type<P> payloadType, StreamCodec<B, P> codec, boolean isClientBound) {
        if (isClientBound) {
            PayloadTypeRegistry.playS2C().register(payloadType, (StreamCodec<FriendlyByteBuf, P>) codec);

            if (Services.PLATFORM.isPhysicalClient())
                FabricClientMod.registerPacket(payloadType);
        } else {
            PayloadTypeRegistry.playC2S().register(payloadType, (StreamCodec<FriendlyByteBuf, P>) codec);
            ServerPlayNetworking.registerGlobalReceiver(payloadType, (packet, context) -> packet.receiveMessage(context.player(), context.player().getServer()::execute));
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
        if (trackingEntity instanceof ServerPlayer pl)
            sendToPlayer(packet, pl);

        for (ServerPlayer player : PlayerLookup.tracking(trackingEntity)) {
            sendToPlayer(packet, player);
        }
    }

    /**
     * Send a packet to all players tracking a given block position
     */
    @Override
    public void sendToAllPlayersTrackingBlock(MultiloaderPacket packet, ServerLevel level, BlockPos pos) {
        for (ServerPlayer player : PlayerLookup.tracking(level, pos)) {
            sendToPlayer(packet, player);
        }
    }

    /**
     * Send a packet to the given player
     */
    @Override
    public void sendToPlayer(MultiloaderPacket packet, ServerPlayer player) {
        ServerPlayNetworking.send(player, packet);
    }

    @Override
    public void sendToAllPlayer(MultiloaderPacket packet, ServerLevel level) {
        for (ServerPlayer player : level.players()) {
            sendToPlayer(packet, player);
        }
    }

    @Override
    public void sendToServer(MultiloaderPacket packet) {
        ClientPlayNetworking.send(packet);
    }
}
