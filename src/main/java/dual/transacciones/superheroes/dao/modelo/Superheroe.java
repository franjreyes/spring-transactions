package dual.transacciones.superheroes.dao.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "superheroes")
@Data
@EqualsAndHashCode(of = "id")
public class Superheroe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255, nullable = false)
    private String nombre;

    @Column(length = 255, nullable = false)
    private String alterego;

    private String imagen;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "superheroe_debilidad",
            joinColumns = {@JoinColumn(name = "superheroe_id")},
            inverseJoinColumns = {@JoinColumn(name = "debilidad_id")})
    private Set<Debilidad> debilidades;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "superheroe_superpoder",
            joinColumns = {@JoinColumn(name = "superheroe_id")},
            inverseJoinColumns = {@JoinColumn(name = "superpoder_id")})
    private Set<Superpoder> superpoderes;

    public Superheroe(Integer id, String nombre, String alterego, String imagen) {
        this(id, nombre, alterego, imagen, null, null);
    }
}