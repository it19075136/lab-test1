package com.hackerthon.common;

import  com.hackerthon.common.CommonConstants;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.Properties;

import com.hackerthon.common.*;
public class CommonUtil {
	

public static final Logger log = Logger.getLogger(CommonUtil.class.getName());
	public static final Properties properties = new Properties();

	static {
		try {
			properties.load(QueryUtil.class.getResourceAsStream(CommonConstants.CONFIG_PROPERTIES));
		} catch (Exception e) {
			log.log(Level.SEVERE,e.getMessage());
		}
	}
}
