package com.system.snooker.Service.Impl;


import com.system.snooker.Pojo.SnookerPojo;
import com.system.snooker.Repo.SnookerRepo;
import com.system.snooker.Service.SnookerService;
import com.system.snooker.entity.Snooker;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SnookerServiceImpl implements SnookerService {

    private final SnookerRepo snookerRepo;

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/images/";


    @Override
    public SnookerPojo savesnooker(SnookerPojo snookerPojo) throws IOException {
        Snooker snooker = new Snooker();
        if (snookerPojo.getFid()!= null){
            snooker.setFut_salId(snookerPojo.getFid());
        }
        snooker.setSnookername(snookerPojo.getFname());
        snooker.setSnookerprice(snookerPojo.getFprice());
        snooker.setSnookercontact(snookerPojo.getFcontact());
        snooker.setSnookerlocation(snookerPojo.getFlocation());
        snooker.setDescription(snookerPojo.getDescription());



        if(snookerPojo.getImage1()!=null){
//            System.out.println(UPLOAD_DIRECTORY);
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, snookerPojo.getImage1().getOriginalFilename());
            fileNames.append(snookerPojo.getImage1().getOriginalFilename());
            Files.write(fileNameAndPath, snookerPojo.getImage1().getBytes());

            snooker.setSnookerimage1(snookerPojo.getImage1().getOriginalFilename());
        }
        if(snookerPojo.getImage2()!=null){
//            System.out.println(UPLOAD_DIRECTORY);
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, snookerPojo.getImage2().getOriginalFilename());
            fileNames.append(snookerPojo.getImage2().getOriginalFilename());
            Files.write(fileNameAndPath, snookerPojo.getImage2().getBytes());

            snooker.setSnookerimage2(snookerPojo.getImage2().getOriginalFilename());
        }
        if(snookerPojo.getImage()!=null){
//            System.out.println(UPLOAD_DIRECTORY);
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, snookerPojo.getImage().getOriginalFilename());
            fileNames.append(snookerPojo.getImage().getOriginalFilename());
            Files.write(fileNameAndPath, snookerPojo.getImage().getBytes());

            snooker.setSnookerimage(snookerPojo.getImage().getOriginalFilename());
        }
        snookerRepo.save(snooker);
        return new SnookerPojo(snooker);
    }

    @Override
    public Snooker fetchById(Integer id) {
        Snooker snooker= snookerRepo.findById(id).orElseThrow(()-> new RuntimeException("Couldnot find"));
        snooker = Snooker.builder()
                .fut_salId(snooker.getFut_salId())
                .imageBase64(getImageBase64(snooker.getSnookerimage()))
                .image1Base64(getImageBase64(snooker.getSnookerimage1()))
                .image2Base64(getImageBase64(snooker.getSnookerimage2()))
                .snookername(snooker.getSnookername())
                .snookercontact(snooker.getSnookercontact())
                .snookerprice(snooker.getSnookerprice())
                . snookerlocation(snooker.getSnookerlocation())
                .Description(snooker.getDescription())
                .build();
        return snooker;
    }

    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/images/";
        File file = new File(filePath + fileName);
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return Base64.getEncoder().encodeToString(bytes);
    }

    @Override
    public List<Snooker> fetchAll() {
        return snookerRepo.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        snookerRepo.deleteById(id);
    }

}
