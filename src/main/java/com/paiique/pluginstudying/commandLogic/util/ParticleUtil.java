package com.paiique.pluginstudying.commandLogic.util;

import com.destroystokyo.paper.ParticleBuilder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;

public class ParticleUtil {

    public static void createParticle(Location location, Particle particleName, int count, double offsetX, double offsetY, double offsetZ) {
                new ParticleBuilder(particleName).location(location).count(count).offset(offsetX, offsetY, offsetZ).allPlayers().spawn();
    }

    public static void createRedstoneParticle(Color color, Location location, int count, double offsetX, double offsetY, double offsetZ) {
                new ParticleBuilder(Particle.REDSTONE).location(location).count(count).offset(offsetX, offsetY, offsetZ).color(color).allPlayers().spawn();
    }
}
