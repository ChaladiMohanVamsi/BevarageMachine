package machine.models;

import java.util.Objects;

public class Ingrediant {

    private final String ingrediantName;
    private final IngrediantStore store;
    private Integer lowLimit=0;

    public Ingrediant(String ingrediantName, IngrediantStore store){
        this.ingrediantName = ingrediantName;
        this.store = store;
    }

    public void setLowLimit(Integer limit){
        this.lowLimit = limit;
    }

    public String getIngrediantName(){
        return this.ingrediantName;
    }

    public void refillIngrediant(Integer quantity){
        store.refillIngrediant(this, quantity);
    }

    /**
     * This method identifies if a ingrediant is running low as per config.
     * @return
     */
    public Boolean isRunninLow(){
        return (store.getIngrediantQuantity(this)<lowLimit);
    }

    @Override
    public int hashCode(){
        return Objects.hash(ingrediantName);
    }

    @Override
    public String toString(){
        return ingrediantName;
    }
}
