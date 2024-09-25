package com.happysg.firefight.item;

import net.minecraft.world.item.Item;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class GeoGunItem extends Item implements GeoItem {

    public final AnimatableInstanceCache animatableInstanceCache = GeckoLibUtil.createInstanceCache(this);

    public GeoGunItem() {
        super(new Properties().stacksTo(1));
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }


    public static final RawAnimation IDLE_ANIMATION = RawAnimation.begin().then("idle", Animation.LoopType.PLAY_ONCE);
    public static final RawAnimation FIRE_ANIMATION = RawAnimation.begin().then("fire", Animation.LoopType.PLAY_ONCE);
    public static final RawAnimation RELOAD_ANIMATION = RawAnimation.begin().then("reload", Animation.LoopType.PLAY_ONCE);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "gun_controller", 0, state -> PlayState.STOP)
                .triggerableAnim("idle", IDLE_ANIMATION)
                .triggerableAnim("reload", RELOAD_ANIMATION)
                .triggerableAnim("fire", FIRE_ANIMATION));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return animatableInstanceCache;
    }
}
