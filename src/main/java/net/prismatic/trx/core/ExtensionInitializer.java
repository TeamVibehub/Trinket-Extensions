package net.prismatic.trx.core;

import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketSlots;
import nerdhub.cardinal.components.api.event.EntityComponentCallback;
import nerdhub.cardinal.components.api.util.EntityComponents;
import nerdhub.cardinal.components.api.util.RespawnCopyStrategy;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.prismatic.trx.api.Ability;

public class ExtensionInitializer implements ModInitializer {

    @Override
    public void onInitialize() {
        EntityComponentCallback.event(PlayerEntity.class).register(((playerEntity, componentContainer) -> componentContainer.put(Abilities.RING, new Ability(playerEntity))));
        EntityComponentCallback.event(PlayerEntity.class).register(((playerEntity, componentContainer) -> componentContainer.put(Abilities.NECKLACE, new Ability(playerEntity))));
        EntityComponentCallback.event(PlayerEntity.class).register(((playerEntity, componentContainer) -> componentContainer.put(Abilities.CAPE, new Ability(playerEntity))));
        EntityComponentCallback.event(PlayerEntity.class).register(((playerEntity, componentContainer) -> componentContainer.put(Abilities.CHARM, new Ability(playerEntity))));
        EntityComponentCallback.event(PlayerEntity.class).register(((playerEntity, componentContainer) -> componentContainer.put(Abilities.BELT, new Ability(playerEntity))));
        EntityComponentCallback.event(PlayerEntity.class).register(((playerEntity, componentContainer) -> componentContainer.put(Abilities.GLOVES, new Ability(playerEntity))));
        EntityComponentCallback.event(PlayerEntity.class).register(((playerEntity, componentContainer) -> componentContainer.put(Abilities.AGLET, new Ability(playerEntity))));
        EntityComponentCallback.event(PlayerEntity.class).register(((playerEntity, componentContainer) -> componentContainer.put(Abilities.MASK, new Ability(playerEntity))));
        EntityComponents.setRespawnCopyStrategy(Abilities.RING, RespawnCopyStrategy.ALWAYS_COPY);
        EntityComponents.setRespawnCopyStrategy(Abilities.NECKLACE, RespawnCopyStrategy.ALWAYS_COPY);
        EntityComponents.setRespawnCopyStrategy(Abilities.CAPE, RespawnCopyStrategy.ALWAYS_COPY);
        EntityComponents.setRespawnCopyStrategy(Abilities.CHARM, RespawnCopyStrategy.ALWAYS_COPY);
        EntityComponents.setRespawnCopyStrategy(Abilities.BELT, RespawnCopyStrategy.ALWAYS_COPY);
        EntityComponents.setRespawnCopyStrategy(Abilities.GLOVES, RespawnCopyStrategy.ALWAYS_COPY);
        EntityComponents.setRespawnCopyStrategy(Abilities.AGLET, RespawnCopyStrategy.ALWAYS_COPY);
        EntityComponents.setRespawnCopyStrategy(Abilities.MASK, RespawnCopyStrategy.ALWAYS_COPY);
        TrinketSlots.addSlot(SlotGroups.HAND, Slots.RING, new Identifier("trinkets", "textures/item/empty_trinket_slot_ring.png"));
        TrinketSlots.addSlot(SlotGroups.CHEST, Slots.NECKLACE, new Identifier("trinkets", "textures/item/empty_trinket_slot_necklace.png"));
        TrinketSlots.addSlot(SlotGroups.CHEST, Slots.CAPE, new Identifier("trinkets", "textures/item/empty_trinket_slot_cape.png"));
        TrinketSlots.addSlot(SlotGroups.CHEST, Slots.CHARM, new Identifier("trinkets", "textures/item/empty_trinket_slot_charm.png"));
        TrinketSlots.addSlot(SlotGroups.LEGS, Slots.BELT, new Identifier("trinkets", "textures/item/empty_trinket_slot_belt.png"));
        TrinketSlots.addSlot(SlotGroups.HAND, Slots.GLOVES, new Identifier("trinkets", "textures/item/empty_trinket_slot_gloves.png"));
        TrinketSlots.addSlot(SlotGroups.FEET, Slots.AGLET, new Identifier("trinkets", "textures/item/empty_trinket_slot_aglet.png"));
        TrinketSlots.addSlot(SlotGroups.HEAD, Slots.MASK, new Identifier("trinkets", "textures/item/empty_trinket_slot_mask.png"));
    }
}