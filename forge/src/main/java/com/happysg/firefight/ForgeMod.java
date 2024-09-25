package com.happysg.firefight;

import com.happysg.firefight.platform.ForgeNetworking;
import com.happysg.firefight.registry.ModEntities;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class ForgeMod {

    public ForgeMod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        Constants.LOGGER.info("Init FireFight Forge!");
        CommonClass.init();
        ForgeNetworking.init();
        ForgeRegistry.register(modEventBus);
        modEventBus.addListener(ForgeMod::onClientSetup);
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        ModEntities.registerRenderers();
    }
}