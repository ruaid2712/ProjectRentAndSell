package com.project.RentSellBuyProject.Services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CloudinaryImageService {
    @Autowired
    Cloudinary cloudinary;

    public List<String> uploadImages(MultipartFile[] multipartFiles) throws IOException {
        List<String> urls = new ArrayList<>();
        for(MultipartFile files : multipartFiles) {
            Map upload = cloudinary.uploader().upload(files.getBytes(), ObjectUtils.emptyMap());
            urls.add(upload.get("secure_url").toString());
        }
        return urls;
    }
}
