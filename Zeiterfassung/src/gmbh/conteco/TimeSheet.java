package gmbh.conteco;
/*
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
*/

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TimeSheet {
    private Employee employee;
    private List<TimeSheetEntry> entries;

    class Headers {
        public static final String employeeName = "Employee";
    }

    public void display() {
        //   +----------+
        //   | Employee |
        //   +----------+
        List<String> headers = Arrays.asList(Headers.employeeName);
        displayHeader(headers);
    }

    private void displayHeader(Collection<String> listOfTitles) {
        listOfTitles.stream().forEach(title -> {
            drawLine(title.length()+4 /* 2 spaces + 2 delimiters */);
            System.out.print("| " + title + " |");
            drawLine(title.length()+4 /* 2 spaces + 2 delimiters */);
        });
    }

    private void drawLine(int length) {
        if (length < 3)
            return;

        // Start of line
        System.out.print("+");

        // Draw the middle part of the line
        System.out.print("-".repeat(length-2));

        // End of line
        System.out.print("+");
    }
}
