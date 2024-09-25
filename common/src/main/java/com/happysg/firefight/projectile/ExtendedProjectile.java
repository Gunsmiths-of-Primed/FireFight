package com.happysg.firefight.projectile;

import com.happysg.firefight.registry.ModEntities;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

public class ExtendedProjectile extends AbstractHurtingProjectile {
    int damage;
    float speed;
    float armorPenetration;
    int lifeTicksRemaining = 50;


    public ExtendedProjectile(EntityType<? extends AbstractHurtingProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        setNoGravity(true);
    }

    public static ExtendedProjectile create(Level pLevel, ProjectileType pType) {
        ExtendedProjectile projectile = new ExtendedProjectile(ModEntities.SIMPLE_PROJECTILE.get(), pLevel);
        projectile.damage = pType.damage();
        projectile.speed = pType.speed();
        projectile.armorPenetration = pType.armorPenetration();
        return projectile;
    }

    public DamageSource getDamageSource() {
        LivingEntity owner = this.getOwner() instanceof LivingEntity ? (LivingEntity) this.getOwner() : null;
        return level().damageSources().mobProjectile(this, owner);
    }


    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if (pResult.getEntity() != this.getOwner()) {
            pResult.getEntity().hurt(getDamageSource(), damage);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide() && lifeTicksRemaining > 0) {
            if (--lifeTicksRemaining <= 0) {
                this.kill();
            }
        }

    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        this.kill();
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Nullable
    @Override
    protected ParticleOptions getTrailParticle() {
        return null;
    }
}
