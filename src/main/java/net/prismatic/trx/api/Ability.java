package net.prismatic.trx.api;

import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;
import io.netty.buffer.Unpooled;
import nerdhub.cardinal.components.api.util.sync.EntitySyncedComponent;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class Ability implements PlayerComponent, EntitySyncedComponent {
    private final PlayerEntity player;
    public String name;
    public boolean state;

    public Ability(PlayerEntity player) {
        this.player = player;
        this.name = "";
        this.state = false;
    }

    @Override
    public Entity getEntity() {
        return this.player;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        this.state = tag.getBoolean("state");
        this.name = tag.getString("name");
        this.sync();
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putBoolean("state", this.state);
        tag.putString("name", this.name);
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
    }

    @Override
    public void readFromPacket(PacketByteBuf packet) {
        this.state = packet.readBoolean();
        this.name = packet.readString();
    }
}
