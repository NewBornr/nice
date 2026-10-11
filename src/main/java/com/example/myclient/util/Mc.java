package com.example.myclient.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.status.ChunkStatus;

/** Small helpers so version-specific calls live in one place. */
public final class Mc {
    private Mc() {}

    /** Currently open screen, or null. (26.2: Minecraft.screen moved to Minecraft.gui.screen()) */
    public static Screen screen() { return Minecraft.getInstance().gui.screen(); }

    /** Opens a screen. (26.2: setScreen was renamed setScreenAndShow) */
    public static void setScreen(Screen screen) { Minecraft.getInstance().setScreenAndShow(screen); }

    public static void say(String message) {
        var player = Minecraft.getInstance().player;
        if (player != null) player.displayClientMessage(Component.literal(message), false);
    }

    /** Loaded client chunk or null. */
    public static LevelChunk chunk(int cx, int cz) {
        var level = Minecraft.getInstance().level;
        if (level == null) return null;
        ChunkAccess c = level.getChunkSource().getChunk(cx, cz, ChunkStatus.FULL, false);
        return c instanceof LevelChunk lc ? lc : null;
    }
}
