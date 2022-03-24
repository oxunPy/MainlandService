package uz.akfa.regionservice.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "districts")
public class District implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "district_name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "region_id", referencedColumnName = "id")
    private Region region;

    public District(String name) {
        this.name = name;
    }
    public District(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public District() {
    }
}
