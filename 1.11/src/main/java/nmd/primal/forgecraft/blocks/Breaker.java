package nmd.primal.forgecraft.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nmd.primal.core.api.PrimalItems;
import nmd.primal.core.common.items.tools.WorkMallet;
import nmd.primal.forgecraft.CommonUtils;
import nmd.primal.forgecraft.ModInfo;
import nmd.primal.forgecraft.tiles.TileBreaker;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by mminaie on 4/9/17.
 */
public class Breaker extends CustomContainerFacing {

    public static final PropertyBool ACTIVE =  PropertyBool.create("active");

    public Breaker(Material material, String registryName, Float hardness) {
        super(material);
        setUnlocalizedName(registryName);
        setRegistryName(registryName);
        setCreativeTab(ModInfo.TAB_FORGECRAFT);
        setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVE, false));
        setHardness(hardness);
    }

    private void doWork (World world, IBlockState state, BlockPos pos, TileBreaker tile){
        if (state.getValue(FACING) == EnumFacing.EAST) {
            if(tile.getCharge() > world.getBlockState(pos.east()).getBlockHardness(world, pos.east())) {
                if (world.getBlockState(pos.east()).getBlock() == Blocks.IRON_ORE) {
                    world.setBlockToAir(pos.east());
                    CommonUtils.spawnItemEntityFromWorld(world, pos.east(), new ItemStack(PrimalItems.IRON_DUST, ThreadLocalRandom.current().nextInt(1, 2)));
                    tile.getSlotStack(0).setItemDamage(tile.getSlotStack(0).getItemDamage()-1);
                }
            } else {
                tile.getSlotStack(0).setItemDamage(tile.getSlotStack(0).getItemDamage()-10);
            }
        }
        if (state.getValue(FACING) == EnumFacing.WEST) {
            if(tile.getCharge() > world.getBlockState(pos.west()).getBlockHardness(world, pos.west())) {
                if (world.getBlockState(pos.west()).getBlock() == Blocks.IRON_ORE) {
                    world.setBlockToAir(pos.west());
                    CommonUtils.spawnItemEntityFromWorld(world, pos.east(), new ItemStack(PrimalItems.IRON_DUST, ThreadLocalRandom.current().nextInt(1, 2)));
                }
            } else {
                tile.getSlotStack(0).setItemDamage(tile.getSlotStack(0).getItemDamage()-10);
            }
        }
        if (state.getValue(FACING) == EnumFacing.SOUTH) {
            if(tile.getCharge() > world.getBlockState(pos.south()).getBlockHardness(world, pos.south())) {
                if (world.getBlockState(pos.south()).getBlock() == Blocks.IRON_ORE) {
                    world.setBlockToAir(pos.south());
                    CommonUtils.spawnItemEntityFromWorld(world, pos.east(), new ItemStack(PrimalItems.IRON_DUST, ThreadLocalRandom.current().nextInt(1, 2)));
                }
            } else {
                tile.getSlotStack(0).setItemDamage(tile.getSlotStack(0).getItemDamage()-10);
            }
        }
        if (state.getValue(FACING) == EnumFacing.NORTH) {
            if(tile.getCharge() > world.getBlockState(pos.north()).getBlockHardness(world, pos.north())) {
                if (world.getBlockState(pos.north()).getBlock() == Blocks.IRON_ORE) {
                    world.setBlockToAir(pos.north());
                    CommonUtils.spawnItemEntityFromWorld(world, pos.east(), new ItemStack(PrimalItems.IRON_DUST, ThreadLocalRandom.current().nextInt(1, 2)));
                }
            } else {
                //tile.getSlotStack(0).damageItem(10, (EntityPlayer) null);
                tile.getSlotStack(0).setItemDamage(tile.getSlotStack(0).getItemDamage()-10);
            }
        }
        tile.setCharge(0.0f);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitx, float hity, float hitz) {

        if(!world.isRemote){
            TileBreaker tile = (TileBreaker) world.getTileEntity(pos);
            ItemStack pItem = player.inventory.getCurrentItem();

            if(state.getValue(ACTIVE) == true && player.isSneaking() && pItem.isEmpty()){
                world.setBlockState(pos, state.withProperty(FACING, state.getValue(FACING)).withProperty(ACTIVE, false));

                doWork(world, state, pos, tile);
            }
            if(!player.isSneaking() && pItem.isEmpty()) {
                if (!state.getValue(ACTIVE)) {
                    world.setBlockState(pos, state.withProperty(FACING, state.getValue(FACING)).withProperty(ACTIVE, true), 2);
                }
                if(tile.getCharge() < 181) {
                    tile.setCharge(tile.getCharge() + 2.0f);
                    tile.updateBlock();
                    //System.out.println(tile.charge);
                    return true;
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
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing()).withProperty(ACTIVE, false), 2);
        //}
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;

        if(state.getValue(ACTIVE ) == false) {
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

        if(state.getValue(ACTIVE)) {
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
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.EAST).withProperty(ACTIVE, false);
        }
        if (meta == 1) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.WEST).withProperty(ACTIVE, false);
        }
        if (meta == 2) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.SOUTH).withProperty(ACTIVE, false);
        }
        if (meta == 3) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVE, false);
        }
        if (meta == 4){
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.EAST).withProperty(ACTIVE, true);
        }
        if (meta == 5) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.WEST).withProperty(ACTIVE, true);
        }
        if (meta == 6) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.SOUTH).withProperty(ACTIVE, true);
        }
        if (meta == 7) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVE, true);
        }
        return iblockstate;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {ACTIVE, FACING});
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullyOpaque(IBlockState state)
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
