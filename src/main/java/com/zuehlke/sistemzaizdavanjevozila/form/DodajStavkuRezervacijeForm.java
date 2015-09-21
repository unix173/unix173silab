package com.zuehlke.sistemzaizdavanjevozila.form;

import javax.validation.constraints.AssertTrue;
import java.util.Date;

public class DodajStavkuRezervacijeForm {

    private Long itemTypeId;

    private String itemTypeName;

//    @NotNull
    private Integer desiredQuantity;

//    @NotNull
    private Integer realQuantity;

    private Date reservationStartDate;

    private Date reservationEndDate;

    public Date getReservationStartDate() {
        return reservationStartDate;
    }

    public void setReservationStartDate(Date reservationStartDate) {
        this.reservationStartDate = reservationStartDate;
    }

    public Date getReservationEndDate() {
        return reservationEndDate;
    }

    public void setReservationEndDate(Date reservationEndDate) {
        this.reservationEndDate = reservationEndDate;
    }

    public Long getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Long itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public Integer getDesiredQuantity() {
        return desiredQuantity;
    }

    public void setDesiredQuantity(Integer desiredQuantity) {
        this.desiredQuantity = desiredQuantity;
    }

    public Integer getRealQuantity() {
        return realQuantity;
    }

    public void setRealQuantity(Integer realQuantity) {
        this.realQuantity = realQuantity;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    @AssertTrue(message = "Desired quantity is not available")
    private boolean isValidQuantity() {
        if((desiredQuantity == null) && (realQuantity == null)) return true;
        else if((desiredQuantity == null) || (realQuantity == null)) return false;
        else if((desiredQuantity < 1) || (realQuantity < 1)) return false;
        else return this.desiredQuantity <= this.realQuantity;
    }


    @AssertTrue(message = "Date range is not valid")
    private boolean isValidDateRange() {
        if(reservationStartDate == null || reservationEndDate == null) return false;
        return this.reservationStartDate.before(this.reservationEndDate);
    }



}
