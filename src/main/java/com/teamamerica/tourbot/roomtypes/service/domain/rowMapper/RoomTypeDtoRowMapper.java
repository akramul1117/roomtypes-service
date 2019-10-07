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
 *   @project mealPlanApi
 *   @author  akadir <it@teamamericany.com>
 *   @created at ২/১০/১৯
 * --------------------------------------------------------------------------
 *
 */


import com.teamamerica.tourbot.roomtypes.service.domain.dto.RoomTypeDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomTypeDtoRowMapper implements RowMapper<RoomTypeDto> {
    @Override
    public RoomTypeDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        RoomTypeDto roomTypeDto = new RoomTypeDto();

        roomTypeDto.setRoomTypeId(rs.getInt("RoomTypeID"));
        roomTypeDto.setRoomType(rs.getString("RoomType"));

        return roomTypeDto;
    }
}
