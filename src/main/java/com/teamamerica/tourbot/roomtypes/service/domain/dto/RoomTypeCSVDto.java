package com.teamamerica.tourbot.roomtypes.service.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author burhan <it@teamamericany.com>
 * @project roomtypes-service
 * @created at 10/25/19
 */
public class RoomTypeCSVDto {
    @JsonProperty("ID")
    private int roomTypeID;
    @JsonProperty("Name")
    private String roomType;

    public int getRoomTypeID() {
        return roomTypeID;
    }

    public void setRoomTypeID(int roomTypeID) {
        this.roomTypeID = roomTypeID;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}
