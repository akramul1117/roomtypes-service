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

package com.teamamerica.tourbot.roomtypes.service.domain.rowMapper;/*
 *
 * --------------------------------------------------------------------------
 *   @project romtypes-service
 *   @author  akadir <it@teamamericany.com>
 *   @created at ৭/১০/১৯
 * --------------------------------------------------------------------------
 *
 */

import com.teamamerica.tourbot.roomtypes.service.domain.beans.RoomType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class RoomTypeRowMapper implements RowMapper<RoomType> {

    @Override
    public RoomType mapRow(ResultSet rs, int rowNum) throws SQLException {
        RoomType roomType = new RoomType();

        roomType.setRoomTypeId(rs.getInt("RoomTypeID"));
        roomType.setRoomType(rs.getString("RoomType"));
        roomType.setDateUpdated(rs.getString("DateUpdated"));
        roomType.setDeleted(rs.getBoolean("deleted"));
        roomType.setHiltonRoomTypeId(rs.getInt("HiltonRoomTypeId"));
        roomType.setHiltonHotelCodes(rs.getInt("HiltonHotelCodes"));
        roomType.setExternalRoomTypeId(rs.getInt("externalRoomTypeId"));

        return roomType;
    }
}
