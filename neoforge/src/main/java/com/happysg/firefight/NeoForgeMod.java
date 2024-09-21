package com.happysg.firefight;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class NeoForgeMod {

    public NeoForgeMod(IEventBus eventBus) {
        Constants.LOGGER.info("Init FireFight NeoForge!");
        CommonClass.init();
    }
}