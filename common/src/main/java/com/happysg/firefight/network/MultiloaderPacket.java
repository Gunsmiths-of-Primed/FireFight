package com.happysg.firefight.network;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * Multiloader implementation of a packet base, for loader-agnostic packet handling
 * Credit GeckoLib
 */
public interface MultiloaderPacket extends CustomPacketPayload {
    /**
     * Handle the message after being received and decoded
     * <p>
     * This method is side-agnostic, so make sure you call out to client proxies as needed
     * <p>
     * The player may be null if the packet is being sent before the player loads in
     */
    void receiveMessage(@Nullable Player sender, Consumer<Runnable> workQueue);
}
