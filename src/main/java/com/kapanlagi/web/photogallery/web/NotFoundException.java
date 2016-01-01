package com.kapanlagi.web.photogallery.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

/**
 * Created by ferdhie on 24-12-2015.
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends IOException {

    public NotFoundException(String message) {
        super(message);
    }
}
