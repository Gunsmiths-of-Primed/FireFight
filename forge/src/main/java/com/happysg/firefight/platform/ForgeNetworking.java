package com.happysg.firefight.platform;


import com.happysg.firefight.Constants;
import com.happysg.firefight.network.MultiloaderPacket;
import com.happysg.firefight.platform.services.INetworkingHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.Channel;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.payload.PayloadProtocol;

/**
 * Forge service implementation for networking functionalities
 * Credit GeckoLib
 */
public final class ForgeNetworking implements INetworkingHelper {
    public static PayloadProtocol<RegistryFriendlyByteBuf, CustomPacketPayload> NETWORK_CHANNEL_BUILDER = ChannelBuilder.named(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "main")).networkProtocolVersion(1).optional().payloadChannel().play();
    public static Channel<CustomPacketPayload> CHANNEL;

    public static void init() {
        INetworkingHelper.init();

        CHANNEL = NETWORK_CHANNEL_BUILDER.bidirectional().build();
    }

    /**
     * Register a GeckoLib packet for use
     * <p>
     * <b><u>FOR GECKOLIB USE ONLY</u></b>
     */
    @Override
    public <B extends FriendlyByteBuf, P extends MultiloaderPacket> void registerPacketInternal(CustomPacketPayload.Type<P> packetType, StreamCodec<B, P> codec, boolean isClientBound) {
        if (isClientBound) {
            NETWORK_CHANNEL_BUILDER.clientbound().add(packetType, (StreamCodec<RegistryFriendlyByteBuf, P>) codec, (packet, context) -> {
                packet.receiveMessage(context.getSender() != null ? context.getSender() : Minecraft.getInstance().player, context::enqueueWork);
                context.setPacketHandled(true);
            });
        } else {
            NETWORK_CHANNEL_BUILDER.serverbound().add(packetType, (StreamCodec<RegistryFriendlyByteBuf, P>) codec, (packet, context) -> {
                packet.receiveMessage(context.getSender() != null ? context.getSender() : Minecraft.getInstance().player, context::enqueueWork);
                context.setPacketHandled(true);
            });
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
        CHANNEL.send(packet, PacketDistributor.TRACKING_ENTITY_AND_SELF.with(trackingEntity));
    }

    /**
     * Send a packet to all players tracking a given block position
     */
    @Override
    public void sendToAllPlayersTrackingBlock(MultiloaderPacket packet, ServerLevel level, BlockPos pos) {
        CHANNEL.send(packet, PacketDistributor.TRACKING_CHUNK.with(level.getChunkAt(pos)));
    }

    /**
     * Send a packet to the given player
     */
    @Override
    public void sendToPlayer(MultiloaderPacket packet, ServerPlayer player) {
        CHANNEL.send(packet, PacketDistributor.PLAYER.with(player));
    }

    @Override
    public void sendToAllPlayer(MultiloaderPacket packet, ServerLevel level) {
        CHANNEL.send(packet, PacketDistributor.ALL.noArg());
    }

    @Override
    public void sendToServer(MultiloaderPacket packet) {
        CHANNEL.send(packet, PacketDistributor.SERVER.noArg());
    }
}
