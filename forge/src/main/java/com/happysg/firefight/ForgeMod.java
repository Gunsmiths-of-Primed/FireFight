package com.happysg.firefight;

import net.minecraftforge.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class ForgeMod {

    public ForgeMod() {

        Constants.LOG.info("Init FireFight Forge!");
        CommonClass.init();

    }
}