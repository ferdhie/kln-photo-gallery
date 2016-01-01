package com.kapanlagi.web.photogallery.web;

import com.kapanlagi.web.photogallery.service.FileStorageService;
import com.kapanlagi.web.photogallery.service.ImageResizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ferdhie on 16-12-2015.
 */
@Controller
public class GalleryController {
    static final Logger LOG = LoggerFactory.getLogger(GalleryController.class);

    @Autowired
    private ImageResizer imageResizer;

    @Autowired
    private FileStorageService fileStorageService;

    @RequestMapping("/")
    public String welcome(
            @RequestParam(value="upload", required=false) String result,
            Map<String, Object> model) {

        model.put("upload_success", result);
        model.put("files", fileStorageService.getAllPhotos());

        return "index";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file, Map<String,Object> model) throws IOException {

        if (!file.isEmpty()) {
            String name = file.getOriginalFilename().toLowerCase();
            String mimeType = file.getContentType().toLowerCase();
            String imageType = name.substring(name.lastIndexOf(".")+1).toLowerCase();

            //@TODO 1. check if imageType is valid (jpg,png,gif),
            //@TODO 2. check if mimetype is one of image/jpeg, image/gif, image/png
            // if ( ! valid mime or !valid type ) model.put("upload_error", ".....")

            byte[] bytes = file.getBytes();
            BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream( bytes ));

            //@TODO 3. have 2 sizes, 300x300 (square) and 1024 x ?? (proportional larger version)
            BufferedImage squareThumb = imageResizer.resizeImage(originalImage, 300, 300);

            fileStorageService.savePhoto(squareThumb, name, "thumb");

            //success? redirect ke index
            return "redirect:/?upload=ok";

        } else {

            model.put("upload_error", "File is empty");

        }

        return "index";

    }

    @RequestMapping(value = "/allphotos", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,Object> photosAsJson(
            @RequestParam(value="start", required=false) int start,
            @RequestParam(value="limit", required=false) int limit,
            Map<String, Object> model) {

        //@TODO 6. use this method to modify the application to use ajax for loading images dynamically
        //Tips: use JScroll jQuery plugin to to this easier and cooler

        if (limit<0)limit=10;
        Map<String,Object> result = new HashMap<String, Object>();
        List<String> allPhotos = fileStorageService.getAllPhotos();
        result.put("images", new ArrayList<String>(allPhotos.subList(start, limit)));
        result.put("status", "OK");
        return result;
    }

    @RequestMapping(value = "/delete", produces= MediaType.APPLICATION_JSON_VALUE)
    public Object deleteImage(@RequestParam(value = "file", required = true) String file) throws IOException {

        //@TODO 4. implement deletion code, if possible, use FileStorageService.deletePhoto method
        //@TODO 5. use ajax if possible to improve user experience

        //success? redirect ke index
        return "redirect:/?delete=ok";
    }

}
