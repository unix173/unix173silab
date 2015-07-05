package com.zuehlke.sistemzaizdavanjevozila.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Item {

    @Id
    private String id;

    @ManyToOne
    @NotNull
    private ItemType itemType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    @Override
    public String toString() {
        return " Item ID: " + id;
    }
}
