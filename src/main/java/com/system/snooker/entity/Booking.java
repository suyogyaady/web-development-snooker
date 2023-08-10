package com.system.snooker.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @SequenceGenerator(name = "shb_product_seq_gen", sequenceName = "shb_product_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "shb_product_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer bookId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "u_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_user_id"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "f_id", referencedColumnName = "fut_salId",
            foreignKey = @ForeignKey(name = "fk_snooker_id"))
    private Snooker snooker;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;

    private String starting;
    private String email;

}
