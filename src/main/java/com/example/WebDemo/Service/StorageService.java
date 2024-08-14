package com.example.WebDemo.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Service
public class StorageService {

    private final Path rootLocation = Paths.get("uploads");

    public String save(MultipartFile file) throws IOException {
        // Tạo thư mục nếu chưa tồn tại
        Files.createDirectories(rootLocation);

        // Đặt tên tệp và đường dẫn lưu trữ
        Path destinationFile = rootLocation.resolve(Paths.get(file.getOriginalFilename())).normalize().toAbsolutePath();

        // Kiểm tra nếu tệp đã tồn tại
        if (Files.exists(destinationFile)) {
            throw new IOException("File already exists: " + file.getOriginalFilename());
        }

        // Lưu tệp vào hệ thống tập tin
        try (var inputStream = file.getInputStream()) {
            Files.copy(inputStream, destinationFile);
        }

        // Trả về đường dẫn đến hình ảnh
        return destinationFile.toString();
    }
}
