package net.prismatic.trx.api;

import dev.emi.trinkets.api.TrinketItem;
import dev.onyxstudios.cca.api.v3.component.ComponentProvider;
import nerdhub.cardinal.components.api.ComponentType;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.prismatic.trx.core.Abilities;

public class ExtendedTrinket extends TrinketItem {
    private final ComponentType<Ability> type;
    private final String name;
    private final StatusEffectInstance effect;
    private final EntityAttribute attribute;
    private final EntityAttributeModifier attributeModifier;
    private final String slot;

    /**
     * Creates a "basic" ExtendedTrinket with no special functionality.
     * @param type The ability type to use. (see {@link Abilities})
     * @param name The ability name.
     * @param slot The slot (or slots) this item can be equipped in.
     */
    public ExtendedTrinket(ComponentType<Ability> type, String name, String slot) {
        super(new Settings().maxCount(1));
        this.type = type;
        this.name = name;
        this.effect = null;
        this.attribute = null;
        this.attributeModifier = null;
        this.slot = slot;
    }

    /**
     * Creates an ExtendedTrinket which applies a status effect while worn.
     * @param type The ability type to use.
     * @param name The ability name, used internally and in mixins.
     * @param slot The slot (or slots) this item can be equipped in.
     * @param effect The effect that is applied while the item is worn.
     */
    public ExtendedTrinket(ComponentType<Ability> type, String name, String slot, StatusEffect effect, int amplifier) {
        super(new Settings().maxCount(1));
        this.type = type;
        this.name = name;
        this.attribute = null;
        this.attributeModifier = null;
        this.slot = slot;
        this.effect = new StatusEffectInstance(effect, 1, amplifier);
        this.effect.setPermanent(true);
    }

    /**
     * Creates an ExtendedTrinket which applies an attribute modifier on equip (and removes it on unequip).
     * @param type The ability type to use.
     * @param name The ability name, used internally and in mixins.
     * @param slot The slot (or slots) this item can be equipped in.
     * @param attribute The attribute to modify, using the provided modifier..
     * @param attributeModifier The attribute modifier to apply to the provided attribute.
     */
    public ExtendedTrinket(ComponentType<Ability> type, String name, String slot, EntityAttribute attribute, EntityAttributeModifier attributeModifier) {
        super(new Settings().maxCount(1));
        this.type = type;
        this.name = name;
        this.effect = null;
        this.attribute = attribute;
        this.attributeModifier = attributeModifier;
        this.slot = slot;
    }

    @Override
    public void onEquip(PlayerEntity player, ItemStack stack) {
        this.type.get(ComponentProvider.fromEntity(player)).name = this.name;
        this.type.get(ComponentProvider.fromEntity(player)).state = true;
        if (this.attribute != null) {
            player.getAttributeInstance(this.attribute).addPersistentModifier(this.attributeModifier);
        }

        if (this.effect != null) {
            if (!(player.hasStatusEffect(this.effect.getEffectType()))) {
                player.addStatusEffect(this.effect);
            }
        }
    }

    @Override
    public void onUnequip(PlayerEntity player, ItemStack stack) {
        this.type.get(ComponentProvider.fromEntity(player)).state = false;
        if (this.attribute != null) {
            player.getAttributeInstance(this.attribute).removeModifier(this.attributeModifier);
        }

        if (this.effect != null) {
            player.removeStatusEffect(this.effect.getEffectType());
        }
    }

    @Override
    public boolean canWearInSlot(String group, String slot) {
        return slot.equals(this.slot);
    }
}