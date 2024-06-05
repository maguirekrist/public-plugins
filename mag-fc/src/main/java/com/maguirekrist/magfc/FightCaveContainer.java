package com.maguirekrist.magfc;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.awt.Color;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.runelite.api.Actor;
import net.runelite.api.NPC;
import net.runelite.api.NPCComposition;
import net.runelite.api.NpcID;
import net.runelite.api.Prayer;

import static com.maguirekrist.magfc.FightCavePlugin.KET_ZEK_MAGE_ATTACK;
import static com.maguirekrist.magfc.FightCavePlugin.KET_ZEK_MELEE_ATTACK;
import static com.maguirekrist.magfc.FightCavePlugin.MEJ_KOT_HEAL_ATTACK;
import static com.maguirekrist.magfc.FightCavePlugin.MEJ_KOT_MELEE_ATTACK;
import static com.maguirekrist.magfc.FightCavePlugin.TOK_XIL_MELEE_ATTACK;
import static com.maguirekrist.magfc.FightCavePlugin.TOK_XIL_RANGE_ATTACK;
import static com.maguirekrist.magfc.FightCavePlugin.TZTOK_JAD_MAGIC_ATTACK;
import static com.maguirekrist.magfc.FightCavePlugin.TZTOK_JAD_MELEE_ATTACK;
import static com.maguirekrist.magfc.FightCavePlugin.TZTOK_JAD_RANGE_ATTACK;

import java.awt.*;

@Getter(AccessLevel.PACKAGE)
class FightCaveContainer
{
    private NPC npc;
    private String npcName;
    private int npcIndex;
    private int npcSize;
    private int attackSpeed;
    private int priority;
    private ImmutableSet<Integer> animations;
    @Setter(AccessLevel.PACKAGE)
    private int ticksUntilAttack;
    @Setter(AccessLevel.PACKAGE)
    private Actor npcInteracting;
    @Setter(AccessLevel.PACKAGE)
    private AttackStyle attackStyle;
    @Setter(AccessLevel.PACKAGE)
    private int attackRange;

    FightCaveContainer(NPC npc)
    {
        this.npc = npc;
        this.npcName = npc.getName();
        this.npcIndex = npc.getIndex();
        this.npcInteracting = npc.getInteracting();
        this.attackStyle = AttackStyle.UNKNOWN;
        this.ticksUntilAttack = -1;
        this.attackRange = -1;
        final NPCComposition composition = npc.getTransformedComposition();

        BossMonsters monster = BossMonsters.of(npc.getId());

        if (monster == null)
        {
            throw new IllegalStateException();
        }

        this.animations = monster.animations;
        this.attackStyle = monster.attackStyle;
        this.priority = monster.priority;
        this.attackSpeed = monster.attackSpeed;

        if (composition != null)
        {
            this.npcSize = composition.getSize();
        }
    }

    @RequiredArgsConstructor
    enum BossMonsters
    {
        TOK_XIL1(NpcID.TOKXIL_3121, AttackStyle.RANGE, ImmutableSet.of(TOK_XIL_RANGE_ATTACK, TOK_XIL_MELEE_ATTACK), 1, 4),
        TOK_XIL2(NpcID.TOKXIL_3122, AttackStyle.RANGE, ImmutableSet.of(TOK_XIL_RANGE_ATTACK, TOK_XIL_MELEE_ATTACK), 1, 4),
        KETZEK1(NpcID.KETZEK, AttackStyle.MAGE, ImmutableSet.of(KET_ZEK_MAGE_ATTACK, KET_ZEK_MELEE_ATTACK), 0, 4),
        KETZEK2(NpcID.KETZEK_3126, AttackStyle.MAGE, ImmutableSet.of(KET_ZEK_MAGE_ATTACK, KET_ZEK_MELEE_ATTACK), 0, 4),
        YTMEJKOT1(NpcID.YTMEJKOT, AttackStyle.MELEE, ImmutableSet.of(MEJ_KOT_HEAL_ATTACK, MEJ_KOT_MELEE_ATTACK), 2, 4),
        YTMEJKOT2(NpcID.YTMEJKOT_3124, AttackStyle.MELEE, ImmutableSet.of(MEJ_KOT_HEAL_ATTACK, MEJ_KOT_MELEE_ATTACK), 2, 4),
        TZTOKJAD1(NpcID.TZTOKJAD, AttackStyle.UNKNOWN, ImmutableSet.of(TZTOK_JAD_MAGIC_ATTACK, TZTOK_JAD_RANGE_ATTACK, TZTOK_JAD_MELEE_ATTACK), 0, 4),
        TZTOKJAD2(NpcID.TZTOKJAD_6506, AttackStyle.UNKNOWN, ImmutableSet.of(TZTOK_JAD_MAGIC_ATTACK, TZTOK_JAD_RANGE_ATTACK, TZTOK_JAD_MELEE_ATTACK), 0, 8);

        private static final ImmutableMap<Integer, BossMonsters> idMap;

        static
        {
            ImmutableMap.Builder<Integer, BossMonsters> builder = ImmutableMap.builder();

            for (BossMonsters monster : values())
            {
                builder.put(monster.npcID, monster);
            }

            idMap = builder.build();
        }

        private final int npcID;
        private final AttackStyle attackStyle;
        private final ImmutableSet<Integer> animations;
        private final int priority;
        private final int attackSpeed;

        static BossMonsters of(int npcID)
        {
            return idMap.get(npcID);
        }
    }

    @Getter(AccessLevel.PACKAGE)
    @AllArgsConstructor
    enum AttackStyle
    {
        MAGE("Mage", Color.CYAN, Prayer.PROTECT_FROM_MAGIC),
        RANGE("Range", Color.GREEN, Prayer.PROTECT_FROM_MISSILES),
        MELEE("Melee", Color.RED, Prayer.PROTECT_FROM_MELEE),
        UNKNOWN("Unknown", Color.WHITE, null);

        private String name;
        private Color color;
        private Prayer prayer;
    }
}