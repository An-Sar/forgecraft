package nmd.primal.forgecraft.items.weapons;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nmd.primal.core.common.entities.projectiles.TypeMud;
import nmd.primal.forgecraft.ModInfo;
import nmd.primal.forgecraft.init.ModSounds;
import nmd.primal.forgecraft.items.BaseItem;

import javax.annotation.Nullable;

/**
 * Created by Lyle on 4/2/2017.
 */
public class Musket extends BaseItem{

    public Musket(String name) {
        super(name);
        this.setCreativeTab(ModInfo.TAB_FORGECRAFT);
        this.setMaxStackSize(1);
        this.setNoRepair();
        //this.description = ".72 Calibre"
        this.addPropertyOverride(new ResourceLocation("type"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack item, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                if(item.hasTagCompound()){
                    if ( item.getTagCompound().getBoolean("use") == true) {
                        return 0.1f;
                    } else return 0.0f;
                } else return 0.0f;

            }
        });
    }
    // Temporary onItemRightClick method
    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft)
    {
        System.out.println("Stopped Using");
        EntityPlayer player = (EntityPlayer) entityLiving;
        System.out.println(player.getItemInUseCount());


            //if(player.getItemInUseCount() > 5) {
        world.playSound( null, player.posX, player.posY, player.posZ, ModSounds.MUSKET_SHOT, SoundCategory.BLOCKS, 0.5F, 0.3F / (itemRand.nextFloat() * 0.4F + 0.8F));
        TypeMud entity = new TypeMud(world, player);
        entity.setType("muck");
        entity.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 7.0F, 0.5F);
        world.spawnEntity(entity);
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        stack.getTagCompound().setBoolean("use", false);

        //}
    }
/*
    public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entityLiving)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entityLiving;
            world.playSound( null, player.posX, player.posY, player.posZ, ModSounds.MUSKET_SHOT, SoundCategory.BLOCKS, 0.5F, 0.3F / (itemRand.nextFloat() * 0.4F + 0.8F));
            EntityMuckBall entity = new EntityMuckBall(world, player);
            entity.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0.0F, 7.0F, 0.5F);
            world.spawnEntity(entity);

        }

        return stack;
    }
*/
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        boolean flag = true;
        if (!itemstack.hasTagCompound()) {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        itemstack.getTagCompound().setBoolean("use", true);

        ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, flag);
        if (ret != null) {
            return ret;
        }

        if (!playerIn.capabilities.isCreativeMode && !flag)
        {
            return flag ? new ActionResult(EnumActionResult.PASS, itemstack) : new ActionResult(EnumActionResult.FAIL, itemstack);
        }
        else
        {
            playerIn.setActiveHand(handIn);
            return new ActionResult(EnumActionResult.SUCCESS, itemstack);
        }
    }

    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 7200;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
    }
}
