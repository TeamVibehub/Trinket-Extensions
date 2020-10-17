package net.prismatic.trx.api;

import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;
import io.netty.buffer.Unpooled;
import nerdhub.cardinal.components.api.util.sync.EntitySyncedComponent;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class Ability implements PlayerComponent, EntitySyncedComponent {
    private final PlayerEntity player;
    public String name;
    public boolean state;
    public StatusEffectInstance effect;
    public EntityAttributeModifier modifier;

    public Ability(PlayerEntity player) {
        this.player = player;
    }

    @Override
    public Entity getEntity() {
        return this.player;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        this.state = tag.getBoolean("state");
        this.name = tag.getString("name");
        if (tag.contains("effect")) {
            this.effect = StatusEffectInstance.fromTag(tag.getCompound("effect"));
        }
        if (tag.contains("modifier")) {
            this.modifier = EntityAttributeModifier.fromTag(tag.getCompound("modifier"));
        }
        this.sync();
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putBoolean("state", this.state);
        tag.putString("name", this.name);
        if (this.effect != null) {
            tag.put("statusEffect", this.effect.toTag(new CompoundTag()));
        }

        if (this.modifier != null) {
            tag.put("attributeModifier", this.modifier.toTag());
        }
        return tag;
    }

    @Override
    public void syncWith(ServerPlayerEntity player) {
        if (player == this.player) {
            PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
            this.writeToPacket(buf);
            ServerSidePacketRegistry.INSTANCE.sendToPlayer(player, PACKET_ID, buf);
        }
    }

    @Override
    public void writeToPacket(PacketByteBuf packet) {
        packet.writeBoolean(this.state);
        packet.writeString(this.name);
        packet.writeBoolean(this.effect != null);

        if (this.effect != null) {
            packet.writeCompoundTag(this.effect.toTag(new CompoundTag()));
        }

        packet.writeBoolean(this.modifier != null);

        if (this.modifier != null) {
            packet.writeCompoundTag(this.modifier.toTag());
        }
    }

    @Override
    public void readFromPacket(PacketByteBuf packet) {
        this.state = packet.readBoolean();
        this.name = packet.readString();

        if (packet.readBoolean()) {
            this.effect = StatusEffectInstance.fromTag(packet.readCompoundTag());
        }

        if (packet.readBoolean()) {
            this.modifier = EntityAttributeModifier.fromTag(packet.readCompoundTag());
        }
    }
}
