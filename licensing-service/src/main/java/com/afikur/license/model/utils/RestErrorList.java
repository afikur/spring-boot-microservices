package com.afikur.license.model.utils;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

@Getter
@Setter
public class RestErrorList {

    private HttpStatus status;
    private List<ErrorMessage> errors = new ArrayList<>();

    public RestErrorList(int status, ErrorMessage... errors) {
        super();
        this.status = HttpStatus.valueOf(status);
        this.errors.addAll(asList(errors));
    }

    public RestErrorList(HttpStatus status, ErrorMessage... errors) {
        this(status.value(), errors);
    }
}
