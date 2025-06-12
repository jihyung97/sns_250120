package com.sns.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
public class FileManagerService {

    public final static String FILE_UPLOAD_PATH = "C:\\Users\\jihyu\\web\\sns_250120\\sns_image/";





    public String uploadFile(MultipartFile file, String userLoginId) {
        //문서 이름 만들기 : 문서이름_현재시간(millisecond)/이미지이름.png
        String fileName = userLoginId + "_" + System.currentTimeMillis();
        String filePath = FILE_UPLOAD_PATH  + fileName;
        File directory = new File(filePath);
        if (directory.mkdir() == false) {
            return null;
        }
        try{
            byte[] bytes = file.getBytes();
            Path path = Paths.get(filePath + "/" + file.getOriginalFilename());
            Files.write(path, bytes); // path로 가서 bytes형식으로 업로드 한다
        }catch(IOException e) {
            e.printStackTrace();
            return null;
        }
        return "/images" + "/" + fileName + "/" + file.getOriginalFilename();

    }

    public void deleteFile(String imagePath){
        Path path = Paths.get(FILE_UPLOAD_PATH + imagePath.replace("/images/",""));

        if(Files.exists(path)){
            try{
                Files.delete(path);
            }catch(IOException e){
                log.info("[파일 image 삭제] imagePath:{}",imagePath);
                return;

            }

            //이미지의 폴더 삭제
            path = path.getParent();

            if(Files.exists(path)){
                try{
                    Files.delete(path);
                } catch(IOException e){
                    log.info("path:{}",path);
                }
            }


        }
    }
}

