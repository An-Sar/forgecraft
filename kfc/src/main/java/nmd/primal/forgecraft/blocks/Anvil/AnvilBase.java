package nmd.primal.forgecraft.blocks.Anvil;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nmd.primal.forgecraft.ModInfo;
import nmd.primal.forgecraft.blocks.CustomContainerFacing;
import nmd.primal.forgecraft.tiles.TileAnvil;
import nmd.primal.forgecraft.util.AnvilHandler;

/**
 * Created by mminaie on 6/11/17.
 */
public abstract class AnvilBase extends CustomContainerFacing implements AnvilHandler{

    private boolean anvil;

    public AnvilBase(Material material, String registryName, Float hardness, Boolean anvil) {
        super(material, registryName);
        setCreativeTab(ModInfo.TAB_FORGECRAFT);
        setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        setHardness(hardness);
        this.setIsAnvil(anvil);
    }

    public boolean isAnvil() {
        return anvil;
    }

    public void setIsAnvil(boolean anvil) {
        anvil = anvil;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        AnvilHandler.doDrops(world, pos);
        super.breakBlock(world, pos, state);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileAnvil();
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        //if(!worldIn.isRemote) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing()), 2);
        //}
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;

        if( state.getValue(FACING) == EnumFacing.EAST) {
            i = 0;
            return i;
        }
        if( state.getValue(FACING) == EnumFacing.WEST) {
            i = 1;
            return i;
        }
        if( state.getValue(FACING) == EnumFacing.SOUTH){
            i = 2;
            return i;
        }
        if( state.getValue(FACING) == EnumFacing.NORTH){
            i = 3;
            return i;
        }
        return i;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState();

        if (meta == 0){
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.EAST);
        }
        if (meta == 1) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.WEST);
        }
        if (meta == 2) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.SOUTH);
        }
        if (meta == 3) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.NORTH);
        }
        return iblockstate;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }



    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return true;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }


}
