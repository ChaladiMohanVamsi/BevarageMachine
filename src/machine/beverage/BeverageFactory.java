package machine.beverage;

import machine.models.Beverage;
import machine.models.IRecipe;
import machine.models.Ingrediant;
import machine.models.IngrediantStore;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static machine.constants.BeverageConstants.*;

public class BeverageFactory {

    private IngrediantStore store;
    private Map<String, LinkedHashMap<Ingrediant, Integer>> beverageIngredientsMap;
    private Map<String, IRecipe> recipeList;

    public BeverageFactory(IngrediantStore store, Map<String, LinkedHashMap<Ingrediant, Integer>> beverageIngredientsMap){
        this.store = store;
        this.beverageIngredientsMap = beverageIngredientsMap;
        this.recipeList = new HashMap<>();
    }

    public Beverage getBeverage(String beverageName) throws Exception {

        Beverage beverage = null;

        switch (beverageName){
            case COFFEE: {
                if(!recipeList.containsKey(COFFEE)) recipeList.put(COFFEE, new CoffeeRecipe());
                beverage = new Beverage(COFFEE, beverageIngredientsMap.get(COFFEE),recipeList.get(COFFEE), store);
                break;
            }
            case ELAICHI_TEA: {
                if(!recipeList.containsKey(ELAICHI_TEA)) recipeList.put(ELAICHI_TEA, new ElaichiTeaRecipe());
                beverage = new Beverage(ELAICHI_TEA, beverageIngredientsMap.get(ELAICHI_TEA),recipeList.get(ELAICHI_TEA), store);
                break;
            }
            case GINGER_TEA: {
                if(!recipeList.containsKey(GINGER_TEA)) recipeList.put(GINGER_TEA, new GingerTeaRecipe());
                beverage = new Beverage(GINGER_TEA, beverageIngredientsMap.get(GINGER_TEA),recipeList.get(GINGER_TEA), store);
                break;
            }
            case HOT_MILK: {
                if(!recipeList.containsKey(HOT_MILK)) recipeList.put(HOT_MILK, new HotMilkRecipe());
                beverage = new Beverage(HOT_MILK, beverageIngredientsMap.get(HOT_MILK),recipeList.get(HOT_MILK), store);
                break;
            }
            case HOT_WATER: {
                if(!recipeList.containsKey(HOT_WATER)) recipeList.put(HOT_WATER, new HotWaterRecipe());
                beverage = new Beverage(HOT_WATER, beverageIngredientsMap.get(HOT_WATER),recipeList.get(HOT_WATER), store);
                break;
            }
            default: {
                throw new Exception("Invalid Beverage Name Provided. Please Provide Valid Beverage Name.");
            }

        }

        return beverage;
    }
}
