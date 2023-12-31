package com.github.justinwon777.humancompanions.mixin;

import net.minecraft.client.gui.components.ImageButton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ImageButton.class)
public interface ImageButtonAccessor {
    @Accessor
    int getXTexStart();

    @Mutable
    @Accessor
    void setXTexStart(int xTexStart);
}
