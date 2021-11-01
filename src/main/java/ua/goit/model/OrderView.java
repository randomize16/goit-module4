package ua.goit.model;

import java.util.ArrayList;
import java.util.List;

public class OrderView {
    private Integer number;
    private String description;
    private String userName;

    private List<OrderLineView> lines = new ArrayList<>();

    public List<OrderLineView> getLines() {
        return lines;
    }

    public void setLines(List<OrderLineView> lines) {
        this.lines = lines;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
