package io.github.whimsicalities.dontuntogglesprint;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.JTextComponent;

public class DontUntoggleSprint implements ModInitializer {

    private static boolean sprintToggledOnLastDeath = false;
    public static final Logger LOGGER = LoggerFactory.getLogger("ClientModInitialiser");

    public void onInitialize() {
        ClientTickEvents.START_CLIENT_TICK.register(client ->
        {
            if (client.player != null && sprintToggledOnLastDeath && client.player.isAlive() && client.player.input.hasForwardImpulse()) {
                if (client.player.isSprinting()) {
                    sprintToggledOnLastDeath = false;
                    LOGGER.info("Setting sprint back to false!!!!!"); //todo remove this
                } else {
                    //client.player.setSprinting(true);
                    KeyMapping.set(client.options.keySprint.getDefaultKey(), true);
                    LOGGER.info("Setting sprint to true!!!!!"); //todo remove this
                }
            }
//            if (client.player!=null) {
//                LOGGER.info("Player is sprinting: " + client.player.isSprinting());
//            }
        });
        LOGGER.info("INITIALISING MOD!!!!!"); //todo remove this
    }

    public static void handleDeath(){
        Minecraft minecraft = Minecraft.getInstance();
        sprintToggledOnLastDeath = (minecraft.options.keySprint.isDown() && minecraft.options.toggleSprint().get());
        LOGGER.info("!!!!!!!!!! Sprint toggle set to "+(minecraft.options.keySprint.isDown() && minecraft.options.toggleSprint().get())); //todo remove this
    }

    public static void handleRespawn(){
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.options.keySprint.setDown(true);
    }
}
