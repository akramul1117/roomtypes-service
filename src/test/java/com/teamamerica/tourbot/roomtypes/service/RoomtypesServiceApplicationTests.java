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

package com.teamamerica.tourbot.roomtypes.service;

import com.teamamerica.tourbot.roomtypes.service.controller.RoomTypeController;

import com.teamamerica.tourbot.roomtypes.service.domain.beans.RoomType;
import com.teamamerica.tourbot.roomtypes.service.domain.dto.RoomTypeCSVDto;
import com.teamamerica.tourbot.roomtypes.service.domain.dto.RoomTypeDto;
import com.teamamerica.tourbot.roomtypes.service.service.RoomTypeService;
import com.teamamerica.tourbot.roomtypes.service.utils.RoomTypeUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomtypesServiceApplicationTests {

	@Autowired
	private RoomTypeController roomTypeController;

	@Autowired
	private RoomTypeService roomTypeService;

	@Test
	public void contextLoads() throws Exception {

		Assert.assertNotNull(roomTypeController);
	}

	@Test
	public void test_list() {
		List<RoomType> roomtypeList = roomTypeService.list(1,10, RoomTypeUtils.EMPTY_STRING);
		List<RoomTypeCSVDto> roomTypeCSVDtoList = roomTypeService.listForCSV();

		Assert.assertFalse(roomtypeList.size()==0 );
		Assert.assertNotEquals(0, roomTypeCSVDtoList.size());
	}

	@Test
	public void test_getAllRoomTypeList() {
		List<RoomTypeDto> roomTypeList = roomTypeService.getAllRoomTypeList();

		Assert.assertFalse(roomTypeList.size()==0 );
	}

	@Test
	public void test_checkRoomTypeAvailability() {
		Boolean result = roomTypeService.checkRoomTypeAvailability("TEST_111_XXX_111");

		Assert.assertTrue(result);
	}


	@Test
	public void test_CRUD_RoomType() {
		RoomType roomType = new RoomType();
		roomType.setRoomType("Test Room Type");
		roomType.setDeleted(false);
		roomType.setExternalRoomTypeId("");
		roomType.setHiltonHotelCodes("");
		roomType.setHiltonRoomTypeId("");

		//test insert
		roomType.setRoomTypeId(roomTypeService.insertItem(roomType));;
		Assert.assertFalse(roomType.getRoomTypeId() ==0 );

		//test update
		roomType.setRoomType("Test Update RoomType");
		Assert.assertFalse(roomTypeService.updateItem(roomType) ==0 );

		//test read
		Assert.assertNotEquals(roomType, roomTypeService.getItemById(roomType.getRoomTypeId()));

		// test delete
		Assert.assertFalse(roomTypeService.deleteItemById(roomType.getRoomTypeId()) ==0 );


	}

}
