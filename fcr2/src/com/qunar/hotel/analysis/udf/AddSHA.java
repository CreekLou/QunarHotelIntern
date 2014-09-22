package com.qunar.hotel.analysis.udf;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.hadoop.hive.ql.exec.UDF;

public class AddSHA extends UDF {
	public String evaluate(final String s) {

		return SHA1(s);
	}
	
    private static String SHA1(String inStr) {
        MessageDigest md = null;
        String outStr = null;
        try {
            md = MessageDigest.getInstance("SHA-1");     
            byte[] digest = md.digest(inStr.getBytes()); 
            outStr = bytetoString(digest);
        }
        catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
        }
        return outStr;
    }
    
    
    private static String bytetoString(byte[] digest) {
        String str = "";
        String tempStr = "";
        
        for (int i = 0; i < digest.length; i++) {
            tempStr = (Integer.toHexString(digest[i] & 0xff));
            if (tempStr.length() == 1) {
                str = str + "0" + tempStr;
            }
            else {
                str = str + tempStr;
            }
        }
        return str.toLowerCase();
    }
}
