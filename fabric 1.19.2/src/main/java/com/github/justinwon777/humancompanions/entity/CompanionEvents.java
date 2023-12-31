package com.github.justinwon777.humancompanions.entity;

import com.github.justinwon777.humancompanions.HumanCompanions;
import com.github.justinwon777.humancompanions.core.Config;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import io.github.fabricators_of_create.porting_lib.event.common.LivingEntityEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;

public class CompanionEvents {
    public static void init() {
        EntityEvent.LIVING_DEATH.register(((entity, source) -> {
            giveExperience(entity, source);
            return EventResult.pass();
        }));

        LivingEntityEvents.ATTACK.register(CompanionEvents::friendlyFire);
    }

    public static void giveExperience(LivingEntity entity, DamageSource source) {
        if (source.getEntity() instanceof AbstractHumanCompanionEntity companion) {
            companion.giveExperiencePoints(entity.getExperienceReward());
        }
    }

    public static boolean friendlyFire(LivingEntity entity, DamageSource source, float amount) {
        if (source.getEntity() instanceof AbstractHumanCompanionEntity companion && companion.isTame()) {
            if (!Config.FRIENDLY_FIRE_PLAYER.get()) {
                if (entity instanceof Player player) {
                    if (companion.getOwner() == player) {
                        return true;
                    }
                }
            }
            if (!Config.FRIENDLY_FIRE_COMPANIONS.get()) {
                if (entity instanceof TamableAnimal e) {
                    if (e.isTame()) {
                        LivingEntity owner1 = e.getOwner();
                        LivingEntity owner2 = companion.getOwner();
                        if (owner1 == owner2) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}
