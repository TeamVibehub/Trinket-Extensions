package net.prismatic.trx.core;

import dev.emi.trinkets.api.*;
import nerdhub.cardinal.components.api.event.EntityComponentCallback;
import nerdhub.cardinal.components.api.util.EntityComponents;
import nerdhub.cardinal.components.api.util.RespawnCopyStrategy;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.prismatic.trx.api.Ability;
import net.prismatic.trx.api.ExtendedTrinket;

public class ExtensionInitializer implements ModInitializer {

    static ExtendedTrinket RING = new ExtendedTrinket(Abilities.RING, "none", "ring");
    static ExtendedTrinket NECKLACE = new ExtendedTrinket(Abilities.NECKLACE, "none", "necklace");

    @Override
    public void onInitialize() {
        EntityComponentCallback.event(PlayerEntity.class).register(((playerEntity, componentContainer) -> componentContainer.put(Abilities.RING, new Ability(playerEntity))));
        EntityComponents.setRespawnCopyStrategy(Abilities.RING, RespawnCopyStrategy.ALWAYS_COPY);

        Registry.register(Registry.ITEM, new Identifier("trx", "ring"), RING);
        Registry.register(Registry.ITEM, new Identifier("trx", "necklace"), NECKLACE);
        TrinketSlots.addSlot(SlotGroups.HAND, Slots.RING, new Identifier("trinkets", "textures/item/empty_trinket_slot_ring.png"));
        TrinketSlots.addSlot(SlotGroups.CHEST, Slots.NECKLACE, new Identifier("trinkets", "textures/item/empty_trinket_slot_necklace.png"));
    }
}