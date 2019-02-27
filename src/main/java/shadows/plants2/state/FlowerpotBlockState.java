package shadows.plants2.state;

import java.util.Optional;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.BlockFlowerPot.EnumFlowerType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;

public final class FlowerpotBlockState extends ExtendedBlockState {

	public FlowerpotBlockState(Block block, IProperty<?>[] properties, IUnlistedProperty<?>[] unlistedProperties) {
		super(block, properties, unlistedProperties);
	}

	@Override
	protected StateImplementation createState(Block block, ImmutableMap<IProperty<?>, Comparable<?>> properties, @Nullable ImmutableMap<IUnlistedProperty<?>, Optional<?>> unlistedProperties) {
		return new FlowerpotStateImpl(block, properties, unlistedProperties, null, null);
	}

	public static final class FlowerpotStateImpl extends ExtendedBlockState.ExtendedStateImplementation {

		public FlowerpotStateImpl(Block block, ImmutableMap<IProperty<?>, Comparable<?>> properties, ImmutableMap<IUnlistedProperty<?>, Optional<?>> unlistedProperties, @Nullable ImmutableTable<IProperty<?>, Comparable<?>, IBlockState> table, IBlockState clean) {
			super(block, properties, unlistedProperties, table, clean);
		}

		@Override
		public <T extends Comparable<T>, V extends T> IBlockState withProperty(IProperty<T> property, V value) {
			if (value instanceof EnumFlowerType || property == BlockFlowerPot.LEGACY_DATA) return this;
			else return super.withProperty(property, value);
		}

		@Override
		public <T extends Comparable<T>> T getValue(IProperty<T> property) {
			if (property == BlockFlowerPot.CONTENTS) return property.getValueClass().cast(EnumFlowerType.EMPTY);
			else if (property == BlockFlowerPot.LEGACY_DATA) return property.getValueClass().cast(0);
			//Inspirations
			else if (property.getName().equals("extra")) return property.getValueClass().cast(false);
			//Quark
			else if (property.getName().equals("custom")) return property.getValueClass().cast(false);
			else return super.getValue(property);
		}
	}
}
