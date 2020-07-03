package com.company;

// In java 7
// Konstante Variablen
// Abstrakte Methoden

// In Java 8
// Default Methoden
// Static Methoden

// In Java 9
// Private methods (JEP 213: Milling Project Coin)
// Private static methods (JEP 213: Milling Project Coin)

// Credit card information
public interface Card {
    String MONGO_DB_DATASTORE = "my Datastore ...";

    default void logInfo(String message) {
        log(message, "INFO");
    }

    default void logError(String message) {
        log(message, "ERROR");
    }

    private void log (String message, String logLevel) {
        // code goes here ...
    }

    private long createCardID() {
        return 0L;
    }

    private static void displayCardDetails() {

    }
}