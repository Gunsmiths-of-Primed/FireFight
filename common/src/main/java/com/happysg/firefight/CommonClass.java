package com.happysg.firefight;

import com.happysg.firefight.registry.ModEntities;
import com.happysg.firefight.registry.ModItems;

public class CommonClass {
    public static void init() {
        Constants.LOGGER.info("FireFight init");
        ModEntities.register();
        ModItems.register();
    }
}