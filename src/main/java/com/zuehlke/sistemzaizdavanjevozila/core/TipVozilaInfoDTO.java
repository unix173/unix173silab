package com.zuehlke.sistemzaizdavanjevozila.core;

public class TipVozilaInfoDTO {

    private Long itemTypeId;

    private Long quantity;

    private String itemTypeName;

    public TipVozilaInfoDTO(Long itemTypeId, Long quantity, String itemTypeName) {
        this.itemTypeId = itemTypeId;
        this.quantity = quantity;
        this.itemTypeName = itemTypeName;
    }

    public Long getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Long itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }
}
