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
import nmd.primal.core.api.PrimalStates;
import nmd.primal.core.common.items.tools.WorkMallet;
import nmd.primal.forgecraft.ModInfo;
import nmd.primal.forgecraft.tiles.TileBreaker;
import nmd.primal.forgecraft.util.BreakerHandler;

/**
 * Created by mminaie on 4/9/17.
 */
public class Breaker extends CustomContainerFacing implements BreakerHandler {

    public AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 6/16D, 1.0D);

    public Breaker(Material material, String registryName, Float hardness) {
        super(material, registryName);
        setCreativeTab(ModInfo.TAB_FORGECRAFT);
        setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(PrimalStates.ACTIVE, false));
        setHardness(hardness);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return AABB;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitx, float hity, float hitz) {

        if(!world.isRemote){
            TileBreaker tile = (TileBreaker) world.getTileEntity(pos);
            ItemStack pItem = player.inventory.getCurrentItem();

            if(state.getValue(PrimalStates.ACTIVE) == true && player.isSneaking() && pItem.isEmpty()){
                doBreaking(world, state, pos, tile);
                world.setBlockState(pos, state.withProperty(FACING, state.getValue(FACING)).withProperty(PrimalStates.ACTIVE, false));

                tile.setCharge(0);
                return true;
            }
            if(!player.isSneaking() && pItem.isEmpty()) {
                if (!state.getValue(PrimalStates.ACTIVE)) {
                    world.setBlockState(pos, state.withProperty(FACING, state.getValue(FACING)).withProperty(PrimalStates.ACTIVE, true), 2);
                    return true;
                }
                if(state.getValue(PrimalStates.ACTIVE)) {
                    if (tile.getCharge() < 181) {
                        tile.setCharge(tile.getCharge() + 2.0f);
                        tile.updateBlock();
                        //System.out.println(tile.charge);
                        return true;
                    }
                }
            }

            if(pItem.getItem() instanceof WorkMallet){
                tile.setSlotStack(0, player.inventory.getCurrentItem());
                player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
                return true;
            }



        }

        return false;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        if (!world.isRemote && world.getGameRules().getBoolean("doTileDrops"))
        {
            TileBreaker tile = (TileBreaker) world.getTileEntity(pos);
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
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileBreaker();
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        //if(!worldIn.isRemote) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing()).withProperty(PrimalStates.ACTIVE, false), 2);
        //}
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;

        if(state.getValue(PrimalStates.ACTIVE ) == false) {
            if (state.getValue(FACING) == EnumFacing.EAST) {
                i = 0;
                return i;
            }
            if (state.getValue(FACING) == EnumFacing.WEST) {
                i = 1;
                return i;
            }
            if (state.getValue(FACING) == EnumFacing.SOUTH) {
                i = 2;
                return i;
            }
            if (state.getValue(FACING) == EnumFacing.NORTH) {
                i = 3;
                return i;
            }
        }

        if(state.getValue(PrimalStates.ACTIVE)) {
            if (state.getValue(FACING) == EnumFacing.EAST) {
                i = 4;
                return i;
            }
            if (state.getValue(FACING) == EnumFacing.WEST) {
                i = 5;
                return i;
            }
            if (state.getValue(FACING) == EnumFacing.SOUTH) {
                i = 6;
                return i;
            }
            if (state.getValue(FACING) == EnumFacing.NORTH) {
                i = 7;
                return i;
            }
        }
        return i;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState();

        if (meta == 0){
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.EAST).withProperty(PrimalStates.ACTIVE, false);
        }
        if (meta == 1) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.WEST).withProperty(PrimalStates.ACTIVE, false);
        }
        if (meta == 2) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.SOUTH).withProperty(PrimalStates.ACTIVE, false);
        }
        if (meta == 3) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.NORTH).withProperty(PrimalStates.ACTIVE, false);
        }
        if (meta == 4){
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.EAST).withProperty(PrimalStates.ACTIVE, true);
        }
        if (meta == 5) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.WEST).withProperty(PrimalStates.ACTIVE, true);
        }
        if (meta == 6) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.SOUTH).withProperty(PrimalStates.ACTIVE, true);
        }
        if (meta == 7) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.NORTH).withProperty(PrimalStates.ACTIVE, true);
        }
        return iblockstate;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {PrimalStates.ACTIVE, FACING});
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
