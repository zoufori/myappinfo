function  loadCategoryLevel(pid,cl,categoryLevel){
	$.ajax({
		type:"GET",//请求类型
		url:"categorylevellist.json",//请求的url
		data:{pid:pid},//请求参数
		dataType:"json",//ajax接口（请求url）返回的数据类型
		success:function(data){//data：返回数据（json对象）
			var dataObj = eval(data);
			$("#"+categoryLevel).html("");
			var options = "<option value=\"\">--请选择--</option>";
			for(var i = 0; i < data.length; i++){
				if(cl != null && cl != undefined && dataObj[i].id == cl ){
					options += "<option selected=\"selected\" value=\""+dataObj[i].id+"\" >"+dataObj[i].categoryname+"</option>";
				}else{
					options += "<option value=\""+dataObj[i].id+"\">"+dataObj[i].categoryname+"</option>";
				}
			}
			$("#"+categoryLevel).html(options);
		},
		error:function(data){//当访问时候，404，500 等非200的错误状态码
			alert("加载分类列表失败！");
		}
	});
}   

function delfile(id){
	$.ajax({
		type:"GET",//请求类型
		url:"delfile.json",//请求的url
		data:{id:id,flag:'logo'},//请求参数
		dataType:"json",//ajax接口（请求url）返回的数据类型
		success:function(data){//data：返回数据（json对象）
			if(data.result == "success"){
				alert("删除成功！");
				$("#uploadfile").show();
				$("#logoFile").html('');
			}else if(data.result == "failed"){
				alert("删除失败！");
			}
		},
		error:function(data){//当访问时候，404，500 等非200的错误状态码
			alert("请求错误！");
		}
	});  
}

$(function(){  
	//动态加载所属平台列表
	$.ajax({
		type:"GET",//请求类型
		url:"datadictionarylist.json",//请求的url
		data:{tcode:"APP_FLATFORM"},//请求参数
		dataType:"json",//ajax接口（请求url）返回的数据类型
		success:function(data){//data：返回数据（json对象）
			var dataObj = eval(data);
			console.log(data);
			console.log(dataObj);
			var fid = $("#fid").val();
			$("#flatformId").html("");
			var options = "<option value=\"\">--请选择--</option>";
			for(var i = 0; i < dataObj.length; i++){
				if(fid != null && fid != undefined && dataObj[i].valueid == fid ){
					options += "<option selected=\"selected\" value=\""+dataObj[i].valueid+"\" >"+dataObj[i].valuename+"</option>";
					console.log("1111111");
				}else{
					options += "<option value=\""+dataObj[i].valueid+"\">"+dataObj[i].valuename+"</option>";
					console.log("2222222");
				}
			}
			$("#flatformId").html(options);
		},
		error:function(data){//当访问时候，404，500 等非200的错误状态码
			alert("加载平台列表失败！");
		}
	});  
	
	var cl1 = $("#cl1").val();
	var cl2 = $("#cl2").val();
	var cl3 = $("#cl3").val();
	//动态加载一级分类列表
	loadCategoryLevel(null,cl1,"categoryLevel1");
	//动态加载二级分类列表
	loadCategoryLevel(cl1,cl2,"categoryLevel2");
	//动态加载三级分类列表
	loadCategoryLevel(cl2,cl3,"categoryLevel3");
	
	//联动效果：动态加载二级分类列表
	$("#categoryLevel1").change(function(){
		var categoryLevel1 = $("#categoryLevel1").val();
		if(categoryLevel1 != '' && categoryLevel1 != null){
			loadCategoryLevel(categoryLevel1,cl2,"categoryLevel2");
		}else{
			$("#categoryLevel2").html("");
			var options = "<option value=\"\">--请选择--</option>";
			$("#categoryLevel2").html(options);
		}
		$("#categoryLevel3").html("");
		var options = "<option value=\"\">--请选择--</option>";
		$("#categoryLevel3").html(options);
	});
	//联动效果：动态加载三级分类列表
	$("#categoryLevel2").change(function(){
		var categoryLevel2 = $("#categoryLevel2").val();
		if(categoryLevel2 != '' && categoryLevel2 != null){
			loadCategoryLevel(categoryLevel2,cl3,"categoryLevel3");
		}else{
			$("#categoryLevel3").html("");
			var options = "<option value=\"\">--请选择--</option>";
			$("#categoryLevel3").html(options);
		}
	});
	
	$("#back").on("click",function(){
		window.location.href = "list";
	});
	
	
	//LOGO图片---------------------
	var logoPicPath = $("#logoPicPath").val();
	var id = $("#id").val();
	if(logoPicPath == null || logoPicPath == "" ){
		$("#uploadfile").show();
	}else{
		$("#logoFile").append("<p><img src=\""+logoPicPath+"?m="+Math.random()+"\" width=\"100px;\"/> &nbsp;&nbsp;"+
							"<a href=\"javascript:;\" onclick=\"delfile('"+id+"');\">删除</a></p>");
		
	}

});
      
      
      