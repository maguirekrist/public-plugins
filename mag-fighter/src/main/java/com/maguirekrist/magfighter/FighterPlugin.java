package com.maguirekrist.magfighter;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.events.GameTick;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.game.NpcUtil;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.dailytaskindicators.DailyTasksPlugin;
import net.unethicalite.api.commons.Time;
import net.unethicalite.api.game.Combat;
import net.unethicalite.api.plugins.LoopedPlugin;
import net.unethicalite.api.plugins.Script;
import net.unethicalite.api.plugins.TaskPlugin;
import org.pf4j.Extension;

import javax.inject.Inject;

@Slf4j
@Extension
@PluginDescriptor(
        name = "Mag Fighter",
        enabledByDefault = false,
        description = "Another bullshit plugin.",
        tags = {"bosses", "combat", "overlay", "pve", "pvm"}
)
public class FighterPlugin extends Plugin {

    private String monsterToAttack;

    @Inject
    FighterPluginConfig config;

    @Inject
    private Client client;

    @Inject
    private Combat combat;

    @Inject
    private ClientThread clientThread;

    @Override
    protected void startUp() throws Exception {
        log.info("Starting Mag Fighter");
    }

    @Override
    protected void shutDown() throws Exception {
        log.info("Shutting down Mag Fighter");
    }

    @Subscribe
    private void onConfigChanged(ConfigChanged event)
    {
        log.info("Config changed");
        if (!event.getKey().equals("bot"))
        {
            return;
        }

        clientThread.invoke(() -> {
            monsterToAttack = config.monsterToAttack();
        });
    }

    @Subscribe
    private void onGameTick(GameTick Event) {
        if(isInCombat()) {
            return;
        } else {
            NPC target = Combat.getAttackableNPC(monsterToAttack);
            if(target != null) {
                log.info("Attacking {}", target.getName());
                target.interact("attack");
                longSleep();
            }
        }
    }

    private boolean isInCombat() {
        return client.getLocalPlayer().isInteracting();
    }

    private static void shortSleep() {
        Time.sleep(50,200);
    }

    private static void longSleep() {
        Time.sleep(1000, 4000);
    }
}
