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

package com.teamamerica.tourbot.roomtypes.service.domain.dto;/*
 *
 * --------------------------------------------------------------------------
 *   @project mealPlanApi
 *   @author  akadir <it@teamamericany.com>
 *   @created at ২/১০/১৯
 * --------------------------------------------------------------------------
 *
 */

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoomTypeDto {

    @JsonProperty("value")
    private int roomTypeId;
    @JsonProperty("label")
    private String roomType;

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

    @Override
    public String toString() {
        return "RoomTypeDto{" +
                "roomTypeId=" + roomTypeId +
                ", roomType='" + roomType + '\'' +
                '}';
    }
}
