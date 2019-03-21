sample
===
* 注释

	select #use("cols")# from tb_system_logs  where  #use("condition")#

cols
===
	id,is_aes,response_code,module,operation,remark,remote_ip,request_body,request_id,request_method,request_params,request_data,url,user_id,user_name,request_time,response_time

updateSample
===
	
	id=#id#,is_aes=#isAes#,response_code=#responseCode#,module=#module#,operation=#operation#,remark=#remark#,remote_ip=#remoteIp#,request_body=#requestBody#,request_id=#requestId#,request_method=#requestMethod#,request_params=#requestParams#,request_data=#requestData#,url=#url#,user_id=#userId#,user_name=#userName#,request_time=#requestTime#,response_time=#responseTime#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(isAes)){
	 and is_aes=#isAes#
	@}
	@if(!isEmpty(responseCode)){
	 and response_code=#responseCode#
	@}
	@if(!isEmpty(module)){
	 and module=#module#
	@}
	@if(!isEmpty(operation)){
	 and operation=#operation#
	@}
	@if(!isEmpty(remark)){
	 and remark=#remark#
	@}
	@if(!isEmpty(remoteIp)){
	 and remote_ip=#remoteIp#
	@}
	@if(!isEmpty(requestBody)){
	 and request_body=#requestBody#
	@}
	@if(!isEmpty(requestId)){
	 and request_id=#requestId#
	@}
	@if(!isEmpty(requestMethod)){
	 and request_method=#requestMethod#
	@}
	@if(!isEmpty(requestParams)){
	 and request_params=#requestParams#
	@}
	@if(!isEmpty(requestData)){
	 and request_data=#requestData#
	@}
	@if(!isEmpty(url)){
	 and url=#url#
	@}
	@if(!isEmpty(userId)){
	 and user_id=#userId#
	@}
	@if(!isEmpty(userName)){
	 and user_name=#userName#
	@}
	@if(!isEmpty(requestTime)){
	 and request_time=#requestTime#
	@}
	@if(!isEmpty(responseTime)){
	 and response_time=#responseTime#
	@}
	
	