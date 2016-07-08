package coljamkop.momoney.Content;

import android.support.annotation.Nullable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aghbac on 6/8/16.
 */
public class Category implements Budgetable {
    private List<Expense> expenseList;
    private String categoryName;
    private BigDecimal total;
    private BigDecimal goal;

    /**
     * @return name of category
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName sets the name of the category
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * @param total sets the total of the category
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * @param goal sets the goal for the category
     */
    public void setGoal(BigDecimal goal) {
        this.goal = goal;
    }

    /**
     * Loads the passed parameters into the private variables and
     * sums the expenses to set the total
     * @param expenseList a list of expenses
     * @param categoryName name for new category
     * @param goal budgetable goal for new category
     */
    public Category(@Nullable List<Expense> expenseList, String categoryName, BigDecimal goal) {
        this.expenseList = new ArrayList<>();
        this.total = new BigDecimal(0.0);
        if (expenseList != null) {
            for (Expense expense :
                    expenseList) {
                this.expenseList.add(expense);
                this.total = this.total.add(expense.getTotal());
            }
        }
        this.categoryName = categoryName;
        this.goal = goal;

    }

    /**
     * Adds an expense to the current list of expenses
     * @param expense expense to be added
     */
    public void addExpense(BigDecimal expense) {
        expenseList.add(new Expense(expense));
        total = total.add(expense);
    }

    @Override
    /**
     * @return true when total is less than or equal to goal, else returns false
     */
    public boolean isInGoal() {
        return (total.compareTo(goal) <= 0);
    }

    @Override
    /**
     * @return total of expenses in category
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @return total of expenses in category
     */
    public String getDollarTotal() {
        return "$" + String.valueOf(total);
    }

    /**
     * @return goal of category
     */
    public BigDecimal getGoal() {
        return goal;
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }
}
