package nmd.primal.forgecraft.blocks;

import net.minecraft.block.BlockLog;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nmd.primal.forgecraft.CommonUtils;
import nmd.primal.forgecraft.ModInfo;
import nmd.primal.forgecraft.init.ModItems;
import nmd.primal.forgecraft.tiles.TileBloomery;
import nmd.primal.forgecraft.tiles.TileFirebox;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by mminaie on 1/21/17.
 */
public class Bloomery extends CustomContainerFacing implements ITileEntityProvider {

    public static final PropertyBool ACTIVE =  PropertyBool.create("active");
    public static final PropertyBool COVERED =  PropertyBool.create("covered");

    public Bloomery(Material material, String registryName) {
        super(material);
        setUnlocalizedName(ModInfo.ForgecraftBlocks.BLOOMERY.getUnlocalizedName());
        setRegistryName(registryName);
        //setRegistryName(ModInfo.ForgecraftBlocks.FIREBOX.getRegistryName());
        setCreativeTab(ModInfo.TAB_FORGECRAFT);
        setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVE, Boolean.valueOf(false)));
        setHardness(3.0f);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileBloomery();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote) {
            TileBloomery tile = (TileBloomery) world.getTileEntity(pos);
            if (tile != null) {
                ItemStack pItem = player.inventory.getCurrentItem();
                ItemStack tileItem = tile.getSlotStack(0);
                ItemStack tileItem1 = tile.getSlotStack(1);
                if(pItem.isEmpty()) {
                    /*if (player.isSneaking()) {
                        if (!tileItem.isEmpty()) {
                            CommonUtils.spawnItemEntity(world, player, tile.getSlotStack(0));
                            tile.setSlotStack(0, ItemStack.EMPTY);
                            tile.markDirty();
                            tile.updateBlock();
                            return true;
                        }
                    }*/
                    if(!player.isSneaking()){
                        if(world.getBlockState(pos).getValue(ACTIVE) == true){
                            Integer tempInt = tile.getHeat();
                            String tempString = tempInt.toString();
                            ITextComponent itextcomponent = new TextComponentString(tempString);
                            player.sendStatusMessage(itextcomponent, true);
                            //System.out.println(pos);
                            return true;
                        }
                    }
                }
                if(tile.getSlotStack(0) != ItemStack.EMPTY) {
                    if ((pItem.getItem() == Items.FLINT_AND_STEEL) //|| (pItem.getItem() == PrimalItems.FIRE_BOW)
                            || pItem.getItem() == Item.getItemFromBlock(Blocks.TORCH)) {
                        world.setBlockState(pos, state.withProperty(ACTIVE, true), 2);
                        tile.markDirty();
                        tile.updateBlock();
                        return true;
                    }
                }
                if((!pItem.isEmpty()) && tile.isItemValidForSlot(0, pItem)) {
                    if (!tileItem.isEmpty()){
                        if(pItem.getItem() == tileItem.getItem()){
                            if(tileItem.getCount() < 64){
                                if(tileItem.getCount() + pItem.getCount() <= 64){
                                    tileItem.grow(pItem.getCount());
                                    player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
                                    tile.markDirty();
                                    tile.updateBlock();
                                    return true;
                                }
                                if(tileItem.getCount() + pItem.getCount() > 64){
                                    pItem.setCount(64-pItem.getCount());
                                    tileItem.setCount(64);
                                    tile.markDirty();
                                    tile.updateBlock();
                                    return true;
                                }
                            }
                        }
                    }
                    if(tileItem.isEmpty()) {
                        tile.setSlotStack(0, pItem);
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
                        return true;
                    }
                }

                if((!pItem.isEmpty()) && tile.isItemValidForSlot(1, pItem)) {
                    if (!tileItem1.isEmpty()) {
                        return false;
                    }
                    if(tileItem1.isEmpty()){
                        ItemStack tempItem = new ItemStack(ModItems.softcrucible, 1);
                        tile.setSlotStack(1, tempItem);
                        pItem.shrink(1);
                    }
                }

                if(!pItem.isEmpty()) {
                    if(pItem.getItem() == Item.getItemFromBlock(Blocks.STONE_SLAB)){
                        world.setBlockState(pos, state.withProperty(COVERED, true), 2);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*@Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity ent)
    {
        if (!world.isRemote)
        {
            if(ent instanceof EntityItem){
                //System.out.println("collision");
                EntityItem itemEnt = (EntityItem) ent;
                ItemStack stack = itemEnt.getEntityItem();
                //System.out.println(stack);
                TileFirebox tile = (TileFirebox)world.getTileEntity(pos);
                if (tile != null) {
                    if(!tile.getSlotStack(0).isEmpty()) {
                        if(tile.getSlotStack(0).getItem() == stack.getItem()) {
                            int entStackSize = stack.getCount();
                            int tileStackSize = tile.getSlotStack(0).getCount();
                            int tileSizeRemaining = 64 - tileStackSize;
                            if (tileStackSize < 64) {
                                if (entStackSize <= tileSizeRemaining) {
                                    tile.incrementStackSize(tile.getSlotList(), 0, entStackSize);
                                    //tile.setSlotStack(0, new ItemStack(stack.getItem(), tileStackSize + entStackSize, stack.getItemDamage()));
                                    ent.setDead();
                                    world.notifyBlockUpdate(pos, state, state, 3);
                                    tile.updateBlock();
                                }
                                if (entStackSize > tileSizeRemaining) {
                                    tile.getSlotStack(0).setCount(64);
                                    stack.setCount(64 - entStackSize);
                                }
                            }
                        }
                    }
                    if (tile.getSlotStack(0).isEmpty()) {
                        //int entStackSize = stack.getCount();
                        tile.setSlotStack(0, itemEnt.getEntityItem());
                        itemEnt.setDead();
                        world.notifyBlockUpdate(pos, state, state, 3);
                        tile.updateBlock();
                    }
                }
            }
        }
    }
*/
    public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {

    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        int lightState =0;
        if(state.getValue(ACTIVE) == true){
            lightState = 10;
        }
        return lightState;
    }

    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return 0;
    }

    public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return true;
    }

    @Override
    public boolean isFireSource(World world, BlockPos pos, EnumFacing side)
    {
        if(!world.isRemote){
            if(world.getBlockState(pos).getValue(ACTIVE)==true){
                return true;
            }
        }
        return false;
    }

    /**
     * Called serverside after this block is replaced with another in Chunk, but before the Tile Entity is updated
     */
    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        if (!world.isRemote && world.getGameRules().getBoolean("doTileDrops"))
        {
            TileBloomery tile = (TileBloomery) world.getTileEntity(pos);
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
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing()).withProperty(ACTIVE, Boolean.valueOf(false)).withProperty(COVERED, Boolean.valueOf(false)), 2);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;

        if( state.getValue(FACING) == EnumFacing.EAST && state.getValue(ACTIVE) == false && state.getValue(COVERED) == false){
            i = 0;
            return i;
        }
        if( state.getValue(FACING) == EnumFacing.WEST && state.getValue(ACTIVE) == false && state.getValue(COVERED) == false){
            i = 1;
            return i;
        }
        if( state.getValue(FACING) == EnumFacing.SOUTH && state.getValue(ACTIVE) == false && state.getValue(COVERED) == false){
            i = 2;
            return i;
        }
        if( state.getValue(FACING) == EnumFacing.NORTH && state.getValue(ACTIVE) == false && state.getValue(COVERED) == false){
            i = 3;
            return i;
        }
        if( state.getValue(FACING) == EnumFacing.EAST && state.getValue(ACTIVE) == true && state.getValue(COVERED) == false){
            i = 4;
            return i;
        }
        if( state.getValue(FACING) == EnumFacing.WEST && state.getValue(ACTIVE) == true && state.getValue(COVERED) == false){
            i = 5;
            return i;
        }
        if( state.getValue(FACING) == EnumFacing.SOUTH && state.getValue(ACTIVE) == true && state.getValue(COVERED) == false){
            i = 6;
            return i;
        }
        if( state.getValue(FACING) == EnumFacing.NORTH && state.getValue(ACTIVE) == true && state.getValue(COVERED) == false){
            i = 7;
            return i;
        }
        if( state.getValue(FACING) == EnumFacing.EAST && state.getValue(ACTIVE) == true && state.getValue(COVERED) == true){
            i = 8;
            return i;
        }
        if( state.getValue(FACING) == EnumFacing.WEST && state.getValue(ACTIVE) == true && state.getValue(COVERED) == true){
            i = 9;
            return i;
        }
        if( state.getValue(FACING) == EnumFacing.SOUTH && state.getValue(ACTIVE) == true && state.getValue(COVERED) == true){
            i = 10;
            return i;
        }
        if( state.getValue(FACING) == EnumFacing.NORTH && state.getValue(ACTIVE) == true && state.getValue(COVERED) == true){
            i = 11;
            return i;
        }
        if( state.getValue(FACING) == EnumFacing.EAST && state.getValue(ACTIVE) == false && state.getValue(COVERED) == true){
            i = 12;
            return i;
        }
        if( state.getValue(FACING) == EnumFacing.WEST && state.getValue(ACTIVE) == false && state.getValue(COVERED) == true){
            i = 13;
            return i;
        }
        if( state.getValue(FACING) == EnumFacing.SOUTH && state.getValue(ACTIVE) == false && state.getValue(COVERED) == true){
            i = 14;
            return i;
        }
        if( state.getValue(FACING) == EnumFacing.NORTH && state.getValue(ACTIVE) == false && state.getValue(COVERED) == true){
            i = 15;
            return i;
        }
        return i;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState();

        if (meta == 0){
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.EAST).withProperty(ACTIVE, Boolean.valueOf(false)).withProperty(COVERED, Boolean.valueOf(false));
        }
        if (meta == 1) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.WEST).withProperty(ACTIVE, Boolean.valueOf(false)).withProperty(COVERED, Boolean.valueOf(false));
        }
        if (meta == 2) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.SOUTH).withProperty(ACTIVE, Boolean.valueOf(false)).withProperty(COVERED, Boolean.valueOf(false));
        }
        if (meta == 3) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVE, Boolean.valueOf(false)).withProperty(COVERED, Boolean.valueOf(false));
        }
        if (meta == 4) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.EAST).withProperty(ACTIVE, Boolean.valueOf(true)).withProperty(COVERED, Boolean.valueOf(false));
        }
        if (meta == 5) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.WEST).withProperty(ACTIVE, Boolean.valueOf(true)).withProperty(COVERED, Boolean.valueOf(false));
        }
        if (meta == 6) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.SOUTH).withProperty(ACTIVE, Boolean.valueOf(true)).withProperty(COVERED, Boolean.valueOf(false));
        }
        if (meta == 7) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVE, Boolean.valueOf(true)).withProperty(COVERED, Boolean.valueOf(false));
        }
        if (meta == 8) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.EAST).withProperty(ACTIVE, Boolean.valueOf(true)).withProperty(COVERED, Boolean.valueOf(true));
        }
        if (meta == 9) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.WEST).withProperty(ACTIVE, Boolean.valueOf(true)).withProperty(COVERED, Boolean.valueOf(true));
        }
        if (meta == 10) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.SOUTH).withProperty(ACTIVE, Boolean.valueOf(true)).withProperty(COVERED, Boolean.valueOf(true));
        }
        if (meta == 11) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVE, Boolean.valueOf(true)).withProperty(COVERED, Boolean.valueOf(true));
        }
        if (meta == 12) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.EAST).withProperty(ACTIVE, Boolean.valueOf(false)).withProperty(COVERED, Boolean.valueOf(true));
        }
        if (meta == 13) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.WEST).withProperty(ACTIVE, Boolean.valueOf(false)).withProperty(COVERED, Boolean.valueOf(true));
        }
        if (meta == 14) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.SOUTH).withProperty(ACTIVE, Boolean.valueOf(false)).withProperty(COVERED, Boolean.valueOf(true));
        }
        if (meta == 15) {
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVE, Boolean.valueOf(false)).withProperty(COVERED, Boolean.valueOf(true));
        }
        return iblockstate;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {FACING, ACTIVE, COVERED});
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

    @SideOnly(Side.CLIENT)
    @SuppressWarnings("incomplete-switch")
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        if(state.getValue(Bloomery.ACTIVE) == true)
        {
            double d0 = (double)pos.getX() + 0.5D;
            double d1 = (double)pos.getY() + 0.2D;
            double d2 = (double)pos.getZ() + 0.5D;
            double d3 = 0.52D;
            //double d4 = rand.nextDouble() * 0.6D - 0.3D;
            double d4 = ThreadLocalRandom.current().nextDouble(0.15, 0.35);

            if (rand.nextDouble() < 0.1D)
            {
                world.playSound((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }
            if(rand.nextInt(4) == 1){
                world.spawnParticle(EnumParticleTypes.FLAME, d0+d4, d1, d2+d4, 0.0D, 0.001D, 0.0D, new int[0]);
                world.spawnParticle(EnumParticleTypes.FLAME, d0+d4, d1, d2+d4, 0.0D, 0.001D, 0.0D, new int[0]);
            }
            if(rand.nextInt(4) == 2){
                world.spawnParticle(EnumParticleTypes.FLAME, d0+d4, d1, d2-d4, 0.0D, 0.001D, 0.0D, new int[0]);
                world.spawnParticle(EnumParticleTypes.FLAME, d0+d4, d1, d2+d4, 0.0D, 0.001D, 0.0D, new int[0]);
            }
            if(rand.nextInt(4) == 3){
                world.spawnParticle(EnumParticleTypes.FLAME, d0-d4, d1, d2+d4, 0.0D, 0.001D, 0.0D, new int[0]);
                world.spawnParticle(EnumParticleTypes.FLAME, d0+d4, d1, d2+d4, 0.0D, 0.001D, 0.0D, new int[0]);
            }
            if(rand.nextInt(4) == 4){
                world.spawnParticle(EnumParticleTypes.FLAME, d0-d4, d1, d2-d4, 0.0D, 0.001D, 0.0D, new int[0]);
                world.spawnParticle(EnumParticleTypes.FLAME, d0+d4, d1, d2+d4, 0.0D, 0.001D, 0.0D, new int[0]);
            }
        }
    }
}