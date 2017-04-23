package nmd.primal.forgecraft.tiles;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nmd.primal.core.common.helper.CommonUtils;
import nmd.primal.forgecraft.blocks.Forge;
import nmd.primal.forgecraft.crafting.ForgeCrafting;

import static nmd.primal.forgecraft.CommonUtils.getVanillaItemBurnTime;

/**
 * Created by mminaie on 11/30/16.
 */
public class TileForge extends TileBaseSlot implements ITickable {

    private NonNullList<ItemStack> slotList = NonNullList.<ItemStack>withSize(7, ItemStack.EMPTY);
    //private ItemStack[] inventory = new ItemStack [0];
    //private String customName;
    private int iteration = 0;
    private int heat;
    private int cookCounter2, cookCounter3, cookCounter4, cookCounter5, cookCounter6;

    @Override
    public void update () {
        if(!world.isRemote){
            World world = this.getWorld();
            this.iteration ++;
            IBlockState state = world.getBlockState(this.pos);
            BlockPos abovePos = new BlockPos(this.getPos().getX(), this.getPos().getY()+1, this.getPos().getZ());
            IBlockState aboveState = world.getBlockState(abovePos);
            Block block = world.getBlockState(abovePos).getBlock();

            if(this.iteration == 300 ) {
                this.iteration = 0;


                if (world.getBlockState(this.getPos()).getValue(Forge.ACTIVE)) {
                    if (this.getSlotStack(0) == ItemStack.EMPTY) {
                        world.setBlockState(this.getPos(), state.withProperty(Forge.ACTIVE, false), 2);
                        this.markDirty();
                        world.notifyBlockUpdate(pos, state, state, 2);
                    }
                    slotZeroManager(world);
                }
                this.heatManager(this.getHeat(), state, this.getSlotStack(0), world, pos);
            }
            craftingManager();
        }
    }

    private void slotZeroManager(World world){
        if(this.getSlotStack(0) != ItemStack.EMPTY) {
            Integer decrInt = (int) Math.floor(getVanillaItemBurnTime(this.getSlotStack(0)) / 20);
            if(decrInt == 0) {
                decrInt = 1;
            }
            if (world.rand.nextInt(decrInt) == 0) {
                this.decrStackSize(0, 1);
                this.markDirty();
                this.updateBlock();
            }
            if (this.getSlotStack(0).getCount() == 1){
                this.decrStackSize(0, 1);
                this.markDirty();
                this.updateBlock();
            }
            CommonUtils.makeSmoke(world, pos);
        }
    }

    private void heatManager(Integer h, IBlockState state, ItemStack stack, World world, BlockPos pos){
        if(state.getValue(Forge.ACTIVE) == true){
            if(!stack.isEmpty()) {
                if(h > 0) {
                    this.setHeat(h - 25);
                }
                if(h < 10 ){
                    world.setBlockState(pos, state.withProperty(Forge.ACTIVE, false), 2);
                }
            }
            if(stack.isEmpty()){
                world.setBlockState(pos, state.withProperty(Forge.ACTIVE, false), 2);
            }
        }
        if(state.getValue(Forge.ACTIVE) == false){
            if(h > 50){
                this.setHeat(h - 50);
            }
            if(h < 0){
                this.setHeat(0);
            }
        }
        this.updateBlock();
        this.markDirty();
    }


    private void craftingManager() {

        for (int i = 2; i < this.getSlotListSize(); i++) {
            Item itemTest = this.getSlotStack(i).getItem();
            ForgeCrafting recipe = ForgeCrafting.getRecipe(itemTest);
            if (recipe != null) {

                if(i == 2){
                    if (this.getHeat() >= recipe.getHeatThreshold()) {
                        cookCounter2++;
                    }
                    if (this.getHeat() < recipe.getHeatThreshold() && cookCounter2 > 0) {
                        cookCounter2--;
                    }
                    if (cookCounter2 >= recipe.getIdealTime()) {
                        if(this.getSlotStack(i).hasTagCompound()){
                            this.getSlotStack(i).getSubCompound("tags").setBoolean("hot", true);
                        } else this.setSlotStack(i, recipe.getOutput());
                        cookCounter2 = 0;
                    }
                }
                if(i == 3){
                    if (this.getHeat() >= recipe.getHeatThreshold()) {
                        cookCounter3++;
                    }
                    if (this.getHeat() < recipe.getHeatThreshold() && cookCounter3 > 0) {
                        cookCounter3--;
                    }
                    if (cookCounter3 >= recipe.getIdealTime()) {
                        if(this.getSlotStack(i).hasTagCompound()){
                            this.getSlotStack(i).getSubCompound("tags").setBoolean("hot", true);
                        } else this.setSlotStack(i, recipe.getOutput());
                        cookCounter3 = 0;
                    }
                }
                if(i == 4){
                    if (this.getHeat() >= recipe.getHeatThreshold()) {
                        cookCounter4++;
                    }
                    if (this.getHeat() < recipe.getHeatThreshold() && cookCounter4 > 0) {
                        cookCounter4--;
                    }
                    if (cookCounter4 >= recipe.getIdealTime()) {
                        if(this.getSlotStack(i).hasTagCompound()){
                            if( this.getSlotStack(i).getSubCompound("tags").getBoolean("hot") == false) {
                                this.getSlotStack(i).getSubCompound("tags").setBoolean("hot", true);
                                //System.out.println(this.getSlotStack(i).getSubCompound("tags"));
                                //System.out.println("its hot now");
                                cookCounter4 = 0;
                            }
                        } else this.setSlotStack(i, recipe.getOutput());
                        cookCounter4 = 0;
                    }
                }
                if(i == 5){
                    if (this.getHeat() >= recipe.getHeatThreshold()) {
                        cookCounter5++;
                    }
                    if (this.getHeat() < recipe.getHeatThreshold() && cookCounter5 > 0) {
                        cookCounter5--;
                    }
                    if (cookCounter5 >= recipe.getIdealTime()) {
                        if(this.getSlotStack(i).hasTagCompound()){
                            this.getSlotStack(i).getSubCompound("tags").setBoolean("hot", true);
                        } else this.setSlotStack(i, recipe.getOutput());
                        cookCounter5 = 0;
                    }
                }
                if(i == 6){
                    if (this.getHeat() >= recipe.getHeatThreshold()) {
                        cookCounter6++;
                    }
                    if (this.getHeat() < recipe.getHeatThreshold() && cookCounter6 > 0) {
                        cookCounter6--;
                    }
                    if (cookCounter6 >= recipe.getIdealTime()) {
                        if(this.getSlotStack(i).hasTagCompound()){
                            this.getSlotStack(i).getSubCompound("tags").setBoolean("hot", true);
                        } else this.setSlotStack(i, recipe.getOutput());
                        cookCounter6 = 0;
                    }
                }

            /*
            System.out.println(state.getValue(IngotBall.ACTIVE) + " : " + recipe.getStartState());
            System.out.println(cookCounter + " : " + recipe.getIdealTime());
            System.out.println(this.heat + " : " + recipe.getHeatThreshold());
            System.out.println("========");
             */
            }
        }
    }


    public int getHeat(){
        return this.heat;
    }

    public void setHeat(int newHeat){
        this.heat = newHeat;
    }
    @Override
    public int getSlotLimit() {
        return 1;
    }

    public ItemStack removeStackFromSlot(int index) {
        ItemStack stack = this.getSlotStack(index);
        this.setSlotStack(index, ItemStack.EMPTY);
        return stack;
    }

    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if(index == 0){
            if(stack.getItem() == Items.COAL){
                if(stack.getMetadata() == 1) {
                    return true;
                }
            }
        }

        return false;
    }

    // ***************************************************************************** //
    //  NBT
    // ***************************************************************************** //
    @Override
    public NBTTagCompound readNBT(NBTTagCompound nbt)
    {
        super.readNBT(nbt);
        this.heat = nbt.getInteger("heat");
        return nbt;
    }

    @Override
    public NBTTagCompound writeNBT(NBTTagCompound nbt)
    {
        nbt.setInteger("heat", this.heat);
        super.writeNBT(nbt);
        return nbt;
    }

}
