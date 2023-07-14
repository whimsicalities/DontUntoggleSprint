package io.github.whimsicalities.dontuntogglesprint.mixin;

import net.minecraft.client.KeyMapping;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.whimsicalities.dontuntogglesprint.client.DontUntoggleSprintClient;

@Mixin(KeyMapping.class)
public abstract class ResetToggleMixinTail implements Comparable<KeyMapping> {

    @Inject(at=@At("TAIL"),method="resetToggleKeys")
    private static void resetToggleKeys(CallbackInfo ci) {
        DontUntoggleSprintClient.handleResetTail();
    }
}
