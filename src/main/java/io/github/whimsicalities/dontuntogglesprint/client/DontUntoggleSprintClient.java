package io.github.whimsicalities.dontuntogglesprint.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;

@Environment(EnvType.CLIENT)
public class DontUntoggleSprintClient implements ClientModInitializer {

    private static boolean sprintToggledOnLastDeath = false;

    public static void handleResetHead(){
        Minecraft minecraft = Minecraft.getInstance();
        sprintToggledOnLastDeath = (minecraft.options.keySprint.isDown() && minecraft.options.toggleSprint().get());
    }

    public static void handleResetTail(){
        Minecraft mc = Minecraft.getInstance();
        mc.options.keySprint.setDown(sprintToggledOnLastDeath);
    }

    public void onInitializeClient(){}
}
