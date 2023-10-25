package org.example.models;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MobileBooking {
    @NotNull
    private Integer deviceId;
    @NotNull
    private int employeeId;
    @NotNull
    private Action action;

    public enum Action {
        BOOK,
        RETURN
    }
}

