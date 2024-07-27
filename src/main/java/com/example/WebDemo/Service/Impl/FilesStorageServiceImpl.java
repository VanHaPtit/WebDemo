//package com.example.WebDemo.Service.Impl;
//
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.nio.file.FileAlreadyExistsException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.stream.Stream;
//
//import com.example.WebDemo.Service.FilesStorageService;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
//import org.springframework.stereotype.Service;
//import org.springframework.util.FileSystemUtils;
//import org.springframework.web.multipart.MultipartFile;
//
//@Service
//public class FilesStorageServiceImpl implements FilesStorageService {
//    private final Path root = Paths.get("./uploads");
//
//    @Override
//    public void init() {
//        try {
//            Files.createDirectories(root);
//        } catch (IOException e) {
//            throw new RuntimeException("Could not initialize folder for upload!");
//        }
//    }
//
//    @Override
//    public String save(MultipartFile file) {
//        try {
//            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
//        } catch (Exception e) {
//            if (e instanceof FileAlreadyExistsException) {
//                throw new RuntimeException("A file of that name already exists.");
//            }
//
//            throw new RuntimeException(e.getMessage());
//        }
//        return null;
//    }
//
//    @Override
//    public Resource load(String filename) {
//        try {
//            Path file = root.resolve(filename);
//            Resource resource = new UrlResource(file.toUri());
//
//            if (resource.exists() || resource.isReadable()) {
//                return resource;
//            } else {
//                throw new RuntimeException("Could not read the file!");
//            }
//        } catch (MalformedURLException e) {
//            throw new RuntimeException("Error: " + e.getMessage());
//        }
//    }
//
//    @Override
//    public boolean delete(String filename) {
//        try {
//            Path file = root.resolve(filename);
//            return Files.deleteIfExists(file);
//        } catch (IOException e) {
//            throw new RuntimeException("Error: " + e.getMessage());
//        }
//    }
//
//    @Override
//    public void deleteAll() {
//        FileSystemUtils.deleteRecursively(root.toFile());
//    }
//
//    @Override
//    public Stream<Path> loadAll() {
//        try {
//            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
//        } catch (IOException e) {
//            throw new RuntimeException("Could not load the files!");
//        }
//    }
//}



package com.example.WebDemo.Service.Impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.example.WebDemo.Service.FilesStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

    private final Path root = Paths.get("./uploads");

    @Override
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!", e);
        }
    }

    @Override
    public String save(MultipartFile file) {
        try {
            // Generate the path where the file will be stored
            Path destinationFile = this.root.resolve(file.getOriginalFilename());
            // Check if file already exists
            if (Files.exists(destinationFile)) {
                throw new FileAlreadyExistsException("A file with the name " + file.getOriginalFilename() + " already exists.");
            }
            // Copy the file to the destination path
            Files.copy(file.getInputStream(), destinationFile);
            // Return the name of the saved file
            return file.getOriginalFilename();
        } catch (FileAlreadyExistsException e) {
            throw new RuntimeException("A file with the name " + file.getOriginalFilename() + " already exists.", e);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(String filename) {
        try {
            Path file = root.resolve(filename);
            return Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        try {
            FileSystemUtils.deleteRecursively(root.toFile());
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete all files.", e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1)
                    .filter(path -> !path.equals(this.root))
                    .map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load files.", e);
        }
    }
}
