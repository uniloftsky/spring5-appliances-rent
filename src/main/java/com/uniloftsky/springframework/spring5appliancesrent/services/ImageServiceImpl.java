package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.model.Item;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    private final String uploadPath = System.getProperty("user.dir") + "/resources/";

    @Override
    public void setItemImage(Item item, MultipartFile file) throws IOException {
        if (file != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
                File uploadItemsDir = new File("resources/items");
                if (!uploadItemsDir.exists()) {
                    uploadItemsDir.mkdir();
                }
            }

            String uuidFile = UUID.randomUUID().toString();

            String resultFilename = "items/" + uuidFile + "-" + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + resultFilename));

            item.setImg(resultFilename);
        }
    }
}
