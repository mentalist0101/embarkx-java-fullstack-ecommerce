package org.paolino.sb2026.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String imageOriginalFileName = file.getOriginalFilename();
        String randomId = UUID.randomUUID().toString();
        String imageUniqueFileName = randomId.concat(imageOriginalFileName.substring(imageOriginalFileName.lastIndexOf('.')));
        String filePath = path + File.separator + imageUniqueFileName;
        File folder = new File(path);
        if (!folder.exists())
            folder.mkdir();
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return imageUniqueFileName;
    }
}
