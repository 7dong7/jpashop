package gitshop.gitjpashop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.Date;

@Entity
@Getter
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String member_id;
    private String name;

    private int price;
    private int stockQuantity;
    private Date registerDate;


}
