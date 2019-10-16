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

package com.teamamerica.tourbot.roomtypes.service.utils;

import java.util.Calendar;

public class RoomTypeUtils {
    public static final String EMPTY_STRING="";
    public static final String AUTHENTICATION_ERROR="AuthenticationError: User unauthenticated";
    public static final String INCORRECT_REQUEST="Incorrect Request Format";
    public static final String REDIS_KEY_AGENT_OPTIONS_LIST="FULL_AGENT_CODE_LIST";
    public static final String DATE_FORMATTER ="yyyy-MM-dd HH:mm:ss";
    public static final String DATA_MODIFIED_SUCCESSFULLY ="Data Modified Successfully!";
    public static final String VALIDATION_FAILED = "Validation Failed";

    public static long getCacheExpireTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,7);
        return calendar.getTimeInMillis();
    }
}
