package machine.models;

import com.sun.istack.internal.NotNull;

import java.util.LinkedHashMap;

public class Beverage {

    private final String beverageName;
    private final LinkedHashMap<Ingrediant, Integer> ingrediantsMap;
    private final IRecipe recipe;
    private final IngrediantStore store;

    public Beverage(@NotNull String beverageName,
             @NotNull LinkedHashMap<Ingrediant, Integer>ingrediantsMap,
             @NotNull IRecipe recipe,
             @NotNull IngrediantStore store){
        this.beverageName = beverageName;
        this.ingrediantsMap = ingrediantsMap;
        this.recipe = recipe;
        this.store = store;
    }

    /**
     * Method to validate if all the ingrediants are availble
     * and serve the beverage.
     */
    public void serveBeverage(){
        //Validate if the ingrediants are available with store.
        if(!store.blockIfAvailableIngrediants(ingrediantsMap)){
            System.out.println(beverageName+ ": Beverage "+beverageName+" is not available.");
            return;
        }

        //Making the thread sleep to mimic the serving process.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(beverageName+ ": Processing "+beverageName+" started.");

        //Using the recipe of beverage to process beverage.
        recipe.processBeverage(ingrediantsMap);
    }

}
