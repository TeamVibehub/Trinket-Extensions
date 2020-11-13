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

    public static final ComponentType<Ability> CAPE =
            ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("trx", "cape"), Ability.class);

    public static final ComponentType<Ability> CHARM =
            ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("trx", "charm"), Ability.class);

    public static final ComponentType<Ability> BELT =
            ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("trx", "belt"), Ability.class);

    public static final ComponentType<Ability> GLOVES =
            ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("trx", "gloves"), Ability.class);

    public static final ComponentType<Ability> AGLET =
            ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("trx", "aglet"), Ability.class);

    public static final ComponentType<Ability> MASK =
            ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("trx", "mask"), Ability.class);
}
