## Trinket Extensions
Trinket Extensions is an extension API to Trinkets, allowing developers to quickly add equipment like rings and necklaces.
Does not add any trinket slots on it's own; you must add those. This mod only provides the tools for creating trinkets.

### Usage

You'll want an instance of `ExtendedTrinket`.
There are three constructors: one takes a StatusEffect and an amplifier (for e.g. potion rings), one takes an EntityAttribute and an EntityAttributeModifier.
There's also one which takes neither. For example:

#### Creating the item

```java
import net.prismatic.trx.api.ExtendedTrinket;
import net.prismatic.trx.core.Abilities;
import dev.emi.trinkets.api.Slots;

ExtendedTrinket trinket = new ExtendedTrinket(Abilities.RING, "Ring of Invincibility", Slots.RING);
```

Obviously don't forget to actually register the item.

#### Using it in a mixin

```java
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.prismatic.trx.core.Abilities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class DamageNullificationMixin {
    @Inject(at = @At("HEAD"), method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z")
    public void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Ability ability = Abilities.RING.get((PlayerEntity) (Object) this);
        if (ability.name.equals("Ring of Invincibility") && ability.state) {
            cir.setReturnValue(false);
        }
    }
}
```

This ring, when equipped, would nullify all damage.

### Adding to your mod

TODO
