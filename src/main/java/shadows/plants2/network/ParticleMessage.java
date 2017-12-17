package shadows.plants2.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import shadows.plants2.Plants2;

public class ParticleMessage implements IMessage {

	public ParticleMessage() {
	}

	private BlockPos pos;

	public ParticleMessage(BlockPos pos) {
		this.pos = pos;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		pos = BlockPos.fromLong(buf.readLong());

	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(pos.toLong());
	}

	public static class ParticleMessageHandler implements IMessageHandler<ParticleMessage, IMessage> {

		@Override
		public IMessage onMessage(ParticleMessage message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				Plants2.PROXY.doCauldronInputParticles(message.pos);
			});
			return null;
		}

	}

}
