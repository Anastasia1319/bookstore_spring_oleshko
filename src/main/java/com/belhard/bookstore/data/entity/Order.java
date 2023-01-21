package com.belhard.bookstore.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST,
                        CascadeType.MERGE,
                        CascadeType.REFRESH})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private List<OrderItem> items;

    @Column(name = "status_id", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    public enum Status {
        PENDING, PAID, DELIVERED, CANCELED
    }
}
