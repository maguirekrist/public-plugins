package com.maguirekrist.magfighter;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("magfighter")
public interface FighterPluginConfig extends Config {
    @ConfigSection(
            keyName = "bot",
            position = 0,
            name = "Bot Config",
            description = "Configure the automation"
    )
    String config = "Bot Config";
    @ConfigItem(
            position = 0,
            keyName = "monsterToAttack",
            name = "Monster to Attack",
            description = "The monster to attack",
            section = config
    )
    default String monsterToAttack() { return ""; }
}
