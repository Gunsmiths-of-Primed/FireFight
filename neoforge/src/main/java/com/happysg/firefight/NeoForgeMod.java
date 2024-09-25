package com.happysg.firefight;


import com.happysg.firefight.platform.NeoForgeNetworking;
import com.happysg.firefight.registry.ModEntities;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@Mod(Constants.MOD_ID)
public class NeoForgeMod {

    public NeoForgeMod(IEventBus eventBus) {
        Constants.LOGGER.info("Init FireFight NeoForge!");
        CommonClass.init();
        NeoForgeNetworking.init(eventBus);
        NeoForgeRegistry.register(eventBus);
        eventBus.addListener(NeoForgeMod::onClientSetup);
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        ModEntities.registerRenderers();
    }
}