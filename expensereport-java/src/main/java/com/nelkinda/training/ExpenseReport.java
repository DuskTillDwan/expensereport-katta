package com.nelkinda.training;

import lombok.Getter;

import java.util.Date;
import java.util.List;

enum ExpenseType {
    DINNER("Dinner", true, 5000),
    BREAKFAST("Breakfast", true, 1000),
    CAR_RENTAL("Car Rental", false, Integer.MAX_VALUE),
    LUNCH("Lunch", true, 2000);

    private final String name;
    private final Boolean meal;
    @Getter
    private final int limit;

    ExpenseType(String name, Boolean meal, int limit) {
        this.name = name;
        this.limit = limit;
        this.meal = meal;
    }

    String getExpenseName() {
        return name;
    }

    boolean isMeal() {
        return meal;
    }

}

class Expense {
    ExpenseType type;
    int amount;

    boolean isOverLimit() {
        return amount > type.getLimit();
    }

    boolean isMeal() {
        return type.isMeal();
    }

    String getName() {
        return type.getExpenseName();
    }
}

public class ExpenseReport {
    public void printReport(List<Expense> expenses, Date date) {
        int total = 0;
        int mealExpenses = 0;

        System.out.println("Expenses " + date);

        for (Expense expense : expenses) {
            if (expense.isMeal()) {
                mealExpenses += expense.amount;
            }

            String expenseName = expense.getName();
            String mealOverExpensesMarker = expense.isOverLimit() ? "X" : " ";
            System.out.println(expenseName + "\t" + expense.amount + "\t" + mealOverExpensesMarker);
            total += expense.amount;
        }

        System.out.println("Meal expenses: " + mealExpenses);
        System.out.println("Total expenses: " + total);
    }

}
