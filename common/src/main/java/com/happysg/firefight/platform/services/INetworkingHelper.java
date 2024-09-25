package com.happysg.firefight.platform.services;


import com.happysg.firefight.network.MultiloaderPacket;
import com.happysg.firefight.platform.Services;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.ApiStatus;

/**
 * Loader-agnostic service interface for GeckoLib's networking functionalities
 * Credit GeckoLib
 */
public interface INetworkingHelper {
    static void init() {

    }

    /**
     * Register a GeckoLib packet for use
     */
    @ApiStatus.Internal
    private static <B extends FriendlyByteBuf, P extends MultiloaderPacket> void registerPacket(CustomPacketPayload.Type<P> payloadType, StreamCodec<B, P> codec, boolean isClientBound) {
        Services.NETWORKING.registerPacketInternal(payloadType, codec, isClientBound);
    }

    /**
     * Register a packet for use
     */
    @ApiStatus.Internal
    <B extends FriendlyByteBuf, P extends MultiloaderPacket> void registerPacketInternal(CustomPacketPayload.Type<P> payloadType, StreamCodec<B, P> codec, boolean isClientBound);

    /**
     * Send a packet to all players currently tracking a given entity
     * <p>
     * Good as a shortcut for sending a packet to all players that may have an interest in a given entity or its dealings
     * <p>
     * Will also send the packet to the entity itself if the entity is also a player
     */
    void sendToAllPlayersTrackingEntity(MultiloaderPacket packet, Entity trackingEntity);

    /**
     * Send a packet to all players tracking a given block position
     */
    void sendToAllPlayersTrackingBlock(MultiloaderPacket packet, ServerLevel level, BlockPos pos);

    /**
     * Send a packet to the given player
     */
    void sendToPlayer(MultiloaderPacket packet, ServerPlayer player);


    void sendToAllPlayer(MultiloaderPacket packet, ServerLevel level);

    void sendToServer(MultiloaderPacket packet);
}
