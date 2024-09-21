package com.happysg.firefight.projectile;

import net.minecraft.resources.ResourceLocation;

public record ProjectileType(ResourceLocation id, int damage, float speed, float armorPenetration) {
}