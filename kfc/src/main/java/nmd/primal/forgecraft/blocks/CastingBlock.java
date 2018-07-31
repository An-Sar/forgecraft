package nmd.primal.forgecraft.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nmd.primal.core.api.PrimalAPI;
import nmd.primal.core.common.helper.CommonUtils;
import nmd.primal.core.common.helper.PlayerHelper;
import nmd.primal.forgecraft.ModInfo;
import nmd.primal.forgecraft.init.ModBlocks;
import nmd.primal.forgecraft.init.ModItems;
import nmd.primal.forgecraft.items.casting.CastingPart;
import nmd.primal.forgecraft.tiles.TileCastingBlock;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by mminaie on 6/24/17.
 */
public class CastingBlock extends CustomContainerFacing {

    protected static AxisAlignedBB bound = new AxisAlignedBB(0/16D, 0.0D, 0/16D, 16/16D, 5.5/16D, 16/16D);

    public CastingBlock(Material material, String registryName) {
        super(material, registryName);
        setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        setCreativeTab(ModInfo.TAB_FORGECRAFT);
    }
    private void makeEmbers(World world, BlockPos pos, Random rand){
        double d0 = (double)pos.getX() + 0.5D;
        double d1 = (double)pos.getY() + 0.2D;
        double d2 = (double)pos.getZ() + 0.5D;
        double d3 = 0.52D;
        double d4 = ThreadLocalRandom.current().nextDouble(0.066, 0.33);
        double ySpeed = ThreadLocalRandom.current().nextDouble(0.05, 0.20);

        if(rand.nextInt(3) == 0){
            world.spawnParticle(EnumParticleTypes.FLAME, d0+d4, d1, d2+d4, ySpeed, ySpeed, ySpeed, new int[0]);
            world.spawnParticle(EnumParticleTypes.FLAME, d0+d4, d1, d2-d4, ySpeed, ySpeed, ySpeed, new int[0]);
        }
        if(rand.nextInt(3) == 1){
            world.spawnParticle(EnumParticleTypes.FLAME, d0+d4, d1, d2-d4, ySpeed, ySpeed, ySpeed, new int[0]);
            world.spawnParticle(EnumParticleTypes.FLAME, d0-d4, d1, d2+d4, ySpeed, ySpeed, ySpeed, new int[0]);
        }
        if(rand.nextInt(3) == 2){
            world.spawnParticle(EnumParticleTypes.FLAME, d0-d4, d1, d2+d4, ySpeed, ySpeed, ySpeed, new int[0]);
            world.spawnParticle(EnumParticleTypes.FLAME, d0-d4, d1, d2-d4, ySpeed, ySpeed, ySpeed, new int[0]);
        }
        if(rand.nextInt(3) == 3){
            world.spawnParticle(EnumParticleTypes.FLAME, d0-d4, d1, d2-d4, ySpeed, ySpeed, ySpeed, new int[0]);
            world.spawnParticle(EnumParticleTypes.FLAME, d0+d4, d1, d2+d4, ySpeed, ySpeed, ySpeed, new int[0]);
        }
    }
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitx, float hity, float hitz) {

        if (!world.isRemote) {
            TileCastingBlock tile = (TileCastingBlock)  world.getTileEntity(pos);
            ItemStack pItem = player.inventory.getCurrentItem();

            if(player.isSneaking()) {
                if(!tile.getSlotStack(0).isEmpty()){
                    ItemStack copyStack = tile.getSlotStack(0).copy();
                    PlayerHelper.spawnItemOnPlayer(world, player, copyStack);
                    tile.setSlotStack(0, ItemStack.EMPTY);
                    tile.updateBlock();
                    return true;
                }
            }

            if(pItem.getItem() instanceof CastingPart){
                if(!player.isSneaking()) {
                    if (tile.getSlotStack(0).isEmpty()) {
                        ItemStack copyStack = pItem.copy();
                        copyStack.setCount(1);
                        pItem.shrink(1);
                        tile.setSlotStack(0, copyStack);
                        tile.updateBlock();
                        return true;
                    }
                }
            }

            if(pItem.getItem() == ModItems.stonetongs ){
                ItemStack dropCrucible = new ItemStack(ModBlocks.emptycrucible);
                if(pItem.getTagCompound().getInteger("type") == 40){
                    if(tile.getSlotStack(0).getItem() == ModItems.cast_hoe){
                        ItemStack dropStack = new ItemStack(ModItems.bronzehoehead);

                        PlayerHelper.spawnItemOnGround(world, pos, dropStack);
                        PlayerHelper.spawnItemOnGround(world, pos, dropCrucible);
                        pItem.getTagCompound().setInteger("type", 0);
                        if(PrimalAPI.randomCheck(2)){
                            tile.setSlotStack(0, ItemStack.EMPTY);
                        }
                        //makeEmbers(world, pos, world.rand);
                        return true;
                    }
                    if(tile.getSlotStack(0).getItem() == ModItems.cast_pickaxe){
                        ItemStack dropStack = new ItemStack(ModItems.bronzepickaxehead);
                        PlayerHelper.spawnItemOnGround(world, pos, dropStack);
                        PlayerHelper.spawnItemOnGround(world, pos, dropCrucible);
                        pItem.getTagCompound().setInteger("type", 0);
                        if(PrimalAPI.randomCheck(2)){
                            tile.setSlotStack(0, ItemStack.EMPTY);
                        }
                        return true;
                    }
                    if(tile.getSlotStack(0).getItem() == ModItems.cast_axe){
                        ItemStack dropStack = new ItemStack(ModItems.bronzeaxehead);
                        PlayerHelper.spawnItemOnGround(world, pos, dropStack);
                        PlayerHelper.spawnItemOnGround(world, pos, dropCrucible);
                        pItem.getTagCompound().setInteger("type", 0);
                        if(PrimalAPI.randomCheck(2)){
                            tile.setSlotStack(0, ItemStack.EMPTY);
                        }
                        return true;
                    }
                    if(tile.getSlotStack(0).getItem() == ModItems.cast_shovel){
                        ItemStack dropStack = new ItemStack(ModItems.bronzeshovelhead);
                        PlayerHelper.spawnItemOnGround(world, pos, dropStack);
                        PlayerHelper.spawnItemOnGround(world, pos, dropCrucible);
                        pItem.getTagCompound().setInteger("type", 0);
                        if(PrimalAPI.randomCheck(2)){
                            tile.setSlotStack(0, ItemStack.EMPTY);
                        }
                        return true;
                    }
                    if(tile.getSlotStack(0).getItem() == ModItems.cast_gladius){
                        ItemStack dropStack = new ItemStack(ModItems.bronzegladius);
                        PlayerHelper.spawnItemOnGround(world, pos, dropStack);
                        PlayerHelper.spawnItemOnGround(world, pos, dropCrucible);
                        pItem.getTagCompound().setInteger("type", 0);
                        if(PrimalAPI.randomCheck(2)){
                            tile.setSlotStack(0, ItemStack.EMPTY);
                        }
                        return true;
                    }
                }
                /***************************
                 *          Bronze          *
                 *
                 46 | Hot Cooked Bronze Crucible Emerald
                 48 | Hot Cooked Bronze Crucible Diamond
                 50 | Hot Cooked Bronze Crucible Redstone
                 52 | Hot Cooked Bronze Crucible Lapis
                 ***************************/
                if(pItem.getTagCompound().getInteger("type") == 46){
                    if(tile.getSlotStack(0).getItem() == ModItems.cast_hoe){
                        ItemStack dropStack = new ItemStack(ModItems.bronzehoehead);
                        dropStack.setTagCompound(new NBTTagCompound());
                        NBTTagCompound tags = new NBTTagCompound();

                        dropStack.getTagCompound().setTag("tags", tags);
                        dropStack.getSubCompound("tags").setBoolean("hot", false);

                        dropStack.getSubCompound("tags").setBoolean("emerald", true);
                        dropStack.getSubCompound("tags").setInteger("diamond", 0);
                        dropStack.getSubCompound("tags").setInteger("redstone", 0);
                        dropStack.getSubCompound("tags").setInteger("lapis", 0);

                        dropStack.getSubCompound("tags").setInteger("modifiers", 1);
                        PlayerHelper.spawnItemOnGround(world, pos, dropStack);
                        PlayerHelper.spawnItemOnGround(world, pos, dropCrucible);
                        pItem.getTagCompound().setInteger("type", 0);
                        if(PrimalAPI.randomCheck(2)){
                            tile.setSlotStack(0, ItemStack.EMPTY);
                        }
                        return true;
                    }
                    if(tile.getSlotStack(0).getItem() == ModItems.cast_pickaxe){
                        ItemStack dropStack = new ItemStack(ModItems.bronzepickaxehead);
                        dropStack.setTagCompound(new NBTTagCompound());
                        NBTTagCompound tags = new NBTTagCompound();

                        dropStack.getTagCompound().setTag("tags", tags);
                        dropStack.getSubCompound("tags").setBoolean("hot", false);

                        dropStack.getSubCompound("tags").setBoolean("emerald", true);
                        dropStack.getSubCompound("tags").setInteger("diamond", 0);
                        dropStack.getSubCompound("tags").setInteger("redstone", 0);
                        dropStack.getSubCompound("tags").setInteger("lapis", 0);

                        dropStack.getSubCompound("tags").setInteger("modifiers", 1);
                        PlayerHelper.spawnItemOnGround(world, pos, dropStack);
                        PlayerHelper.spawnItemOnGround(world, pos, dropCrucible);
                        pItem.getTagCompound().setInteger("type", 0);
                        if(PrimalAPI.randomCheck(2)){
                            tile.setSlotStack(0, ItemStack.EMPTY);
                        }
                        return true;
                    }
                    if(tile.getSlotStack(0).getItem() == ModItems.cast_axe){
                        ItemStack dropStack = new ItemStack(ModItems.bronzeaxehead);
                        dropStack.setTagCompound(new NBTTagCompound());
                        NBTTagCompound tags = new NBTTagCompound();

                        dropStack.getTagCompound().setTag("tags", tags);
                        dropStack.getSubCompound("tags").setBoolean("hot", false);

                        dropStack.getSubCompound("tags").setBoolean("emerald", true);
                        dropStack.getSubCompound("tags").setInteger("diamond", 0);
                        dropStack.getSubCompound("tags").setInteger("redstone", 0);
                        dropStack.getSubCompound("tags").setInteger("lapis", 0);

                        dropStack.getSubCompound("tags").setInteger("modifiers", 1);
                        PlayerHelper.spawnItemOnGround(world, pos, dropStack);
                        PlayerHelper.spawnItemOnGround(world, pos, dropCrucible);
                        pItem.getTagCompound().setInteger("type", 0);
                        if(PrimalAPI.randomCheck(2)){
                            tile.setSlotStack(0, ItemStack.EMPTY);
                        }
                        return true;
                    }
                    if(tile.getSlotStack(0).getItem() == ModItems.cast_shovel){
                        ItemStack dropStack = new ItemStack(ModItems.bronzeshovelhead);
                        dropStack.setTagCompound(new NBTTagCompound());
                        NBTTagCompound tags = new NBTTagCompound();

                        dropStack.getTagCompound().setTag("tags", tags);
                        dropStack.getSubCompound("tags").setBoolean("hot", false);

                        dropStack.getSubCompound("tags").setBoolean("emerald", true);
                        dropStack.getSubCompound("tags").setInteger("diamond", 0);
                        dropStack.getSubCompound("tags").setInteger("redstone", 0);
                        dropStack.getSubCompound("tags").setInteger("lapis", 0);

                        dropStack.getSubCompound("tags").setInteger("modifiers", 1);
                        PlayerHelper.spawnItemOnGround(world, pos, dropStack);
                        PlayerHelper.spawnItemOnGround(world, pos, dropCrucible);
                        pItem.getTagCompound().setInteger("type", 0);
                        if(PrimalAPI.randomCheck(2)){
                            tile.setSlotStack(0, ItemStack.EMPTY);
                        }
                        return true;
                    }
                    if(tile.getSlotStack(0).getItem() == ModItems.cast_gladius){
                        ItemStack dropStack = new ItemStack(ModItems.bronzegladius);
                        PlayerHelper.spawnItemOnGround(world, pos, dropStack);
                        PlayerHelper.spawnItemOnGround(world, pos, dropCrucible);
                        pItem.getTagCompound().setInteger("type", 0);
                        if(PrimalAPI.randomCheck(2)){
                            tile.setSlotStack(0, ItemStack.EMPTY);
                        }
                        return true;
                    }
                }
                if(pItem.getTagCompound().getInteger("type") == 48) {
                    if (tile.getSlotStack(0).getItem() == ModItems.cast_hoe) {
                        ItemStack dropStack = new ItemStack(ModItems.bronzehoehead);
                        dropStack.setTagCompound(new NBTTagCompound());

                        NBTTagCompound tags = new NBTTagCompound();
                        dropStack.getTagCompound().setTag("tags", tags);
                        dropStack.getSubCompound("tags").setBoolean("hot", false);
                        dropStack.getSubCompound("tags").setBoolean("emerald", false);
                        dropStack.getSubCompound("tags").setInteger("diamond", 1);
                        dropStack.getSubCompound("tags").setInteger("redstone", 0);
                        dropStack.getSubCompound("tags").setInteger("lapis", 0);
                        dropStack.getSubCompound("tags").setInteger("modifiers", 1);

                        PlayerHelper.spawnItemOnGround(world, pos, dropStack);
                        PlayerHelper.spawnItemOnGround(world, pos, dropCrucible);
                        pItem.getTagCompound().setInteger("type", 0);
                        if (PrimalAPI.randomCheck(2)) {
                            tile.setSlotStack(0, ItemStack.EMPTY);
                        }
                        return true;
                    }
                    if (tile.getSlotStack(0).getItem() == ModItems.cast_pickaxe) {
                        ItemStack dropStack = new ItemStack(ModItems.bronzepickaxehead);
                        dropStack.setTagCompound(new NBTTagCompound());

                        NBTTagCompound tags = new NBTTagCompound();
                        dropStack.getTagCompound().setTag("tags", tags);
                        dropStack.getSubCompound("tags").setBoolean("hot", false);
                        dropStack.getSubCompound("tags").setBoolean("emerald", false);
                        dropStack.getSubCompound("tags").setInteger("diamond", 1);
                        dropStack.getSubCompound("tags").setInteger("redstone", 0);
                        dropStack.getSubCompound("tags").setInteger("lapis", 0);
                        dropStack.getSubCompound("tags").setInteger("modifiers", 1);

                        PlayerHelper.spawnItemOnGround(world, pos, dropStack);
                        PlayerHelper.spawnItemOnGround(world, pos, dropCrucible);
                        pItem.getTagCompound().setInteger("type", 0);
                        if (PrimalAPI.randomCheck(2)) {
                            tile.setSlotStack(0, ItemStack.EMPTY);
                        }
                        return true;
                    }
                    if (tile.getSlotStack(0).getItem() == ModItems.cast_axe) {
                        ItemStack dropStack = new ItemStack(ModItems.bronzeaxehead);
                        dropStack.setTagCompound(new NBTTagCompound());


                        NBTTagCompound tags = new NBTTagCompound();
                        dropStack.getTagCompound().setTag("tags", tags);
                        dropStack.getSubCompound("tags").setBoolean("hot", false);
                        dropStack.getSubCompound("tags").setBoolean("emerald", false);
                        dropStack.getSubCompound("tags").setInteger("diamond", 1);
                        dropStack.getSubCompound("tags").setInteger("redstone", 0);
                        dropStack.getSubCompound("tags").setInteger("lapis", 0);
                        dropStack.getSubCompound("tags").setInteger("modifiers", 1);


                        PlayerHelper.spawnItemOnGround(world, pos, dropStack);
                        PlayerHelper.spawnItemOnGround(world, pos, dropCrucible);
                        pItem.getTagCompound().setInteger("type", 0);
                        if (PrimalAPI.randomCheck(2)) {
                            tile.setSlotStack(0, ItemStack.EMPTY);
                        }
                        return true;
                    }
                    if (tile.getSlotStack(0).getItem() == ModItems.cast_shovel) {
                        ItemStack dropStack = new ItemStack(ModItems.bronzeshovelhead);
                        dropStack.setTagCompound(new NBTTagCompound());


                        NBTTagCompound tags = new NBTTagCompound();
                        dropStack.getTagCompound().setTag("tags", tags);
                        dropStack.getSubCompound("tags").setBoolean("hot", false);
                        dropStack.getSubCompound("tags").setBoolean("emerald", false);
                        dropStack.getSubCompound("tags").setInteger("diamond", 1);
                        dropStack.getSubCompound("tags").setInteger("redstone", 0);
                        dropStack.getSubCompound("tags").setInteger("lapis", 0);
                        dropStack.getSubCompound("tags").setInteger("modifiers", 1);


                        PlayerHelper.spawnItemOnGround(world, pos, dropStack);
                        PlayerHelper.spawnItemOnGround(world, pos, dropCrucible);
                        pItem.getTagCompound().setInteger("type", 0);
                        if (PrimalAPI.randomCheck(2)) {
                            tile.setSlotStack(0, ItemStack.EMPTY);
                        }
                        return true;
                    }
                    if (tile.getSlotStack(0).getItem() == ModItems.cast_gladius) {
                        ItemStack dropStack = new ItemStack(ModItems.bronzegladius);
                        PlayerHelper.spawnItemOnGround(world, pos, dropStack);
                        PlayerHelper.spawnItemOnGround(world, pos, dropCrucible);
                        pItem.getTagCompound().setInteger("type", 0);
                        if (PrimalAPI.randomCheck(2)) {
                            tile.setSlotStack(0, ItemStack.EMPTY);
                        }
                        return true;
                    }
                }
                if(pItem.getTagCompound().getInteger("type") == 50) {
                    if (tile.getSlotStack(0).getItem() == ModItems.cast_hoe) {
                        ItemStack dropStack = new ItemStack(ModItems.bronzehoehead);
                        dropStack.setTagCompound(new NBTTagCompound());

                        NBTTagCompound tags = new NBTTagCompound();
                        dropStack.getTagCompound().setTag("tags", tags);
                        dropStack.getSubCompound("tags").setBoolean("hot", false);
                        dropStack.getSubCompound("tags").setBoolean("emerald", false);
                        dropStack.getSubCompound("tags").setInteger("diamond", 0);
                        dropStack.getSubCompound("tags").setInteger("redstone", 1);
                        dropStack.getSubCompound("tags").setInteger("lapis", 0);
                        dropStack.getSubCompound("tags").setInteger("modifiers", 1);

                        PlayerHelper.spawnItemOnGround(world, pos, dropStack);
                        PlayerHelper.spawnItemOnGround(world, pos, dropCrucible);
                        pItem.getTagCompound().setInteger("type", 0);
                        if(PrimalAPI.randomCheck(2)){
                            tile.setSlotStack(0, ItemStack.EMPTY);
                        }
                        return true;
                    }
                    if (tile.getSlotStack(0).getItem() == ModItems.cast_pickaxe) {
                        ItemStack dropStack = new ItemStack(ModItems.bronzepickaxehead);
                        dropStack.setTagCompound(new NBTTagCompound());

                        NBTTagCompound tags = new NBTTagCompound();
                        dropStack.getTagCompound().setTag("tags", tags);
                        dropStack.getSubCompound("tags").setBoolean("hot", false);
                        dropStack.getSubCompound("tags").setBoolean("emerald", false);
                        dropStack.getSubCompound("tags").setInteger("diamond", 0);
                        dropStack.getSubCompound("tags").setInteger("redstone", 1);
                        dropStack.getSubCompound("tags").setInteger("lapis", 0);
                        dropStack.getSubCompound("tags").setInteger("modifiers", 1);

                        PlayerHelper.spawnItemOnGround(world, pos, dropStack);
                        PlayerHelper.spawnItemOnGround(world, pos, dropCrucible);
                        pItem.getTagCompound().setInteger("type", 0);
                        if(PrimalAPI.randomCheck(2)){
                            tile.setSlotStack(0, ItemStack.EMPTY);
                        }
                        return true;
                    }
                    if (tile.getSlotStack(0).getItem() == ModItems.cast_axe) {
                        ItemStack dropStack = new ItemStack(ModItems.bronzeaxehead);
                        dropStack.setTagCompound(new NBTTagCompound());


                        NBTTagCompound tags = new NBTTagCompound();
                        dropStack.getTagCompound().setTag("tags", tags);
                        dropStack.getSubCompound("tags").setBoolean("hot", false);
                        dropStack.getSubCompound("tags").setBoolean("emerald", false);
                        dropStack.getSubCompound("tags").setInteger("diamond", 0);
                        dropStack.getSubCompound("tags").setInteger("redstone", 1);
                        dropStack.getSubCompound("tags").setInteger("lapis", 0);
                        dropStack.getSubCompound("tags").setInteger("modifiers", 1);


                        PlayerHelper.spawnItemOnGround(world, pos, dropStack);
                        PlayerHelper.spawnItemOnGround(world, pos, dropCrucible);
                        pItem.getTagCompound().setInteger("type", 0);
                        if(PrimalAPI.randomCheck(2)){
                            tile.setSlotStack(0, ItemStack.EMPTY);
                        }
                        return true;
                    }
                    if (tile.getSlotStack(0).getItem() == ModItems.cast_shovel) {
                        ItemStack dropStack = new ItemStack(ModItems.bronzeshovelhead);
                        dropStack.setTagCompound(new NBTTagCompound());


                        NBTTagCompound tags = new NBTTagCompound();
                        dropStack.getTagCompound().setTag("tags", tags);
                        dropStack.getSubCompound("tags").setBoolean("hot", false);
                        dropStack.getSubCompound("tags").setBoolean("emerald", false);
                        dropStack.getSubCompound("tags").setInteger("diamond", 0);
                        dropStack.getSubCompound("tags").setInteger("redstone", 1);
                        dropStack.getSubCompound("tags").setInteger("lapis", 0);
                        dropStack.getSubCompound("tags").setInteger("modifiers", 1);


                        PlayerHelper.spawnItemOnGround(world, pos, dropStack);
                        PlayerHelper.spawnItemOnGround(world, pos, dropCrucible);
                        pItem.getTagCompound().setInteger("type", 0);
                        if(PrimalAPI.randomCheck(2)){
                            tile.setSlotStack(0, ItemStack.EMPTY);
                        }
                        return true;
                    }
                    if (tile.getSlotStack(0).getItem() == ModItems.cast_gladius) {
                        ItemStack dropStack = new ItemStack(ModItems.bronzegladius);
                        PlayerHelper.spawnItemOnGround(world, pos, dropStack);
                        PlayerHelper.spawnItemOnGround(world, pos, dropCrucible);
                        pItem.getTagCompound().setInteger("type", 0);
                        if(PrimalAPI.randomCheck(2)){
                            tile.setSlotStack(0, ItemStack.EMPTY);
                        }
                        return true;
                    }
                }
                if(pItem.getTagCompound().getInteger("type") == 52) {
                    if (tile.getSlotStack(0).getItem() == ModItems.cast_hoe) {
                        ItemStack dropStack = new ItemStack(ModItems.bronzehoehead);
                        dropStack.setTagCompound(new NBTTagCompound());

                        NBTTagCompound tags = new NBTTagCompound();
                        dropStack.getTagCompound().setTag("tags", tags);
                        dropStack.getSubCompound("tags").setBoolean("hot", false);
                        dropStack.getSubCompound("tags").setBoolean("emerald", false);
                        dropStack.getSubCompound("tags").setInteger("diamond", 0);
                        dropStack.getSubCompound("tags").setInteger("redstone", 0);
                        dropStack.getSubCompound("tags").setInteger("lapis", 1);
                        dropStack.getSubCompound("tags").setInteger("modifiers", 1);

                        PlayerHelper.spawnItemOnGround(world, pos, dropStack);
                        PlayerHelper.spawnItemOnGround(world, pos, dropCrucible);
                        pItem.getTagCompound().setInteger("type", 0);
                        if(PrimalAPI.randomCheck(2)){
                            tile.setSlotStack(0, ItemStack.EMPTY);
                        }
                        return true;
                    }
                    if (tile.getSlotStack(0).getItem() == ModItems.cast_pickaxe) {
                        ItemStack dropStack = new ItemStack(ModItems.bronzepickaxehead);
                        dropStack.setTagCompound(new NBTTagCompound());

                        NBTTagCompound tags = new NBTTagCompound();
                        dropStack.getTagCompound().setTag("tags", tags);
                        dropStack.getSubCompound("tags").setBoolean("hot", false);
                        dropStack.getSubCompound("tags").setBoolean("emerald", false);
                        dropStack.getSubCompound("tags").setInteger("diamond", 0);
                        dropStack.getSubCompound("tags").setInteger("redstone", 0);
                        dropStack.getSubCompound("tags").setInteger("lapis", 1);
                        dropStack.getSubCompound("tags").setInteger("modifiers", 1);

                        PlayerHelper.spawnItemOnGround(world, pos, dropStack);
                        PlayerHelper.spawnItemOnGround(world, pos, dropCrucible);
                        pItem.getTagCompound().setInteger("type", 0);
                        if(PrimalAPI.randomCheck(2)){
                            tile.setSlotStack(0, ItemStack.EMPTY);
                        }
                        return true;
                    }
                    if (tile.getSlotStack(0).getItem() == ModItems.cast_axe) {
                        ItemStack dropStack = new ItemStack(ModItems.bronzeaxehead);
                        dropStack.setTagCompound(new NBTTagCompound());


                        NBTTagCompound tags = new NBTTagCompound();
                        dropStack.getTagCompound().setTag("tags", tags);
                        dropStack.getSubCompound("tags").setBoolean("hot", false);
                        dropStack.getSubCompound("tags").setBoolean("emerald", false);
                        dropStack.getSubCompound("tags").setInteger("diamond", 0);
                        dropStack.getSubCompound("tags").setInteger("redstone", 0);
                        dropStack.getSubCompound("tags").setInteger("lapis", 1);
                        dropStack.getSubCompound("tags").setInteger("modifiers", 1);


                        PlayerHelper.spawnItemOnGround(world, pos, dropStack);
                        PlayerHelper.spawnItemOnGround(world, pos, dropCrucible);
                        pItem.getTagCompound().setInteger("type", 0);
                        if(PrimalAPI.randomCheck(2)){
                            tile.setSlotStack(0, ItemStack.EMPTY);
                        }
                        return true;
                    }
                    if (tile.getSlotStack(0).getItem() == ModItems.cast_shovel) {
                        ItemStack dropStack = new ItemStack(ModItems.bronzeshovelhead);
                        dropStack.setTagCompound(new NBTTagCompound());


                        NBTTagCompound tags = new NBTTagCompound();
                        dropStack.getTagCompound().setTag("tags", tags);
                        dropStack.getSubCompound("tags").setBoolean("hot", false);
                        dropStack.getSubCompound("tags").setBoolean("emerald", false);
                        dropStack.getSubCompound("tags").setInteger("diamond", 0);
                        dropStack.getSubCompound("tags").setInteger("redstone", 0);
                        dropStack.getSubCompound("tags").setInteger("lapis", 1);
                        dropStack.getSubCompound("tags").setInteger("modifiers", 1);


                        PlayerHelper.spawnItemOnGround(world, pos, dropStack);
                        PlayerHelper.spawnItemOnGround(world, pos, dropCrucible);
                        pItem.getTagCompound().setInteger("type", 0);
                        if(PrimalAPI.randomCheck(2)){
                            tile.setSlotStack(0, ItemStack.EMPTY);
                        }
                        return true;
                    }
                    if (tile.getSlotStack(0).getItem() == ModItems.cast_gladius) {
                        ItemStack dropStack = new ItemStack(ModItems.bronzegladius);
                        PlayerHelper.spawnItemOnGround(world, pos, dropStack);
                        PlayerHelper.spawnItemOnGround(world, pos, dropCrucible);
                        pItem.getTagCompound().setInteger("type", 0);
                        if(PrimalAPI.randomCheck(2)){
                            tile.setSlotStack(0, ItemStack.EMPTY);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        if (!world.isRemote && world.getGameRules().getBoolean("doTileDrops"))
        {
            TileCastingBlock tile = (TileCastingBlock) world.getTileEntity(pos);
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
        return new TileCastingBlock();
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
