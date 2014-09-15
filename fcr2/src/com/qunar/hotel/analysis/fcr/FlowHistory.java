package com.qunar.hotel.analysis.fcr;

public class FlowHistory {
	private String flowid;
	private String flowno;
	private String orderno;
	private String flownode;
	private String user;
	private String usergroup;
	private String usergroup2;
	private String createTime;
	private String createDate;
	private String logType;
	private String problems;
	private String agent;
	private String agenttype;
	private String content;
	
	private int fcr;
	
	private boolean iswrite;
	
	public static final String OUTPUT_SEPARATOR = "\001"; 
	public static final String INPUT_SEPARATOR = "\t"; 

	public FlowHistory(String flow) {
		this(flow.split(INPUT_SEPARATOR, -1));
	}
	
	public FlowHistory(String [] flow) {
		int i = 0;
		flowid = flow[i++];
		flowno = flow[i++];
		orderno = flow[i++];
		flownode = flow[i++];
		user = flow[i++];
		usergroup = flow[i++];
		usergroup2 = flow[i++];
		createTime = flow[i++];
		createDate = flow[i++];
		logType = flow[i++];
		problems = flow[i++];
		agent = flow[i++];
		agenttype = flow[i++];
		content = flow[i++];
	}
	
	public String toString(String reportdate) {
		StringBuilder sb = new StringBuilder();
		sb.append(flowid);
		sb.append(OUTPUT_SEPARATOR);
		sb.append(flowno);
		sb.append(OUTPUT_SEPARATOR);
		sb.append(orderno);
		sb.append(OUTPUT_SEPARATOR);
		sb.append(flownode);
		sb.append(OUTPUT_SEPARATOR);
		sb.append(user);
		sb.append(OUTPUT_SEPARATOR);
		sb.append(usergroup);
		sb.append(OUTPUT_SEPARATOR);
		sb.append(usergroup2);
		sb.append(OUTPUT_SEPARATOR);
		sb.append(createTime);
		sb.append(OUTPUT_SEPARATOR);
		sb.append(createDate);
		sb.append(OUTPUT_SEPARATOR);
		sb.append(reportdate);
		sb.append(OUTPUT_SEPARATOR);
		sb.append(fcr);
		sb.append(OUTPUT_SEPARATOR);
		sb.append(logType);
		sb.append(OUTPUT_SEPARATOR);
		sb.append(problems);
		sb.append(OUTPUT_SEPARATOR);
		sb.append(agent);
		sb.append(OUTPUT_SEPARATOR);
		sb.append(agenttype);
		sb.append(OUTPUT_SEPARATOR);
		sb.append(content);
		sb.append('\n');
		return sb.toString();
	}
	
	public String getFlowid() {
		return flowid;
	}
	public void setFlowid(String flowid) {
		this.flowid = flowid;
	}
	public String getFlowno() {
		return flowno;
	}
	public void setFlowno(String flowno) {
		this.flowno = flowno;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getFlownode() {
		return flownode;
	}
	public void setFlownode(String flownode) {
		this.flownode = flownode;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public String getProblems() {
		return problems;
	}
	public void setProblems(String problems) {
		this.problems = problems;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public boolean isIswrite() {
		return iswrite;
	}

	public void setIswrite(boolean iswrite) {
		this.iswrite = iswrite;
	}

	public String getUsergroup() {
		return usergroup;
	}

	public void setUsergroup(String usergroup) {
		this.usergroup = usergroup;
	}

	public String getAgenttype() {
		return agenttype;
	}

	public void setAgenttype(String agenttype) {
		this.agenttype = agenttype;
	}

	public int getFcr() {
		return fcr;
	}

	public void setFcr(int fcr) {
		this.fcr = fcr;
	}

	public String getUsergroup2() {
		return usergroup2;
	}

	public void setUsergroup2(String usergroup2) {
		this.usergroup2 = usergroup2;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
