package tn.esprit.tpfoyer.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bloc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBloc;

    private String nomBloc;

    private Long capaciteBloc;

    @ManyToOne()
    @JsonIgnoreProperties("blocs")
    private Foyer foyer;


    @OneToMany(mappedBy="bloc",cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("bloc")
    private Set<Chambre> chambres;
}
