package tn.esprit.tpfoyer.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name="image")
@Builder

@Entity

public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Column(name = "image_name")
    private String name;

    @Column(name = "image_type")
    private String type;

    @Lob
    @Column(name = "picbyte", length = 100000)  // Adjust the length as needed
    private byte[] picbyte;

  /*  public Image(String name, String type, byte[] picbyte) {
        this.name = name;
        this.type = type;
        this.picbyte = picbyte;
    }*/
/*
    @OneToOne(mappedBy = "image", fetch = FetchType.LAZY)
    private Universite universite;
*/

}