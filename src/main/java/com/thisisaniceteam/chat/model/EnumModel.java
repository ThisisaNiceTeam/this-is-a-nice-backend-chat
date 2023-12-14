package com.thisisaniceteam.chat.model;

import jakarta.validation.constraints.NotNull;

public interface EnumModel {
    @NotNull
    String getKey();

    @NotNull
    String getDescription();
}
