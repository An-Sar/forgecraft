package nmd.primal.forgecraft.blocks.Crucibles;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nmd.primal.core.api.PrimalItems;
import nmd.primal.forgecraft.CommonUtils;
import nmd.primal.forgecraft.ModInfo;
import nmd.primal.forgecraft.init.ModBlocks;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;

import static nmd.primal.forgecraft.CommonUtils.spawnItemEntityFromWorld;

/**
 * Created by mminaie on 1/24/17.
 */
public class Crucible extends Block {

    protected static final AxisAlignedBB boundBox = new AxisAlignedBB(4/16D, 0.0D, 4/16D, 12/16D, 7/16D, 12/16D);

    //public static final PropertyInteger SIZE =  PropertyInteger.create("size", 0, 3);

    public Crucible(Material material, String registryName) {
        super(material);
        setUnlocalizedName(registryName);
        setRegistryName(registryName);
        setCreativeTab(ModInfo.TAB_FORGECRAFT);
        setHardness(3.0f);
        //this.blockState.getBaseState().withProperty(SIZE, Integer.valueOf(0));
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        if (!world.isRemote) {
            ItemStack pItem = player.inventory.getCurrentItem();
            if(pItem.isEmpty()){
                CommonUtils.spawnItemEntity(world, player, new ItemStack(this, 1));
                world.setBlockToAir(pos);
                return true;
            }
            if(Block.getBlockFromItem(pItem.getItem()) == this){
                CommonUtils.spawnItemEntity(world, player, new ItemStack(this, 1));
                world.setBlockToAir(pos);
                return true;
            }
        }

        return false;
    }

    /*@Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune){
        List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
            ret.add(0, new ItemStack(ModBlocks.emptycrucible, 1));
            if(this.getUnlocalizedName()=="tile.coolironcrucible"){
                ret.add(1, new ItemStack(Items.IRON_INGOT, 1));
            }

        return ret;
    }*/

    public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state)
    {

        if(!world.isRemote){
            if(this.getUnlocalizedName().equals("tile.emptycruciblecracked")){} else spawnItemEntityFromWorld(world, pos, new ItemStack(ModBlocks.emptycrucible, 1));

            if(StringUtils.isEmpty(this.getUnlocalizedName()) == false) {
                if(checkDrops(this.getUnlocalizedName()) != null) {
                    if (checkDrops(this.getUnlocalizedName()).equals(this.getUnlocalizedName())) {
                        spawnItemEntityFromWorld(world, pos, new ItemStack(getItemFromName(this.getUnlocalizedName()), 1));
                    }
                }
            }
        }
    }

    private String checkDrops(String name){
        String string = null;

        if(name.equals("tile.coolironcrucible")){
            string = this.getUnlocalizedName();
        }
        if(name.equals("tile.rawironcrucible")){
            string = this.getUnlocalizedName();
        }
        if(name.equals("tile.coolcleanironcrucible")){
            string = this.getUnlocalizedName();
        }
        if(name.equals("tile.rawcleanironcrucible")){
            string = this.getUnlocalizedName();
        }
        if(name.equals("tile.coolsteelcrucible")){
            string = this.getUnlocalizedName();
        }
        if(name.equals("tile.rawsteelcrucible")){
            string = this.getUnlocalizedName();
        }
        if(name.equals("tile.coolwootzcrucible")){
            string = this.getUnlocalizedName();
        }
        if(name.equals("tile.rawwootzcrucible")){
            string = this.getUnlocalizedName();
        }
        if(name.equals("tile.emptycruciblecracked")){
            string = this.getUnlocalizedName();
        }

        return string;
    }

    private Item getItemFromName(String name){
        if(name.equals("tile.coolironcrucible")){
            return Item.getItemFromBlock(ModBlocks.ironball);
        } else if (name.equals("tile.rawironcrucible")){
            return Item.getItemFromBlock(Blocks.IRON_ORE);
        } else if (name.equals("tile.coolcleanironcrucible")){
            return Item.getItemFromBlock(ModBlocks.ironcleanball);
        } else if (name.equals("tile.rawcleanironcrucible")){
            return PrimalItems.IRON_DUST;
        } else if (name.equals("tile.coolsteelcrucible")){
            return Item.getItemFromBlock(ModBlocks.steelball);
        } else if (name.equals("tile.rawsteelcrucible")){
            return Item.getItemFromBlock(ModBlocks.ironcleanball);
        } else if (name.equals("tile.coolwootzcrucible")){
            return Item.getItemFromBlock(ModBlocks.wootzball);
        } else if (name.equals("tile.rawcleanironcrucible")){
            return PrimalItems.GOLDEN_STICK;
        }else if (name.equals("tile.emptycruciblecracked")){
            return Items.CLAY_BALL;
        }
        else return Items.AIR;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 0;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return boundBox;
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
