package nmd.primal.forgecraft.blocks;

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
import net.minecraft.nbt.NBTTagCompound;
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
//import nmd.primal.core.api.PrimalBlocks;
import nmd.primal.core.api.PrimalItems;
import nmd.primal.forgecraft.CommonUtils;
import nmd.primal.forgecraft.ModInfo;
import nmd.primal.forgecraft.init.ModBlocks;
import nmd.primal.forgecraft.init.ModItems;
import nmd.primal.forgecraft.items.toolparts.ToolPart;
import nmd.primal.forgecraft.tiles.TileForge;

import javax.annotation.Nullable;
import java.util.Random;



/**
 * Created by kitsu on 11/26/2016.
 */
public class Forge extends CustomContainerFacing implements ITileEntityProvider/*, ITextComponent*/ {

    public static final PropertyBool ACTIVE =  PropertyBool.create("active");
    protected static final AxisAlignedBB collideBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.99D, 1.0D);
    protected static final AxisAlignedBB boundBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

    public Forge(Material material) {
        super(material);
        setUnlocalizedName(ModInfo.ForgecraftBlocks.FIREBOX.getUnlocalizedName());
        setRegistryName(ModInfo.ForgecraftBlocks.FIREBOX.getRegistryName());
        setCreativeTab(ModInfo.TAB_FORGECRAFT);
        setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVE, Boolean.valueOf(false)));
        setHardness(3.0f);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileForge();
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return collideBox;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return boundBox;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote) {
            TileForge tile = (TileForge) world.getTileEntity(pos);
            if (tile != null) {
                ItemStack pItem = player.inventory.getCurrentItem();
                ItemStack fuelItem = tile.getSlotStack(0);
                //System.out.println(pItem.getItem().getRegistryName().toString());




                /***********************
                FUEL SLOT CODE
                 ***********************/
                if(pItem.isEmpty()) {
                    if (player.isSneaking()) {
                        if (!fuelItem.isEmpty()) {
                            if(facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH || facing == EnumFacing.EAST || facing == EnumFacing.WEST ) {
                            //System.out.println();
                                CommonUtils.spawnItemEntity(world, player, tile.getSlotStack(0));
                                tile.setSlotStack(0, ItemStack.EMPTY);
                                tile.markDirty();
                                tile.updateBlock();
                                return true;
                            }
                        }
                    }
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
                if((pItem.getItem() == Items.FLINT_AND_STEEL) || (pItem.getItem() == PrimalItems.FIRE_BOW) || pItem.getItem() == Item.getItemFromBlock(Blocks.TORCH)) {
                    world.setBlockState(pos, state.withProperty(ACTIVE, true), 2);
                    tile.setHeat(100);
                    tile.markDirty();
                    tile.updateBlock();
                    return true;
                }
                if((!pItem.isEmpty()) && tile.isItemValidForSlot(0, pItem)) {
                    if (!fuelItem.isEmpty()){
                        if(pItem.getItem() == fuelItem.getItem()){
                            if(fuelItem.getCount() < 64){
                                if(fuelItem.getCount() + pItem.getCount() <= 64){
                                    fuelItem.grow(pItem.getCount());
                                    player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
                                    tile.markDirty();
                                    tile.updateBlock();
                                    return true;
                                }
                                if(fuelItem.getCount() + pItem.getCount() > 64){
                                    pItem.setCount(64-pItem.getCount());
                                    fuelItem.setCount(64);
                                    tile.markDirty();
                                    tile.updateBlock();
                                    return true;
                                }
                            }
                        }
                    }
                    if(fuelItem.isEmpty()) {
                        tile.setSlotStack(0, pItem);
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
                        return true;
                    }
                }
                /***********************
                 FORGING SLOTS CODE
                 ***********************/
                //REMOVE COOL INGOT
                if(facing == EnumFacing.UP ) {
                    if (pItem.isEmpty()) {
                        for (int i = 2; i < tile.getSlotListSize(); i++) {
                            //System.out.println(i);
                            if (!tile.getSlotStack(i).isEmpty()) {
                                if (tile.getSlotStack(i).getItem().equals(new ItemStack(ModBlocks.ironchunk).getItem())) {
                                    CommonUtils.spawnItemEntity(world, player, tile.getSlotStack(i));
                                    tile.setSlotStack(i, ItemStack.EMPTY);
                                    return true;
                                }
                                if (tile.getSlotStack(i).getItem().equals(new ItemStack(ModBlocks.ironball).getItem())) {
                                    CommonUtils.spawnItemEntity(world, player, tile.getSlotStack(i));
                                    tile.setSlotStack(i, ItemStack.EMPTY);
                                    return true;
                                }
                                if(tile.getSlotStack(i).hasTagCompound() == true){
                                    if (tile.getSlotStack(i).getTagCompound().getBoolean("hot") == false) {
                                        CommonUtils.spawnItemEntity(world, player, tile.getSlotStack(i));
                                        tile.setSlotStack(i, ItemStack.EMPTY);
                                        return true;
                                    }
                                }

                            }
                        }
                    }


                    if (pItem.getItem().equals(new ItemStack(ModBlocks.ironchunk).getItem())) {
                        //System.out.println("Activating");
                        for (int i = 2; i <= tile.getSlotListSize(); i++) {
                            if (tile.getSlotStack(i).isEmpty()) {
                                tile.setSlotStack(i, new ItemStack(pItem.getItem(), 1));
                                pItem.shrink(1);
                                return true;
                            }
                        }
                    }
                    if (pItem.getItem().equals(new ItemStack(ModBlocks.ironball).getItem())) {
                        for (int i = 2; i < tile.getSlotListSize(); i++) {
                            if (tile.getSlotStack(i).isEmpty()) {
                                tile.setSlotStack(i, new ItemStack(pItem.getItem(), 1));
                                pItem.shrink(1);
                                return true;
                            }
                        }
                    }

                    if(pItem.getItem() instanceof ToolPart){
                        if(tile.getSlotStack(4).isEmpty()){
                            //System.out.println("Adding player Item to slot");
                            tile.setSlotStack(4, pItem);
                            //System.out.println(tile.getSlotStack(4));
                            player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
                            return true;
                        }
                    }

                }
            }
            //System.out.println(tile.getSlotStack(0));
            //System.out.println(tile.getSlotStack(1));
            //System.out.println(tile.getSlotStack(2));
            //System.out.println(tile.getSlotStack(3));
            //System.out.println(tile.getSlotStack(4));
            //System.out.println(tile.getSlotStack(5));
            //System.out.println(tile.getSlotStack(6));
        }
        return false;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity ent)
    {
        if (!world.isRemote)
        {
            if(ent instanceof EntityItem){
                //System.out.println("collision");
                EntityItem itemEnt = (EntityItem) ent;
                ItemStack stack = itemEnt.getEntityItem();
                //System.out.println(stack);
                TileForge tile = (TileForge)world.getTileEntity(pos);
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

    public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {

    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        if(state.getValue(ACTIVE) == true){
            return 15;
        }
        return 0;
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
        if (side == EnumFacing.UP)
        {
            if(!world.isRemote){
                TileForge tile = (TileForge) world.getTileEntity(pos);
                if(tile.getSlotStack(0) != ItemStack.EMPTY){
                    if(world.getBlockState(pos).getValue(ACTIVE)==true){
                        return true;
                    }
                }
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
            TileForge tile = (TileForge) world.getTileEntity(pos);
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

    /*@Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        IBlockState state = super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
        return state.withProperty(FACING, placer.getHorizontalFacing()).withProperty(ACTIVE, Boolean.valueOf(false));
    }*/

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing()).withProperty(ACTIVE, Boolean.valueOf(false)), 2);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;

        if( (state.getValue(FACING) == EnumFacing.EAST) && state.getValue(ACTIVE) == false){
            i = 0;
            return i;
        }
        if( (state.getValue(FACING) == EnumFacing.WEST) && state.getValue(ACTIVE) == false){
            i = 1;
            return i;
        }
        if( (state.getValue(FACING) == EnumFacing.SOUTH) && state.getValue(ACTIVE) == false){
            i = 2;
            return i;
        }
        if( (state.getValue(FACING) == EnumFacing.NORTH) && state.getValue(ACTIVE) == false){
            i = 3;
            return i;
        }
        if( (state.getValue(FACING) == EnumFacing.EAST) && state.getValue(ACTIVE) == true){
            i = 4;
            return i;
        }
        if( (state.getValue(FACING) == EnumFacing.WEST) && state.getValue(ACTIVE) == true){
            i = 5;
            return i;
        }
        if( (state.getValue(FACING) == EnumFacing.SOUTH) && state.getValue(ACTIVE) == true){
            i = 6;
            return i;
        }
        if( (state.getValue(FACING) == EnumFacing.NORTH) && state.getValue(ACTIVE) == true){
            i = 7;
            return i;
        }

        return i;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumfacing;
        Boolean active;



        switch (meta & 7)
        {
            case 0:
                enumfacing = EnumFacing.EAST;
                active = false;
                break;
            case 1:
                enumfacing = EnumFacing.WEST;
                active = false;
                break;
            case 2:
                enumfacing = EnumFacing.SOUTH;
                active = false;
                break;
            case 3:
                enumfacing = EnumFacing.NORTH;
                active = false;
                break;
            case 4:
                enumfacing = EnumFacing.EAST;
                active = true;
                break;
            case 5:
                enumfacing = EnumFacing.WEST;
                active = true;
                break;
            case 6:
                enumfacing = EnumFacing.SOUTH;
                active = true;
                break;
            case 7:
                enumfacing = EnumFacing.NORTH;
                active = true;
                break;
            default:
                enumfacing = EnumFacing.NORTH;
                active = false;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing).withProperty(ACTIVE, Boolean.valueOf(active));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {FACING, ACTIVE});
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
        if(state.getValue(Forge.ACTIVE) == true)
        {
            double d0 = (double)pos.getX() + 0.5D;
            double d1 = (double)pos.getY() + 0.96D;
            double d2 = (double)pos.getZ() + 0.5D;
            double d3 = 0.52D;
            double d4 = rand.nextDouble() * 0.6D - 0.3D;

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