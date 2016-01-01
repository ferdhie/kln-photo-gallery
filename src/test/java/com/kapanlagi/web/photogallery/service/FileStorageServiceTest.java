package com.kapanlagi.web.photogallery.service;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by ferdhie on 25-12-2015.
 */
public class FileStorageServiceTest {

    @Test
    public void testName() throws Exception {

        String file = "indonesia.merdeka.jpg";
        String base = file.substring(0,file.lastIndexOf("."));
        System.err.println(base);

    }
}