package net.danielgolan.elderion;

import net.danielgolan.elderion.library.Author;

public enum ElderionAuthor implements Author {
    canedpeanutshels, daniel_golan, crystalline_robin;

    @Override
    public String modID() {
        return Elderion.MOD_ID;
    }
}
