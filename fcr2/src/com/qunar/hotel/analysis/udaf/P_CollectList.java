package com.qunar.hotel.analysis.udaf;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hive.ql.exec.UDAF;
import org.apache.hadoop.hive.ql.exec.UDAFEvaluator;

public class P_CollectList extends UDAF {
	public static class ConcatUDAFEvaluator implements UDAFEvaluator {
		private List<String> result;


		public void init() {
			result = new ArrayList<String>();
		}

		public boolean iterate(String value) {

			if (value == null) {
				return true;
			}
			if (result == null) {
				result = new ArrayList<String>();
			}

			result.add(value);
			return true;
		}

		public List<String> terminatePartial() {
			return result;
		}

		public boolean merge(List<String> other) {
			if (other == null) {
				return true;
			}
			if (result == null) {
				result = new ArrayList<String>();
			}
			result.addAll(other);
			return true;
		}

		public List<String> terminate() {
			return result;
		}
	}
}