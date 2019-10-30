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

package com.teamamerica.tourbot.roomtypes.service.repository;/*
 *
 * --------------------------------------------------------------------------
 *   @project romtypes-service
 *   @author  akadir <it@teamamericany.com>
 *   @created at ৭/১০/১৯
 * --------------------------------------------------------------------------
 *
 */

import com.teamamerica.tourbot.roomtypes.service.domain.beans.RoomType;
import com.teamamerica.tourbot.roomtypes.service.domain.dto.RoomTypeCSVDto;
import com.teamamerica.tourbot.roomtypes.service.domain.dto.RoomTypeDto;
import com.teamamerica.tourbot.roomtypes.service.domain.rowMapper.RoomTypeCSVDtoRowMapper;
import com.teamamerica.tourbot.roomtypes.service.domain.rowMapper.RoomTypeDtoRowMapper;
import com.teamamerica.tourbot.roomtypes.service.domain.rowMapper.RoomTypeRowMapper;
import com.teamamerica.tourbot.roomtypes.service.logger.Loggable;
import com.teamamerica.tourbot.roomtypes.service.utils.RoomTypeUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class RoomTypesRepository {

    @Loggable
    private Logger logger;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    private static  final String ROOM_TYPE_BASE_QUERY="SELECT RoomTypeID,RoomType,DateUpdated,deleted,HiltonRoomTypeId,HiltonHotelCodes,externalRoomTypeId FROM tblRoomTypes ";
    private static  final String ROOM_TYPE_LIST_QUERY="SELECT RoomTypeID,RoomType FROM tblRoomTypes";
    private static final String ROOM_TYPE_SELECTED_BY_ID = " WHERE RoomTypeID= :roomTypeID";

    private static final String ROW_LIMIT_PART = " LIMIT :limit OFFSET :offset";

    private static final String ORDER_BY_PART = " ORDER BY RoomTypeID ASC";

    private static final String ROOM_TYPE_BASE_INSERT_QUERY = "INSERT INTO tblRoomTypes (RoomType, DateUpdated, deleted,HiltonRoomTypeId,HiltonHotelCodes,externalRoomTypeId)" +
            " VALUES (:roomType,:currentDate,:deleted,:hiltonRoomTypeId,:hiltonHotelCodes,:externalRoomTypeId)";

    private static final String ROOM_TYPE_DELETE_QUERY ="DELETE FROM tblRoomTypes";
    private static final String ROOM_TYPE_BASE_UPDATE_QUERY ="UPDATE tblRoomTypes SET RoomType=:roomType, DateUpdated=:currentDate, deleted=:deleted, HiltonRoomTypeId=:hiltonRoomTypeId,HiltonHotelCodes=:hiltonHotelCodes,externalRoomTypeId=:externalRoomTypeId";

    private static final String ROOM_TYPE_COUNT_QUERY="SELECT COUNT('RoomTypeID') FROM tblRoomTypes ";

    private static final String ROOM_TYPE_WHERE_LIKE_QUERY="WHERE RoomType LIKE :roomType ";

    private static final String ROOM_TYPE_WHERE_EXACT_MATCH_QUERY="WHERE RoomType =:roomType ";

    private static final String ROOM_TYPE_AND_ID_QUERY="AND RoomTypeID<> :roomTypeID ";

    private static final String ROOM_TYPE_CSV_GET_QUERY = "SELECT RoomTypeId, RoomType FROM tblRoomTypes WHERE deleted = :deleted ORDER BY RoomTypeId ASC ";


    public List<RoomType> list(int offset, int limit, String filterByClause, MapSqlParameterSource parameterSource) {

        String query;
        parameterSource.addValue("limit",limit);
        parameterSource.addValue("offset",offset);


        if(filterByClause != null && !RoomTypeUtils.EMPTY_STRING.equals(filterByClause)){
            query = ROOM_TYPE_BASE_QUERY + filterByClause + ORDER_BY_PART + ROW_LIMIT_PART;

        }else {
            query = ROOM_TYPE_BASE_QUERY + ORDER_BY_PART +ROW_LIMIT_PART;

        }

        return namedParameterJdbcTemplate.query(query,parameterSource,new RoomTypeRowMapper());
    }

    public List<RoomTypeDto> getAllRoomTypeList() {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.query(ROOM_TYPE_LIST_QUERY,parameterSource, new RoomTypeDtoRowMapper());
    }

    public RoomType getItemById(int id) {

        String query;

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("roomTypeID",id);

        query =ROOM_TYPE_BASE_QUERY + ROOM_TYPE_SELECTED_BY_ID;

        return namedParameterJdbcTemplate.queryForObject(query,parameterSource,new RoomTypeRowMapper());
    }

    public int deleteItemById(int id) {

        String query;

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("roomTypeID",id);

        query =ROOM_TYPE_DELETE_QUERY + ROOM_TYPE_SELECTED_BY_ID;

        return  namedParameterJdbcTemplate.update(query,parameterSource);
    }

    public List<RoomType> listDataTable(Integer offset, Integer limit, String orderByClause, String filterByClause) {

        String query;
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("limit",limit);
        parameterSource.addValue("offset",offset);

        if(filterByClause != null && !RoomTypeUtils.EMPTY_STRING.equals(filterByClause)){
            query = ROOM_TYPE_BASE_QUERY+"WHERE "+filterByClause+orderByClause+ROW_LIMIT_PART;

        }else {
            query = ROOM_TYPE_BASE_QUERY+orderByClause+ROW_LIMIT_PART;

        }

        return namedParameterJdbcTemplate.query(query,parameterSource,new RoomTypeRowMapper());
    }

    public String getFilteredUserNumber(String filterByClause) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String query=ROOM_TYPE_COUNT_QUERY+" WHERE"+filterByClause;
        return namedParameterJdbcTemplate.queryForObject(query,parameterSource,String.class);
    }

    public String getTotalUserNumber() {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.queryForObject(ROOM_TYPE_COUNT_QUERY,parameterSource,String.class);
    }

    public int insetItem(RoomType roomType) {

        String query;

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue("roomType", StringEscapeUtils.escapeHtml4(roomType.getRoomType()));
        parameterSource.addValue("hiltonRoomTypeId",roomType.getHiltonRoomTypeId());
        parameterSource.addValue("hiltonHotelCodes",roomType.getHiltonHotelCodes());
        parameterSource.addValue("externalRoomTypeId",roomType.getExternalRoomTypeId());

        parameterSource.addValue("deleted",roomType.isDeleted() ? 1 : 0 );


        DateFormat dateFormat = new SimpleDateFormat(RoomTypeUtils.DATE_FORMATTER);
        Date date = new Date();
        parameterSource.addValue("currentDate",dateFormat.format(date) );


        KeyHolder keyHolder = new GeneratedKeyHolder();
        query =ROOM_TYPE_BASE_INSERT_QUERY;

        namedParameterJdbcTemplate.update(query,parameterSource,keyHolder, new String [] {"RoomTypeId"});

        return keyHolder.getKey().intValue();
    }

    public int updateItem(RoomType roomType) {

        String query;

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue("roomTypeID",roomType.getRoomTypeId());
        parameterSource.addValue("roomType",StringEscapeUtils.escapeHtml4(roomType.getRoomType()));
        parameterSource.addValue("deleted",roomType.isDeleted() ? 1 : 0 );
        parameterSource.addValue("hiltonRoomTypeId",roomType.getHiltonRoomTypeId());
        parameterSource.addValue("hiltonHotelCodes",roomType.getHiltonHotelCodes());
        parameterSource.addValue("externalRoomTypeId",roomType.getExternalRoomTypeId());




        DateFormat dateFormat = new SimpleDateFormat(RoomTypeUtils.DATE_FORMATTER);
        Date date = new Date();
        parameterSource.addValue("currentDate",dateFormat.format(date) );


        query =ROOM_TYPE_BASE_UPDATE_QUERY + ROOM_TYPE_SELECTED_BY_ID;

        return namedParameterJdbcTemplate.update(query,parameterSource);
    }

    public Boolean checkRoomTypeAvailability(String roomType, int id) {

        String query = RoomTypeUtils.EMPTY_STRING;
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        switch (id){
            case -1:
                parameterSource.addValue("roomType",StringEscapeUtils.escapeHtml4(roomType)+"%");
                query = ROOM_TYPE_COUNT_QUERY + ROOM_TYPE_WHERE_EXACT_MATCH_QUERY;
                break;
            case 0 :
                parameterSource.addValue("roomType",StringEscapeUtils.escapeHtml4(roomType));
                query = ROOM_TYPE_COUNT_QUERY + ROOM_TYPE_WHERE_EXACT_MATCH_QUERY;
                break;

            default:
                parameterSource.addValue("roomType",StringEscapeUtils.escapeHtml4(roomType));
                parameterSource.addValue("roomTypeID",id);
                query = ROOM_TYPE_COUNT_QUERY + ROOM_TYPE_WHERE_EXACT_MATCH_QUERY  + ROOM_TYPE_AND_ID_QUERY ;


        }

        int count = namedParameterJdbcTemplate.queryForObject(query,parameterSource,Integer.class);

        if(count >0) {
            return false;
        }

        return true;
    }

    public List<RoomTypeCSVDto> listForCSV() {
        return namedParameterJdbcTemplate.query(ROOM_TYPE_CSV_GET_QUERY,new MapSqlParameterSource().addValue("deleted",0), new RoomTypeCSVDtoRowMapper());
    }
}
