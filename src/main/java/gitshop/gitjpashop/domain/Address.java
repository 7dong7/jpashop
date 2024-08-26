package gitshop.gitjpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {
    } // 퍼블릭에서 프로텍트로 jpa 스펙상 만든것임
    // 변경 불가능하게 만들기 위해 @Setter를 없애고 생성자에서 받고 @Getter만 사용

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
