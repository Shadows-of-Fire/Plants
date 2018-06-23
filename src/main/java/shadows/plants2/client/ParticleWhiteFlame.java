package shadows.plants2.client;

import net.minecraft.client.particle.ParticleFlame;
import net.minecraft.world.World;
import shadows.plants2.proxy.ClientProxy;

public class ParticleWhiteFlame extends ParticleFlame {

	public ParticleWhiteFlame(World world, double x, double y, double z, double xS, double yS, double zS) {
		super(world, x, y, z, xS, yS, zS);
		setParticleTexture(ClientProxy.whiteFlame);
	}

	@Override
	public int getFXLayer() {
		return 1;
	}

	@Override
	public void setParticleTextureIndex(int idx) {
	}

}
