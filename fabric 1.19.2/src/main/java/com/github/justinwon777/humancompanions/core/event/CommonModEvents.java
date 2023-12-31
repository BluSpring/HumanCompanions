package com.github.justinwon777.humancompanions.core.event;

import com.github.justinwon777.humancompanions.HumanCompanions;
import com.github.justinwon777.humancompanions.core.EntityInit;
import com.github.justinwon777.humancompanions.entity.Arbalist;
import com.github.justinwon777.humancompanions.entity.Archer;
import com.github.justinwon777.humancompanions.entity.Axeguard;
import com.github.justinwon777.humancompanions.entity.Knight;
import dev.architectury.registry.level.entity.EntityAttributeRegistry;

public class CommonModEvents {
    public static void init() {
        EntityAttributeRegistry.register(EntityInit.Knight, Knight::createAttributes);
        EntityAttributeRegistry.register(EntityInit.Archer, Archer::createAttributes);
        EntityAttributeRegistry.register(EntityInit.Arbalist, Arbalist::createAttributes);
        EntityAttributeRegistry.register(EntityInit.Axeguard, Axeguard::createAttributes);
    }
}
