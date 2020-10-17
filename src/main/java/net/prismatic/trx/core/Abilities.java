package net.prismatic.trx.core;

import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import net.minecraft.util.Identifier;
import net.prismatic.trx.api.Ability;

public class Abilities {
    public static final ComponentType<Ability> RING =
            ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("trx", "ring"), Ability.class);

    public static final ComponentType<Ability> NECKLACE =
            ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("trx", "necklace"), Ability.class);
}
