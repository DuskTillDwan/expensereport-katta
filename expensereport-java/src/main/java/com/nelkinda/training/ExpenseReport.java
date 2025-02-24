package com.nelkinda.training;

import lombok.Getter;
import lombok.NonNull;

import java.util.*;

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

class Expenses implements Iterable<Expense> {

    private final List<Expense> expenseList = new ArrayList<>();

    public Expenses() {
    }

    public Expenses(Expense ... expenses){
        expenseList.addAll(Arrays.asList(expenses));
    }

    int getMealExpenses() {
        return expenseList.stream().filter(Expense::isMeal).mapToInt(expense -> expense.amount).sum();
    }

    int getTotal() {
        return expenseList.stream().mapToInt(expense -> expense.amount).sum();
    }

    @Override
    @NonNull
    public Iterator<Expense> iterator() {
        return expenseList.iterator();
    }
}

public class ExpenseReport {

    public void printReport(Expenses expenses, Date date) {

        System.out.println("Expenses " + date);

        for (Expense expense : expenses) {
            printSingleExpense(expense);
        }

        System.out.println("Meal expenses: " + expenses.getMealExpenses());
        System.out.println("Total expenses: " + expenses.getTotal());
    }

    public void printSingleExpense(Expense expense) {
        String mealOverExpensesMarker = expense.isOverLimit() ? "X" : " ";
        System.out.println(expense.getName() + "\t" + expense.amount + "\t" + mealOverExpensesMarker);
    }

}
