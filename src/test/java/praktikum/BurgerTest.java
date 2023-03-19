package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private final static float DELTA = (float)0.00000001;
    private final static String BUN_NAME = "white bun";
    private final static float BUN_PRICE = (float) 9.999999999999;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredientSauceTeriyaki;

    @Mock
    private Ingredient mockIngredientSauceChili;

    @Mock
    private Ingredient mockIngredientFillingMeat;

    @Mock
    private Ingredient mockIngredientFillingCheese;

    @Mock
    private Ingredient mockIngredientFillingSalad;

    private Burger burger;

    @Before
    public void setUp() throws Exception {
        burger = new Burger();
    }

    @Test
    public void setBuns() {
        Mockito.when(mockBun.getName()).thenReturn(BUN_NAME);
        Mockito.when(mockBun.getPrice()).thenReturn(BUN_PRICE);
        burger.setBuns(mockBun);
        assertEquals(burger.bun.getName(), BUN_NAME);
        assertEquals(burger.bun.getPrice(), (float)10.0, DELTA);
    }

    @Test
    public void addIngredient() {
        Mockito.when(mockIngredientSauceTeriyaki.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(mockIngredientSauceTeriyaki.getName()).thenReturn("teriyaki");
        Mockito.when(mockIngredientSauceTeriyaki.getPrice()).thenReturn((float)2.0);
        burger.addIngredient((mockIngredientSauceTeriyaki));
        assertEquals(burger.ingredients.get(0).getType(), IngredientType.SAUCE);
        assertEquals(burger.ingredients.get(0).getName(), "teriyaki");
        assertEquals(burger.ingredients.get(0).getPrice(), (float)2.0, DELTA);
    }

    @Test
    public void removeIngredient() {
        burger.addIngredient((mockIngredientSauceTeriyaki));
        burger.removeIngredient(0);
        assertEquals(burger.ingredients.size(), 0);
    }

    @Test
    public void moveIngredient() {
        Mockito.when(mockIngredientSauceTeriyaki.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(mockIngredientSauceTeriyaki.getName()).thenReturn("teriyaki");
        Mockito.when(mockIngredientSauceTeriyaki.getPrice()).thenReturn((float)2.0);
        burger.addIngredient(mockIngredientSauceTeriyaki);
        burger.addIngredient(mockIngredientSauceChili);
        burger.addIngredient(mockIngredientFillingCheese);
        burger.moveIngredient(0, 2);
        assertEquals(burger.ingredients.get(2).getType(), IngredientType.SAUCE);
        assertEquals(burger.ingredients.get(2).getName(), "teriyaki");
        assertEquals(burger.ingredients.get(2).getPrice(), (float)2.0, DELTA);
    }

    @Test
    public void getPrice() {
        Mockito.when(mockBun.getPrice()).thenReturn((float)5.0);
        Mockito.when(mockIngredientSauceChili.getPrice()).thenReturn((float)2.0);
        Mockito.when(mockIngredientFillingMeat.getPrice()).thenReturn((float)8.0);
        Mockito.when(mockIngredientFillingCheese.getPrice()).thenReturn((float)3.0);
        Mockito.when(mockIngredientFillingSalad.getPrice()).thenReturn((float)2.0);
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredientSauceChili);
        burger.addIngredient(mockIngredientFillingMeat);
        burger.addIngredient(mockIngredientFillingCheese);
        burger.addIngredient(mockIngredientFillingSalad);
        assertEquals((float)25.0, burger.getPrice(), DELTA);
    }

    @Test
    public void getReceipt() {
        Mockito.when(mockBun.getPrice()).thenReturn((float)5.0);
        Mockito.when(mockBun.getName()).thenReturn("white bun");
        Mockito.when(mockIngredientSauceChili.getPrice()).thenReturn((float)2.0);
        Mockito.when(mockIngredientSauceChili.getName()).thenReturn("chili");
        Mockito.when(mockIngredientSauceChili.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(mockIngredientFillingMeat.getPrice()).thenReturn((float)8.0);
        Mockito.when(mockIngredientFillingMeat.getName()).thenReturn("meat");
        Mockito.when(mockIngredientFillingMeat.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(mockIngredientFillingCheese.getPrice()).thenReturn((float)3.0);
        Mockito.when(mockIngredientFillingCheese.getName()).thenReturn("cheese");
        Mockito.when(mockIngredientFillingCheese.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(mockIngredientFillingSalad.getPrice()).thenReturn((float)2.0);
        Mockito.when(mockIngredientFillingSalad.getName()).thenReturn("salad");
        Mockito.when(mockIngredientFillingSalad.getType()).thenReturn(IngredientType.FILLING);
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredientSauceChili);
        burger.addIngredient(mockIngredientFillingMeat);
        burger.addIngredient(mockIngredientFillingCheese);
        burger.addIngredient(mockIngredientFillingSalad);
        String receipt = burger.getReceipt();
        float price = burger.getPrice();
        String expectedReceipt = "(==== white bun ====)\n" +
                                 "= sauce chili =\n" +
                                 "= filling meat =\n" +
                                 "= filling cheese =\n" +
                                 "= filling salad =\n" +
                                 "(==== white bun ====)\n" +
                                 String.format("%nPrice: %f%n", price);
        assertEquals(expectedReceipt, receipt);
    }
}