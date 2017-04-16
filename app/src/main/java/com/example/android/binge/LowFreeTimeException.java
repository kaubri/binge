package com.example.android.binge;

/**
 * Thrown when there is not enough free time to watch the desired amount of shows.
 */

public class LowFreeTimeException extends Exception {
    public LowFreeTimeException(String detailMessage) {
        super(detailMessage);
    }
}
