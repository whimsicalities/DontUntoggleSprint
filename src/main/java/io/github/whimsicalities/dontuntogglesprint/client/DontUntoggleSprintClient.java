package io.github.whimsicalities.dontuntogglesprint.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

@Environment(EnvType.CLIENT)
public class DontUntoggleSprintClient implements ClientModInitializer {

    private static boolean sprintToggledOnLastDeath = false;

    public static void handleDeath() {
        Minecraft minecraft = Minecraft.getInstance();
        sprintToggledOnLastDeath = (minecraft.options.keySprint.isDown() && minecraft.options.toggleSprint().get());
    }

    public void onInitializeClient() {
        ClientTickEvents.START_CLIENT_TICK.register(client ->
        {
            if (client.player != null && sprintToggledOnLastDeath && client.player.isAlive() && client.player.input.hasForwardImpulse()) {
                if (client.player.isSprinting()) {
                    sprintToggledOnLastDeath = false;
                } else {
                    KeyMapping.set(KeyBindingHelper.getBoundKeyOf(client.options.keySprint), true);
                }
            }
        });
    }
}
