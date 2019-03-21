package com.demo.adminsystem.core.entity;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;

/* 
* 
* gen by beetlsql 2019-03-21
*/
public class TbSystemLogs  {
	
	private Long id ;
	private Integer isAes ;
	private Integer responseCode ;
	private String module ;
	private String operation ;
	private String remark ;
	private String remoteIp ;
	private String requestBody ;
	private String requestData ;
	private String requestId ;
	private String requestMethod ;
	private String requestParams ;
	private String url ;
	private Long userId ;
	private String userName ;
	private Date requestTime ;
	private Date responseTime ;
	
	public TbSystemLogs() {
	}
	
	public Long getId(){
		return  id;
	}
	public void setId(Long id ){
		this.id = id;
	}
	
	public Integer getIsAes(){
		return  isAes;
	}
	public void setIsAes(Integer isAes ){
		this.isAes = isAes;
	}
	
	public Integer getResponseCode(){
		return  responseCode;
	}
	public void setResponseCode(Integer responseCode ){
		this.responseCode = responseCode;
	}
	
	public String getModule(){
		return  module;
	}
	public void setModule(String module ){
		this.module = module;
	}
	
	public String getOperation(){
		return  operation;
	}
	public void setOperation(String operation ){
		this.operation = operation;
	}
	
	public String getRemark(){
		return  remark;
	}
	public void setRemark(String remark ){
		this.remark = remark;
	}
	
	public String getRemoteIp(){
		return  remoteIp;
	}
	public void setRemoteIp(String remoteIp ){
		this.remoteIp = remoteIp;
	}
	
	public String getRequestBody(){
		return  requestBody;
	}
	public void setRequestBody(String requestBody ){
		this.requestBody = requestBody;
	}
	
	public String getRequestData(){
		return  requestData;
	}
	public void setRequestData(String requestData ){
		this.requestData = requestData;
	}
	
	public String getRequestId(){
		return  requestId;
	}
	public void setRequestId(String requestId ){
		this.requestId = requestId;
	}
	
	public String getRequestMethod(){
		return  requestMethod;
	}
	public void setRequestMethod(String requestMethod ){
		this.requestMethod = requestMethod;
	}
	
	public String getRequestParams(){
		return  requestParams;
	}
	public void setRequestParams(String requestParams ){
		this.requestParams = requestParams;
	}
	
	public String getUrl(){
		return  url;
	}
	public void setUrl(String url ){
		this.url = url;
	}
	
	public Long getUserId(){
		return  userId;
	}
	public void setUserId(Long userId ){
		this.userId = userId;
	}
	
	public String getUserName(){
		return  userName;
	}
	public void setUserName(String userName ){
		this.userName = userName;
	}
	
	public Date getRequestTime(){
		return  requestTime;
	}
	public void setRequestTime(Date requestTime ){
		this.requestTime = requestTime;
	}
	
	public Date getResponseTime(){
		return  responseTime;
	}
	public void setResponseTime(Date responseTime ){
		this.responseTime = responseTime;
	}
	

}
