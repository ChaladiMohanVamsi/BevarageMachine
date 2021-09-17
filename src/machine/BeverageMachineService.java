package machine;

import machine.beverage.BeverageFactory;
import machine.models.Ingrediant;
import machine.models.IngrediantStore;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static machine.constants.BeverageConstants.*;

public class BeverageMachineService {

    private final ExecutorService service;
    private final BeverageFactory beverageFactory;
    private final IngrediantStore store;

    public BeverageMachineService() {

        store = new IngrediantStore();
        Ingrediant hotWater = new Ingrediant("hot_water", store);
        Ingrediant hotMilk = new Ingrediant("hot_milk", store);
        Ingrediant gingerSyrup = new Ingrediant("ginger_syrup", store);
        Ingrediant sugarSyrup = new Ingrediant("sugar_syrup", store);
        Ingrediant teaLeavesSyrup = new Ingrediant("tea_leaves_syrup", store);

        store.refillIngrediant(hotWater, 500);
        store.refillIngrediant(hotMilk, 500);
        store.refillIngrediant(gingerSyrup, 100);
        store.refillIngrediant(sugarSyrup, 100);
        store.refillIngrediant(teaLeavesSyrup, 100);

        Map<String, LinkedHashMap<Ingrediant, Integer>> beverageIngrediantMap;

        beverageIngrediantMap = new HashMap<>();
        LinkedHashMap<Ingrediant, Integer> coffeeIngrediants = new LinkedHashMap<>();
        coffeeIngrediants.put(hotWater, 100);
        coffeeIngrediants.put(gingerSyrup, 30);
        coffeeIngrediants.put(hotMilk, 400);
        coffeeIngrediants.put(sugarSyrup, 50);
        coffeeIngrediants.put(teaLeavesSyrup, 30);

        LinkedHashMap<Ingrediant, Integer> elaichiTeaIngrediants = new LinkedHashMap<>();
        elaichiTeaIngrediants.put(hotWater, 300);
        elaichiTeaIngrediants.put(gingerSyrup, 30);
        elaichiTeaIngrediants.put(sugarSyrup, 50);
        elaichiTeaIngrediants.put(teaLeavesSyrup, 30);

        LinkedHashMap<Ingrediant, Integer> gingerTeaIngrediants = new LinkedHashMap<>();
        gingerTeaIngrediants.put(hotWater, 100);
        gingerTeaIngrediants.put(gingerSyrup, 30);
        gingerTeaIngrediants.put(hotMilk, 100);
        gingerTeaIngrediants.put(sugarSyrup, 50);

        LinkedHashMap<Ingrediant, Integer> hotMilkIngrediants = new LinkedHashMap<>();
        hotMilkIngrediants.put(hotWater, 10);
        hotMilkIngrediants.put(hotMilk, 50);

        LinkedHashMap<Ingrediant, Integer> hotWaterIngrediants = new LinkedHashMap<>();
        hotWaterIngrediants.put(hotWater, 50);

        beverageIngrediantMap.put(COFFEE, coffeeIngrediants );
        beverageIngrediantMap.put(ELAICHI_TEA, elaichiTeaIngrediants );
        beverageIngrediantMap.put(GINGER_TEA, gingerTeaIngrediants);
        beverageIngrediantMap.put(HOT_MILK, hotMilkIngrediants);
        beverageIngrediantMap.put(HOT_WATER, hotWaterIngrediants);

        //Number of Outlets the machine has.
        int outlets = 5;
        beverageFactory = new BeverageFactory(store, beverageIngrediantMap);

        service = Executors.newFixedThreadPool(outlets);
    }

    public void getBeverage(String beverageName){
        service.execute(() -> {
            try {
                beverageFactory.getBeverage(beverageName).serveBeverage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void shutDownMachine(){
        service.shutdown();
    }
    public Boolean isTerminated(){
        return service.isTerminated();
    }
    public void displayIngrediantStore(){
        System.out.println(this.store);
    }
}
