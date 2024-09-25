package com.happysg.firefight;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {
	public static final String MOD_ID = "firefight";
	public static final String MOD_NAME = "FireFight";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	public static ResourceLocation rl(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}