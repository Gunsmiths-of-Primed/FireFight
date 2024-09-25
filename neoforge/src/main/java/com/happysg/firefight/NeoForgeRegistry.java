package com.happysg.firefight;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NeoForgeRegistry {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Constants.MOD_ID);
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, Constants.MOD_ID);

    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
        ITEMS.register(bus);
    }
}
