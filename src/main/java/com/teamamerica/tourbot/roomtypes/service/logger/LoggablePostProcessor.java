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

package com.teamamerica.tourbot.roomtypes.service.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;


@Component
public class LoggablePostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(final Object bean, String beanName) throws BeansException {
        ReflectionUtils.doWithFields(bean.getClass(), new ReflectionUtils.FieldCallback() {
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                if (field.getAnnotation(Loggable.class) != null) {
                    ReflectionUtils.makeAccessible(field);
                    Logger loadedLogger = LoggerFactory.getLogger(bean.getClass());
                    field.set(bean, loadedLogger);
                }
            }
        });
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
