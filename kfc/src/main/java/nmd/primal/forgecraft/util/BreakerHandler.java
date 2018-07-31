package nmd.primal.forgecraft.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nmd.primal.core.api.PrimalAPI;
import nmd.primal.core.common.helper.PlayerHelper;
import nmd.primal.core.common.helper.RecipeHelper;
import nmd.primal.forgecraft.blocks.CustomContainerFacing;
import nmd.primal.forgecraft.tiles.TileBreaker;

import static nmd.primal.core.api.PrimalAPI.randomChanceReturn;

//import nmd.primal.forgecraft.CommonUtils;

/**
 * Created by mminaie on 6/11/17.
 */
public interface BreakerHandler {

    /*default boolean hasOreName(ItemStack itemStack, String oreName)
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
    }*/

    default boolean doBreaking(World world, IBlockState state, BlockPos pos, TileBreaker tile) {
        for (int i=0; i < EnumFacing.HORIZONTALS.length; i++) {
            EnumFacing face = world.getBlockState(pos).getValue(CustomContainerFacing.FACING);
            if(face.equals(world.getBlockState(pos).getValue(CustomContainerFacing.FACING))){
                //Block smashBlock = world.getBlockState(pos.offset(face)).getBlock();
                IBlockState smashState = world.getBlockState(pos.offset(face));
                if(!smashState.getBlock().equals(Blocks.AIR)) {
                    ItemStack smashStack = new ItemStack(Item.getItemFromBlock(smashState.getBlock()), 1, smashState.getBlock().getMetaFromState(smashState));
                    if (RecipeHelper.isOreName(smashStack, "oreIron")) {
                        if (tile.getCharge() > getThreshold(world, pos.offset(face))) {
                            world.setBlockToAir(pos.offset(face));
                            PlayerHelper.spawnItemOnGround(world, pos.offset(face), new ItemStack(PrimalAPI.Items.IRON_DUST, randomChanceReturn(9, 1, 2)));
                            tile.getSlotStack(0).setItemDamage(tile.getSlotStack(0).getItemDamage() + 1);
                            return true;
                        }
                    }
                    if (RecipeHelper.isOreName(smashStack, "oreCopper")) {
                        if (tile.getCharge() > getThreshold(world, pos.offset(face))) {
                            world.setBlockToAir(pos.offset(face));
                            PlayerHelper.spawnItemOnGround(world, pos.offset(face), new ItemStack(PrimalAPI.Items.COPPER_DUST, randomChanceReturn(9, 1, 2)));
                            tile.getSlotStack(0).setItemDamage(tile.getSlotStack(0).getItemDamage() + 1);
                            return true;
                        }
                    }
                    if (RecipeHelper.isOreName(smashStack, "oreTin")) {
                        if (tile.getCharge() > getThreshold(world, pos.offset(face))) {
                            world.setBlockToAir(pos.offset(face));
                            PlayerHelper.spawnItemOnGround(world, pos.offset(face), new ItemStack(PrimalAPI.Items.TIN_DUST, randomChanceReturn(9, 1, 2)));
                            tile.getSlotStack(0).setItemDamage(tile.getSlotStack(0).getItemDamage() + 1);
                            return true;
                        }
                    }
                    if (RecipeHelper.isOreName(smashStack, "oreZinc")) {
                        if (tile.getCharge() > getThreshold(world, pos.offset(face))) {
                            world.setBlockToAir(pos.offset(face));
                            PlayerHelper.spawnItemOnGround(world, pos.offset(face), new ItemStack(PrimalAPI.Items.ZINC_DUST, randomChanceReturn(9, 1, 2)));
                            tile.getSlotStack(0).setItemDamage(tile.getSlotStack(0).getItemDamage() + 1);
                            return true;
                        }
                    }
                    if (RecipeHelper.isOreName(smashStack, "oreGold")) {
                        if (tile.getCharge() > getThreshold(world, pos.offset(face))) {
                            world.setBlockToAir(pos.offset(face));
                            PlayerHelper.spawnItemOnGround(world, pos.offset(face), new ItemStack(PrimalAPI.Items.GOLD_DUST, randomChanceReturn(9, 1, 2)));
                            tile.getSlotStack(0).setItemDamage(tile.getSlotStack(0).getItemDamage() + 1);
                            return true;
                        }
                    }
                    if (RecipeHelper.isOreName(smashStack, "cobblestone")) {
                        if (tile.getCharge() > getThreshold(world, pos.offset(face))) {
                            world.setBlockToAir(pos.offset(face));
                            PlayerHelper.spawnItemOnGround(world, pos.offset(face), new ItemStack(Blocks.GRAVEL, randomChanceReturn(9, 1, 1)));
                            tile.getSlotStack(0).setItemDamage(tile.getSlotStack(0).getItemDamage() + 1);
                            return true;
                        }
                    }
                    if (RecipeHelper.isOreName(smashStack, "gravel")) {
                        if (tile.getCharge() > getThreshold(world, pos.offset(face))) {
                            world.setBlockToAir(pos.offset(face));
                            PlayerHelper.spawnItemOnGround(world, pos.offset(face), new ItemStack(Blocks.SAND, randomChanceReturn(9, 1, 1)));
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
}
