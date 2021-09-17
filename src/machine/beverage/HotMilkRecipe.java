package machine.beverage;

import machine.models.IRecipe;
import machine.models.Ingrediant;

import java.util.Map;

public class HotMilkRecipe implements IRecipe {
    @Override
    public void processBeverage(Map<Ingrediant, Integer> ingrediants) {
        System.out.println("Hot Milk is Preparing.");
        for(Map.Entry<Ingrediant, Integer> entry: ingrediants.entrySet()){
            System.out.println(entry.getKey()+" Recipe: Added "+entry.getKey()+ " of Quantity "+entry.getValue());
        }
    }
}
