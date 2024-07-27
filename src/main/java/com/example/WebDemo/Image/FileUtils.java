package com.example.WebDemo.Image;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FileUtils {

    public static byte[] downloadFileFromUrl(String fileUrl) throws IOException {
        try (InputStream inputStream = new URL(fileUrl).openStream()) {
            return IOUtils.toByteArray(inputStream);
        }
    }
}
