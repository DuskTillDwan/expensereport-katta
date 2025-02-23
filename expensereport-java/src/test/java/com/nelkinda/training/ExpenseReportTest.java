package com.nelkinda.training;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;
import org.approvaltests.ApprovalUtilities;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.Date;
import java.util.List;


class ExpenseReportTest {
    @Test
    void emptyReport(){
        ByteArrayOutputStream output = new ApprovalUtilities().writeSystemOutToStringBuffer();
        ExpenseReport report = new ExpenseReport();
        report.printReport(Collections.emptyList(), new Date(0));
        Approvals.verify(output);
    }
    @Test
    void bigReport(){
        ByteArrayOutputStream output = new ApprovalUtilities().writeSystemOutToStringBuffer();
        ExpenseReport report = new ExpenseReport();
        List<Expense> expenses = List.of(
                createExpense(ExpenseType.DINNER, 5000),
                createExpense(ExpenseType.DINNER, 5001),
                createExpense(ExpenseType.BREAKFAST, 5000),
                createExpense(ExpenseType.BREAKFAST, 5001),
                createExpense(ExpenseType.CAR_RENTAL, 50),
                createExpense(ExpenseType.CAR_RENTAL, Integer.MAX_VALUE)
        );
        report.printReport(expenses, new Date(0));
        Approvals.verify(output);
    }

    private static Expense createExpense(ExpenseType expenseType, int amount) {
        Expense expense = new Expense();
        expense.type = expenseType;
        expense.amount = amount;
        return expense;
    }
/**
 * DINNER 5000,
 * DINNER 5001,
 * BREAKFAST 1000,
 * BREAKFAST 1001,
 * CAR_RENTAL 60
 * CAR_RENTAL Integer.MAX_VALUE, as there is no limit
 */
}