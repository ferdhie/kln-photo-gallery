package com.kapanlagi.web.photogallery.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ferdhie on 25-12-2015.
 */
@Component
public class FileStorageService {

    static final Logger LOG = LoggerFactory.getLogger(FileStorageService.class);
    static final String[] imageTypes = {"jpg", "gif", "png"};
    String[] subDirs = {"thumb", "photo", "trash"};

    List<String> allPhotos = Collections.synchronizedList(new ArrayList<String>());

    @Value("${app.upload_dir}")
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public String getThumbnailDir() {
        return uploadDir + "/" + subDirs[0];
    }

    public String getImageDir() {
        return uploadDir + "/" + subDirs[1];
    }

    public String getTrashDir() {
        return uploadDir + "/" + subDirs[2];
    }

    @PostConstruct
    public void initialize() throws IOException {
        LOG.info("initializing directory {}", uploadDir);
        File upload = new File(uploadDir);
        if (!upload.exists())
            upload.mkdir();

        uploadDir = upload.getCanonicalPath();

        for(String s: subDirs) {
            File d = new File(uploadDir, s);
            if (!d.exists())
                d.mkdir();
        }

        refresh();
    }

    public List<String> getAllPhotos() {
        return new ArrayList<String>(allPhotos);
    }

    private void refresh() {
        allPhotos.clear();
        allPhotos.addAll( listPhotos() );
    }

    public String formatFileName(String s) {
        return s.replaceAll("[^a-zA-Z0-9_-]+", "_").replaceAll("_+", "_");
    }

    public String savePhoto(BufferedImage image, String fileName, String photoType) throws IOException {
        LOG.info("savePhoto fileName={}, photoType={}", fileName, photoType);

        for(String s: subDirs) {
            if ( s.equalsIgnoreCase(photoType) ) {
                File dir = new File( uploadDir + "/" + photoType );

                int lastDotIndex = fileName.lastIndexOf(".");
                String fileBaseName = fileName.substring(0, lastDotIndex);
                fileBaseName = formatFileName(fileBaseName);
                String imageType = fileName.substring(lastDotIndex+1).toLowerCase();

                File file = new File(dir, fileBaseName + "." + imageType);
                int i=0;
                while(file.exists()) {
                    String newName = fileBaseName + "_" + (++i);
                    file = new File(dir, newName + "." + imageType);
                }

                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ImageIO.write(image, imageType, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();

                if (photoType.equalsIgnoreCase("thumb"))
                    refresh();

                return file.getName();
            }
        }

        throw new IllegalArgumentException("Argument photoType not known");
    }

    public List<String> listPhotos() {
        File dir = new File( getThumbnailDir() );
        String[] files = dir.list(filenameFilter);
        return new ArrayList<String>(Arrays.asList(files));
    }

    public void deletePhoto(String fileName) {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        File file = new File( getThumbnailDir() + "/" + fileName );
        if (file.exists()) {
            //we never actually delete
            file.renameTo(new File( getTrashDir() + "/" + timeStamp + "_thumb_" + fileName ));
        }

        file = new File( getImageDir() + "/" + fileName );
        if (file.exists()) {
            //we never actually delete
            file.renameTo(new File( getTrashDir() + "/" + timeStamp + "_photo_" + fileName ));
        }

        allPhotos.remove( file.getName() );
    }

    private FilenameFilter filenameFilter = new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
            final String n = name.toLowerCase();
            if (n.endsWith(".jpg") || n.endsWith(".png") || n.endsWith(".gif"))
                return true;
            return false;
        }
    };



}
