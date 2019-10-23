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

package com.teamamerica.tourbot.roomtypes.service.controller;/*
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
import com.teamamerica.tourbot.roomtypes.service.domain.pagination.DataTableRequest;
import com.teamamerica.tourbot.roomtypes.service.domain.pagination.DataTableResults;
import com.teamamerica.tourbot.roomtypes.service.domain.pagination.PaginationCriteria;
import com.teamamerica.tourbot.roomtypes.service.exception.DataNotFoundException;
import com.teamamerica.tourbot.roomtypes.service.service.RoomTypeService;
import com.teamamerica.tourbot.roomtypes.service.utils.AppUtil;
import com.teamamerica.tourbot.roomtypes.service.utils.RoomTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value ="/roomTypes")
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;



    @GetMapping(value = "/list")
    public ResponseEntity<List<RoomType>> list(@RequestParam(defaultValue ="1" ) int page, @RequestParam(defaultValue ="25" ) int limit, @RequestParam(defaultValue ="" )String filterByRoomType){



        return ResponseEntity.ok(roomTypeService.list(page, limit, filterByRoomType));
    }

    @GetMapping (value = "/roomTypeList")
    public ResponseEntity<List<RoomTypeDto>> getAllRoomTypeList(){

        return ResponseEntity.ok(roomTypeService.getAllRoomTypeList());
    }


    @GetMapping (value = "checkRoomTypeAvailability/{roomType}")
    public ResponseEntity<Boolean> checkRoomTypeAvailability(@PathVariable(name ="roomType" )String roomType){



        return ResponseEntity.ok(roomTypeService.checkRoomTypeAvailability(roomType));
    }


    @GetMapping (value = "/{id}")
    public ResponseEntity<RoomType> getItemById(@PathVariable(name ="id" )int id){

        RoomType roomType = roomTypeService.getItemById(id);

        return ResponseEntity.ok(roomType);
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> addItem(@Valid @RequestBody RoomType roomType){

        int id = roomTypeService.insertItem(roomType);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping(value = "")
    public ResponseEntity<Object> updateItem(@Valid @RequestBody RoomType roomType){

        int result = roomTypeService.updateItem(roomType);


        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(roomType.getRoomTypeId()).toUri();

        return ResponseEntity.ok(RoomTypeUtils.DATA_MODIFIED_SUCCESSFULLY);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object>  deleteItemById(@PathVariable(name ="id")int id){

        int result = roomTypeService.deleteItemById(id);

        if(result ==0) {
            throw new DataNotFoundException("Data Not found id: " +id);
        }

        return  ResponseEntity.ok("Delete Successful");
    }


    @GetMapping(value = "/dataTable")
    public DataTableResults<RoomType> listDataTable(HttpServletRequest request){
        DataTableRequest<RoomType> dataTableInRQ = new DataTableRequest<>(request);
        PaginationCriteria pagination = dataTableInRQ.getPaginationRequest();

        List<RoomType> userDtoList=roomTypeService.listDataTable(pagination.getPageNumber(),pagination.getPageSize(),
                pagination.getOrderByClause(),pagination.getFilterByClause());

        DataTableResults<RoomType> dataTableResults= new DataTableResults<>();
        dataTableResults.setDraw(dataTableInRQ.getDraw());
        dataTableResults.setListOfDataObjects(userDtoList);

        if(!AppUtil.isObjectEmpty(userDtoList)){
            dataTableResults.setRecordsTotal(roomTypeService.getTotalUserNumber());
            if(dataTableInRQ.getPaginationRequest().isFilterByEmpty()){
                dataTableResults.setRecordsFiltered(roomTypeService.getTotalUserNumber());
            }else {
                dataTableResults.setRecordsFiltered(roomTypeService.getFilteredUserNumber(pagination.getFilterByClause()));
            }
        }

        return dataTableResults;
    }

}
