package shadows.plants2.gen;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ArrayGen {

	public static final ArrayGen TEST = new ArrayGen(new Block[][][] { new Block[][] { new Block[] { Blocks.LEAVES, Blocks.LEAVES, Blocks.LEAVES, Blocks.LEAVES, }, new Block[] { Blocks.LEAVES, Blocks.AIR, Blocks.AIR, Blocks.LEAVES, }, new Block[] { Blocks.LEAVES, Blocks.AIR, Blocks.AIR, Blocks.LEAVES, }, new Block[] { Blocks.LEAVES, Blocks.LEAVES, Blocks.LEAVES, Blocks.LEAVES } } });

	private final IBlockState[][][] states;

	public ArrayGen(IBlockState[][][] statesToPlace) {
		states = statesToPlace;
	}

	public ArrayGen(Block[][][] blocks) {
		IBlockState[][][] states = new IBlockState[blocks.length][][];
		for (int i = 0; i < blocks.length; i++) {
			states[i] = new IBlockState[blocks[i].length][];

			for (int j = 0; j < blocks[i].length; j++) {
				states[i][j] = new IBlockState[blocks[i][j].length];

				for (int k = 0; k < blocks[i][j].length; k++) {
					states[i][j][k] = blocks[i][j][k] == null ? null : blocks[i][j][k].getDefaultState();
				}
			}
		}
		this.states = states;
	}

	public void placeStateArray(BlockPos pos, World world) {
		for (int y = 0; y < states.length; y++) {
			for (int x = 0; x < states[y].length; x++) {
				for (int z = 0; z < states[y][x].length; z++) {
					if (states[y][x][z] != null) world.setBlockState(pos.add(x, y, z), states[y][x][z]);
				}
			}
		}
	}

}
