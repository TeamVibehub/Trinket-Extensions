package net.prismatic.trx.api;

import dev.emi.trinkets.api.TrinketItem;
import dev.onyxstudios.cca.api.v3.component.ComponentProvider;
import nerdhub.cardinal.components.api.ComponentType;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class ExtendedTrinket extends TrinketItem {
    private final ComponentType<Ability> type;
    private final String ability;
    private final StatusEffectInstance effect;
    private final EntityAttribute attribute;
    private final EntityAttributeModifier attributeModifier;
    private final String slot;

    public ExtendedTrinket(ComponentType<Ability> type, String ability, String slot) {
        super(new Settings().maxCount(1));
        this.type = type;
        this.ability = ability;
        this.effect = null;
        this.attribute = null;
        this.attributeModifier = null;
        this.slot = slot;
    }

    public ExtendedTrinket(ComponentType<Ability> type, String ability, String slot, StatusEffectInstance effect) {
        super(new Settings().maxCount(1));
        this.type = type;
        this.ability = ability;
        this.effect = effect;
        this.attribute = null;
        this.attributeModifier = null;
        this.slot = slot;
    }

    public ExtendedTrinket(ComponentType<Ability> type, String ability, String slot, EntityAttribute attribute, EntityAttributeModifier attributeModifier) {
        super(new Settings().maxCount(1));
        this.type = type;
        this.ability = ability;
        this.effect = null;
        this.attribute = attribute;
        this.attributeModifier = modifier;
        this.slot = slot;
    }

    @Override
    public void onEquip(PlayerEntity player, ItemStack stack) {
        this.type.get(ComponentProvider.fromEntity(player)).name = this.ability;
        this.type.get(ComponentProvider.fromEntity(player)).state = true;
        if (this.attribute != null) {
            player.getAttributeInstance(this.attribute).addPersistentModifier(this.attributeModifier);
        }
    }

    @Override
    public void tick(PlayerEntity player, ItemStack stack) {
        if (this.effect != null) {
            
        }
    }

    @Override
    public void onUnequip(PlayerEntity player, ItemStack stack) {
        this.type.get(ComponentProvider.fromEntity(player)).state = false;
    }

    @Override
    public boolean canWearInSlot(String group, String slot) {
        return slot.equals(this.slot);
    }
}