package ui;

import model.Event;
import model.EventLog;

// A log printer that prints to the console
public class LogPrinter {

    // EFFECTS: prints out all the logs as a string to the console
    public void printLog(EventLog e) {
        for (Event event: e) {
            System.out.println(event.toString());
        }
    }
}
