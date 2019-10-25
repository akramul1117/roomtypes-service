package com.teamamerica.tourbot.roomtypes.service.domain.rowMapper;

import com.teamamerica.tourbot.roomtypes.service.domain.dto.RoomTypeCSVDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author burhan <it@teamamericany.com>
 * @project roomtypes-service
 * @created at 10/25/19
 */
public class RoomTypeCSVDtoRowMapper implements RowMapper<RoomTypeCSVDto> {
    @Override
    public RoomTypeCSVDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        RoomTypeCSVDto roomTypeCSVDto = new RoomTypeCSVDto();
        roomTypeCSVDto.setRoomTypeID(rs.getInt("RoomTypeId"));
        roomTypeCSVDto.setRoomType(rs.getString("RoomType"));
        return roomTypeCSVDto;
    }
}
