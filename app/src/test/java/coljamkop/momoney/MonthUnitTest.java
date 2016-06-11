package coljamkop.momoney;

import org.junit.Test;

import coljamkop.momoney.Content.Category;
import coljamkop.momoney.Content.Month;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class MonthUnitTest {
    @Test
    public void isInGoal_isCorrect() throws Exception {
        Month month = new Month(2016, 6, null);
        // $0.00 <= $0.00
        assertEquals(true, month.isInGoal());
        month.addCategory(new Category(null, "Groceries", 20.0));
    }

    @Test
    public void total_isCorrect() throws Exception {
        double total = 0;
        Category category = new Category(null, "New Category", 0.0);
        for(int i = 0; i < 10; i++) {
            category.addExpense(10.0);
            total += 10.0;
            assertEquals(total, category.getTotal(), 0.0);
        }
    }

    @Test
    public void month_roll_over() {
        Month oldMonth = new Month(2016, 1, null);

        // give month data
        oldMonth.addCategory(new Category(null, "Groceries", 100.0));
        oldMonth.addCategory(new Category(null, "Dates", 10.0));
        oldMonth.addCategory(new Category(null, "Fast Food", 15.0));
        oldMonth.getCategory("Groceries").addExpense(30.0);
        oldMonth.getCategory("Dates").addExpense(8.0);
        oldMonth.getCategory("Fast Food").addExpense(5.0);

        // check data
        for (Category category :
                oldMonth.getCategories()) {
            if(category.getCategoryName().equals("Groceries")) {
                assertEquals(30.00, category.getTotal(), 0.0);
                assertEquals(100.00, category.getGoal(), 0.0);
            }
            if(category.getCategoryName().equals("Dates")) {
                assertEquals(8.00, category.getTotal(), 0.0);
                assertEquals(10.00, category.getGoal(), 0.0);
            }
            if(category.getCategoryName().equals("Fast Food")) {
                assertEquals(5.0, category.getTotal(), 0.0);
                assertEquals(15.00, category.getGoal(), 0.0);
            }
        }

        // month rolls over
        Month newMonth = new Month(2016, 2, oldMonth.getCategories());
        // check categories for 0.00 total
        for (Category category :
                newMonth.getCategories()) {
            assertEquals(0.00, category.getTotal(), 0.0);
        }
    }
}