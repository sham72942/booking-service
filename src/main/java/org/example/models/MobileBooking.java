package org.example.models;

import lombok.Data;

@Data
public class MobileBooking {
    private int deviceId;
    private int employeeId;
    private Action action;

    public enum Action {
        BOOK,
        RETURN
    }
}

