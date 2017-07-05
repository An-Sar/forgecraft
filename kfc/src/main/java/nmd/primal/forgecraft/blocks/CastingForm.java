package nmd.primal.forgecraft.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
import nmd.primal.core.common.helper.CommonUtils;
import nmd.primal.forgecraft.ModInfo;
import nmd.primal.forgecraft.tiles.TileCastingForm;
import nmd.primal.forgecraft.util.CastingFormHandler;

import java.util.Random;

/**
 * Created by mminaie on 6/19/17.
 */
public class CastingForm extends CustomContainerFacing implements CastingFormHandler{

    protected static AxisAlignedBB bound = new AxisAlignedBB(0/16D, 0.0D, 0/16D, 16/16D, 3/16D, 16/16D);

    public CastingForm(Material material, String registryName) {
        super(material, registryName);
        setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        setCreativeTab(ModInfo.TAB_FORGECRAFT);
        this.setTickRandomly(true);
    }


    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitx, float hity, float hitz) {

        if (!world.isRemote) {
            TileCastingForm tile = (TileCastingForm)  world.getTileEntity(pos);
            ItemStack pItem = player.inventory.getCurrentItem();
            doInventoryManager(pItem, world, tile, pos, hitx, hity, hitz, state, player);

            return false;
        }
        return false;
    }


    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random random)
    {
        if (!world.isRemote) {
            if(CommonUtils.randomCheck(4)) {
                TileCastingForm tile = (TileCastingForm) world.getTileEntity(pos);
                String[] tempArray = new String[25];
                for (int i = 0; i < 25; i++) {
                    tempArray[i] = tile.getSlotStack(i).getItem().getRegistryName().toString();
                }
                doCraftingformCrafting(tempArray, world, tile, pos);
            }
        }
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        if (!world.isRemote && world.getGameRules().getBoolean("doTileDrops"))
        {
            TileCastingForm tile = (TileCastingForm) world.getTileEntity(pos);
            if (tile !=null)
            {
                for (ItemStack stack : tile.getSlotList())
                {
                    if (stack != null) {
                        float offset = 0.7F;
                        double offsetX = world.rand.nextFloat() * offset + (1.0F - offset) * 0.5D;
                        double offsetY = world.rand.nextFloat() * offset + (1.0F - offset) * 0.5D;
                        double offsetZ = world.rand.nextFloat() * offset + (1.0F - offset) * 0.5D;
                        EntityItem item = new EntityItem(world, pos.getX() + offsetX, pos.getY() + offsetY, pos.getZ() + offsetZ, stack);
                        item.setDefaultPickupDelay();
                        world.spawnEntity(item);
                    }
                }
            }
        }

        super.breakBlock(world, pos, state);
    }


    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileCastingForm();
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return bound;
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
