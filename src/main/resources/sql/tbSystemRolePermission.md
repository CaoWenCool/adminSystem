sample
===
* 注释

	select #use("cols")# from tb_system_role_permission  where  #use("condition")#

cols
===
	id,is_menu,is_point,permission_id,role_id

updateSample
===
	
	id=#id#,is_menu=#isMenu#,is_point=#isPoint#,permission_id=#permissionId#,role_id=#roleId#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(isMenu)){
	 and is_menu=#isMenu#
	@}
	@if(!isEmpty(isPoint)){
	 and is_point=#isPoint#
	@}
	@if(!isEmpty(permissionId)){
	 and permission_id=#permissionId#
	@}
	@if(!isEmpty(roleId)){
	 and role_id=#roleId#
	@}
	
	