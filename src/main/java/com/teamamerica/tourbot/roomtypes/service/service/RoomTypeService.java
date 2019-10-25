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
import com.teamamerica.tourbot.roomtypes.service.domain.dto.RoomTypeCSVDto;
import com.teamamerica.tourbot.roomtypes.service.domain.dto.RoomTypeDto;
import com.teamamerica.tourbot.roomtypes.service.exception.DataNotFoundException;
import com.teamamerica.tourbot.roomtypes.service.exception.InvalidDataException;
import com.teamamerica.tourbot.roomtypes.service.repository.RoomTypesRepository;
import com.teamamerica.tourbot.roomtypes.service.utils.RoomTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

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

        List<RoomType> roomTypeList = roomTypesRepository.list((page - 1) * limit, limit, filterByClause, parameterSource);

        if(roomTypeList.size() ==0) {
            throw new DataNotFoundException("Data Not found");
        }

        return roomTypeList;

    }

    public List<RoomTypeDto> getAllRoomTypeList() {

        List<RoomTypeDto> roomTypeList = roomTypesRepository.getAllRoomTypeList();

        if(roomTypeList.size() ==0) {
            throw new DataNotFoundException("Data Not found");
        }

       return roomTypeList;
    }

    public RoomType getItemById(int id) {

        return roomTypesRepository.getItemById(id);
    }

    public int insertItem(RoomType roomType) {

        if(!roomTypesRepository.checkRoomTypeAvailability(roomType.getRoomType(),0)){
            throw new InvalidDataException("Duplicate values not allowed for RoomType!");
        }

        return roomTypesRepository.insetItem(roomType);
    }

    public int updateItem(RoomType roomType) {

        if(!roomTypesRepository.checkRoomTypeAvailability(roomType.getRoomType(),roomType.getRoomTypeId())){
            throw new InvalidDataException("Duplicate values not allowed for RoomType!");
        }

        int result = roomTypesRepository.updateItem(roomType);

        if(result ==0) {
            throw new DataNotFoundException("Data Not found id: " +roomType.getRoomTypeId());
        }

        return result;
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

    public Boolean checkRoomTypeAvailability(String roomType) {
        return roomTypesRepository.checkRoomTypeAvailability(roomType,-1);
    }

    public List<RoomTypeCSVDto> listForCSV() {
        return roomTypesRepository.listForCSV();
    }
}
