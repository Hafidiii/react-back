/*
 * Copyright (c) 2014 by Intelcia Group, Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of Intelcia Group, Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package com.example.storemanagement.mapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component("mapper")
public class ObjectMapper extends ModelMapper {
    private static final Log logger = LogFactory.getLog(ObjectMapper.class);
    private static final String ROOT_PACKAGE = "com.intelcia";

    public ObjectMapper() {
        super();
        init(ROOT_PACKAGE);
    }

    public ObjectMapper(String rootPackage) {
        super();
        init(rootPackage);
    }

    private void init(String rootPackage) {
        Configuration configuration = getConfiguration();
        configuration.setMatchingStrategy(MatchingStrategies.STRICT);

        Reflections reflections = new Reflections(rootPackage, new TypeAnnotationsScanner(), new SubTypesScanner());
        Set<Class<?>> mapperClasses = reflections.getTypesAnnotatedWith(Mapper.class);
        Set<Class<?>> mapperConverters = reflections.getTypesAnnotatedWith(ObjectConverter.class);
        Set<Class<?>> typeConverters = reflections.getTypesAnnotatedWith(TypeConverter.class);

        try {
            for (Class<?> mapperClass : mapperClasses) {
                logger.info("Add mapping for " + mapperClass.getName());
                addMappings((PropertyMap) mapperClass.getConstructor().newInstance());
            }
        } catch (Exception ex) {
            logger.error(ex);
        }

        try {
            for (Class<?> mapperClass : mapperConverters) {
                logger.info("Add converter for " + mapperClass.getName());
                addConverter((org.modelmapper.Converter) mapperClass.getConstructor().newInstance());
            }
        } catch (Exception ex) {
            logger.error(ex);
        }

        try {
            for (Class<?> mapperClass : typeConverters) {
                logger.info("Add type converter for " + mapperClass.getName());
                createTypeMap(mapperClass.getAnnotation(TypeConverter.class).source(),
                        mapperClass.getAnnotation(TypeConverter.class).destination());
            }
        } catch (Exception ex) {
            logger.error(ex);
        }
    }
}
