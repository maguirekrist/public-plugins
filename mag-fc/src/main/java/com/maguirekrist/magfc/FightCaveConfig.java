package com.maguirekrist.magfc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.runelite.client.config.*;

import java.awt.*;

@ConfigGroup("fightcave")
public interface FightCaveConfig extends Config
{
    @ConfigSection(
            keyName = "bot",
            position = 0,
            name = "Bot Config",
            description = "Configure the automation"
    )
    String config = "Bot Config";
    @ConfigItem(
            position = 0,
            keyName = "flick",
            name = "Flick Prayers",
            description = "The prayers will be flicked, otherwise leaves last prayer on",
            section = config
    )
    default boolean flick() { return false; }

    @ConfigItem(
            position = 1,
            keyName = "drinkRestore",
            name = "Drink Restore pots",
            description = "The restore pots will be drank",
            section = config
    )
    default boolean drinkRestore() { return false; }

    @Range(
            min = 1,
            max = 98
    )
    @ConfigItem(
            position = 2,
            hidden = true,
            unhide = "drinkRestore",
            keyName = "restoreSetpoint",
            name = "Prayer Setpoint",
            description = "When prayer <= this value then sip a restore",
            section = config
    )
    default int prayerSetpoint()
    {
        return 15;
    }
    @ConfigItem(
            position = 3,
            keyName = "drinkSara",
            name = "Drink Saradomin Brews",
            description = "The sara brews will be drank",
            section = config
    )
    default boolean drinkSara() { return false; }

    @Range(
            min = 1,
            max = 98
    )
    @ConfigItem(
            position = 4,
            hidden = true,
            unhide = "drinkSara",
            keyName = "healSetpoint",
            name = "Prayer Setpoint",
            description = "When prayer <= this value then sip a restore",
            section = config
    )
    default int healSetpoint()
    {
        return 75;
    }

    @ConfigItem(
            position = 5,
            keyName = "drinkRanging",
            name = "Drink Ranging Pots",
            description = "The Ranging pots will be drank at max ranged",
            section = config
    )
    default boolean drinkRanging() { return false; }


    @ConfigSection(
            keyName = "text",
            position = 1,
            name = "Text",
            description = ""
    )
    String text = "Text";

    @ConfigItem(
            position = 2,
            keyName = "fontStyle",
            name = "Font Style",
            description = "Plain | Bold | Italics",
            section = text
    )
    default FontStyle fontStyle()
    {
        return FontStyle.BOLD;
    }

    @Range(
            min = 14,
            max = 40
    )
    @ConfigItem(
            position = 3,
            keyName = "textSize",
            name = "Text Size",
            description = "Text Size for Timers.",
            section = text
    )
    @Units(Units.POINTS)
    default int textSize()
    {
        return 32;
    }

    @ConfigItem(
            position = 4,
            keyName = "shadows",
            name = "Shadows",
            description = "Adds Shadows to text.",
            section = text
    )
    default boolean shadows()
    {
        return false;
    }

    @Getter
    @AllArgsConstructor
    enum FontStyle
    {
        BOLD("Bold", Font.BOLD),
        ITALIC("Italic", Font.ITALIC),
        PLAIN("Plain", Font.PLAIN);

        private String name;
        private int font;

        @Override
        public String toString()
        {
            return getName();
        }
    }
}