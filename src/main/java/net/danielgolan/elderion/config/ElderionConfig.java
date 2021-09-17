package net.danielgolan.elderion.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "elderion")
@Config.Gui.Background("minecraft:textures/block/tuff.png")
public class ElderionConfig implements ModMenuApi, ConfigData {
    @ConfigEntry.Gui.RequiresRestart
    public Resolution resolution;

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> AutoConfig.getConfigScreen(ElderionConfig.class, parent).get();
    }

    public enum Resolution {
        normal, detailed;

        @Override
        public String toString() {
            return switch (this) {
                case normal -> "16pixels";
                case detailed -> "32pixels";
            };
        }
    }
}
