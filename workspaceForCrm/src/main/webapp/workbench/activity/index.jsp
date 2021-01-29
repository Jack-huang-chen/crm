<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
String basePath = request.getScheme() + "://" + request.getServerName()+ ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
<meta charset="UTF-8">

<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
	<link rel="stylesheet" type="text/css" href="jquery/bs_pagination/jquery.bs_pagination.min.css">
	<script type="text/javascript" src="jquery/bs_pagination/jquery.bs_pagination.min.js"></script>
	<script type="text/javascript" src="jquery/bs_pagination/en.js"></script>
<script type="text/javascript">


	$(function () {
		$(".time").datetimepicker({
			minView: "month",
			language:  'zh-CN',
			format: 'yyyy-mm-dd',
			autoclose: true,
			todayBtn: true,
			pickerPosition: "bottom-left"
		});
		//更新
		$("#updateBtn").click(function () {

			$.ajax({
				url: "workbench/activity/update.do",
				data: {
					"id": $.trim($("#edit-id").val()),
					"owner": $.trim($("#edit-Owner").val()),
					"name" : $.trim($("#edit-name").val()),
					"startDate":$.trim($("#edit-startTime").val()),
					"endDate" : $.trim($("#edit-endTime").val()),
					"cost" : $.trim($("#edit-cost").val()),
					"description" : $.trim($("#edit-describe").val()),



				},
				dataType: "json",
				date:"post",
				success : function (data) {
					if (data.success){
						//更新成功，刷新市场活动列表
						//关闭添加模块敞口
						var form = $("#editForm")[0];//只有js才有reset方法
						form.reset();
						$("#editActivityModal").modal("hide");
						pageList($("#activityPage").bs_pagination('getOption', 'currentPage')
								,$("#activityPage").bs_pagination('getOption', 'rowsPerPage'));
						//pageList(1,2);
					}else {
						alert("更新失败");
					}

				}
			})

		})

		//修改
		$("#editBtn").click(function () {


			//点击修改按钮
			if($(".xz:checked").length == 0){
				alert("请点击按钮");
			}else if($(".xz:checked").length > 1){
				alert("只能选择一个");
			}else{
				//获得id
				var id = $(".xz:checked").val();
				//发送ajax请求
				$.ajax({
					url : "workbench/activity/edit.do",
					data : {
						"id" : id
					},
					type : "get",
					dataType : "json",
					success : function (data) {
						//data里面有userList和activity

							var html = "";
							$.each(data.uList, function (i, n) {
								html += "<option value='" + n.id + "'>" + n.name + "</option>";

							})
							$("#edit-id").val(data.a.id);
							$("#edit-Owner").html(html);
							$("#edit-name").val(data.a.name);
							$("#edit-startTime").val(data.a.startDate);
							$("#edit-endTime").val(data.a.endDate);
							$("#edit-cost").val(data.a.cost);
							$("#edit-describe").val(data.a.description);
							$("#editActivityModal").modal("show");




					}

				})

			}

		})
		//删除
		$("#deleteBtn").click(function () {
			if ($(".xz:checked").length == 0){
				alert("请选择删除框");
			}else {
				if (confirm("确定删除吗")) {
					//获得删除的val
					var $xz = $(".xz:checked");
					var parameters = "";
					for (var i = 0; i < $xz.length; i++) {
						var value = $($xz[i]).val();
						parameters += "id=" + value;
						//r如果不是最后一个元素
						if (i < $xz.length - 1) {
							parameters += "&";
						}

					}
					//alert(parameters);
					$.ajax({
						url: "workbench/activity/delete.do",
						data: parameters,
						type: "post",
						dataType: "json",
						success: function (data) {
							if (data.success) {
								//删除成功后回到第一页
								pageList(1
										,$("#activityPage").bs_pagination('getOption', 'rowsPerPage'));
								//pageList(1, 2);


							} else {
								alert("删除失败");
							}

						}

					})

				}
			}

		})
		$("#qx").click(function () {
			$(".xz").prop("checked",this.checked);

		})
		$("#activityBody").on("click",$(".xz"),function () {
			$("#qx").prop("checked",$(".xz").length==$(".xz:checked").length);


		})


		$("#searchBtn").click(function () {
			$("#hidden-name").val($.trim($("#name").val()));
			$("#hidden-owner").val($.trim($("#owner").val()));
			$("#hidden-startDate").val($.trim($("#startTime").val()));
			$("#hidden-endDateDate").val($.trim($("#endTime").val()));

			pageList(1,2);

		})

		//处理查询列表



		$("#addBtn").click(function () {
			/**$(".time").datetimepicker({
				minView: "month",
				language:  'zh-CN',
				format: 'yyyy-mm-dd',
				autoclose: true,
				todayBtn: true,
				pickerPosition: "bottom-left"
			});**/
			$.ajax({
				url : "workbench/activity/getUserList.do",
				data :{

				},
				type : "get",
				dataType : "json",
				success : function (data) {
					var html = "";//"<option></option>"
					$.each(data,function (i,n) {
						html += "<option value='"+n.id+"'>"+n.name+"</option>";

					})
					$("#create-owner").html(html);
					//在js中使用el必须套用字符串
					var id = "${user.id}";

					$("#create-owner").val(id);
					$("#createActivityModal").modal("show");

				}

			})
			//$("#createActivityModal").modal("show");

		})
		//添加
		$("#saveBtn").click(function () {
			$.ajax({
				url: "workbench/activity/save.do",
				data: {
					"owner": $.trim($("#create-owner").val()),
					"name" : $.trim($("#creat-name").val()),
					"startDate":$.trim($("#create-startDate").val()),
					"endDate" : $.trim($("#create-endDate").val()),
					"cost" : $.trim($("#create-cost").val()),
					"description" : $.trim($("#create-description").val()),



				},
				dataType: "json",
				date:"post",
				success : function (data) {
					if (data.success){
						//添加成功，刷新市场活动列表
						//关闭添加模块敞口
						var form = $("#addForm")[0];
						form.reset();
						$("#createActivityModal").modal("hide");

						/**
						 * $("#activityPage").bs_pagination('getOption', 'currentPage')
						 *操作后停留到当前页
						 * $("#activityPage").bs_pagination('getOption', 'rowsPerPage'));
						 * 操作后维持已经设置的每页展现的记录
						 */
						pageList(1,$("#activityPage").bs_pagination('getOption', 'rowsPerPage'));
						//pageList(1,2);
					}else {
						alert("添加失败");
					}

				}
			})

		})
		pageList(1,2);

	});
	function pageList(pageNo, pageSize) {
		//重置复选框
		$("#qx").prop("checked",false);
		$("#name").val($.trim($("#hidden-name").val()));
		$("#owner").val($.trim($("#hidden-owner").val()));
		$("#startTime").val($.trim($("#hidden-startDate").val()));
		$("#endTime").val($.trim($("#hidden-endDate").val()));


		$.ajax({
			url : "workbench/activity/pageList.do",
			data: {
				"pageNo": pageNo,
				"pageSize" : pageSize,
				"name" : $.trim($("#name").val()),
				"owner" : $.trim($("#owner").val()),
				"startDate" : $.trim($("#startTime").val()),
				"endDate" : $.trim($("#endTime").val())

			},
			type: "get",
			dataType : "json",
			success : function (data) {

				var html = "";
				$.each(data.dateList,function (i,n) {


					html += '<tr class="active">tr class="active">';

					html += '"<td><input type="checkbox" class="xz" value="'+n.id+'" /></td>';
					html += '"<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href=\'workbench/activity/detail.do?id='+n.id+'\';">'+n.name+'</a></td>';
					html += '"<td>'+n.owner+'</td>';
					html += '"<td>'+n.startDate+'</td>';
					html += '"<td>'+n.endDate+'</td>';
					html += '</tr>';
				})
				$("#activityBody").html(html);
				//计算总页数
				var totalPages = data.total%pageSize==0?data.total/pageSize:parseInt(data.total/pageSize)+1;
				//
				$("#activityPage").bs_pagination({
					currentPage: pageNo, // 页码
					rowsPerPage: pageSize, // 每页显示的记录条数
					maxRowsPerPage: 20, // 每页最多显示的记录条数
					totalPages: totalPages, // 总页数
					totalRows: data.total, // 总记录条数

					visiblePageLinks: 3, // 显示几个卡片

					showGoToPage: true,
					showRowsPerPage: true,
					showRowsInfo: true,
					showRowsDefaultInfo: true,

					onChangePage : function(event, data){
						pageList(data.currentPage , data.rowsPerPage);
					}
				});







			}


		})

	}

	
</script>
</head>
<body>
<input type="hidden" id="hidden-name">
<input type="hidden" id="hidden-owner">
<input type="hidden" id="hidden-startDate">
<input type="hidden" id="hidden-endDate">

	<!-- 创建市场活动的模态窗口 -->
	<div class="modal fade" id="createActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel1">创建市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form" id="addForm">
					
						<div class="form-group">
							<label for="create-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-owner">

								</select>
							</div>
                            <label for="create-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="creat-name">
                            </div>
						</div>
						
						<div class="form-group">
							<label for="create-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="create-startDate" readonly>
							</div>
							<label for="create-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="create-endDate" readonly>
							</div>
						</div>
                        <div class="form-group">

                            <label for="create-cost" class="col-sm-2 control-label">成本</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-cost">
                            </div>
                        </div>
						<div class="form-group">
							<label for="create-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="create-description"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<!--保存-->
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="saveBtn">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改市场活动的模态窗口 -->
	<div class="modal fade" id="editActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2">修改市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form" id="editForm">
						<input type="hidden" id="edit-id"/>
					
						<div class="form-group">
							<label for="edit-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-Owner">
								  <!--这里拼接-->




								</select>
							</div>
                            <label for="edit-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-name" value="发传单">
                            </div>
						</div>

						<div class="form-group">
							<label for="edit-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="edit-startTime" >
							</div>
							<label for="edit-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="edit-endTime" >
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-cost" class="col-sm-2 control-label">成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-cost">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-describe"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="updateBtn"class="btn btn-primary"><!--data-dismiss="modal"-->更新</button>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>市场活动列表</h3>
			</div>
		</div>
	</div>
	<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
		<div style="width: 100%; position: absolute;top: 5px; left: 10px;">
		
			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">名称</div>
				      <input class="form-control" type="text" id="name">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input class="form-control" type="text" id="owner">
				    </div>
				  </div>


				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">开始日期</div>


					  <input class="form-control time" type="text" id="startTime">
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">结束日期</div>
					  <input class="form-control time" type="text" id="endTime">
				    </div>
				  </div>
				  
				  <button type="button" id="searchBtn" class="btn btn-default">查询</button>
				  
				</form>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button id="addBtn" type="button" class="btn btn-primary" ><span class="glyphicon glyphicon-plus"></span>创建</button>
				  <button type="button" id="editBtn" class="btn btn-default"><span class="glyphicon glyphicon-pencil"></span>修改</button>
				  <button id="deleteBtn" type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span>删除</button>
				</div>
				
			</div>
			<div style="position: relative;top: 10px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" id="qx"/></td>
							<td>名称1</td>
                            <td>所有者</td>
							<td>开始日期</td>
							<td>结束日期</td>
						</tr>
					</thead>
					<tbody id="activityBody">
						<%--<tr class="active">
							<td><input type="checkbox" /></td>
							<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='workbench/activity/detail.jsp';">发传单</a></td>
                            <td>zhangsan</td>
							<td>2020-10-10</td>
							<td>2020-10-20</td>
						</tr>
                        <tr class="active">
                            <td><input type="checkbox" /></td>
                            <td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.jsp';">发传单</a></td>
                            <td>zhangsan</td>
                            <td>2020-10-10</td>
                            <td>2020-10-20</td>
                        </tr>
                        --%>
					</tbody>
				</table>
			</div>
			
			<div style="height: 50px; position: relative;top: 30px;">
				<div id="activityPage"></div>

			</div>
			
		</div>
		
	</div>
</body>
</html>