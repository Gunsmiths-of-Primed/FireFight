package com.happysg.firefight.registry;

import com.happysg.firefight.Constants;
import com.happysg.firefight.item.GeoGunItem;
import com.happysg.firefight.platform.Services;

import java.util.function.Supplier;

public class ModItems {

    public static final Supplier<GeoGunItem> GEO_GUN = Services.REGISTRATION
            .registerItem("geo_gun", GeoGunItem::new);

    public static void register() {
        Constants.LOGGER.info("Registering Mod Items");
    }
}
