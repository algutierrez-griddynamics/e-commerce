package org.ecommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "locations")
public class Location extends AddressInformation {
    int width_cm;
    int height_cm;
    int depth_cm;
}
