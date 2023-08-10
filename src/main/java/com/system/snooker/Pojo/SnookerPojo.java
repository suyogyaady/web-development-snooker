package com.system.snooker.Pojo;

import com.system.snooker.entity.Snooker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SnookerPojo {
    private Integer fid;
    private String fname;
    private String fcontact;
    private  String fprice;
    private String flocation;
    private MultipartFile image;
    private MultipartFile image1;
    private MultipartFile image2;
    private String Description;


    public SnookerPojo(Snooker snooker) {
        this.fid = snooker.getFut_salId();
        this.fname = snooker.getSnookername();
        this.fcontact= snooker.getSnookercontact();
        this.fprice = snooker.getSnookerprice();
        this.flocation = snooker.getSnookerlocation();
        this.Description = snooker.getDescription();

    }
}