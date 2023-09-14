package com.kimia_technologies.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * @author Desire Junior NDJOG
 * @version 1.0
 * @project ifiranz_backend
 * @since 04/09/2023
 */

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class HttpResponse {
    protected String timeStamp;
    protected int statusCode;
    protected HttpStatus httpStatus;
    protected String message;
    protected Map<?,?> datas;
}
