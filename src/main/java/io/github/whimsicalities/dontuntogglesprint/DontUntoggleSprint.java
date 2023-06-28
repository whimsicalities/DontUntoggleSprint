package io.github.whimsicalities.dontuntogglesprint;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

public class DontUntoggleSprint implements ModInitializer {

    private static boolean sprintToggledOnLastDeath = false;

    public void onInitialize() {
        ClientTickEvents.START_CLIENT_TICK.register(client ->
        {
            if (client.player != null && sprintToggledOnLastDeath && client.player.isAlive() && client.player.input.hasForwardImpulse()) {
                if (client.player.isSprinting()) {
                    sprintToggledOnLastDeath = false;
                } else {
                    KeyMapping.set(client.options.keySprint.getDefaultKey(), true);
                }
            }
        });
    }

    public static void handleDeath(){
        Minecraft minecraft = Minecraft.getInstance();
        sprintToggledOnLastDeath = (minecraft.options.keySprint.isDown() && minecraft.options.toggleSprint().get());
    }
}
