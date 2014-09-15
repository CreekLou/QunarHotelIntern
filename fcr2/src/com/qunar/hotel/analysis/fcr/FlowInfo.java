package com.qunar.hotel.analysis.fcr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class FlowInfo {
	private ArrayList<FlowHistory> flowinfo = new ArrayList<FlowHistory>();

	public void addInfo(String info) {
		if (info == null || info.isEmpty())
			return;
		flowinfo.add(new FlowHistory(info));
	}

	public void clearInfo() {
		flowinfo.clear();
	}

	@SuppressWarnings("unchecked")
	public void calResult() throws ParseException {
		Collections.sort(flowinfo, new SortByTypeAndTime());
		for (int i = 0; i < flowinfo.size(); i++) {
			FlowHistory fh = flowinfo.get(i);
			if (fh.getFlownode().equals("房控组") && i + 1 < flowinfo.size()
					&& !flowinfo.get(i + 1).getFlownode().isEmpty()
					&& !flowinfo.get(i + 1).getFlownode().equals("房控组")) {
				fh.setIswrite(true);
			}
			if (fh.getFlownode().equals("座席") && i + 1 < flowinfo.size()
					&& flowinfo.get(i + 1).getFlownode().equals("结束")) {
				fh.setIswrite(true);
			}
			if (fh.getFlownode().isEmpty()) {
				fh.setIswrite(true);
			}
		}

		ArrayList<FlowHistory> newflowinfo = new ArrayList<FlowHistory>();
		for (int i = 0; i < flowinfo.size(); i++) {
			FlowHistory fh = flowinfo.get(i);
			if (fh.isIswrite()) {
				newflowinfo.add(fh);
			}
		}

		flowinfo = newflowinfo;

		Collections.sort(flowinfo, new SortByTime());
		String lastProblems = "";
		for (int i = 0; i < flowinfo.size(); i++) {
			FlowHistory fh = flowinfo.get(i);
			if (fh.getProblems().isEmpty()) {
				fh.setProblems(lastProblems);
			} else {
				lastProblems = fh.getProblems();
			}
		}

		// cal fcr
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for (int i = 1; i < flowinfo.size(); i++) {
			FlowHistory fh1 = flowinfo.get(i - 1);
			Date date1 = format.parse(fh1.getCreateTime());
			FlowHistory fh2 = flowinfo.get(i);
			Date date2 = format.parse(fh2.getCreateTime());
			if ((date2.getTime() - date1.getTime()) / 1000 / 3600 / 24 > 3) {
				fh1.setFcr(0);
			} else {
				fh1.setFcr(1);
			}
		}
		if (flowinfo.size() > 0)
			flowinfo.get(flowinfo.size() - 1).setFcr(0);
	}

	public String toString(String reportdate) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < flowinfo.size(); i++) {
			FlowHistory fh = flowinfo.get(i);
			if (fh.isIswrite()) {
				sb.append(fh.toString(reportdate));
			}
		}
		return sb.toString();
	}

	private class SortByTypeAndTime implements Comparator {

		@Override
		public int compare(Object a, Object b) {
			FlowHistory fh1 = (FlowHistory) a;
			FlowHistory fh2 = (FlowHistory) b;
			int f1 = 0, f2 = 0;
			if (fh1.getLogType().equals("用户来电"))
				f1 = 1;
			if (fh2.getLogType().equals("用户来电"))
				f2 = 1;
			if (f1 != f2)
				return f1 - f2;
			int cmp = fh1.getCreateTime().compareTo(fh2.getCreateTime());
			if (cmp != 0)
				return cmp;
			f1 = 0;
			f2 = 0;
			if (fh1.getFlownode().equals("结束"))
				f1 = 1;
			if (fh2.getFlownode().equals("结束"))
				f2 = 1;
			return f1 - f2;
		}

	}

	private class SortByTime implements Comparator {
		@Override
		public int compare(Object a, Object b) {
			FlowHistory fh1 = (FlowHistory) a;
			FlowHistory fh2 = (FlowHistory) b;
			return fh1.getCreateTime().compareTo(fh2.getCreateTime());
		}
	}
}
