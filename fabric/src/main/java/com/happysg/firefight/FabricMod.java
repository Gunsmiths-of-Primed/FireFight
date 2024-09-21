package com.happysg.firefight;

import net.fabricmc.api.ModInitializer;

public class FabricMod implements ModInitializer {
    
    @Override
    public void onInitialize() {
        Constants.LOGGER.info("Init Fabric FireFight!");
        CommonClass.init();
    }
}
