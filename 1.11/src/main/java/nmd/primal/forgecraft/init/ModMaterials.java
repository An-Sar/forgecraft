package nmd.primal.forgecraft.init;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Created by mminaie on 3/30/17.
 */
public class ModMaterials {

    public static Item.ToolMaterial TOOL_WROUGHT_IRON = EnumHelper.addToolMaterial("wroughtiron", 2, 500, 4.0F, 1.0F, 0);
    public static Item.ToolMaterial CLEAN_IRON = EnumHelper.addToolMaterial("cleaniron", 2, 700, 6.0F, 3.0F, 0);
    public static Item.ToolMaterial BASIC_STEEL = EnumHelper.addToolMaterial("steelbasic", 3, 900, 8.0F, 5.0F, 0);
    public static Item.ToolMaterial WOOTZ_STEEL = EnumHelper.addToolMaterial("wootzsteel", 3, 1100, 10.0F, 7.0F, 0);
}
