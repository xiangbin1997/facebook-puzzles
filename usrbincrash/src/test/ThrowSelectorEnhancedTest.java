package test;

import logic.StockKeepingUnit;
import logic.ThrowSelectorEnhanced;
import logic.UnitsCalc;
import org.junit.*;

import java.util.ArrayList;

import static junit.framework.Assert.*;

import static org.mockito.Mockito.*;

public class ThrowSelectorEnhancedTest {

    private ArrayList<StockKeepingUnit> units;

    @Before public void set() {
        units = new ArrayList<StockKeepingUnit>();
    }

    @Test public void nothingToThrowBecauseNothingGiven() {
        assertEquals(0, ThrowSelectorEnhanced.whatToThrow(0, units).size());
    }

    @Test public void throwEverythingBecauseWeCanNotLeaveNothing() {
        units.add(new StockKeepingUnit("item1", 10, 10));
        units.add(new StockKeepingUnit("item2", 20, 20));

        assertEquals(2, ThrowSelectorEnhanced.whatToThrow(30, units).size());
    }

    @Test public void choiceWhatToThrowIsClear() {

        units.add(new StockKeepingUnit("item1", 10, 10));
        units.add(new StockKeepingUnit("item2", 20, 20));

        ArrayList<StockKeepingUnit> result = ThrowSelectorEnhanced.whatToThrow(20, units);
        assertEquals(1, result.size());
        assertEquals("item2", result.get(0).getLabel());
    }

    @Test public void throwTheLessValuableItem() {
        units.add(new StockKeepingUnit("item1", 10, 10));
        units.add(new StockKeepingUnit("item2", 10, 13));
        units.add(new StockKeepingUnit("item3", 10, 5));


        ArrayList<StockKeepingUnit> result = ThrowSelectorEnhanced.whatToThrow(10, units);
        assertEquals(1, result.size());
        assertEquals("item3", result.get(0).getLabel());
    }

    @Test public void checkFacebookExample() {

        units.add(new StockKeepingUnit("LJS93K", 1300, 10500));
        units.add(new StockKeepingUnit("J38ZZ9", 700, 4750));
        units.add(new StockKeepingUnit("HJ394L", 200, 3250));
        units.add(new StockKeepingUnit("O1IE82", 75, 10250));

        ArrayList<StockKeepingUnit> result = ThrowSelectorEnhanced.whatToThrow(1250, units);

        assertEquals(9500, UnitsCalc.valueSum(result));

    }

}