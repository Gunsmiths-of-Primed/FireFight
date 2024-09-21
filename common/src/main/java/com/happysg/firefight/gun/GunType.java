package com.happysg.firefight.gun;

import net.minecraft.resources.ResourceLocation;

public record GunType(ResourceLocation id, boolean automatic, int chargeUpTicks, int reloadTicks, int cooldownTicks,
                      float kickback, float aimFov) {
}