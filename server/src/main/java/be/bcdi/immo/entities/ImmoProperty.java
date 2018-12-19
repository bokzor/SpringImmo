package be.bcdi.immo.entities;

import be.bcdi.immo.enums.PebEnum;
import be.bcdi.immo.enums.PropertyTypeEnum;
import be.bcdi.immo.enums.SourceEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Entity(name = "ImmoProperty")
@Table(name = "immo_property")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Audited
@IdClass(ImmoProperty.SourceId.class)
public class ImmoProperty {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Id
    SourceEnum source;

    @NotNull
    @Id
    String sourceId;

    Integer bedroomCount;
    Integer landSurface;
    Integer netHabitableSurface;


    Integer sellingPrice;
    Integer rentingPrice;

    @Enumerated(EnumType.STRING)
    PropertyTypeEnum propertyType;

    @Enumerated(EnumType.STRING)
    PebEnum peb;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @NotAudited
    private ImmoAddress address;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    @NotAudited
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    @NotAudited
    private Date updatedAt;

    public static class SourceId implements Serializable {

        private String sourceId;
        private SourceEnum source;

        public SourceId() {}

        public SourceId(String sourceId, SourceEnum source) {
            this.sourceId = sourceId;
            this.source = source;
        }

        @Override
        public boolean equals(Object o) {

            if (o == this) {
                return true;
            }
            if (!(o instanceof ImmoProperty)) {
                return false;
            }
            ImmoProperty property = (ImmoProperty) o;
            return Objects.equals(sourceId, property.getSourceId()) &&
                    Objects.equals(source, property.getSource());
        }

        @Override
        public int hashCode() {
            return Objects.hash(sourceId, source);
        }
    }

}
