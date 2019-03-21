sample
===
* 注释

	select #use("cols")# from tb_system_role  where  #use("condition")#

cols
===
	id,extra_table,name,remark

updateSample
===
	
	id=#id#,extra_table=#extraTable#,name=#name#,remark=#remark#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(extraTable)){
	 and extra_table=#extraTable#
	@}
	@if(!isEmpty(name)){
	 and name=#name#
	@}
	@if(!isEmpty(remark)){
	 and remark=#remark#
	@}
	
	