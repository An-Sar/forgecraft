package nmd.primal.forgecraft.blocks.Anvil;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nmd.primal.core.common.items.tools.Gallagher;
import nmd.primal.forgecraft.init.ModItems;
import nmd.primal.forgecraft.tiles.TileAnvil;

/**
 * Created by mminaie on 3/4/17.
 */
public class AnvilStone extends AnvilBase {

    public AnvilStone(Material material, String registryName, Float hardness, Boolean anvil) {
        super(material, registryName, hardness, anvil);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitx, float hity, float hitz) {

        /******************************************************************************
         Crafting AnvilStone Recipes
         *****************************************************************************/
        if (!world.isRemote) {
            ItemStack pItem = player.inventory.getCurrentItem();
            TileAnvil tile = (TileAnvil) world.getTileEntity(pos);
            if (tile != null) {
                if ((pItem.getItem() instanceof Gallagher) || (pItem.getItem() == ModItems.forgehammer)) {
                    if(tile.getSlotStack(12).getItem().equals(ModItems.ironingotballhot) ){
                        tile.setSlotStack(6, new ItemStack(ModItems.ironchunkhot, 1));
                        tile.setSlotStack(7, new ItemStack(ModItems.ironchunkhot, 1));
                        tile.setSlotStack(8, new ItemStack(ModItems.ironchunkhot, 1));
                        tile.setSlotStack(11, new ItemStack(ModItems.ironchunkhot, 1));
                        tile.setSlotStack(12, new ItemStack(ModItems.ironchunkhot, 1));
                        tile.setSlotStack(13, new ItemStack(ModItems.ironchunkhot, 1));
                        tile.setSlotStack(16, new ItemStack(ModItems.ironchunkhot, 1));
                        tile.setSlotStack(17, new ItemStack(ModItems.ironchunkhot, 1));
                        tile.setSlotStack(18, new ItemStack(ModItems.ironchunkhot, 1));
                        world.playEvent(1031, pos, 0);
                    }
                    if(tile.getSlotStack(12).getItem().equals(ModItems.ironcleaningotballhot) ){
                        tile.setSlotStack(6, new ItemStack(ModItems.ironcleanchunkhot, 1));
                        tile.setSlotStack(7, new ItemStack(ModItems.ironcleanchunkhot, 1));
                        tile.setSlotStack(8, new ItemStack(ModItems.ironcleanchunkhot, 1));
                        tile.setSlotStack(11, new ItemStack(ModItems.ironcleanchunkhot, 1));
                        tile.setSlotStack(12, new ItemStack(ModItems.ironcleanchunkhot, 1));
                        tile.setSlotStack(13, new ItemStack(ModItems.ironcleanchunkhot, 1));
                        tile.setSlotStack(16, new ItemStack(ModItems.ironcleanchunkhot, 1));
                        tile.setSlotStack(17, new ItemStack(ModItems.ironcleanchunkhot, 1));
                        tile.setSlotStack(18, new ItemStack(ModItems.ironcleanchunkhot, 1));
                        world.playEvent(1031, pos, 0);
                    }
                    if(tile.getSlotStack(12).getItem().equals(ModItems.steelingotballhot) ){
                        tile.setSlotStack(6, new ItemStack(ModItems.steelchunkhot, 1));
                        tile.setSlotStack(7, new ItemStack(ModItems.steelchunkhot, 1));
                        tile.setSlotStack(8, new ItemStack(ModItems.steelchunkhot, 1));
                        tile.setSlotStack(11, new ItemStack(ModItems.steelchunkhot, 1));
                        tile.setSlotStack(12, new ItemStack(ModItems.steelchunkhot, 1));
                        tile.setSlotStack(13, new ItemStack(ModItems.steelchunkhot, 1));
                        tile.setSlotStack(16, new ItemStack(ModItems.steelchunkhot, 1));
                        tile.setSlotStack(17, new ItemStack(ModItems.steelchunkhot, 1));
                        tile.setSlotStack(18, new ItemStack(ModItems.steelchunkhot, 1));
                    }
                    if(tile.getSlotStack(12).getItem().equals(ModItems.wootzingotballhot) ){
                    //TODO Wootz chunks
                    }
                }
                if ((pItem.getItem() instanceof Gallagher) || (pItem.getItem() == ModItems.forgehammer)) {
                    String[] tempArray = new String[25];
                    for (int i = 0; i < 25; i++) {
                        tempArray[i] = tile.getSlotStack(i).getItem().getRegistryName().toString();
                    }
                    /*for (int i = 0; i < 25; i++) {
                        if (tile.getSlotStack(i).getItem() instanceof BaseMultiItem) {
                            if (((BaseMultiItem) tile.getSlotStack(i).getItem()).getMaterial(tile.getSlotStack(i).getItem()) != PrimalMaterials.TOOL_WROUGHT_IRON) {
                                world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
                                CommonUtils.spawnItemEntityFromWorld(world, pos, new ItemStack(PrimalItems.ROCK_STONE, 3));
                                CommonUtils.spawnItemEntityFromWorld(world, pos, new ItemStack(ModBlocks.ironball, 1));
                                this.breakBlock(world, pos, state);
                                return true;
                            }
                        }
                    }*/
                    doAnvilRecipe(pItem, tempArray, world, tile, pos, player);
                    return true;
                }
                doAnvilInventoryManager(pItem, world, tile, pos, hitx, hity, hitz, state, player);
                return true;
            }
        }
        return false;
    }
}
