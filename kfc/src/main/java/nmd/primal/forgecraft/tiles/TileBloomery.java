package nmd.primal.forgecraft.tiles;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nmd.primal.core.api.PrimalAPI;
import nmd.primal.core.common.helper.CommonUtils;
import nmd.primal.core.common.helper.RecipeHelper;
import nmd.primal.forgecraft.blocks.BloomeryBase;
import nmd.primal.forgecraft.blocks.Crucibles.Crucible;
import nmd.primal.forgecraft.crafting.BloomeryCrafting;
import nmd.primal.forgecraft.init.ModItems;

import static nmd.primal.core.common.helper.FireHelper.makeSmoke;

/**
 * Created by mminaie on 1/22/17.
 */
public class TileBloomery extends TileBaseSlot implements ITickable {

    private NonNullList<ItemStack> slotList = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
    private int iteration = 0;
    private int heat;
    private int cookCounter;

    @Override
    public void update () {
        World world = this.getWorld();
        if(!world.isRemote){
            IBlockState state = world.getBlockState(this.pos);
            if(state.getValue(PrimalAPI.States.ACTIVE) == true) {
                if (this.getHeat() < 100) {
                    this.setHeat(100);
                }
                this.iteration++;
                if (this.iteration == 100) {
                    RecipeHelper.fuelManager(world,this, this.getSlotStack(0));
                    if(PrimalAPI.randomCheck(1000)) {
                        makeSmoke(world, pos);
                    }
                }
                if (this.iteration == 200) {
                    RecipeHelper.fuelManager(world, this, this.getSlotStack(0));
                    if(PrimalAPI.randomCheck(1000)) {
                        makeSmoke(world, pos);
                    }
                }
                if (this.iteration == 300) {
                    this.iteration = 0;
                    //IBlockState state = world.getBlockState(this.pos);
                    BlockPos abovePos = new BlockPos(this.getPos().getX(), this.getPos().getY() + 1, this.getPos().getZ());
                        if (this.getSlotStack(0) == ItemStack.EMPTY) {
                            world.setBlockState(this.getPos(), state.withProperty(PrimalAPI.States.ACTIVE, false), 2);
                            this.markDirty();
                            world.notifyBlockUpdate(pos, state, state, 2);
                        }
                    this.heatManager(this.getHeat(), state, this.getSlotStack(0), world, pos);
                    RecipeHelper.fuelManager(world, this, this.getSlotStack(0));
                    if(PrimalAPI.randomCheck(1000)) {
                        makeSmoke(world, pos);
                    }
                }
                slotOneManager();
            }
        }
    }

    private void slotOneManager(){
        BloomeryCrafting recipe = BloomeryCrafting.getRecipe(this.getSlotStack(1));
        if(recipe != null){
            //System.out.println(recipe.getIdealTime() + " : " + recipe.getHeatThreshold());
            //System.out.println(this.cookCounter + " : " + this.getHeat());
            //System.out.println("====================");
            if(this.getHeat() >= recipe.getHeatThreshold()){
                cookCounter++;
            }
            if(cookCounter >= recipe.getIdealTime() ){
                if(this.getSlotStack(1).getItem() == recipe.getInput().getItem()) {
                    this.setSlotStack(1, recipe.getOutput());
                    //this.cookCounter = 0;
                    //System.out.print(" :Success: " + this.getSlotStack(1));
                    this.updateBlock();
                    this.markDirty();
                }
            }
            if(cookCounter > recipe.getIdealTime() + (recipe.getIdealTime() * recipe.getTimeVariance())){
                if(this.getSlotStack(1).getItem() == recipe.getOutput().getItem()) {
                    this.setSlotStack(1, recipe.getOutputFailed());
                    this.cookCounter = 0;
                    //System.out.print(" :Failure Time: " + this.getSlotStack(1));
                    this.updateBlock();
                    this.markDirty();
                }
            }
            if(this.getHeat() > recipe.getHeatThreshold() + (recipe.getHeatThreshold() * recipe.getHeatVariance())){
                if(this.getSlotStack(1).getItem() == recipe.getInput().getItem()) {
                    this.setSlotStack(1, recipe.getOutputFailed());
                    this.cookCounter = 0;
                    //System.out.print(" :Failure Heat: " + this.getSlotStack(1));
                    this.updateBlock();
                    this.markDirty();
                }
            }
            if (this.getSlotStack(1).isEmpty()){
                this.cookCounter=0;
            }
        }
    }

    private void slotZeroManager(World world){
        if(this.getSlotStack(0) != ItemStack.EMPTY) {
            Integer decrInt = Math.round(RecipeHelper.getBurnTime(this.getSlotStack(0)) / 5);
            if(decrInt == 0) {
                decrInt = 1;
            }
            //System.out.println(GameRegistry.getFuelValue(this.getSlotStack(0)));
            //System.out.println( "Burn Time: " + RecipeHelper.getBurnTime(this.getSlotStack(0)));
            //System.out.println("Burn Time / 5 :" + RecipeHelper.getBurnTime(this.getSlotStack(0))/5);
            //System.out.println("Burn Time rounded: " + Math.round(RecipeHelper.getBurnTime(this.getSlotStack(0)) / 5));
            //System.out.println(decrInt);
            Integer size = this.getSlotStack(0).getCount();
            Integer burnModifier = 0;
            if(size / 16 <= 1){
                burnModifier = 1;
            }
            if(size / 16 > 1 && size / 16 <= 2){
                burnModifier = 2;
            }
            if(size / 16 > 2 && size / 16 <= 3){
                burnModifier = 3;
            }
            if(size / 16 > 3 && size / 16 <= 4){
                burnModifier = 4;
            }
            if (world.rand.nextInt(decrInt) == 0) {
                if (world.rand.nextInt(burnModifier) == 0) {
                    //System.out.println("Fuel Burn" + this.getSlotStack(0));
                    this.decrStackSize(0, 1);
                    this.markDirty();
                    this.updateBlock();
                }
            }
            if (this.getSlotStack(0).getCount() == 1){
                this.decrStackSize(0, 1);
                this.markDirty();
                this.updateBlock();
            }
            if(PrimalAPI.randomCheck(1000)) {
                makeSmoke(world, pos);
            }
        }
    }

    public int getHeat(){
        return this.heat;
    }

    public void setHeat(int newHeat){
        this.heat = newHeat;
    }

    public int getCookCounter() {return this.cookCounter; }

    @Override
    public int getSlotLimit() {
        return 1;
    }

    private void heatManager(Integer h, IBlockState state, ItemStack stack, World world, BlockPos pos){
        if(state.getValue(PrimalAPI.States.ACTIVE) == true){
            if(!stack.isEmpty()) {
                if(h > 0) {
                    this.setHeat(h - 25);
                }
                if(h < 10 ){
                    world.setBlockState(pos, state.withProperty(PrimalAPI.States.ACTIVE, false), 2);
                }
            }
            if(stack.isEmpty()){
                world.setBlockState(pos, state.withProperty(PrimalAPI.States.ACTIVE, false), 2);
            }
            if(this.getSlotStack(0).getItem() == PrimalAPI.Items.CHARCOAL_FAIR){
                if(this.getHeat() > 1610){
                    this.setHeat(1600);
                }
            }
            if(this.getSlotStack(0).getItem() == Items.COAL && this.getSlotStack(0).getMetadata() == 1){
                if(this.getHeat() > 1210){
                    this.setHeat(1200);
                }
            }
            if(this.getSlotStack(0).getItem() == PrimalAPI.Items.CHARCOAL_GOOD){
                if(this.getHeat() > 2110){
                    this.setHeat(2100);
                }
            }
            if(this.getSlotStack(0).getItem() == PrimalAPI.Items.CHARCOAL_HIGH){
                if(this.getHeat() > 3510){
                    this.setHeat(3500);
                }
            }
        }
        if(state.getValue(PrimalAPI.States.ACTIVE) == false){
            if(h > 50){
                this.setHeat(h - 50);
            }
            if(h < 0){
                this.setHeat(0);
            }
        }
        if(world.getBlockState(pos).getBlock() instanceof BloomeryBase){
            BloomeryBase tempBlock = (BloomeryBase) world.getBlockState(pos).getBlock();
            if(this.getHeat() > tempBlock.getMaxHeat()){
                world.setBlockState(pos, Blocks.FIRE.getDefaultState(), 2);
                //world.markTileEntityForRemoval(this);
            }
        }
        this.updateBlock();
        this.markDirty();
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
            if(stack.getItem() == PrimalAPI.Items.CHARCOAL_GOOD
                    || stack.getItem() == PrimalAPI.Items.CHARCOAL_HIGH
                    || stack.getItem() == PrimalAPI.Items.CHARCOAL_FAIR){
                return true;
            }
        }
        if(index == 1){
            if (stack.getItem() == ModItems.softcrucible) {
                return true;
            }
            if(Block.getBlockFromItem(stack.getItem()) instanceof Crucible ){
                return true;
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
        this.cookCounter = nbt.getInteger("cook");
        return nbt;
    }

    @Override
    public NBTTagCompound writeNBT(NBTTagCompound nbt)
    {
        nbt.setInteger("heat", this.heat);
        nbt.setInteger("cook", this.cookCounter);
        super.writeNBT(nbt);
        return nbt;
    }

}
