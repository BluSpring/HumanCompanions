package com.github.justinwon777.humancompanions.client.event;

import com.github.justinwon777.humancompanions.client.renderer.CompanionRenderer;
import com.github.justinwon777.humancompanions.core.EntityInit;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;

public final class ClientModEvents {

    private ClientModEvents () {}

    public static void init() {
        EntityRendererRegistry.register(EntityInit.Knight, CompanionRenderer::new);
        EntityRendererRegistry.register(EntityInit.Archer, CompanionRenderer::new);
        EntityRendererRegistry.register(EntityInit.Arbalist, CompanionRenderer::new);
        EntityRendererRegistry.register(EntityInit.Axeguard, CompanionRenderer::new);
    }
}
