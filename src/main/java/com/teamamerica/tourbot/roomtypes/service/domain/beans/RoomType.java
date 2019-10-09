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

import javax.validation.constraints.Size;

public class RoomType {

    @JsonProperty("roomTypeId")
    private int roomTypeId;
    @JsonProperty("roomType")
    @Size(min = 4, message = "MealPlan should have atleast 4  characters!!")
    private String RoomType;
    @JsonProperty("dateUpdated")
    private String dateUpdated;
    @JsonProperty("hiltonRoomTypeId")
    private String hiltonRoomTypeId;
    @JsonProperty("hiltonHotelCodes")
    private String hiltonHotelCodes;
    @JsonProperty("externalRoomTypeId")
    private String externalRoomTypeId;
    @JsonProperty("deleted")
    private boolean deleted;

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomType() {
        return RoomType;
    }

    public void setRoomType(String roomType) {
        RoomType = roomType;
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
                ", RoomType='" + RoomType + '\'' +
                ", dateUpdated='" + dateUpdated + '\'' +
                ", hiltonRoomTypeId=" + hiltonRoomTypeId +
                ", hiltonHotelCodes=" + hiltonHotelCodes +
                ", externalRoomTypeId=" + externalRoomTypeId +
                ", deleted=" + deleted +
                '}';
    }
}
