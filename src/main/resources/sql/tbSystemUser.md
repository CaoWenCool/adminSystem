sample
===
* 注释

	select #use("cols")# from tb_system_user  where  #use("condition")#

cols
===
	id,age,is_use,is_admin,department,engish_name,img_url,job_no,name,nick_nmae,password,position,remark,role_id,role_name,create_time,update_time

updateSample
===
	
	id=#id#,age=#age#,is_use=#isUse#,is_admin=#isAdmin#,department=#department#,engish_name=#engishName#,img_url=#imgUrl#,job_no=#jobNo#,name=#name#,nick_nmae=#nickNmae#,password=#password#,position=#position#,remark=#remark#,role_id=#roleId#,role_name=#roleName#,create_time=#createTime#,update_time=#updateTime#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(age)){
	 and age=#age#
	@}
	@if(!isEmpty(isUse)){
	 and is_use=#isUse#
	@}
	@if(!isEmpty(isAdmin)){
	 and is_admin=#isAdmin#
	@}
	@if(!isEmpty(department)){
	 and department=#department#
	@}
	@if(!isEmpty(engishName)){
	 and engish_name=#engishName#
	@}
	@if(!isEmpty(imgUrl)){
	 and img_url=#imgUrl#
	@}
	@if(!isEmpty(jobNo)){
	 and job_no=#jobNo#
	@}
	@if(!isEmpty(name)){
	 and name=#name#
	@}
	@if(!isEmpty(nickNmae)){
	 and nick_nmae=#nickNmae#
	@}
	@if(!isEmpty(password)){
	 and password=#password#
	@}
	@if(!isEmpty(position)){
	 and position=#position#
	@}
	@if(!isEmpty(remark)){
	 and remark=#remark#
	@}
	@if(!isEmpty(roleId)){
	 and role_id=#roleId#
	@}
	@if(!isEmpty(roleName)){
	 and role_name=#roleName#
	@}
	@if(!isEmpty(createTime)){
	 and create_time=#createTime#
	@}
	@if(!isEmpty(updateTime)){
	 and update_time=#updateTime#
	@}
	
	