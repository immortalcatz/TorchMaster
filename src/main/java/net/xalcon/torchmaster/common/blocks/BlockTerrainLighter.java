package net.xalcon.torchmaster.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.xalcon.torchmaster.TorchMasterMod;
import net.xalcon.torchmaster.common.ModGuiHandler;
import net.xalcon.torchmaster.common.tiles.TileEntityTerrainLighter;

import javax.annotation.Nullable;

public class BlockTerrainLighter extends BlockBase implements ITileEntityProvider
{
	public BlockTerrainLighter()
	{
		super(Material.WOOD, "terrain_lighter");
		this.setHardness(1.0f);
		this.setResistance(3f);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityTerrainLighter();
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if(!worldIn.isRemote)
		{
			playerIn.openGui(TorchMasterMod.instance, ModGuiHandler.GuiType.TERRAIN_LIGHTER.ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (tileentity instanceof TileEntityTerrainLighter)
		{
			InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityTerrainLighter)tileentity);
		}

		super.breakBlock(worldIn, pos, state);
	}
}