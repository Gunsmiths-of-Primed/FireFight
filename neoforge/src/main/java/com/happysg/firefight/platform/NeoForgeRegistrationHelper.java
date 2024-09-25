package com.happysg.firefight.platform;

import com.happysg.firefight.NeoForgeRegistry;
import com.happysg.firefight.platform.services.IRegistrationHelper;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class NeoForgeRegistrationHelper implements IRegistrationHelper {
    @Override
    public <T extends EntityType<?>> Supplier<T> registerEntityType(String name, Supplier<T> supplier) {
        return NeoForgeRegistry.ENTITY_TYPES.register(name, supplier);
    }

    @Override
    public <T extends Item> Supplier<T> registerItem(String name, Supplier<T> supplier) {
        return NeoForgeRegistry.ITEMS.register(name, supplier);
    }

    @Override
    public <T extends Entity> void registerEntityRenderer(Supplier<EntityType<T>> entityType, EntityRendererProvider<T> renderer) {
        EntityRenderers.register(entityType.get(), renderer);
    }
}
