package machine.beverage;

import machine.models.IRecipe;
import machine.models.Ingrediant;

import java.util.Map;

public class GingerTeaRecipe implements IRecipe {
    @Override
    public void processBeverage(Map<Ingrediant, Integer> ingrediants) {
        System.out.println("Ginger Tea is Preparing.");
        for(Map.Entry<Ingrediant, Integer> entry: ingrediants.entrySet()){
            System.out.println(entry.getKey()+" Recipe: Added "+entry.getKey()+ " of Quantity "+entry.getValue());
        }
    }
}
