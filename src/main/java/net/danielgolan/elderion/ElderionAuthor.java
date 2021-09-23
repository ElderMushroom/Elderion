package net.danielgolan.elderion;

public enum ElderionAuthor implements Author {
    canedpeanutshels, DanielGolan, CrystallineRobin;

    @Override
    public String modID() {
        return Elderion.MOD_ID;
    }
}
