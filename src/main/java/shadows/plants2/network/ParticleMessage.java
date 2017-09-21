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

	private int[] pos;

	public ParticleMessage(BlockPos pos) {
		this.pos = new int[] { pos.getX(), pos.getY(), pos.getZ() };
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		pos = new int[3];
		for (int i = 0; i < 3; i++)
			pos[i] = buf.readInt();

	}

	@Override
	public void toBytes(ByteBuf buf) {
		for (int i = 0; i < 3; i++)
			buf.writeInt(pos[i]);

	}

	public static class ParticleMessageHandler implements IMessageHandler<ParticleMessage, IMessage> {

		@Override
		public IMessage onMessage(ParticleMessage message, MessageContext ctx) {
			BlockPos pos = new BlockPos(message.pos[0], message.pos[1], message.pos[2]);
			Minecraft.getMinecraft().addScheduledTask(() -> {
				Plants2.proxy.doCauldronInputParticles(pos);
			});
			return null;
		}

	}

}
