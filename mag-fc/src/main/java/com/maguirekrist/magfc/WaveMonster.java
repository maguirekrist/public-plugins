package com.maguirekrist.magfc;

import lombok.AllArgsConstructor;

@AllArgsConstructor
enum WaveMonster
{
    TZ_KIH("Drainer", 22),
    TZ_KEK("Blob", 45),
    TOK_XIL("Range", 90),
    YT_MEJKOT("Melee", 180),
    KET_ZEK("Mage", 360),
    TZKOK_JAD("Jad", 702);

    private final String name;
    private final int level;

    @Override
    public String toString()
    {
        return String.format("%s - Level %s", name, level);
    }
}