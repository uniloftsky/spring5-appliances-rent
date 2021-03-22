package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.model.Item;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    private final String uploadPath = System.getProperty("user.dir") + "/resources/";

    @Override
    public void setItemImage(Item item, List<MultipartFile> file) throws IOException {
        List<String> files = new ArrayList<>();

        if (file != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
                File uploadItemsDir = new File("resources/items");
                if (!uploadItemsDir.exists()) {
                    uploadItemsDir.mkdir();
                }
            }

            String resultFilename = "";

            for (MultipartFile img : file) {
                resultFilename = "items/" + UUID.randomUUID().toString() + "-" + img.getOriginalFilename();
                files.add(resultFilename);
                img.transferTo(new File(uploadPath + resultFilename));
            }

            item.setImg(files);
        }
    }
}
