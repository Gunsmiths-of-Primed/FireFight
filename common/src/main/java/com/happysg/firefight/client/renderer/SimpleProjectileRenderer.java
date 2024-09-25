package com.happysg.firefight.client.renderer;

import com.happysg.firefight.projectile.ExtendedProjectile;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SimpleProjectileRenderer extends EntityRenderer<ExtendedProjectile> {

    public SimpleProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(ExtendedProjectile pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight) {
    }

    @Override
    public ResourceLocation getTextureLocation(ExtendedProjectile extendedProjectile) {
        return null;
    }

}
