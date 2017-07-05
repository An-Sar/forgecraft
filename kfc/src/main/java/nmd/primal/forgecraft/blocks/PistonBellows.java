package nmd.primal.forgecraft.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nmd.primal.forgecraft.ModInfo;
import nmd.primal.forgecraft.init.ModSounds;
import nmd.primal.forgecraft.tiles.TileBloomery;
import nmd.primal.forgecraft.tiles.TileForge;
import nmd.primal.forgecraft.tiles.TilePistonBellows;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static nmd.primal.core.api.PrimalStates.ACTIVE;

/**
 * Created by mminaie on 1/1/17.
 */
public class PistonBellows extends CustomContainerFacing {

    //public static final PropertyBool ACTIVE =  PropertyBool.create("active");
    //protected static final AxisAlignedBB collideBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.99D, 1.0D);
    protected static final AxisAlignedBB boundBoxNorth = new AxisAlignedBB(0.1875D, 0.0D, 0.0D, 1.0D, 12 / 16D, 1.0D);
    protected static final AxisAlignedBB boundBoxSouth = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.8125D, 12 / 16D, 1.0D);
    protected static final AxisAlignedBB boundBoxEast = new AxisAlignedBB(0.0D, 0.0D, 0.1875D, 1.0D, 12 / 16D, 1.0D);
    protected static final AxisAlignedBB boundBoxWest = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 12 / 16D, 0.8125D);

    public PistonBellows(Material material, String registryName) {
        super(material, registryName);
        setCreativeTab(ModInfo.TAB_FORGECRAFT);
        setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVE, Boolean.valueOf(false)));
        setHardness(3.0f);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TilePistonBellows();
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {

        TilePistonBellows tile = (TilePistonBellows) worldIn.getTileEntity(pos);
        if (tile != null) {
            worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing()).withProperty(ACTIVE, Boolean.valueOf(false)), 2);
        }

    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!world.isRemote){
            //System.out.println(state.getValue(PistonBellows.FACING));
            if(state.getValue(ACTIVE) == false) {
                world.setBlockState(pos, state.withProperty(ACTIVE, true), 2);
                //world.playSound(pos, ForgecraftSounds.PISTON_BELLOWS, SoundCategory.BLOCKS, 1.0f, 1.0f);
                //world.playSound((double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), ModSounds.PISTON_BELLOWS, SoundCategory.BLOCKS, 1.0f, 1.0f, true);
                world.playSound(null, pos, ModSounds.PISTON_BELLOWS, SoundCategory.BLOCKS, 0.25F, 0.8F);
                //playSound(@Nullable EntityPlayer player, double x, double y, double z, SoundEvent soundIn, SoundCategory category, float volume, float pitch)


                if (state.getValue(PistonBellows.FACING) == EnumFacing.NORTH) {
                    BlockPos tempPos = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
                    if (world.getBlockState(tempPos).getBlock() instanceof Forge) {
                        TileForge tile = (TileForge) world.getTileEntity(tempPos);
                        if ((world.getBlockState(tempPos).getValue(ACTIVE) == true) && (world.getBlockState(tempPos).getValue(Forge.FACING) == EnumFacing.EAST)) {
                            if (tile != null) {
                                //System.out.println(world.getBlockState(tempPos).getValue(Forge.FACING));
                                tile.setHeat(tile.getHeat() + 50);
                                tile.updateBlock();
                                tile.markDirty();
                                return true;
                            }
                        }
                    }
                    if (world.getBlockState(tempPos).getBlock() instanceof BloomeryBase) {
                        TileBloomery tile = (TileBloomery) world.getTileEntity(tempPos);
                        if ((world.getBlockState(tempPos).getValue(ACTIVE) == true)
                                && (world.getBlockState(tempPos).getValue(FACING) == EnumFacing.EAST)) {
                            if (tile != null) {
                                //System.out.println(world.getBlockState(tempPos).getValue(Forge.FACING));
                                tile.setHeat(tile.getHeat() + 50);
                                tile.updateBlock();
                                tile.markDirty();
                                return true;
                            }
                        }
                    }
                }
                if (state.getValue(PistonBellows.FACING) == EnumFacing.SOUTH) {
                    BlockPos tempPos = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
                    if (world.getBlockState(tempPos).getBlock() instanceof Forge) {
                        TileForge tile = (TileForge) world.getTileEntity(tempPos);
                        if ((world.getBlockState(tempPos).getValue(ACTIVE) == true) && (world.getBlockState(tempPos).getValue(Forge.FACING) == EnumFacing.WEST)) {
                            if (tile != null) {
                                //System.out.println(world.getBlockState(tempPos).getValue(Forge.FACING));
                                tile.setHeat(tile.getHeat() + 50);
                                tile.updateBlock();
                                tile.markDirty();
                                return true;
                            }
                        }
                    }
                    if (world.getBlockState(tempPos).getBlock() instanceof BloomeryBase) {
                        TileBloomery tile = (TileBloomery) world.getTileEntity(tempPos);
                        if ((world.getBlockState(tempPos).getValue(ACTIVE) == true)
                                && (world.getBlockState(tempPos).getValue(FACING) == EnumFacing.WEST)) {
                            if (tile != null) {
                                //System.out.println(world.getBlockState(tempPos).getValue(Forge.FACING));
                                tile.setHeat(tile.getHeat() + 50);
                                tile.updateBlock();
                                tile.markDirty();
                                return true;
                            }
                        }
                    }
                }
                if (state.getValue(PistonBellows.FACING) == EnumFacing.EAST) {
                    BlockPos tempPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
                    if (world.getBlockState(tempPos).getBlock() instanceof Forge) {
                        TileForge tile = (TileForge) world.getTileEntity(tempPos);
                        if ((world.getBlockState(tempPos).getValue(ACTIVE) == true) && (world.getBlockState(tempPos).getValue(Forge.FACING) == EnumFacing.SOUTH)) {
                            if (tile != null) {
                                //System.out.println(world.getBlockState(tempPos).getValue(Forge.FACING));
                                tile.setHeat(tile.getHeat() + 50);
                                tile.updateBlock();
                                tile.markDirty();
                                return true;
                            }
                        }
                    }
                    if (world.getBlockState(tempPos).getBlock() instanceof BloomeryBase) {
                        TileBloomery tile = (TileBloomery) world.getTileEntity(tempPos);
                        if ((world.getBlockState(tempPos).getValue(ACTIVE) == true)
                                && (world.getBlockState(tempPos).getValue(FACING) == EnumFacing.SOUTH)) {
                            if (tile != null) {
                                //System.out.println(world.getBlockState(tempPos).getValue(Forge.FACING));
                                tile.setHeat(tile.getHeat() + 50);
                                tile.updateBlock();
                                tile.markDirty();
                                return true;
                            }
                        }
                    }
                }
                if (state.getValue(PistonBellows.FACING) == EnumFacing.WEST) {
                    BlockPos tempPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
                    if (world.getBlockState(tempPos).getBlock() instanceof Forge) {
                        TileForge tile = (TileForge) world.getTileEntity(tempPos);
                        if ((world.getBlockState(tempPos).getValue(ACTIVE) == true) && (world.getBlockState(tempPos).getValue(Forge.FACING) == EnumFacing.NORTH)) {
                            if (tile != null) {
                                //System.out.println(world.getBlockState(tempPos).getValue(Forge.FACING));
                                tile.setHeat(tile.getHeat() + 50);
                                tile.updateBlock();
                                tile.markDirty();
                                return true;
                            }
                        }
                    }
                    if (world.getBlockState(tempPos).getBlock() instanceof BloomeryBase) {
                        TileBloomery tile = (TileBloomery) world.getTileEntity(tempPos);
                        if ((world.getBlockState(tempPos).getValue(ACTIVE) == true)
                                && (world.getBlockState(tempPos).getValue(FACING) == EnumFacing.NORTH)) {
                            if (tile != null) {
                                //System.out.println(world.getBlockState(tempPos).getValue(Forge.FACING));
                                tile.setHeat(tile.getHeat() + 50);
                                tile.updateBlock();
                                tile.markDirty();
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }




    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        state = state.getActualState(source, pos);
        EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

        switch (enumfacing)
        {
            case EAST:
            default:
                return boundBoxEast;
            case SOUTH:
                return boundBoxSouth;
            case WEST:
                return boundBoxWest;
            case NORTH:
                return boundBoxNorth;
        }
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        /*if (!world.isRemote && world.getGameRules().getBoolean("doTileDrops"))
        {
            TilePistonBellows tile = (TilePistonBellows) world.getTileEntity(pos);
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
        }*/

        super.breakBlock(world, pos, state);
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

    //return new ExtendedBlockState(this, new IProperty[] { BotaniaStateProps.CARDINALS, Properties.StaticProperty }, new IUnlistedProperty[] { Properties.AnimationProperty });

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

    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        if(state.getValue(ACTIVE) == Boolean.TRUE) {
            if (state.getValue(PistonBellows.FACING) == EnumFacing.NORTH) {
                BlockPos tempPos = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
                if (world.getBlockState(tempPos).getBlock() instanceof Forge) {
                    TileForge tile = (TileForge) world.getTileEntity(tempPos);
                    if ((world.getBlockState(tempPos).getValue(ACTIVE) == true) && (world.getBlockState(tempPos).getValue(Forge.FACING) == EnumFacing.EAST)) {
                        makeEmbers(world, tempPos, world.rand);
                    }
                }
                if (world.getBlockState(tempPos).getBlock() instanceof BloomeryBase) {
                    TileBloomery tile = (TileBloomery) world.getTileEntity(tempPos);
                    if ((world.getBlockState(tempPos).getValue(ACTIVE) == true) && (world.getBlockState(tempPos).getValue(BloomeryBase.FACING) == EnumFacing.EAST)) {
                        makeEmbers(world, tempPos, world.rand);
                    }
                }
            }


            if (state.getValue(PistonBellows.FACING) == EnumFacing.SOUTH) {
                BlockPos tempPos = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
                if (world.getBlockState(tempPos).getBlock() instanceof Forge) {
                    TileForge tile = (TileForge) world.getTileEntity(tempPos);
                    if ((world.getBlockState(tempPos).getValue(ACTIVE) == true) && (world.getBlockState(tempPos).getValue(Forge.FACING) == EnumFacing.WEST)) {
                        makeEmbers(world, tempPos, world.rand);
                    }
                }
                if (world.getBlockState(tempPos).getBlock() instanceof BloomeryBase) {
                    TileBloomery tile = (TileBloomery) world.getTileEntity(tempPos);
                    if ((world.getBlockState(tempPos).getValue(ACTIVE) == true) && (world.getBlockState(tempPos).getValue(BloomeryBase.FACING) == EnumFacing.WEST)) {
                        makeEmbers(world, tempPos, world.rand);
                    }
                }
            }


            if (state.getValue(PistonBellows.FACING) == EnumFacing.EAST) {
                BlockPos tempPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
                if (world.getBlockState(tempPos).getBlock() instanceof Forge) {
                    TileForge tile = (TileForge) world.getTileEntity(tempPos);
                    if ((world.getBlockState(tempPos).getValue(ACTIVE) == true) && (world.getBlockState(tempPos).getValue(Forge.FACING) == EnumFacing.SOUTH)) {
                        makeEmbers(world, tempPos, world.rand);
                    }
                }
                if (world.getBlockState(tempPos).getBlock() instanceof BloomeryBase) {
                    TileBloomery tile = (TileBloomery) world.getTileEntity(tempPos);
                    if ((world.getBlockState(tempPos).getValue(ACTIVE) == true) && (world.getBlockState(tempPos).getValue(BloomeryBase.FACING) == EnumFacing.SOUTH)) {
                        makeEmbers(world, tempPos, world.rand);
                    }
                }
            }


            if (state.getValue(PistonBellows.FACING) == EnumFacing.WEST) {
                BlockPos tempPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
                if (world.getBlockState(tempPos).getBlock() instanceof Forge) {
                    TileForge tile = (TileForge) world.getTileEntity(tempPos);
                    if ((world.getBlockState(tempPos).getValue(ACTIVE) == true) && (world.getBlockState(tempPos).getValue(Forge.FACING) == EnumFacing.NORTH)) {
                        makeEmbers(world, tempPos, world.rand);
                    }
                }
                if (world.getBlockState(tempPos).getBlock() instanceof BloomeryBase) {
                    TileBloomery tile = (TileBloomery) world.getTileEntity(tempPos);
                    if ((world.getBlockState(tempPos).getValue(ACTIVE) == true) && (world.getBlockState(tempPos).getValue(BloomeryBase.FACING) == EnumFacing.NORTH)) {
                        makeEmbers(world, tempPos, world.rand);
                    }
                }
            }
        }
    }
    private void makeEmbers(World world, BlockPos pos, Random rand){
        double d0 = (double)pos.getX() + 0.5D;
        double d1 = (double)pos.getY() + 0.2D;
        double d2 = (double)pos.getZ() + 0.5D;
        double d3 = 0.52D;
        double d4 = ThreadLocalRandom.current().nextDouble(0.066, 0.33);
        double ySpeed = ThreadLocalRandom.current().nextDouble(0.05, 0.20);

        if(rand.nextInt(3) == 0){
            world.spawnParticle(EnumParticleTypes.FLAME, d0+d4, d1, d2+d4, 0.0D, ySpeed, 0.0D, new int[0]);
            world.spawnParticle(EnumParticleTypes.FLAME, d0+d4, d1, d2-d4, 0.0D, ySpeed, 0.0D, new int[0]);
        }
        if(rand.nextInt(3) == 1){
            world.spawnParticle(EnumParticleTypes.FLAME, d0+d4, d1, d2-d4, 0.0D, ySpeed, 0.0D, new int[0]);
            world.spawnParticle(EnumParticleTypes.FLAME, d0-d4, d1, d2+d4, 0.0D, ySpeed, 0.0D, new int[0]);
        }
        if(rand.nextInt(3) == 2){
            world.spawnParticle(EnumParticleTypes.FLAME, d0-d4, d1, d2+d4, 0.0D, ySpeed, 0.0D, new int[0]);
            world.spawnParticle(EnumParticleTypes.FLAME, d0-d4, d1, d2-d4, 0.0D, ySpeed, 0.0D, new int[0]);
        }
        if(rand.nextInt(3) == 3){
            world.spawnParticle(EnumParticleTypes.FLAME, d0-d4, d1, d2-d4, 0.0D, ySpeed, 0.0D, new int[0]);
            world.spawnParticle(EnumParticleTypes.FLAME, d0+d4, d1, d2+d4, 0.0D, ySpeed, 0.0D, new int[0]);
        }
    }


}


/*
            if(rand.nextInt(4) == 1){
                world.spawnParticle(EnumParticleTypes.FLAME, d0+d4, d1, d2+d4, 0.0D, (double) (rand.nextInt(2) + 1)/1000, 0.0D, new int[0]);
                world.spawnParticle(EnumParticleTypes.FLAME, d0+d4, d1, d2-d4, 0.0D, (double) (rand.nextInt(2) + 1)/1000, 0.0D, new int[0]);
            }
            if(rand.nextInt(4) == 2){
                world.spawnParticle(EnumParticleTypes.FLAME, d0+d4, d1, d2-d4, 0.0D, (double) (rand.nextInt(2) + 1)/1000, 0.0D, new int[0]);
                world.spawnParticle(EnumParticleTypes.FLAME, d0-d4, d1, d2+d4, 0.0D, (double) (rand.nextInt(2) + 1)/1000, 0.0D, new int[0]);
            }
            if(rand.nextInt(4) == 3){
                world.spawnParticle(EnumParticleTypes.FLAME, d0-d4, d1, d2+d4, 0.0D, (double) (rand.nextInt(2) + 1)/1000, 0.0D, new int[0]);
                world.spawnParticle(EnumParticleTypes.FLAME, d0-d4, d1, d2-d4, 0.0D, (double) (rand.nextInt(2) + 1)/1000, 0.0D, new int[0]);
            }
            if(rand.nextInt(4) == 4){
                world.spawnParticle(EnumParticleTypes.FLAME, d0-d4, d1, d2-d4, 0.0D, (double) (rand.nextInt(2) + 1)/1000, 0.0D, new int[0]);
                world.spawnParticle(EnumParticleTypes.FLAME, d0+d4, d1, d2+d4, 0.0D, (double) (rand.nextInt(2) + 1)/1000, 0.0D, new int[0]);
            }
 */