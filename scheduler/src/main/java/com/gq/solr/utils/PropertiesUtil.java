package com.gq.solr.utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 属性配置文件操作
 * 
 * @author ganq
 * 
 */
public class PropertiesUtil {


    /**
	 * solr 配置文件位置
	 */
	private static final String SOLR_PROPERTIES = "/solr.properties";

    private static final Logger logger = Logger.getLogger(PropertiesUtil.class);

	public static Properties solrProp = new Properties();


    /**
	 * 当前是否debug状态
	 */
	public static boolean isDebug = false;
	static {
        initProperties(SOLR_PROPERTIES,solrProp);
    }

    /**
     * 初始化properties
     * @param filePath
     * @param prop
     */
   static void initProperties(String filePath,Properties prop){
       InputStream fis = null;
       try {
           fis = PropertiesUtil.class.getResourceAsStream(filePath);
           prop.load(fis);

       } catch (IOException e) {
           logger.info("Not Found properties fil path: [" + filePath + "]",e);
       } catch (Exception e) {
           logger.info("load this path: [" + filePath + "] error" ,e);
       } finally {
           if (fis != null) {
               try {
                   fis.close();
               } catch (IOException e) {
                   logger.info(e.getMessage());
               }
           }
       }
   }

	/**
	 * 根据key获取Value
	 */
	public static String getKey(Properties prop, String key) {
		return prop.getProperty(key, "");
	}



	public static void main(String[] args) {

	}
}
