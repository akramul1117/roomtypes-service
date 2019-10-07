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

package com.teamamerica.tourbot.roomtypes.service.service;/*
 *
 * --------------------------------------------------------------------------
 *   @project romtypes-service
 *   @author  akadir <it@teamamericany.com>
 *   @created at ৭/১০/১৯
 * --------------------------------------------------------------------------
 *
 */

import com.teamamerica.tourbot.roomtypes.service.domain.beans.RoomType;
import com.teamamerica.tourbot.roomtypes.service.domain.dto.RoomTypeDto;
import com.teamamerica.tourbot.roomtypes.service.repository.RoomTypesRepository;
import com.teamamerica.tourbot.roomtypes.service.utils.RoomTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeService {

    @Autowired
    RoomTypesRepository roomTypesRepository;

    public List<RoomType> list(int page, int limit, String filterByRoomType) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String filterByClause = RoomTypeUtils.EMPTY_STRING;

        if(filterByRoomType !=null && !filterByRoomType.equals(RoomTypeUtils.EMPTY_STRING) ) {
            filterByClause =" WHERE RoomType LIKE :roomType ";
            String roomType="%"+filterByRoomType.toLowerCase().trim()+"%";
            parameterSource.addValue("roomType",roomType);

        }


        return roomTypesRepository.list((page-1)*limit,limit,filterByClause,parameterSource);

    }

    public List<RoomTypeDto> getAllRoomTypeList() {

       return roomTypesRepository.getAllRoomTypeList();
    }

    public RoomType getItemById(int id) {

        return roomTypesRepository.getItemById(id);
    }

    public int insertItem(RoomType roomType) {

        return roomTypesRepository.insetItem(roomType);
    }

    public int updateItem(RoomType roomType) {

        return roomTypesRepository.updateItem(roomType);
    }

    public int deleteItemById(int id) {

        return roomTypesRepository.deleteItemById(id);
    }

    public List<RoomType> listDataTable(Integer offset, Integer limit, String orderByClause, String filterByClause) {
        return roomTypesRepository.listDataTable(offset,limit,orderByClause,filterByClause);
    }

    public String getTotalUserNumber() {

        return roomTypesRepository.getTotalUserNumber();
    }

    public String getFilteredUserNumber(String filterByClause) {
        return roomTypesRepository.getFilteredUserNumber(filterByClause);
    }
}
