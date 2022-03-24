package uz.akfa.regionservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Table(name = "countries")
public class Country implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mainland_id", referencedColumnName = "id")
    private Mainland mainland;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country", cascade = CascadeType.ALL)
    private Set<Region> regionSet;

    public Country(String name) {
        this.name = name;
    }
    public Country(String name, Mainland mainland) {
        this.mainland = mainland;
        this.name = name;
    }
    public Country(){

    }
}
