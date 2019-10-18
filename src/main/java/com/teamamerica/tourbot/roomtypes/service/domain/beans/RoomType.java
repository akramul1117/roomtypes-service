/*
 *
 *  * --------------------------------------------------------------------------
 *  *   Copyright (C) 1997 - 2019TeamAmerica
 *  * --------------------------------------------------------------------------
 *  *
 *  * NOTICE:  All information contained herein is, and remains the property of TeamAmeirca and its suppliers, if any.
 *  * The intellectual and technical concepts contained herein are proprietary to TeamAmeirca and its suppliers and are protected by trade secret or copyright law.
 *  * Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained from TeamAmeirca.
 *  *
 *  *
 *
 */

package com.teamamerica.tourbot.roomtypes.service.domain.beans;/*
 *
 * --------------------------------------------------------------------------
 *   @project romtypes-service
 *   @author  akadir <it@teamamericany.com>
 *   @created at ৭/১০/১৯
 * --------------------------------------------------------------------------
 *
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RoomType {

    @JsonProperty("RoomTypeId")
    private int roomTypeId;
    @NotNull
    @JsonProperty("RoomType")
    @Size(min = 4, max = 250, message = "MealPlan should be between 4 and 250 characters!!")
    private String roomType;
    @JsonProperty("dateUpdated")
    private String dateUpdated;
    @JsonProperty("hiltonRoomTypeId")
    @Value("")
    private String hiltonRoomTypeId;
    @JsonProperty("hiltonHotelCodes")
    @Value("")
    private String hiltonHotelCodes;
    @JsonProperty("externalRoomTypeId")
    @Value("")
    private String externalRoomTypeId;
    @Value("false")
    @JsonProperty("deleted")
    private boolean deleted;

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getHiltonRoomTypeId() {
        return hiltonRoomTypeId;
    }

    public void setHiltonRoomTypeId(String hiltonRoomTypeId) {
        this.hiltonRoomTypeId = hiltonRoomTypeId;
    }

    public String getHiltonHotelCodes() {
        return hiltonHotelCodes;
    }

    public void setHiltonHotelCodes(String hiltonHotelCodes) {
        this.hiltonHotelCodes = hiltonHotelCodes;
    }

    public String getExternalRoomTypeId() {
        return externalRoomTypeId;
    }

    public void setExternalRoomTypeId(String externalRoomTypeId) {
        this.externalRoomTypeId = externalRoomTypeId;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "roomTypeId=" + roomTypeId +
                ", RoomType='" + roomType + '\'' +
                ", dateUpdated='" + dateUpdated + '\'' +
                ", hiltonRoomTypeId=" + hiltonRoomTypeId +
                ", hiltonHotelCodes=" + hiltonHotelCodes +
                ", externalRoomTypeId=" + externalRoomTypeId +
                ", deleted=" + deleted +
                '}';
    }
}
