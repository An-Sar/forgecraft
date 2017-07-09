package nmd.primal.forgecraft.proxy;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import nmd.primal.forgecraft.init.ModBlocks;
import nmd.primal.forgecraft.init.ModItems;
import nmd.primal.forgecraft.models.ModelPlateHelmet;
import nmd.primal.forgecraft.renders.blocks.*;
import nmd.primal.forgecraft.tiles.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kitsu on 11/26/2016.
 */
public class ClientProxy implements CommonProxy {

    public static final Map<Item, ModelBiped> armorModels = new HashMap<Item, ModelBiped>();

    @Override
    public void preInit(){
        ModItems.registerCustomRenders();
    }

    @Override
    public void init() {
        //OBJLoader.INSTANCE.addDomain(ModInfo.MOD_ID);
        //Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(ModItems.stonetongs, 0, new ModelResourceLocation("stonetongs", "inventory"));
        ModItems.registerRenders();
        ModBlocks.registerRenders();
        //this.registerModelBakeryStuff();
        this.registerTileRendering();
        this.registerModels();
    }

    //@Override
    public void registerTileRendering()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileForge.class, new TileForgeRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TilePistonBellows.class, new TilePistonBellowsRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileBloomery.class, new TileBloomeryRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileAnvil.class, new TileAnvilRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileBreaker.class, new TileBreakerRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCastingForm.class, new TileCastingformRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCastingBlock.class, new TileCastingBlockRender());
    }

    public void registerModels(){

        ModelPlateHelmet custom_armor = new ModelPlateHelmet(1.0f);

        armorModels.put(ModItems.testleggings, custom_armor);
    }

    @Override
    public void registerModelBakeryVariants(){
        //ModelBakery.registerItemVariants(ModItems.stonetongs, new ResourceLocation(ModInfo.MOD_ID, "stonetongs_default"),
        //        new ResourceLocation(ModInfo.MOD_ID, "stonetongs_emptyhot"));
    }



}
