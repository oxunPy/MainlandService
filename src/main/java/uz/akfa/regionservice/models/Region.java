package uz.akfa.regionservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "regions")
@Data
public class Region implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "region_name")
    private String name;

    @Column(name = "region_type")
    @Enumerated(value = EnumType.STRING)
    private RegionType regionType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    @JsonIgnore
    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<District> districtSet;

    public Region(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public Region() {
    }

    public Region(String name) {
        this.name = name;
    }
}
