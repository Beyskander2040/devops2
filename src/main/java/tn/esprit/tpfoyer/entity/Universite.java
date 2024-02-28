package tn.esprit.tpfoyer.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@CrossOrigin(origins = "*")
public class Universite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUniversite;

    private String nomUniversite;

    private String adresse;

    private String ville ;
    private String telephone ;
    @JsonIgnore
    @OneToOne
    private Foyer foyer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="universite")
    @JsonIgnore
    private List<Etudiant> etudiants;
}