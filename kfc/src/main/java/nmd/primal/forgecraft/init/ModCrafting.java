package nmd.primal.forgecraft.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import nmd.primal.core.api.PrimalAPI;
import nmd.primal.core.common.recipes.irecipe.RecipeHandler;
import nmd.primal.forgecraft.crafting.AnvilCrafting;
import nmd.primal.forgecraft.crafting.BloomeryCrafting;
import nmd.primal.forgecraft.crafting.CastingformCrafting;
import nmd.primal.forgecraft.crafting.ForgeCrafting;

import java.util.Random;

/**
 * Created by kitsu on 11/30/2016.
 */
public class ModCrafting {

    public static void register() {

        Random rand = new Random();

        //RecipeHandler.addShapedOreRecipe();
        
        /***CASTING BLOCK***/
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.castingblock),
                " B ", "BXB", " B ", 'X', Blocks.STONE_SLAB, 'B', PrimalAPI.Items.ADOBE_BRICK_DRY);

        /***CASTING FORM***/
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.castingform, 1),
                true, new Object[]{" S ", "S S", " S ",
                ('S'), "logSplit"
        });
        /***CASTING MUD***/
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.castingmud, 4),
                true, new Object[]{" C ", "CSC", " C ",
                ('S'), Blocks.SAND,
                ('C'), PrimalAPI.Items.MUD_CLUMP
        });
        /***YEW STAVE***/
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.yewstave, 2),
                true, new Object[]{"C", "C",
                ('C'), PrimalAPI.Items.LOGS_SPLIT_YEW
        });
        /***Unstrung Longbow***/
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.unstrunglongbow, 1),
                true, new Object[]{"C", "A",
                ('C'), "fatAnimal",
                ('A'), ModItems.rawlongbow
        });
        /***Longbow***/
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.longbow, 1),
                true, new Object[]{"C ","CA","C ",
                ('C'), PrimalAPI.Items.SILK_CORDAGE,
                ('A'), ModItems.unstrunglongbow
        });

        /***Forge***/
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.forge_brick),
                "X X", "XYX", "X X", 'X', Items.BRICK, 'Y', Blocks.FURNACE);
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.forge_adobe),
                "X X", "XYX", "X X", 'X', PrimalAPI.Items.ADOBE_BRICK_DRY, 'Y', Blocks.FURNACE);
        /***Bloomery***/
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.bloomery_brick),
                "X X", "X X", "XXX", 'X', Items.BRICK);

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.bloomery_adobe),
                "X X", "X X", "XXX", 'X', PrimalAPI.Items.ADOBE_BRICK_DRY);
/***Block Breaker***/
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.blockbreaker),
                "L  ", "BSB", "BBB", 'L', Blocks.LEVER, 'B', new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE), 'S', PrimalAPI.Items.SILK_CORDAGE_COILED);

        /*** Soft Crucible ***/
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.softcrucible),
                "X X", "X X", "XXX", 'X', Items.CLAY_BALL);

        /*** Soft Crucible ***/
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.softcrucible),
                "X X", "X X", "XXX", 'X', PrimalAPI.Items.MUD_CLUMP);

        /***Wooden PistonBellows***/
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.pistonbellowsoak), "XXX", "X Y", "XXX",
                'X', new ItemStack(Blocks.PLANKS, 1, 0), 'Y', ModItems.bellowshandle);
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.pistonbellowsspruce), "XXX", "X Y", "XXX",
                'X', new ItemStack(Blocks.PLANKS, 1, 1), 'Y', ModItems.bellowshandle);
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.pistonbellowsbirch), "XXX", "X Y", "XXX",
                'X', new ItemStack(Blocks.PLANKS, 1, 2), 'Y', ModItems.bellowshandle);
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.pistonbellowsjungle), "XXX", "X Y", "XXX",
                'X', new ItemStack(Blocks.PLANKS, 1, 3), 'Y', ModItems.bellowshandle);
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.pistonbellowsacacia), "XXX", "X Y", "XXX",
                'X', new ItemStack(Blocks.PLANKS, 1, 4), 'Y', ModItems.bellowshandle);
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.pistonbellowsdarkoak), "XXX", "X Y", "XXX",
                'X', new ItemStack(Blocks.PLANKS, 1, 5), 'Y', ModItems.bellowshandle);

        /***Bellows Handle***/
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.bellowshandle),
                "X X", "X X", " X ", 'X', Items.STICK);

        /***Tongs***/
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.stonetongs, 1),
                "X X", "YSY", 'X', Blocks.STONE, 'S', Items.STRING, 'Y', Items.STICK);

        /***RAW CRUCIBLES***/

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.rawironcrucible),
                "X","Y",
                ('X'), "oreIron",
                ('Y'), ModBlocks.emptycrucible);

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.rawcleanironcrucible),
                "XL","Y ",
                ('X'), "dustIron",
                ('L'), PrimalAPI.Items.CARBONATE_SLACK,
                ('Y'), ModBlocks.emptycrucible);

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.rawsteelcrucible),
                "XC","Y ",
                ('X'), new ItemStack(ModBlocks.ironcleanball, 1),
                ('C'), new ItemStack(PrimalAPI.Items.CHARCOAL_HIGH),
                ('Y'), ModBlocks.emptycrucible);
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.rawbronzecrucible),
                "XCX"," X "," Y ",
                ('X'), "dustCopper",
                ('C'), "dustTin",
                ('Y'), ModBlocks.emptycrucible);
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.rawbronzecrucible),
                "XXX"," X "," Y ",
                ('X'), "nuggetBronze",
                ('Y'), ModBlocks.emptycrucible);
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.rawbronzecrucible),
                "X","Y",
                ('X'), new ItemStack(ModItems.bronzepickaxehead, 1, OreDictionary.WILDCARD_VALUE),
                ('Y'), ModBlocks.emptycrucible);
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.rawbronzecrucible),
                "X","Y",
                ('X'), new ItemStack(ModItems.bronzeaxehead, 1, OreDictionary.WILDCARD_VALUE),
                ('Y'), ModBlocks.emptycrucible);
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.rawbronzecrucible),
                "X","Y",
                ('X'), new ItemStack(ModItems.bronzeshovelhead, 1, OreDictionary.WILDCARD_VALUE),
                ('Y'), ModBlocks.emptycrucible);
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.rawbronzecrucible),
                "X","Y",
                ('X'), new ItemStack(ModItems.bronzehoehead, 1, OreDictionary.WILDCARD_VALUE),
                ('Y'), ModBlocks.emptycrucible);
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.rawbronzecrucible),
                "X","Y",
                ('X'), new ItemStack(ModItems.bronzegladius, 1, OreDictionary.WILDCARD_VALUE),
                ('Y'), ModBlocks.emptycrucible);

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.rawbronzecrucible_redstone),
                "R","Y",
                ('R'), Items.REDSTONE,
                ('Y'), ModBlocks.rawbronzecrucible);
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.rawbronzecrucible_diamond),
                "D","Y",
                ('D'), PrimalAPI.Items.DIAMOND_KNAPP,
                ('Y'), ModBlocks.rawbronzecrucible);
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.rawbronzecrucible_lapis),
                "L","Y",
                ('L'), new ItemStack(Items.DYE, 1, 4),
                ('Y'), ModBlocks.rawbronzecrucible);
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.rawbronzecrucible_emerald),
                "E","Y",
                ('E'), PrimalAPI.Items.EMERALD_KNAPP,
                ('Y'), ModBlocks.rawbronzecrucible);


        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.rawbronzecrucible, 1),
                "T", "Y",
                ('T'), new ItemStack(ModItems.brokenbronzetool, 1),
                ('Y'), new ItemStack(ModBlocks.emptycrucible, 1)
                );
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.rawironcrucible, 1),
                "T", "Y",
                ('T'), new ItemStack(ModItems.brokenwroughtirontool, 1),
                ('Y'), new ItemStack(ModBlocks.emptycrucible, 1)
        );
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.rawcleanironcrucible, 1),
                "T", "Y",
                ('T'), new ItemStack(ModItems.brokencleanirontool, 1),
                ('Y'), new ItemStack(ModBlocks.emptycrucible, 1)
        );
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.rawsteelcrucible, 1),
                "T", "Y",
                ('T'), new ItemStack(ModItems.brokensteeltool, 1),
                ('Y'), new ItemStack(ModBlocks.emptycrucible, 1)
        );
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModBlocks.rawwootzcrucible, 1),
                "T", "Y",
                ('T'), new ItemStack(ModItems.brokenwootztool, 1),
                ('Y'), new ItemStack(ModBlocks.emptycrucible, 1)
        );


        /***************************************************************
         * TOOL CRAFTING RECIPES
         ***************************************************************/

        /***Pickaxe Crafting***/
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.bronzepickaxe, 1, OreDictionary.WILDCARD_VALUE), "T", "C", "S",
                ('T'), new ItemStack(ModItems.bronzepickaxehead, 1, OreDictionary.WILDCARD_VALUE), //new ItemStack( 1, OreDictionary.WILDCARD_VALUE),
                ('S'), PrimalAPI.Items.LACQUER_STICK,
                ('C'), "cordageGeneral");

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.ironpickaxe, 1, OreDictionary.WILDCARD_VALUE), "T", "C", "S",
                ('T'), new ItemStack(ModItems.pickaxehead, 1, OreDictionary.WILDCARD_VALUE), //new ItemStack( 1, OreDictionary.WILDCARD_VALUE),
                ('S'), PrimalAPI.Items.LACQUER_STICK,
                ('C'), "cordageGeneral");

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.cleanironpickaxe, 1, OreDictionary.WILDCARD_VALUE), "T", "C", "S",
                ('T'), new ItemStack(ModItems.cleanironpickaxehead, 1, OreDictionary.WILDCARD_VALUE),
                ('S'), PrimalAPI.Items.LACQUER_STICK,
                ('C'), "cordageGeneral");

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.steelpickaxe, 1, OreDictionary.WILDCARD_VALUE), "T", "C", "S",
                ('T'), new ItemStack(ModItems.steelpickaxehead, 1, OreDictionary.WILDCARD_VALUE),
                ('S'), PrimalAPI.Items.LACQUER_STICK,
                ('C'), "cordageGeneral");

        RecipeHandler.addShapedOreRecipe(new ItemStack(Items.IRON_PICKAXE), "T", "S",
                ('T'), ModItems.pickaxehead,
                ('S'), Items.STICK);
        //TODO wootz placeholder

        /***Axe Crafting***/
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.bronzeaxe, 1, OreDictionary.WILDCARD_VALUE), "T", "C", "S",
                ('T'), new ItemStack(ModItems.bronzeaxehead, 1, OreDictionary.WILDCARD_VALUE),
                ('S'), PrimalAPI.Items.LACQUER_STICK,
                ('C'), "cordageGeneral");

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.ironaxe, 1, OreDictionary.WILDCARD_VALUE), "T", "C", "S",
                ('T'), new ItemStack(ModItems.ironaxehead, 1, OreDictionary.WILDCARD_VALUE),
                ('S'), PrimalAPI.Items.LACQUER_STICK,
                ('C'), "cordageGeneral");

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.cleanironaxe, 1, OreDictionary.WILDCARD_VALUE), "T", "C", "S",
                ('T'), new ItemStack(ModItems.cleanironaxehead, 1, OreDictionary.WILDCARD_VALUE),
                ('S'), PrimalAPI.Items.LACQUER_STICK,
                ('C'), "cordageGeneral");

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.steelaxe, 1, OreDictionary.WILDCARD_VALUE), "T", "C", "S",
                ('T'), new ItemStack(ModItems.steelaxehead,1, OreDictionary.WILDCARD_VALUE),
                ('S'), PrimalAPI.Items.LACQUER_STICK,
                ('C'), "cordageGeneral");
        //TODO wootz placeholder
        RecipeHandler.addShapedOreRecipe(new ItemStack(Items.IRON_AXE), "T", "S",
                ('T'), ModItems.ironaxehead,
                ('S'), Items.STICK);

        /***Shovel Crafting***/
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.bronzeshovel, 1, OreDictionary.WILDCARD_VALUE), "T", "C", "S",
                ('T'), new ItemStack(ModItems.bronzeshovelhead, 1, OreDictionary.WILDCARD_VALUE),
                ('S'), PrimalAPI.Items.LACQUER_STICK,
                ('C'), "cordageGeneral");

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.ironshovel, 1, OreDictionary.WILDCARD_VALUE), "T", "C", "S",
                ('T'), new ItemStack(ModItems.ironshovelhead, 1, OreDictionary.WILDCARD_VALUE),
                ('S'), PrimalAPI.Items.LACQUER_STICK,
                ('C'), "cordageGeneral");

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.cleanironshovel, 1, OreDictionary.WILDCARD_VALUE), "T", "C", "S",
                ('T'), new ItemStack(ModItems.cleanironshovelhead, 1, OreDictionary.WILDCARD_VALUE),
                ('S'), PrimalAPI.Items.LACQUER_STICK,
                ('C'), "cordageGeneral");

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.steelshovel, 1, OreDictionary.WILDCARD_VALUE), "T", "C", "S",
                ('T'), new ItemStack(ModItems.steelshovelhead, 1, OreDictionary.WILDCARD_VALUE),
                ('S'), PrimalAPI.Items.LACQUER_STICK,
                ('C'), "cordageGeneral");
        //TODO placed holder for wootz
        RecipeHandler.addShapedOreRecipe(new ItemStack(Items.IRON_SHOVEL), "T", "S",
                ('T'), ModItems.ironshovelhead,
                ('S'), Items.STICK);

        /***Hoe Crafting***/
        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.bronzehoe, 1, OreDictionary.WILDCARD_VALUE), "T", "C", "S",
                ('T'), new ItemStack(ModItems.bronzehoehead, 1, OreDictionary.WILDCARD_VALUE),
                ('S'), PrimalAPI.Items.LACQUER_STICK,
                ('C'), "cordageGeneral");

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.ironhoe, 1, OreDictionary.WILDCARD_VALUE), "T", "C", "S",
                ('T'), new ItemStack(ModItems.ironhoehead, 1, OreDictionary.WILDCARD_VALUE),
                ('S'), PrimalAPI.Items.LACQUER_STICK,
                ('C'), "cordageGeneral");

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.cleanironhoe, 1, OreDictionary.WILDCARD_VALUE), "T", "C", "S",
                ('T'), new ItemStack(ModItems.cleanironhoehead, 1, OreDictionary.WILDCARD_VALUE),
                ('S'), PrimalAPI.Items.LACQUER_STICK,
                ('C'), "cordageGeneral");

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.steelhoe, 1, OreDictionary.WILDCARD_VALUE), "T", "C", "S",
                ('T'), new ItemStack(ModItems.steelhoehead, 1, OreDictionary.WILDCARD_VALUE),
                ('S'), PrimalAPI.Items.LACQUER_STICK,
                ('C'), "cordageGeneral");
        //TODO PLACE HOLDER FOR WOOTZ STEEL
        RecipeHandler.addShapedOreRecipe(new ItemStack(Items.IRON_HOE), "T", "S",
                ('T'), ModItems.ironhoehead,
                ('S'), Items.STICK);


        /***************************************************************
         * TOOL DISASSEMBLY RECIPES
         ***************************************************************/
        //RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.ironaxehead, 1, OreDictionary.WILDCARD_VALUE),
        //        "   ", " X ", "   ", 'X', ModItems.ironaxe);

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.bronzeaxehead, 1, OreDictionary.WILDCARD_VALUE),
                true, "X", ('X'), new ItemStack(ModItems.bronzeaxe, 1, OreDictionary.WILDCARD_VALUE));

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.bronzepickaxehead, 1, OreDictionary.WILDCARD_VALUE),
                true, "X", ('X'), new ItemStack(ModItems.bronzepickaxe, 1, OreDictionary.WILDCARD_VALUE));

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.bronzeshovelhead, 1, OreDictionary.WILDCARD_VALUE),
                true, "X", ('X'), new ItemStack(ModItems.bronzeshovel, 1, OreDictionary.WILDCARD_VALUE));

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.bronzehoehead, 1, OreDictionary.WILDCARD_VALUE),
                true, "X", ('X'), new ItemStack(ModItems.bronzehoe, 1, OreDictionary.WILDCARD_VALUE));


        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.ironaxehead, 1, OreDictionary.WILDCARD_VALUE),
                true, "X", ('X'), new ItemStack(ModItems.ironaxe, 1, OreDictionary.WILDCARD_VALUE));

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.pickaxehead, 1, OreDictionary.WILDCARD_VALUE),
                true, "X", ('X'), new ItemStack(ModItems.ironpickaxe, 1, OreDictionary.WILDCARD_VALUE));

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.ironshovelhead, 1, OreDictionary.WILDCARD_VALUE),
                true, "X", ('X'), new ItemStack(ModItems.ironshovel, 1, OreDictionary.WILDCARD_VALUE));

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.ironhoehead, 1, OreDictionary.WILDCARD_VALUE),
                true, "X", ('X'), new ItemStack(ModItems.ironhoe, 1, OreDictionary.WILDCARD_VALUE));


        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.cleanironaxehead, 1, OreDictionary.WILDCARD_VALUE),
                true, "X", ('X'), new ItemStack(ModItems.cleanironaxe, 1, OreDictionary.WILDCARD_VALUE));

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.cleanironpickaxehead, 1, OreDictionary.WILDCARD_VALUE),
                true, "X", ('X'), new ItemStack(ModItems.cleanironpickaxe, 1, OreDictionary.WILDCARD_VALUE));

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.cleanironshovelhead, 1, OreDictionary.WILDCARD_VALUE),
                true, "X", ('X'), new ItemStack(ModItems.cleanironshovel, 1, OreDictionary.WILDCARD_VALUE));

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.cleanironhoehead, 1, OreDictionary.WILDCARD_VALUE),
                true, "X", ('X'), new ItemStack(ModItems.cleanironhoe, 1, OreDictionary.WILDCARD_VALUE));


        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.steelaxehead, 1, OreDictionary.WILDCARD_VALUE),
                true, "X", ('X'), new ItemStack(ModItems.steelaxe, 1, OreDictionary.WILDCARD_VALUE));

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.steelpickaxehead, 1, OreDictionary.WILDCARD_VALUE),
                true, "X", ('X'), new ItemStack(ModItems.steelpickaxe, 1, OreDictionary.WILDCARD_VALUE));

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.steelshovelhead, 1, OreDictionary.WILDCARD_VALUE),
                true, "X", ('X'), new ItemStack(ModItems.steelshovel, 1, OreDictionary.WILDCARD_VALUE));

        RecipeHandler.addShapedOreRecipe(new ItemStack(ModItems.steelhoehead, 1, OreDictionary.WILDCARD_VALUE),
                true, "X", ('X'), new ItemStack(ModItems.steelhoe, 1, OreDictionary.WILDCARD_VALUE));

        /***************************************************************/

        /***Bloomery Crafting***/

//Makes the Empty Crucible
        BloomeryCrafting.addRecipe(
                new ItemStack(ModItems.softcrucible, 1),
                new ItemStack(ModBlocks.emptycruciblehot, 1),
                new ItemStack(ModBlocks.emptycruciblecrackedhot, 1),
                new ItemStack(ModBlocks.emptycrucible, 1),
                2000,
                1600,
                600,
                0.25f,
                0.25f);

//Makes the Cracked Crucible
        BloomeryCrafting.addRecipe(
                new ItemStack(ModBlocks.emptycruciblehot, 1),
                new ItemStack(ModBlocks.emptycruciblecrackedhot, 1),
                new ItemStack(ModBlocks.emptycruciblecrackedhot, 1),
                new ItemStack(ModBlocks.emptycruciblecracked, 1),
                2250,
                9000,
                600,
                0.0f,
                0.0f);

//Makes the Hot Iron Crucible
        BloomeryCrafting.addRecipe(
                new ItemStack(ModBlocks.rawironcrucible, 1),
                new ItemStack(ModBlocks.hotironcrucible, 1),
                new ItemStack(ModBlocks.hotironcrucible, 1),
                new ItemStack(ModBlocks.rawironcrucible, 1),
                800,
                20,
                500,
                0.5f,
                0.0f
                );

//Makes the Finished Hot Iron Crucible
        BloomeryCrafting.addRecipe(
                new ItemStack(ModBlocks.hotironcrucible, 1),
                new ItemStack(ModBlocks.hotcookedironcrucible, 1),
                new ItemStack(ModBlocks.failedironcruciblehot, 1),
                new ItemStack(ModBlocks.coolironcrucible, 1),
                1400,
                1200,
                800,
                0.33f,
                0.5f
        );

//Makes the Hot Clean Iron Crucible
        BloomeryCrafting.addRecipe(
                new ItemStack(ModBlocks.rawcleanironcrucible, 1),
                new ItemStack(ModBlocks.hotcleanironcrucible, 1),
                new ItemStack(ModBlocks.hotcleanironcrucible, 1),
                new ItemStack(ModBlocks.rawcleanironcrucible, 1),
                800,
                20,
                500,
                0.5f,
                0.0f
        );

//Makes the Finished Hot Clean Iron Crucible
        BloomeryCrafting.addRecipe(
                new ItemStack(ModBlocks.hotcleanironcrucible, 1),
                new ItemStack(ModBlocks.hotcookedcleanironcrucible, 1),
                new ItemStack(ModBlocks.failedcleanironcrucible, 1),
                new ItemStack(ModBlocks.coolcleanironcrucible, 1),
                1550,
                1200,
                800,
                0.33f,
                0.5f
        );

        //Makes the Hot Steel Crucible
        BloomeryCrafting.addRecipe(
                new ItemStack(ModBlocks.rawsteelcrucible, 1),
                new ItemStack(ModBlocks.hotsteelcrucible, 1),
                new ItemStack(ModBlocks.hotsteelcrucible, 1),
                new ItemStack(ModBlocks.rawsteelcrucible, 1),
                800,
                20,
                500,
                0.5f,
                0.0f
        );

//Makes the Finished Hot Steel Crucible
        BloomeryCrafting.addRecipe(
                new ItemStack(ModBlocks.hotsteelcrucible, 1),
                new ItemStack(ModBlocks.hotcookedsteelcrucible, 1),
                new ItemStack(ModBlocks.failedsteelcrucible, 1),
                new ItemStack(ModBlocks.coolsteelcrucible, 1),
                2100,
                1500,
                1000,
                0.2f,
                0.25f
        );
        //Makes the Hot Bronze Crucible
        BloomeryCrafting.addRecipe(
                new ItemStack(ModBlocks.rawbronzecrucible, 1),
                new ItemStack(ModBlocks.hotbronzecrucible, 1),
                new ItemStack(ModBlocks.hotbronzecrucible, 1),
                new ItemStack(ModBlocks.rawbronzecrucible, 1),
                800,
                20,
                500,
                0.5f,
                0.0f
        );

//Makes the Finished Hot Bronze Crucible
        BloomeryCrafting.addRecipe(
                new ItemStack(ModBlocks.hotbronzecrucible, 1),
                new ItemStack(ModBlocks.hotcookedbronzecrucible, 1),
                new ItemStack(ModBlocks.hotcookedbronzecrucible, 1),
                new ItemStack(ModBlocks.rawbronzecrucible, 1),
                1100,
                800,
                600,
                0.5f,
                0.5f
        );

        //Makes the Hot Bronze Crucible
        BloomeryCrafting.addRecipe(
                new ItemStack(ModBlocks.rawbronzecrucible_diamond, 1),
                new ItemStack(ModBlocks.hotbronzecrucible_diamond, 1),
                new ItemStack(ModBlocks.hotbronzecrucible_diamond, 1),
                new ItemStack(ModBlocks.rawbronzecrucible_diamond, 1),
                800,
                800,
                500,
                0.5f,
                0.5f
        );

//Makes the Finished Hot Bronze Crucible
        BloomeryCrafting.addRecipe(
                new ItemStack(ModBlocks.hotbronzecrucible_diamond, 1),
                new ItemStack(ModBlocks.hotcookedbronzecrucible_diamond, 1),
                new ItemStack(ModBlocks.hotcookedbronzecrucible_diamond, 1),
                new ItemStack(ModBlocks.rawbronzecrucible_diamond, 1),
                1100,
                1100,
                600,
                0.5f,
                0.5f
        );
        //Makes the Hot Bronze Crucible
        BloomeryCrafting.addRecipe(
                new ItemStack(ModBlocks.rawbronzecrucible_redstone, 1),
                new ItemStack(ModBlocks.hotbronzecrucible_redstone, 1),
                new ItemStack(ModBlocks.hotbronzecrucible_redstone, 1),
                new ItemStack(ModBlocks.rawbronzecrucible_redstone, 1),
                800,
                20,
                500,
                0.5f,
                0.0f
        );

//Makes the Finished Hot Bronze Crucible
        BloomeryCrafting.addRecipe(
                new ItemStack(ModBlocks.hotbronzecrucible_redstone, 1),
                new ItemStack(ModBlocks.hotcookedbronzecrucible_redstone, 1),
                new ItemStack(ModBlocks.hotcookedbronzecrucible_redstone, 1),
                new ItemStack(ModBlocks.rawbronzecrucible_redstone, 1),
                1100,
                1100,
                600,
                0.5f,
                0.5f
        );
//Makes the Hot Bronze Crucible
        BloomeryCrafting.addRecipe(
                new ItemStack(ModBlocks.rawbronzecrucible_emerald, 1),
                new ItemStack(ModBlocks.hotbronzecrucible_emerald, 1),
                new ItemStack(ModBlocks.hotbronzecrucible_emerald, 1),
                new ItemStack(ModBlocks.rawbronzecrucible_emerald, 1),
                800,
                20,
                500,
                0.5f,
                0.0f
        );

//Makes the Finished Hot Bronze Crucible
        BloomeryCrafting.addRecipe(
                new ItemStack(ModBlocks.hotbronzecrucible_emerald, 1),
                new ItemStack(ModBlocks.hotcookedbronzecrucible_emerald, 1),
                new ItemStack(ModBlocks.hotcookedbronzecrucible_emerald, 1),
                new ItemStack(ModBlocks.rawbronzecrucible_emerald, 1),
                1100,
                1100,
                600,
                0.5f,
                0.5f
        );
        //Makes the Hot Bronze Crucible
        BloomeryCrafting.addRecipe(
                new ItemStack(ModBlocks.rawbronzecrucible_lapis, 1),
                new ItemStack(ModBlocks.hotbronzecrucible_lapis, 1),
                new ItemStack(ModBlocks.hotbronzecrucible_lapis, 1),
                new ItemStack(ModBlocks.rawbronzecrucible_lapis, 1),
                800,
                20,
                500,
                0.5f,
                0.0f
        );

//Makes the Finished Hot Bronze Crucible
        BloomeryCrafting.addRecipe(
                new ItemStack(ModBlocks.hotbronzecrucible_lapis, 1),
                new ItemStack(ModBlocks.hotcookedbronzecrucible_lapis, 1),
                new ItemStack(ModBlocks.hotcookedbronzecrucible_lapis, 1),
                new ItemStack(ModBlocks.rawbronzecrucible_lapis, 1),
                1100,
                1100,
                600,
                0.5f,
                0.5f
        );

        //TODO PLACE HOLDER FOR WOOTZ

        /******************************************************************************
         CASTING
         ******************************************************************************/

        String empty = ItemStack.EMPTY.getItem().getRegistryName().toString();
        String muddd = ModItems.castingmud.getRegistryName().toString();

        //Casting Pickaxe
        CastingformCrafting.addRecipe(
                new String[] {
                        muddd,muddd,muddd,muddd,muddd,
                        muddd,empty,empty,empty,muddd,
                        empty,muddd,muddd,muddd,empty,
                        muddd,muddd,muddd,muddd,muddd,
                        muddd,muddd,muddd,muddd,muddd },
                new ItemStack(ModItems.cast_pickaxe, 1)
        );

        //Casting Shovel
        CastingformCrafting.addRecipe(
                new String[] {
                        muddd,muddd,muddd,muddd,muddd,
                        muddd,muddd,empty,muddd,muddd,
                        muddd,empty,empty,empty,muddd,
                        muddd,empty,empty,empty,muddd,
                        muddd,empty,muddd,empty,muddd },
                new ItemStack(ModItems.cast_shovel, 1)
        );

        //Casting Axe
        CastingformCrafting.addRecipe(
                new String[] {
                        muddd,empty,empty,muddd,muddd,
                        muddd,empty,empty,empty,muddd,
                        muddd,empty,empty,empty,muddd,
                        muddd,empty,muddd,muddd,muddd,
                        muddd,muddd,muddd,muddd,muddd },
                new ItemStack(ModItems.cast_axe, 1)
        );

        //Casting Hoe
        CastingformCrafting.addRecipe(
                new String[] {
                        muddd,muddd,muddd,empty,empty,
                        muddd,muddd,empty,muddd,muddd,
                        muddd,empty,muddd,muddd,muddd,
                        empty,muddd,muddd,muddd,muddd,
                        muddd,muddd,muddd,muddd,muddd },
                new ItemStack(ModItems.cast_hoe, 1)
        );

        //Casting Gladius
        CastingformCrafting.addRecipe(
                new String[] {
                        muddd,muddd,muddd,muddd,muddd,
                        muddd,muddd,empty,muddd,muddd,
                        muddd,muddd,empty,muddd,muddd,
                        muddd,empty,empty,empty,muddd,
                        muddd,muddd,empty,muddd,muddd },
                new ItemStack(ModItems.cast_gladius, 1)
        );

        /******************************************************************************
          FORGING
         ******************************************************************************/

//Makes a Hot Iron Ingot
        ForgeCrafting.addRecipe(
                Item.getItemFromBlock(ModBlocks.ironball),
                new ItemStack(ModItems.ironingotballhot, 1),
                800,
                200,
                500,
                1.0f,
                1.0f
        );
//Makes a Hot Clean Iron Ingot
        ForgeCrafting.addRecipe(
                Item.getItemFromBlock(ModBlocks.ironcleanball),
                new ItemStack(ModItems.ironcleaningotballhot, 1),
                900,
                250,
                500,
                1.0f,
                1.0f
        );
//Makes a Hot Steel Ingot
        ForgeCrafting.addRecipe(
                Item.getItemFromBlock(ModBlocks.steelball),
                new ItemStack(ModItems.steelingotballhot, 1),
                1000,
                300,
                500,
                1.0f,
                1.0f
        );

// Converts a Vanilla Ingot into ForgeCraft Ingot Ball
        ForgeCrafting.addRecipe(
                Items.IRON_INGOT,
                new ItemStack(ModItems.ironingotballhot, 1),
                800,
                200,
                500,
                1.0f,
                1.0f
        );

//Makes a Hot Iron Chunk
        ForgeCrafting.addRecipe(
                Item.getItemFromBlock(ModBlocks.ironchunk),
                new ItemStack(ModItems.ironchunkhot,1),
                800,
                160,
                400,
                1.0f,
                1.0f
        );
//Makes a Hot Clean Iron Chunk
        ForgeCrafting.addRecipe(
                Item.getItemFromBlock(ModBlocks.ironcleanchunk),
                new ItemStack(ModItems.ironcleanchunkhot,1),
                900,
                250,
                500,
                1.0f,
                1.0f
        );
//Makes a Hot Steel Chunk
        ForgeCrafting.addRecipe(
                Item.getItemFromBlock(ModBlocks.steelchunk),
                new ItemStack(ModItems.steelchunkhot,1),
                1000,
                300,
                500,
                1.0f,
                1.0f
        );

        /***Makes a Hot Iron PickaxeHead***/
        ForgeCrafting.addRecipe(
                ModItems.pickaxehead,
                new ItemStack(ModItems.pickaxehead, 1),
                800,
                160,
                400,
                1.0f,
                1.0f
        );

        /***Makes a Hot Clean Iron PickaxeHead***/
        ForgeCrafting.addRecipe(
                ModItems.cleanironpickaxehead,
                new ItemStack(ModItems.cleanironpickaxehead, 1),
                800,
                160,
                400,
                1.0f,
                1.0f
        );

        /***Makes a Hot Steel PickaxeHead***/
        ForgeCrafting.addRecipe(
                ModItems.steelpickaxehead,
                new ItemStack(ModItems.steelpickaxehead, 1),
                1000,
                160,
                400,
                1.0f,
                1.0f
        );

        //TODO Wootz Pickaxe

        /***Makes a Hot Iron AxeHead***/
        ForgeCrafting.addRecipe(
                ModItems.ironaxehead,
                new ItemStack(ModItems.ironaxehead, 1),
                800,
                160,
                400,
                1.0f,
                1.0f
        );
        /***Makes a Hot Clean Iron AxeHead***/
        ForgeCrafting.addRecipe(
                ModItems.cleanironaxehead,
                new ItemStack(ModItems.cleanironaxehead, 1),
                800,
                160,
                400,
                1.0f,
                1.0f
        );
        /***Makes a Hot Steel AxeHead***/
        ForgeCrafting.addRecipe(
                ModItems.steelaxehead,
                new ItemStack(ModItems.steelaxehead, 1),
                800,
                160,
                400,
                1.0f,
                1.0f
        );
        //TODO Wootz

        /***Makes a Hot Iron ShovelHead***/
        ForgeCrafting.addRecipe(
                ModItems.ironshovelhead,
                new ItemStack(ModItems.ironshovelhead, 1),
                800,
                160,
                400,
                1.0f,
                1.0f
        );
        /***Makes a Hot Clean Iron ShovelHead***/
        ForgeCrafting.addRecipe(
                ModItems.cleanironshovelhead,
                new ItemStack(ModItems.cleanironshovelhead, 1),
                800,
                160,
                400,
                1.0f,
                1.0f
        );
        /***Makes a Hot Steel ShovelHead***/
        ForgeCrafting.addRecipe(
                ModItems.steelshovelhead,
                new ItemStack(ModItems.steelshovelhead, 1),
                800,
                160,
                400,
                1.0f,
                1.0f
        );
    //TODO Wootz

        /***Makes a Hot Iron HoeHead***/
        ForgeCrafting.addRecipe(
                ModItems.ironhoehead,
                new ItemStack(ModItems.ironhoehead, 1),
                800,
                160,
                400,
                1.0f,
                1.0f
        );
        /***Makes a Hot Clean Iron HoeHead***/
        ForgeCrafting.addRecipe(
                ModItems.cleanironhoehead,
                new ItemStack(ModItems.cleanironhoehead, 1),
                800,
                160,
                400,
                1.0f,
                1.0f
        );
        /***Makes a Hot Steel HoeHead***/
        ForgeCrafting.addRecipe(
                ModItems.steelhoehead,
                new ItemStack(ModItems.steelhoehead, 1),
                800,
                160,
                400,
                1.0f,
                1.0f
        );
    //TODO Wootz

        /******************************************************************************
               ANVILING
         ******************************************************************************/

        //String empty = ItemStack.EMPTY.getItem().getRegistryName().toString();
        String hotChunk = ModItems.ironchunkhot.getRegistryName().toString();
        String hotCleanChunk = ModItems.ironcleanchunkhot.getRegistryName().toString();
        String hotSteelChunk = ModItems.steelchunkhot.getRegistryName().toString();

        String diamond = Items.DIAMOND.getRegistryName().toString();
        String emerald = Items.EMERALD.getRegistryName().toString();

        String emeraldShard = PrimalAPI.Items.EMERALD_KNAPP.getRegistryName().toString();
        String diamondShard = PrimalAPI.Items.DIAMOND_KNAPP.getRegistryName().toString();
        String redstone = Items.REDSTONE.getRegistryName().toString();
        String lapis = Items.DYE.getRegistryName().toString();

        String pickaxehead = ModItems.pickaxehead.getRegistryName().toString();
        String ironaxehead = ModItems.ironaxehead.getRegistryName().toString();
        String ironshovelhead = ModItems.ironshovelhead.getRegistryName().toString();
        String ironhoehead = ModItems.ironhoehead.getRegistryName().toString();

        String cleanpickaxehead = ModItems.cleanironpickaxehead.getRegistryName().toString();
        String cleanaxehead = ModItems.cleanironaxehead.getRegistryName().toString();
        String cleanshovelhead = ModItems.cleanironshovelhead.getRegistryName().toString();
        String cleanhoehead = ModItems.cleanironhoehead.getRegistryName().toString();

        String steelpickaxehead = ModItems.steelpickaxehead.getRegistryName().toString();
        String steelaxehead = ModItems.steelaxehead.getRegistryName().toString();
        String steelshovelhead = ModItems.steelshovelhead.getRegistryName().toString();
        String steelhoehead = ModItems.steelhoehead.getRegistryName().toString();

        String wootzpickaxehead = ModItems.wootzpickaxehead.getRegistryName().toString();
        String wootzaxehead = ModItems.wootzaxehead.getRegistryName().toString();
        String wootzshovelhead = ModItems.wootzshovelhead.getRegistryName().toString();
        String wootzhoehead =  ModItems.wootzhoehead.getRegistryName().toString();

        /*
        Empty = 0
        hotChunk = 1
        diamond = 2
         */



        /*** ForgeHammer ***/
        AnvilCrafting.addRecipe(
                new String [] {
                        empty,hotChunk,hotChunk,hotChunk,empty,
                        empty,hotChunk,hotChunk,empty,empty,
                        empty,empty,hotChunk,empty,empty,
                        empty,empty,hotChunk,empty,empty,
                        empty,empty,hotChunk,empty,empty },
                new ItemStack(ModItems.forgehammer, 1),
                "null"
        );

        /*** Bucket ***/
        AnvilCrafting.addRecipe(
                new String [] {
                        empty,empty,empty,empty,empty,
                        empty,hotChunk,empty,hotChunk,empty,
                        empty,hotChunk,empty,hotChunk,empty,
                        empty,empty,hotChunk,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(Items.BUCKET, 1),
                "null"
        );

        /*** Primal Plate ***/
        AnvilCrafting.addRecipe(
                new String [] {
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,hotChunk,hotChunk,empty,empty,
                        empty,hotChunk,hotChunk,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(PrimalAPI.Items.IRON_PLATE, 1),
                "null"
        );

        /*** Iron Bars ***/
        AnvilCrafting.addRecipe(
                new String [] {
                        hotChunk,empty,hotChunk,empty,hotChunk,
                        hotChunk,hotChunk,hotChunk,hotChunk,hotChunk,
                        hotChunk,empty,hotChunk,empty,hotChunk,
                        hotChunk,hotChunk,hotChunk,hotChunk,hotChunk,
                        hotChunk,empty,hotChunk,empty,hotChunk, },
                new ItemStack(PrimalAPI.Items.IRON_PLATE, 1),
                "null"
        );

        /*** Flaked diamond ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,diamond,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(PrimalAPI.Items.DIAMOND_KNAPP, PrimalAPI.randomChanceReturn(2, 2, 3)),
                "null"
        );

        /*** Temp Iron Sword ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,hotChunk,empty,empty,
                        empty,empty,hotChunk,empty,empty,
                        empty,hotChunk,hotChunk,hotChunk,empty,
                        empty,empty,hotChunk,empty,empty },
                new ItemStack(ModItems.wroughtirongladius, 1),
                "null"
        );
        /*** Temp Clean Iron Sword ***/
        //TODO uncomment this
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,hotCleanChunk,empty,empty,
                        empty,empty,hotCleanChunk,empty,empty,
                        empty,hotCleanChunk,hotCleanChunk,hotCleanChunk,empty,
                        empty,empty,hotCleanChunk,empty,empty },
                new ItemStack(ModItems.cleanirongladius, 1),
                "null"
        );
        /*** Temp Steel Sword ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,hotSteelChunk,empty,empty,
                        empty,empty,hotSteelChunk,empty,empty,
                        empty,hotSteelChunk,hotSteelChunk,hotSteelChunk,empty,
                        empty,empty,hotSteelChunk,empty,empty },
                new ItemStack(ModItems.steelgladius, 1),
                "null"
        );
        /*** Temp Clean Iron LongSword ***/
        //TODO uncomment this
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,hotCleanChunk,empty,empty,
                        empty,empty,hotCleanChunk,empty,empty,
                        empty,empty,hotCleanChunk,empty,empty,
                        empty,hotCleanChunk,hotCleanChunk,hotCleanChunk,empty,
                        empty,empty,hotCleanChunk,empty,empty },
                new ItemStack(ModItems.cleanironlongsword, 1),
                "null"
        );
        /*** Temp Steel LongSword ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,hotSteelChunk,empty,empty,
                        empty,empty,hotSteelChunk,empty,empty,
                        empty,empty,hotSteelChunk,empty,empty,
                        empty,hotSteelChunk,hotSteelChunk,hotSteelChunk,empty,
                        empty,empty,hotSteelChunk,empty,empty },
                new ItemStack(ModItems.steellongsword, 1),
                "null"
        );

        /*** Temp WROUGHTIRON SLAYER ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,hotChunk,hotChunk,hotChunk,
                        empty,empty,hotChunk,hotChunk,empty,
                        empty,empty,hotChunk,hotChunk,empty,
                        empty,hotChunk,hotChunk,hotChunk,hotChunk,
                        empty,empty,hotChunk,hotChunk,empty },
                new ItemStack(ModItems.wroughtironslayer, 1),
                "null"
        );
        /*** Temp CLEANIRON SLAYER ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,hotCleanChunk,hotCleanChunk,hotCleanChunk,
                        empty,empty,hotCleanChunk,hotCleanChunk,empty,
                        empty,empty,hotCleanChunk,hotCleanChunk,empty,
                        empty,hotCleanChunk,hotCleanChunk,hotCleanChunk,hotCleanChunk,
                        empty,empty,hotCleanChunk,hotCleanChunk,empty },
                new ItemStack(ModItems.cleanironslayer, 1),
                "null"
        );
        /*** Temp STEELSLAYER ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,hotSteelChunk,hotSteelChunk,hotSteelChunk,
                        empty,empty,hotSteelChunk,hotSteelChunk,empty,
                        empty,empty,hotSteelChunk,hotSteelChunk,empty,
                        empty,hotSteelChunk,hotSteelChunk,hotSteelChunk,hotSteelChunk,
                        empty,empty,hotSteelChunk,hotSteelChunk,empty },
                new ItemStack(ModItems.steelslayer, 1),
                "null"
        );
        
        
        
        
        /*** Flaked emerald ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,emerald,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(PrimalAPI.Items.EMERALD_KNAPP, PrimalAPI.randomChanceReturn(3, 2, 3)),
                "null"
        );

        /*******************************************************
         *                       Pickaxes                      *
         *******************************************************/
        /***Iron Pickaxe Head***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,hotChunk,hotChunk,hotChunk,empty,
                        hotChunk,empty,empty,empty,hotChunk,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.pickaxehead, 1),
                "null"
        );
        /***Clean Iron Pickaxe Head***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,hotCleanChunk,hotCleanChunk,hotCleanChunk,empty,
                        hotCleanChunk,empty,empty,empty,hotCleanChunk,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.cleanironpickaxehead, 1),
                "null"
        );
        /***Steel Pickaxe Head***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,hotSteelChunk,hotSteelChunk,hotSteelChunk,empty,
                        hotSteelChunk,empty,empty,empty,hotSteelChunk,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.steelpickaxehead, 1),
                "null"
        );
        //TODO wootz

        /*** REPAIR Iron Pickaxe Head***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,hotChunk,empty,empty,
                        empty,empty,pickaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.pickaxehead, 1),
                "repair"
        );
        /*** REPAIR Clean Iron Pickaxe Head***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,hotCleanChunk,empty,empty,
                        empty,empty,cleanpickaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.cleanironpickaxehead, 1),
                "repair"
        );
        /*** REPAIR Steel Pickaxe Head***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,hotSteelChunk,empty,empty,
                        empty,empty,steelpickaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.steelpickaxehead, 1),
                "repair"
        );
        //TODO Wootz

        /*** Emerald Upgrade to Iron Pickaxe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,emeraldShard,empty,empty,
                        empty,empty,pickaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.pickaxehead, 1),
                "emerald"
        );

        /*** Diamond Upgrade to Iron Pickaxe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,diamondShard,empty,empty,
                        empty,empty,pickaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.pickaxehead, 1),
                "diamond"
        );

        /*** Redstone Upgrade to Iron Pickaxe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,redstone,empty,empty,
                        empty,empty,pickaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.pickaxehead, 1),
                "redstone"
        );

        /*** Lapis Upgrade to Iron Pickaxe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,lapis,empty,empty,
                        empty,empty,pickaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.pickaxehead, 1),
                "lapis"
        );

        /*** Emerald Upgrade to Clean Iron Pickaxe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,emeraldShard,empty,empty,
                        empty,empty,cleanpickaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.cleanironpickaxehead, 1),
                "emerald"
        );

        /*** Diamond Upgrade to Clean Iron Pickaxe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,diamondShard,empty,empty,
                        empty,empty,cleanpickaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.cleanironpickaxehead, 1),
                "diamond"
        );

        /*** Redstone Upgrade to Clean Iron Pickaxe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,redstone,empty,empty,
                        empty,empty,cleanpickaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.cleanironpickaxehead, 1),
                "redstone"
        );

        /*** Lapis Upgrade to Clean Iron Pickaxe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,lapis,empty,empty,
                        empty,empty,cleanpickaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.cleanironpickaxehead, 1),
                "lapis"
        );

        /*** Emerald Upgrade to Steel Pickaxe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,emeraldShard,empty,empty,
                        empty,empty,steelpickaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.steelpickaxehead, 1),
                "emerald"
        );

        /*** Diamond Upgrade to Steel Pickaxe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,diamondShard,empty,empty,
                        empty,empty,steelpickaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.steelpickaxehead, 1),
                "diamond"
        );

        /*** Redstone Upgrade to Steel Pickaxe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,redstone,empty,empty,
                        empty,empty,steelpickaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.steelpickaxehead, 1),
                "redstone"
        );

        /*** Lapis Upgrade to Steel Pickaxe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,lapis,empty,empty,
                        empty,empty,steelpickaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.steelpickaxehead, 1),
                "lapis"
        );

        /*******************************************************
         *                        Axes                         *
         *******************************************************/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,hotChunk,hotChunk,empty,empty,
                        empty,hotChunk,hotChunk,hotChunk,empty,
                        empty,hotChunk,hotChunk,hotChunk,empty,
                        empty,hotChunk,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.ironaxehead, 1),
                "null"
        );
        /*** REPAIR AXE Head***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,hotChunk,empty,empty,
                        empty,empty,ironaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.ironaxehead, 1),
                "repair"
        );
        /*** Clean Iron Axe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,hotCleanChunk,hotCleanChunk,empty,empty,
                        empty,hotCleanChunk,hotCleanChunk,hotCleanChunk,empty,
                        empty,hotCleanChunk,hotCleanChunk,hotCleanChunk,empty,
                        empty,hotCleanChunk,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.cleanironaxehead, 1),
                "null"
        );
        /*** REPAIR Clean Iron AXE Head***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,hotChunk,empty,empty,
                        empty,empty,cleanaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.cleanironaxehead, 1),
                "repair"
        );
        /*** Steel Axe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,hotSteelChunk,hotSteelChunk,empty,empty,
                        empty,hotSteelChunk,hotSteelChunk,hotSteelChunk,empty,
                        empty,hotSteelChunk,hotSteelChunk,hotSteelChunk,empty,
                        empty,hotSteelChunk,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.steelaxehead, 1),
                "null"
        );
        /*** REPAIR steel AXE Head***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,hotSteelChunk,empty,empty,
                        empty,empty,steelaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.steelaxehead, 1),
                "repair"
        );


        /*** Emerald Upgrade to Axe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,emeraldShard,empty,empty,
                        empty,empty,ironaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.ironaxehead, 1),
                "emerald"
        );

        /*** Diamond Upgrade to Axe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,diamondShard,empty,empty,
                        empty,empty,ironaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.ironaxehead, 1),
                "diamond"
        );

        /*** Redstone Upgrade to Axe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,redstone,empty,empty,
                        empty,empty,ironaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.ironaxehead, 1),
                "redstone"
        );

        /*** Lapis Upgrade to Axe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,lapis,empty,empty,
                        empty,empty,ironaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.ironaxehead, 1),
                "lapis"
        );

        /*** Emerald Upgrade to Axe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,emeraldShard,empty,empty,
                        empty,empty,cleanaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.cleanironaxehead, 1),
                "emerald"
        );

        /*** Diamond Upgrade to Axe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,diamondShard,empty,empty,
                        empty,empty,cleanaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.cleanironaxehead, 1),
                "diamond"
        );

        /*** Redstone Upgrade to Axe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,redstone,empty,empty,
                        empty,empty,cleanaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.cleanironaxehead, 1),
                "redstone"
        );

        /*** Lapis Upgrade to Axe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,lapis,empty,empty,
                        empty,empty,cleanaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.cleanironaxehead, 1),
                "lapis"
        );

        /*** Emerald Upgrade to Axe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,emeraldShard,empty,empty,
                        empty,empty,steelaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.steelaxehead, 1),
                "emerald"
        );

        /*** Diamond Upgrade to Axe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,diamondShard,empty,empty,
                        empty,empty,steelaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.steelaxehead, 1),
                "diamond"
        );

        /*** Redstone Upgrade to Axe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,redstone,empty,empty,
                        empty,empty,steelaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.steelaxehead, 1),
                "redstone"
        );

        /*** Lapis Upgrade to Axe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,lapis,empty,empty,
                        empty,empty,steelaxehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.steelaxehead, 1),
                "lapis"
        );

        /*******************************************************
         *                       Shovel                        *
         *******************************************************/
        /*** Iron Shovel Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,hotChunk,empty,empty,
                        empty,hotChunk,hotChunk,hotChunk,empty,
                        empty,hotChunk,hotChunk,hotChunk,empty,
                        empty,hotChunk,empty,hotChunk,empty },
                new ItemStack(ModItems.ironshovelhead, 1),
                "null"
        );

        /*** REPAIR iron SHOVEL Head***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,hotChunk,empty,empty,
                        empty,empty,ironshovelhead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.ironshovelhead, 1),
                "repair"
        );
        /*** Clean Iron Shovel Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,hotCleanChunk,empty,empty,
                        empty,hotCleanChunk,hotCleanChunk,hotCleanChunk,empty,
                        empty,hotCleanChunk,hotCleanChunk,hotCleanChunk,empty,
                        empty,hotCleanChunk,empty,hotCleanChunk,empty },
                new ItemStack(ModItems.cleanironshovelhead, 1),
                "null"
        );

        /*** REPAIR clean iron SHOVEL Head***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,hotCleanChunk,empty,empty,
                        empty,empty,cleanshovelhead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.cleanironshovelhead, 1),
                "repair"
        );
        /*** Steel Shovel Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,hotSteelChunk,empty,empty,
                        empty,hotSteelChunk,hotSteelChunk,hotSteelChunk,empty,
                        empty,hotSteelChunk,hotSteelChunk,hotSteelChunk,empty,
                        empty,hotSteelChunk,empty,hotSteelChunk,empty },
                new ItemStack(ModItems.steelshovelhead, 1),
                "null"
        );

        /*** REPAIR steel SHOVEL Head***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,hotSteelChunk,empty,empty,
                        empty,empty,steelshovelhead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.steelshovelhead, 1),
                "repair"
        );

        /*** Emerald Upgrade to Shovel Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,emeraldShard,empty,empty,
                        empty,empty,ironshovelhead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.ironshovelhead, 1),
                "emerald"
        );

        /*** Diamond Upgrade to Shovel Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,diamondShard,empty,empty,
                        empty,empty,ironshovelhead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.ironshovelhead, 1),
                "diamond"
        );

        /*** Redstone Upgrade to Shovel Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,redstone,empty,empty,
                        empty,empty,ironshovelhead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.ironshovelhead, 1),
                "redstone"
        );

        /*** Lapis Upgrade to Shovel Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,lapis,empty,empty,
                        empty,empty,ironshovelhead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.ironshovelhead, 1),
                "lapis"
        );
        /*** Emerald Upgrade to Shovel Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,emeraldShard,empty,empty,
                        empty,empty,cleanshovelhead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.cleanironshovelhead, 1),
                "emerald"
        );

        /*** Diamond Upgrade to Shovel Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,diamondShard,empty,empty,
                        empty,empty,cleanshovelhead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.cleanironshovelhead, 1),
                "diamond"
        );

        /*** Redstone Upgrade to Shovel Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,redstone,empty,empty,
                        empty,empty,cleanshovelhead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.cleanironshovelhead, 1),
                "redstone"
        );

        /*** Lapis Upgrade to Shovel Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,lapis,empty,empty,
                        empty,empty,cleanshovelhead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.cleanironshovelhead, 1),
                "lapis"
        );
        /*** Emerald Upgrade to Shovel Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,emeraldShard,empty,empty,
                        empty,empty,steelshovelhead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.steelshovelhead, 1),
                "emerald"
        );

        /*** Diamond Upgrade to Shovel Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,diamondShard,empty,empty,
                        empty,empty,steelshovelhead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.steelshovelhead, 1),
                "diamond"
        );

        /*** Redstone Upgrade to Shovel Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,redstone,empty,empty,
                        empty,empty,steelshovelhead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.steelshovelhead, 1),
                "redstone"
        );

        /*** Lapis Upgrade to Shovel Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,lapis,empty,empty,
                        empty,empty,steelshovelhead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.steelshovelhead, 1),
                "lapis"
        );

        /*******************************************************
         *                         Hoe                         *
         *******************************************************/
        /*** Iron Hoe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,hotChunk,hotChunk,
                        empty,empty,hotChunk,empty,empty,
                        empty,hotChunk,empty,empty,empty,
                        hotChunk,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.ironhoehead, 1),
                "null"
        );

        /*** REPAIR Iron HOE Head***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,hotChunk,empty,empty,
                        empty,empty,ironhoehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.ironhoehead, 1),
                "repair"
        );
        /*** Clean Iron Hoe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,hotCleanChunk,hotCleanChunk,
                        empty,empty,hotCleanChunk,empty,empty,
                        empty,hotCleanChunk,empty,empty,empty,
                        hotCleanChunk,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.cleanironhoehead, 1),
                "null"
        );

        /*** REPAIR Clean Iron HOE Head***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,hotCleanChunk,empty,empty,
                        empty,empty,cleanhoehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.cleanironhoehead, 1),
                "repair"
        );
        /*** Steel Hoe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,hotSteelChunk,hotSteelChunk,
                        empty,empty,hotSteelChunk,empty,empty,
                        empty,hotSteelChunk,empty,empty,empty,
                        hotSteelChunk,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.steelhoehead, 1),
                "null"
        );

        /*** REPAIR Steel HOE Head***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,hotSteelChunk,empty,empty,
                        empty,empty,steelhoehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.steelhoehead, 1),
                "repair"
        );

        /*** Emerald Upgrade to Hoe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,emeraldShard,empty,empty,
                        empty,empty,ironhoehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.ironhoehead, 1),
                "emerald"
        );

        /*** Diamond Upgrade to Hoe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,diamondShard,empty,empty,
                        empty,empty,ironhoehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.ironhoehead, 1),
                "diamond"
        );

        /*** Redstone Upgrade to Hoe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,redstone,empty,empty,
                        empty,empty,ironhoehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.ironhoehead, 1),
                "redstone"
        );

        /*** Lapis Upgrade to Hoe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,lapis,empty,empty,
                        empty,empty,ironhoehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.ironhoehead, 1),
                "lapis"
        );
        /*** Emerald Upgrade to Hoe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,emeraldShard,empty,empty,
                        empty,empty,cleanhoehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.cleanironhoehead, 1),
                "emerald"
        );

        /*** Diamond Upgrade to Hoe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,diamondShard,empty,empty,
                        empty,empty,cleanhoehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.cleanironhoehead, 1),
                "diamond"
        );

        /*** Redstone Upgrade to Hoe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,redstone,empty,empty,
                        empty,empty,cleanhoehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.cleanironhoehead, 1),
                "redstone"
        );

        /*** Lapis Upgrade to Hoe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,lapis,empty,empty,
                        empty,empty,cleanhoehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.cleanironhoehead, 1),
                "lapis"
        );
        /*** Emerald Upgrade to Hoe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,emeraldShard,empty,empty,
                        empty,empty,steelhoehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.steelhoehead, 1),
                "emerald"
        );

        /*** Diamond Upgrade to Hoe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,diamondShard,empty,empty,
                        empty,empty,steelhoehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.steelhoehead, 1),
                "diamond"
        );

        /*** Redstone Upgrade to Hoe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,redstone,empty,empty,
                        empty,empty,steelhoehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.steelhoehead, 1),
                "redstone"
        );

        /*** Lapis Upgrade to Hoe Head ***/
        AnvilCrafting.addRecipe(
                new String[] {
                        empty,empty,empty,empty,empty,
                        empty,empty,lapis,empty,empty,
                        empty,empty,steelhoehead,empty,empty,
                        empty,empty,empty,empty,empty,
                        empty,empty,empty,empty,empty },
                new ItemStack(ModItems.steelhoehead, 1),
                "lapis"
        );

    }
}
