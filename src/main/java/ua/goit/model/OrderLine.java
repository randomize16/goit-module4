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
    private Long orderId;
    private Long itemId;
    private Integer itemCount;
    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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
