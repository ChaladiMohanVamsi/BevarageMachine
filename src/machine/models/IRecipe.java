package machine.models;

import java.util.Map;

/**
 * Recipe Interface to extend for multiple recipes.
 * Other Classes in models package can dependent on this Interface
 * and addition of new Recipe will not affect current package (No need to recompile). Dependency Inversion.
 */
public interface IRecipe {
    void processBeverage(Map<Ingrediant, Integer> ingrediants);
}
