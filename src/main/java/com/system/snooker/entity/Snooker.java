package com.system.snooker.entity;



import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Snooker")

public class Snooker {
    @Id
    @SequenceGenerator(name = "shb_product_seq_gen", sequenceName = "shb_product_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "shb_product_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer fut_salId;

    private String snookername;

    private String snookerprice;

    private String snookercontact;

    private String snookerlocation;

    private String snookerimage;
    private String snookerimage1;
    private String snookerimage2;

    private String Description;

    @Transient
    private String imageBase64;

    @Transient
    private String image1Base64;


    @Transient
    private String image2Base64;

}
