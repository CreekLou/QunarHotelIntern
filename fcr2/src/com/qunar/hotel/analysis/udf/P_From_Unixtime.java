package com.qunar.hotel.analysis.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

public class P_From_Unixtime extends UDF {
	
	public String evaluate(final long s) {
		long ss = s % 60;
		long mm = s / 60 % 60;
		long hh = s / 3600;
		return hh + ":" + mm + ":" + ss;
	}
}
