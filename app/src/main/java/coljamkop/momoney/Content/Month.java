package coljamkop.momoney.Content;

import android.support.annotation.Nullable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Aghbac on 6/8/16.
 */
public class Month implements Budgetable {
    Map<String, Category> categoryMap;
    List<Category> categoryList;
    int year;
    int month;
    BigDecimal total;
    BigDecimal goal;

    public Month(int year, int month, @Nullable List<Category> categoryList) {
        this.year = year;
        this.month = month;
        this.goal = new BigDecimal(0.0);
        this.total = new BigDecimal(0.0);
        if (categoryList == null) {
            this.categoryMap = null;
            this.categoryList = null;
        } else {
            for (Category category :
                    categoryList) {
                addCategory(new Category(null, category.getCategoryName(), category.getGoal()));
            }
        }
    }

    public void addCategory(Category category) {
        if (categoryMap == null && categoryList == null) {
            categoryMap = new HashMap<>();
            categoryList = new ArrayList<>();
        }

        categoryMap.put(category.getCategoryName(), category);
        categoryList.add(category);

        this.goal.add(category.getGoal());
        this.total.add(category.getTotal());
    }

    @Override
    public boolean isInGoal() {
        return total.compareTo(goal) <= 0;
    }

    @Override
    public BigDecimal getTotal() {
        return total;
    }

    public Category getCategory(String categoryName) {
        return categoryMap.get(categoryName);
    }

    public List<Category> getCategories() {
        return categoryList;
    }
}
