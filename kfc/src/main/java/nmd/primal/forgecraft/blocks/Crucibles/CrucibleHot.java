package nmd.primal.forgecraft.blocks.Crucibles;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nmd.primal.forgecraft.ModInfo;
import nmd.primal.forgecraft.tiles.TileBaseCrucible;

import java.util.Random;

/**
 * Created by mminaie on 2/4/17.
 */
public class CrucibleHot extends Block implements ITileEntityProvider {

    protected static final AxisAlignedBB boundBox = new AxisAlignedBB(4/16D, 0.0D, 4/16D, 12/16D, 7/16D, 12/16D);

    //public static final PropertyInteger SIZE =  PropertyInteger.create("size", 0, 3);

    public CrucibleHot(Material material, String registryName) {
        super(material);
        setUnlocalizedName(registryName);
        setRegistryName(registryName);
        setCreativeTab(ModInfo.TAB_FORGECRAFT);
        setHardness(3.0f);
        //this.blockState.getBaseState().withProperty(SIZE, Integer.valueOf(0));
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        return false;
    }

    public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state)
    {

        if(!world.isRemote){
            world.setBlockState(pos, Blocks.FLOWING_LAVA.getDefaultState().withProperty(BlockDynamicLiquid.LEVEL, 1), 3);
        }
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 0;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileBaseCrucible();
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return boundBox;
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
