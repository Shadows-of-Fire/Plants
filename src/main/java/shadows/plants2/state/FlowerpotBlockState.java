package shadows.plants2.state;

import java.util.Optional;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.BlockFlowerPot.EnumFlowerType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.BlockStateContainer.StateImplementation;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import shadows.plants2.block.BlockFlowerpot;
import shadows.plants2.data.enums.TheBigBookOfEnums;

public final class FlowerpotBlockState extends StateImplementation {

	public FlowerpotBlockState(Block block, ImmutableMap<IProperty<?>, Comparable<?>> properties) {
		super(block, properties);
	}

	@Override
	public <T extends Comparable<T>, V extends T> IBlockState withProperty(IProperty<T> property, V value) {
		if (value instanceof EnumFlowerType) return this.withProperty(BlockFlowerpot.PROP, TheBigBookOfEnums.NAME_TO_ENUM.get(((EnumFlowerType) value).getName()));
		else if (property == BlockFlowerPot.LEGACY_DATA) return this;
		else return super.withProperty(property, value);
	}

	@Override
	public <T extends Comparable<T>> T getValue(IProperty<T> property) {
		if (property == BlockFlowerPot.CONTENTS) return property.getValueClass().cast(EnumFlowerType.EMPTY);
		else if (property == BlockFlowerPot.LEGACY_DATA) return property.getValueClass().cast(0);
		else return super.getValue(property);
	}

	public static final class FlowerpotStateContainer extends BlockStateContainer {

		public FlowerpotStateContainer(Block block, IProperty<?>... properties) {
			super(block, properties);
		}

		@Override
		protected StateImplementation createState(Block block, ImmutableMap<IProperty<?>, Comparable<?>> properties, @Nullable ImmutableMap<IUnlistedProperty<?>, Optional<?>> unlistedProperties) {
			return new FlowerpotBlockState(block, properties);
		}
	}
}
