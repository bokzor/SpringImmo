package be.bcdi.immo.entities;

import be.bcdi.immo.enums.PebEnum;
import be.bcdi.immo.enums.PropertyTypeEnum;
import be.bcdi.immo.enums.SourceEnum;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Optional;

@Entity
@Table(name = "immo_property")
@Data
public class ImmoProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    SourceEnum source;

    @NotNull
    String sourceId;

    Integer bedroomCount;

    Integer landSurface;

    Integer netHabitableSurface;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private ImmoAddress address;

    Integer sellingPrice;

    Integer rentingPrice;

    @Enumerated(EnumType.STRING)
    PropertyTypeEnum propertyType;

    @Enumerated(EnumType.STRING)
    Optional<PebEnum> peb;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Date updatedAt;

}