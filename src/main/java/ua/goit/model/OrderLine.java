package ua.goit.model;

import javax.persistence.*;

@Entity
@Table(name = "order_lines")
@NamedQueries({
        @NamedQuery(name = "lineByOrderId", query = "from OrderLine where orderId = :orderId")
})
public class OrderLine {
    @Id
    @GeneratedValue(generator = "order_lines_id_seq")
    private Long id;

    @Column(name = "item_count")
    private Integer itemCount;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="order_id")
    private Order order;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "item_id")
    private Item item;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
