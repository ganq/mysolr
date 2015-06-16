package com.gq.solr.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class CommonUtil {

	
	private Logger logger = LoggerFactory.getLogger(CommonUtil.class);

	
	/**
	 * 如果超过len长度则切割一个字符串
	 */
	public String separateStringByLen(String str, int len){
		if (StringUtils.isBlank(str)) {
			return "";
		}
		return str.length() >= len ? str.substring(0,len) : str;
	}


    /**
     * 读取文件
     */
    public String readTxtFileContent(String path) {

        if (!new File(path).exists()) {
            logger.error("file :'" + path + "' not found!");
            return "";
        }

        InputStreamReader inputReader = null;
        BufferedReader bufferReader = null;
        StringBuilder strBuider = new StringBuilder();
        try {
            InputStream inputStream = new FileInputStream(path);
            inputReader = new InputStreamReader(inputStream,"UTF-8");
            bufferReader = new BufferedReader(inputReader);

            // 读取一行
            String line = null;
            while ((line = bufferReader.readLine()) != null) {
                strBuider.append(line);
            }

        } catch (IOException e) {
            logger.error("Read file error :" + e.getMessage());
        } finally {
            try {
                inputReader.close();

                if (bufferReader != null) {
                    bufferReader.close();
                }
                if (inputReader != null) {
                    inputReader.close();
                }
            } catch (IOException e) {
                logger.error("close file stream error :" + e.getMessage());
            }
        }
        return strBuider.toString();
    }

}
