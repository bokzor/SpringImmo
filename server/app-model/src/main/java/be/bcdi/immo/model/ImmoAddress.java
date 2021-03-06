package be.bcdi.immo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name = "ImmoAddress")
@Table(name = "immo_address")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class ImmoAddress {
    Double latitude;
    Double longitude;
    String country;

    @NotNull
    String postalCode;

    String locality;
    Integer floor;
    String street;
    String number;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "address")
    private ImmoProperty immoProperty;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Date updatedAt;

}
