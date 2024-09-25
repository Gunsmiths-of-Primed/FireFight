package com.happysg.firefight;

import com.happysg.firefight.platform.FabricRegistrationHelper;
import com.happysg.firefight.platform.services.INetworkingHelper;
import net.fabricmc.api.ModInitializer;

public class FabricMod implements ModInitializer {
    
    @Override
    public void onInitialize() {
        Constants.LOGGER.info("Init Fabric FireFight!");
        CommonClass.init();
        INetworkingHelper.init();
        FabricRegistrationHelper.register();
    }
}
