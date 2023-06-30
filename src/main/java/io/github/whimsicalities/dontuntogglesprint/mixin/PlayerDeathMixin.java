package io.github.whimsicalities.dontuntogglesprint.mixin;

import com.mojang.authlib.GameProfile;
import io.github.whimsicalities.dontuntogglesprint.client.DontUntoggleSprintClient;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class PlayerDeathMixin extends AbstractClientPlayer {
    public PlayerDeathMixin(ClientLevel clientLevel, GameProfile gameProfile) {
        super(clientLevel, gameProfile);
    }

    @Inject(at = @At("TAIL"), method = "hurtTo")
    protected void hurtTo(float health, CallbackInfo ci) {
        if (this.getHealth() <= 0) {
            DontUntoggleSprintClient.handleDeath();
        }
    }
}