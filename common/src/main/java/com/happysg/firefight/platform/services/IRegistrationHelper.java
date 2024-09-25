package com.happysg.firefight.platform.services;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public interface IRegistrationHelper {

    <T extends EntityType<?>> Supplier<T> registerEntityType(String name, Supplier<T> supplier);

    <T extends Item> Supplier<T> registerItem(String name, Supplier<T> supplier);

    <T extends Entity> void registerEntityRenderer(Supplier<EntityType<T>> entityType, EntityRendererProvider<T> renderer);
}
