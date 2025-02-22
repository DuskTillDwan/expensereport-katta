package com.nelkinda.training;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;
import org.approvaltests.ApprovalUtilities;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.Date;


class ExpenseReportTest {
    @Test
    void emptyReport(){
        ByteArrayOutputStream output = new ApprovalUtilities().writeSystemOutToStringBuffer();
        ExpenseReport report = new ExpenseReport();
        report.printReport(Collections.emptyList(), new Date(0));
        Approvals.verify(output);
    }

}