package tn.esprit.tpfoyer.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Chambre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChambre;

    private Long numeroChambre;

    @Enumerated(EnumType.STRING)
    private TypeChambre typeC;

    private String Description;

    private boolean Etat;

    private Date CreatedAt;

    private Date UpdatedAt;
    @Lob
    @Column(name = "imagebyte", length = 100000)  // Adjust the length as needed
    private byte[] imagebyte;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("chambres")
    private Bloc bloc;


    

}