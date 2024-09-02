package gitshop.gitjpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;
    private int stockQuantity;

    private LocalDateTime registerDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    protected Item() {}

    public Item(String name, int price, int stockQuantity, Member member) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.registerDate = LocalDateTime.now();
        this.member = member;
    }

}
