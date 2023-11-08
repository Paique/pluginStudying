package com.paiique.pluginstudying.commandLogic;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

public class CustomArmorStand {

    public static ArmorStand spawn(Location location) {

        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setCanMove(false);
        return armorStand;
    }


}
