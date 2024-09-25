package com.happysg.firefight;

import com.happysg.firefight.network.MultiloaderPacket;
import com.happysg.firefight.registry.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jetbrains.annotations.ApiStatus;

public class FabricClientMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModEntities.registerRenderers();

    }


    @ApiStatus.Internal
    public static <P extends MultiloaderPacket> void registerPacket(CustomPacketPayload.Type<P> packetType) {
        ClientPlayNetworking.registerGlobalReceiver(packetType, (packet, context) -> packet.receiveMessage(context.player(), context.client()::execute));
    }
}
