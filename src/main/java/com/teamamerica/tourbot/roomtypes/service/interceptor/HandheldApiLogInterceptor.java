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

package com.teamamerica.tourbot.roomtypes.service.interceptor;



import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.teamamerica.tourbot.roomtypes.service.logger.Loggable;
import com.teamamerica.tourbot.roomtypes.service.utils.RoomTypeUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class HandheldApiLogInterceptor implements HandlerInterceptor {

    @Loggable
    private Logger logger;

    ClassLoader classLoader = getClass().getClassLoader();
    File fireBaseJson = new File(classLoader.getResource("tourbotapi-firebase-adminsdk-0m5bp-1dedae8d10.json").getFile());

    private FileInputStream serviceAccount = new FileInputStream(fireBaseJson);
    private FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build();

    public HandheldApiLogInterceptor() throws IOException {
        FirebaseApp.initializeApp(options);
    }


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object o) throws Exception {

        if(request.getMethod().equals("OPTIONS")){

            return true;
        }

        Boolean checkRevoked=true;
        String token=request.getHeader("x-oauth-token");
        if(token==null){
            throw new RuntimeException(RoomTypeUtils.INCORRECT_REQUEST);
        }


        FirebaseToken decodedToken= FirebaseAuth.getInstance().verifyIdTokenAsync(token,checkRevoked).get();

//        UserRecord userRecord= FirebaseAuth.getInstance().getUserAsync(decodedToken.getUid()).get();
//        System.out.println(userRecord.getTokensValidAfterTimestamp());

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY HH:mm:ss a");
        Date startTime = new Date();
        request.setAttribute("startTime", startTime);
        logger.debug("Start Handheld Request: [{}][{}]",
                request.getRequestURL().toString(), dateFormat.format(startTime));

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object object, ModelAndView modelAndView) throws Exception {
        // nothing finish time is calculated in afterCompletion method,
        // cause in case of any exception this method is not called
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object object, Exception exception) throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY HH:mm:ss a");
        Date endTime = new Date();
        Date startTime = (Date) request.getAttribute("startTime");
        long timeTaken = endTime.getTime() - startTime.getTime();
        String  queryString = (request.getQueryString() != null ) ? request.getQueryString().toString() :"";
        logger.debug("End Handheld Request: [{}][{}][{} Milliseconds]",
                request.getRequestURL().toString()+ queryString , dateFormat.format(endTime), timeTaken);
    }
}
