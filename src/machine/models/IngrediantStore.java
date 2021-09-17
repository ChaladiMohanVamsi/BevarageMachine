package machine.models;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Ingrediant Store is the class to store the state of Ingrediants present in the machine.
 * This class provides methods to refill and get available quantities.
 */
public class IngrediantStore {

    private final Map<Ingrediant, Integer> store;

    public IngrediantStore(){
        this.store = new HashMap<>();
    }

    public Integer getIngrediantQuantity(Ingrediant i){
        return store.getOrDefault(i, 0);
    }

    public void refillIngrediant(Ingrediant i , Integer quantity){
        store.put(i, store.getOrDefault(i,0)+quantity);
    }

    /**
     * This method validates if required ingrediants are present in required quantitiy
     * and block them for use, So that other threads can't count on them.
     * The content is made synchronized to make it thread safe.
     * @param ingrediantMap
     * @return
     */
    public Boolean blockIfAvailableIngrediants(Map<Ingrediant, Integer> ingrediantMap){
        //Chose synchronized block instead of ConcurrenMap because concurrentMap provides safety one single transaction.
        //In this scenario single transaction contains multiple ingrediants validations.
        synchronized (store) {
            System.out.println("Ingrediants Before: "+store);
            System.out.println("Ingrediants Required: "+ingrediantMap);
            if (!isAvailableIngrediants(ingrediantMap)) return false;

            for (Map.Entry<Ingrediant, Integer> entry : ingrediantMap.entrySet()) {
                Integer storeValue = store.get(entry.getKey());
                store.put(entry.getKey(), storeValue - entry.getValue());
            }
            System.out.println("Ingrediants After: "+store);
        }

        return true;
    }

    private Boolean isAvailableIngrediants(@NotNull Map<Ingrediant, Integer> ingrediantMap){
        boolean result = true;
        for(Map.Entry<Ingrediant, Integer> entry: ingrediantMap.entrySet()){
            if(!isIngrediantAvailable(entry.getKey(), entry.getValue())){
                result = false;
                break;
            }
        }

        return result;
    }

    private Boolean isIngrediantAvailable(Ingrediant i, Integer quantity){
        return store.getOrDefault(i,0) >= quantity;
    }

    @Override
    public String toString(){
        return store.toString();
    }
}
