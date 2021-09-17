package tests;

import machine.BeverageMachineService;
import machine.models.Ingrediant;
import machine.models.IngrediantStore;

import static machine.constants.BeverageConstants.HOT_MILK;

public class MachineServiceTestSuite {

    public static void main(String[] args) throws InterruptedException {
        testSameBeverageUntilIngrediantsOut();
    }

    private static void testSameBeverageUntilIngrediantsOut() throws InterruptedException {

        IngrediantStore store = new IngrediantStore();
        Ingrediant hotWater = new Ingrediant("hot_water", store);
        Ingrediant hotMilk = new Ingrediant("hot_milk", store);
        Ingrediant gingerSyrup = new Ingrediant("ginger_syrup", store);
        Ingrediant sugarSyrup = new Ingrediant("sugar_syrup", store);
        Ingrediant teaLeavesSyrup = new Ingrediant("tea_leaves_syrup", store);

        //Expected state of Ingrediant Map after each execution.
        store.refillIngrediant(hotWater, 400);
        store.refillIngrediant(hotMilk, 0);
        store.refillIngrediant(gingerSyrup, 100);
        store.refillIngrediant(sugarSyrup, 100);
        store.refillIngrediant(teaLeavesSyrup, 100);

        Ingrediant[] list = {hotWater, hotMilk, gingerSyrup, sugarSyrup, teaLeavesSyrup};

        //Rpeating the same job multiple times shouldn't alter the result state of Ingrediants;
        for(int j=0; j<6; j++) {
            BeverageMachineService service = new BeverageMachineService();

            //With the ingrediants provided HOT_MILK can be served 10 times and it is not available after that.
            for (int i = 0; i < 12; i++) {
                service.getBeverage(HOT_MILK);
            }
            service.shutDownMachine();

            //Waiting for executor service completed its tasts;
            while(!service.isTerminated()){
                Thread.sleep(1000);
            };

            System.out.println("Completed");
            service.displayIngrediantStore(); //Result Produced by Machine
            System.out.println("-------------------- "+store+" --------------------"); //Expected result if its matching with

        }
    }
}
