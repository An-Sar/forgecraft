package nmd.primal.forgecraft.crafting;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;

/**
 * Created by mminaie on 2/11/17.
 */
public class ForgeCrafting {

        // ***************************************************************************** //
        //  Recipe Handler ForgeCrafting
        // ***************************************************************************** //
        private static ArrayList<ForgeCrafting> forgeRecipes = new ArrayList<>();

        private ItemStack input;
        private ItemStack output;
        private ItemStack output_failed;
        private ItemStack cool_output;

        private int heat_threshold;
        private int ideal_time;
        private int cooldown;

        public ForgeCrafting(ItemStack input, ItemStack output, ItemStack output_failed, ItemStack cool_output, int heat_threshold, int ideal_time, int cooldown,float heat_variance, float time_variance)
        {
            this.input = input;
            this.output = output;
            this.output_failed = output_failed;
            this.cool_output = cool_output;
            this.heat_threshold = heat_threshold;
            this.ideal_time = ideal_time;
            this.cooldown = cooldown;

        }

        // ***************************************************************************** //
        //  Recipe Methods
        // ***************************************************************************** //
        public static void addRecipe(ItemStack input, ItemStack output, ItemStack failed, ItemStack cool, int heat_threshold, int ideal_time, int cooldown, float heat_variance, float time_variance)
        {
            forgeRecipes.add(new ForgeCrafting(input, output, failed, cool, heat_threshold, ideal_time, cooldown, heat_variance, time_variance));
        }

        public static boolean isRecipeItem(ItemStack stack)
        {
            for(ForgeCrafting recipe : forgeRecipes) {
                if (stack.isItemEqual(recipe.input))
                    return true;
            }
            return false;
        }

        public static boolean isOutputItem(ItemStack stack)
        {
            for(ForgeCrafting recipe : forgeRecipes) {
                if (stack.isItemEqual(recipe.output))
                    return true;
            }
            return false;
        }

        public static boolean isCoolItem(ItemStack stack)
        {
            for(ForgeCrafting recipe : forgeRecipes) {
                if (stack.isItemEqual(recipe.cool_output))
                    return true;
            }
            return false;
        }

        public static ForgeCrafting getRecipe(ItemStack stack)
        {
            for(ForgeCrafting recipe : forgeRecipes) {
                if (stack.isItemEqual(recipe.input))
                    return recipe;
            }
            return null;
        }

        public static ForgeCrafting getRecipeFromOutput(ItemStack stack)
        {
            for(ForgeCrafting recipe : forgeRecipes) {
                if (stack.isItemEqual(recipe.output))
                    return recipe;
            }
            return null;
        }

        public ItemStack getInput()
        {
            return this.input;
        }

        public ItemStack getOutput()
        {
            return this.output;
        }

        public ItemStack getOutputFailed()
        {
            return this.output_failed;
        }

        public ItemStack getCoolOutput()
        {
            return this.cool_output;
        }

        public int getHeatThreshold()
        {
            return this.heat_threshold;
        }

        public int getIdealTime()
        {
            return this.ideal_time;
        }

        public int getCooldown(){ return this.cooldown;}

        ///
        //  end
        ///




}
