package com.example.WebDemo.Image;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class GoogleDriveService {

//    @Value("${google.drive.application.name}")
//    private String applicationName;
//
//    @Value("${google.drive.credentials.file.path}")
//    private String credentialsFilePath;
//
//    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance(); // Sử dụng GsonFactory
//    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
//
//    private Drive getDriveService() throws IOException, GeneralSecurityException {
//        // Tạo đối tượng GoogleCredential từ tệp JSON chứa thông tin xác thực
//        GoogleCredential credential = GoogleCredential.fromStream(
//                        getClass().getResourceAsStream(credentialsFilePath))
//                .createScoped(Collections.singleton("https://www.googleapis.com/auth/drive.file"));
//
//        return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
//                .setApplicationName(applicationName)
//                .build();
//    }
//
//
//    public String uploadFileToDrive(byte[] fileBytes, String fileName) throws IOException, GeneralSecurityException {
//        Drive driveService = getDriveService();
//
//        File fileMetadata = new File();
//        fileMetadata.setName(fileName);
//        fileMetadata.setMimeType("image/jpeg"); // Điều chỉnh MIME type nếu cần
//
//        File uploadedFile = driveService.files().create(fileMetadata,
//                        new ByteArrayContent("image/jpeg", fileBytes))
//                .setFields("id")
//                .execute();
//
//        String fileId = uploadedFile.getId();
//        return "https://drive.google.com/uc?export=view&id=" + fileId;
//    }


    @Value("${google.drive.application.name}")
    private String applicationName;

    @Value("${google.drive.credentials.file.path}")
    private String credentialsFilePath;

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance(); // Sử dụng GsonFactory
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    private Drive getDriveService() throws IOException, GeneralSecurityException {
        // Tạo đối tượng GoogleCredential từ tệp JSON chứa thông tin xác thực
        GoogleCredential credential = GoogleCredential.fromStream(
                        getClass().getResourceAsStream(credentialsFilePath))
                .createScoped(Collections.singleton("https://www.googleapis.com/auth/drive.file"));

        return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(applicationName)
                .build();
    }

    public String uploadFileToDrive(byte[] fileBytes, String fileName) throws IOException, GeneralSecurityException {
        Drive driveService = getDriveService();

        File fileMetadata = new File();
        fileMetadata.setName(fileName);
        fileMetadata.setMimeType("image/jpeg"); // Điều chỉnh MIME type nếu cần

        File uploadedFile = driveService.files().create(fileMetadata,
                        new ByteArrayContent("image/jpeg", fileBytes))
                .setFields("id")
                .execute();

        String fileId = uploadedFile.getId();
        return "https://drive.google.com/uc?export=view&id=" + fileId;
    }

    // Phương thức tải tệp từ URL
    public byte[] downloadFileFromUrl(String fileUrl) throws IOException {
        URL url = new URL(fileUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        try (InputStream inputStream = connection.getInputStream()) {
            return IOUtils.toByteArray(inputStream);
        }
    }
}