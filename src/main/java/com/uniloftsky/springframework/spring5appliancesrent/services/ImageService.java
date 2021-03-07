package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.model.Item;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    void setItemImage(Item item, MultipartFile file) throws IOException;

}
