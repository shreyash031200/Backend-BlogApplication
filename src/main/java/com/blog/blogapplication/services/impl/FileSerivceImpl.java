package com.blog.blogapplication.services.impl;

import com.blog.blogapplication.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileSerivceImpl implements FileService {
    @Override
    public String uploadImages(String path, MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        //abc.png

        // generating random file name
        String ranndomID = UUID.randomUUID().toString();
        String fileName1 = ranndomID.concat(name.substring(name.lastIndexOf(".")));

        //fullPath
        String filepath = path + File.separator + fileName1;

        // create Folder if not created
        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }

        // file Copy
        Files.copy(file.getInputStream(), Paths.get(filepath));
        System.gc();
         return  fileName1;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream is = new FileInputStream(fileName);

        return is;
    }
}
