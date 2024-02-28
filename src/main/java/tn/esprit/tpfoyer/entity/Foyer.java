package tn.esprit.tpfoyer.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Foyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFoyer;
    private String nomFoyer;
    private Long capaciteFoyer;
    private String descriptionFoyer;
    private Long telephoneFoyer;
    @Enumerated(EnumType.STRING)
    private TypeFoyer typeF;

    @JsonIgnore
    @OneToOne(mappedBy = "foyer")

    private Universite universite;
    @JsonIgnore
    @OneToMany(mappedBy="foyer", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("foyer")
    private Set<Bloc> blocs;

}
