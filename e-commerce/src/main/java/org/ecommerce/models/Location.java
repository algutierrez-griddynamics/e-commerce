package org.ecommerce.models;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@Entity
@SuperBuilder
@Table(name = "locations")
@NoArgsConstructor
@AttributeOverride(name="id", column=@Column(name="pk_location_id"))
public class Location extends AddressInformation {
    int width_cm;
    int height_cm;
    int depth_cm;
}
