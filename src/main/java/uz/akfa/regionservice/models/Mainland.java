package uz.akfa.regionservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Table(name = "mainlands")
public class Mainland implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mainland_name", unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "mainland", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Country> countrySet;

    public Mainland(String name) {
        this.name = name;
    }

    public Mainland() {
    }

    public Mainland(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
