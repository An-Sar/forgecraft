package nmd.primal.forgecraft.util;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import nmd.primal.core.api.PrimalItems;
import nmd.primal.core.common.helper.PlayerHelper;
import nmd.primal.forgecraft.blocks.CustomContainerFacing;
import nmd.primal.forgecraft.tiles.TileBreaker;

import static nmd.primal.core.common.helper.CommonUtils.randomCheck;

//import nmd.primal.forgecraft.CommonUtils;

/**
 * Created by mminaie on 6/11/17.
 */
public interface BreakerHandler {

    default boolean hasOreName(ItemStack itemStack, String oreName)
    {
        int[] oreIds = OreDictionary.getOreIDs(itemStack);
        for(int oreId : oreIds)
        {
            String oreNameEntry = OreDictionary.getOreName(oreId);
            if(oreName.equals(oreNameEntry))
            {
                return true;
            }
        }
        return false;
    }

    default boolean doBreaking(World world, IBlockState state, BlockPos pos, TileBreaker tile) {
        for (int i=0; i < EnumFacing.HORIZONTALS.length; i++) {
            EnumFacing face = world.getBlockState(pos).getValue(CustomContainerFacing.FACING);
            if(face.equals(world.getBlockState(pos).getValue(CustomContainerFacing.FACING))){
                //Block smashBlock = world.getBlockState(pos.offset(face)).getBlock();
                IBlockState smashState = world.getBlockState(pos.offset(face));
                if(!smashState.getBlock().equals(Blocks.AIR)) {
                    ItemStack smashStack = new ItemStack(Item.getItemFromBlock(smashState.getBlock()), 1, smashState.getBlock().getMetaFromState(smashState));
                    if (hasOreName(smashStack, "oreIron")) {
                        if (tile.getCharge() > getThreshold(world, pos.offset(face))) {
                            world.setBlockToAir(pos.offset(face));
                            PlayerHelper.spawnItemOnGround(world, pos.offset(face), new ItemStack(PrimalItems.IRON_DUST, randomChanceReturn(9, 1, 2)));
                            tile.getSlotStack(0).setItemDamage(tile.getSlotStack(0).getItemDamage() + 1);
                            return true;
                        }
                    }
                    if (hasOreName(smashStack, "oreCopper")) {
                        if (tile.getCharge() > getThreshold(world, pos.offset(face))) {
                            world.setBlockToAir(pos.offset(face));
                            PlayerHelper.spawnItemOnGround(world, pos.offset(face), new ItemStack(PrimalItems.COPPER_DUST, randomChanceReturn(9, 1, 2)));
                            tile.getSlotStack(0).setItemDamage(tile.getSlotStack(0).getItemDamage() + 1);
                            return true;
                        }
                    }
                    if (hasOreName(smashStack, "oreTin")) {
                        if (tile.getCharge() > getThreshold(world, pos.offset(face))) {
                            world.setBlockToAir(pos.offset(face));
                            PlayerHelper.spawnItemOnGround(world, pos.offset(face), new ItemStack(PrimalItems.TIN_DUST, randomChanceReturn(9, 1, 2)));
                            tile.getSlotStack(0).setItemDamage(tile.getSlotStack(0).getItemDamage() + 1);
                            return true;
                        }
                    }
                    if (hasOreName(smashStack, "oreZinc")) {
                        if (tile.getCharge() > getThreshold(world, pos.offset(face))) {
                            world.setBlockToAir(pos.offset(face));
                            PlayerHelper.spawnItemOnGround(world, pos.offset(face), new ItemStack(PrimalItems.ZINC_DUST, randomChanceReturn(9, 1, 2)));
                            tile.getSlotStack(0).setItemDamage(tile.getSlotStack(0).getItemDamage() + 1);
                            return true;
                        }
                    }
                    if (hasOreName(smashStack, "oreGold")) {
                        if (tile.getCharge() > getThreshold(world, pos.offset(face))) {
                            world.setBlockToAir(pos.offset(face));
                            PlayerHelper.spawnItemOnGround(world, pos.offset(face), new ItemStack(PrimalItems.GOLD_DUST, randomChanceReturn(9, 1, 2)));
                            tile.getSlotStack(0).setItemDamage(tile.getSlotStack(0).getItemDamage() + 1);
                            return true;
                        }
                    }
                }
            } else {
                tile.getSlotStack(0).setItemDamage(tile.getSlotStack(0).getItemDamage() + 10);
                return true;
            }
        i++;
        }
        return false;
    }

    default float getThreshold(World world, BlockPos pos){
        float threshold = world.getBlockState(pos).getBlockHardness(world, pos) * (world.getBlockState(pos).getBlock().getExplosionResistance(null)*5);
        if(threshold > 179){
            threshold = 178;
        }
        return threshold;
    }

    public static int randomChanceReturn(int bound, int output1, int output2)
    {
        return randomCheck(bound) ? output2 : output1;
    }

}
