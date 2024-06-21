package hellojpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ADDRESS")
public class AddressEntity { // 엔티티

    @Id
    @GeneratedValue
    private Long id;

    private Address address;  // 값 타입

    public AddressEntity(Address address) {
        this.address = address;
    }

    public AddressEntity(){}

    public AddressEntity(String city, String street, String zipcode) {
        this.address = new Address(city, street, zipcode);
    }
}
