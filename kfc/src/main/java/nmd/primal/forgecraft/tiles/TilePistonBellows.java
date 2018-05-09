package nmd.primal.forgecraft.tiles;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import nmd.primal.core.api.PrimalAPI;
import nmd.primal.forgecraft.blocks.PistonBellows;


/**
 * Created by mminaie on 1/5/17.
 */
public class TilePistonBellows extends BaseTile implements ITickable{

    private int iteration = 0;
    private int animateIteration = 0;

    @Override
    public void update () {
        if (!world.isRemote) {
            World world = this.getWorld();
            IBlockState state = world.getBlockState(this.pos);
            if (state.getBlock() instanceof PistonBellows && state.getValue(PrimalAPI.States.ACTIVE)) {
                iteration++;
                if(iteration <= 15){
                    animateIteration++;
                    //
                }
                if(iteration > 15){
                    animateIteration = 15 - (iteration - 15);
                    if(animateIteration < 0){
                        animateIteration = 0;
                    }
                    //
                }
                if(iteration > 31){
                    iteration = 0;
                    animateIteration = 0;
                    world.setBlockState(this.getPos(), state.withProperty(PrimalAPI.States.ACTIVE, false), 3);
                }

                //System.out.println("Iterating");

                this.updateBlock();
                this.markDirty();
            }
        }
    }

    public int getIteration(){
        return this.iteration;
    }
    public int getAnimation(){
        return this.animateIteration;
    }


    public NBTTagCompound readNBT(NBTTagCompound nbt)
    {
        super.readNBT(nbt);
        this.iteration = nbt.getInteger("iteration");
        this.animateIteration = nbt.getInteger("animate");
        return nbt;
    }

    public NBTTagCompound writeNBT(NBTTagCompound nbt)
    {
        nbt.setInteger("iteration", this.iteration);
        nbt.setInteger("animate", this.animateIteration);
        return nbt;
    }

}
