package com.saqaya.user.management.common.exception;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class SystemException extends Exception implements Serializable {

    private static final long serialVersionUID = -7686738501121765798L;
    @NotNull
    private ErrorResponse error;
}
