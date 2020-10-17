package net.prismatic.trx.api;

import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketItem;
import dev.onyxstudios.cca.api.v3.component.ComponentProvider;
import nerdhub.cardinal.components.api.ComponentType;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.prismatic.trx.core.Abilities;

public class ExtendedTrinket extends TrinketItem {
    private final ComponentType<Ability> component;
    private final String ability;
    private final StatusEffectInstance effect;
    private final EntityAttribute attribute;
    private final EntityAttributeModifier modifier;
    private final String slot;

    public ExtendedTrinket(ComponentType<Ability> component, String ability, String slot) {
        super(new Settings().maxCount(1));
        this.component = component;
        this.ability = ability;
        this.effect = null;
        this.attribute = null;
        this.modifier = null;
        this.slot = slot;
    }

    public ExtendedTrinket(ComponentType<Ability> component, String ability, String slot, StatusEffectInstance effect) {
        super(new Settings().maxCount(1));
        this.component = component;
        this.ability = ability;
        this.effect = effect;
        this.attribute = null;
        this.modifier = null;
        this.slot = slot;
    }

    public ExtendedTrinket(ComponentType<Ability> component, String ability, String slot, EntityAttributeModifier modifier, EntityAttribute attribute) {
        super(new Settings().maxCount(1));
        this.component = component;
        this.ability = ability;
        this.effect = null;
        this.attribute = attribute;
        this.modifier = modifier;
        this.slot = slot;
    }

    @Override
    public void onEquip(PlayerEntity player, ItemStack stack) {
        Abilities.NECKLACE.get(ComponentProvider.fromEntity(player)).name = this.ability;
        Abilities.NECKLACE.get(ComponentProvider.fromEntity(player)).state = true;

        if (this.modifier != null) {
            Abilities.NECKLACE.get(ComponentProvider.fromEntity(player)).modifier = this.modifier;
        }

        if (this.effect != null) {
            Abilities.NECKLACE.get(ComponentProvider.fromEntity(player)).effect = this.effect;
        }
    }

    @Override
    public void onUnequip(PlayerEntity player, ItemStack stack) {
        Abilities.NECKLACE.get(ComponentProvider.fromEntity(player)).state = false;
    }

    @Override
    public boolean canWearInSlot(String group, String slot) {
        return slot.equals(this.slot);
    }
}