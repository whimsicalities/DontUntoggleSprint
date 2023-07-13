package io.github.whimsicalities.dontuntogglesprint.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Environment(EnvType.CLIENT)
public class DontUntoggleSprintClient implements ClientModInitializer {
    
	public static final Logger LOGGER = LogManager.getLogger("modid");

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
                    client.options.keySprint.setDown(true);
                }
            }
        });
    }
    public static void print(String s){
        LOGGER.info(s);
    }
}
