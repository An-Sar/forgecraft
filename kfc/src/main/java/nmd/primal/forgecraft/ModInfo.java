package nmd.primal.forgecraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nmd.primal.forgecraft.init.ModItems;
//import nmd.primal.forgecraft.Item.ModItems;

/**
 * Created by kitsu on 11/24/2016.
 */
public class ModInfo {
        /** Mod Details**/
    public static final String MOD_ID = "forgecraft";
    public static final String MOD_NAME = "Kitsu's ForgeCraft";
    public static final String MOD_CONFIG = "primal/" + MOD_ID;
    //public static final String MOD_PREFIX = MOD_ID + ":";
    public static final String MOD_CHANNEL = MOD_ID;
    public static final String MOD_VERSION = "1.2.65";
    public static final String MC_VERSIONS = "[1.11.0, 1.12.0)";
    public static final String DEPENDENCIES = "required-after:forge@[13.20.1.2388,);" + "required-after:primal@[0.4,);";

    /** Mod Structures **/
    public static final String SERVER_PROXY = "nmd.primal.forgecraft.proxy.ServerProxy";
    public static final String CLIENT_PROXY = "nmd.primal.forgecraft.proxy.ClientProxy";
    //public static final String GUI_FACTORY  = "nmd.primal.forgecraft.gui.GuiFactory";
    //public static final String UPDATE_JSON  = "";

    /** Creative Tabs **/
    public static CreativeTabs TAB_FORGECRAFT = new CreativeTabs(MOD_ID)
    {
        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.forgehammer);
        }
    };
}