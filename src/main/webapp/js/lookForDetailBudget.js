/**
 * 
 */
   
//页面装载就加载函数   ,查看明细
$(document).ready(function() {   
	 $("#yusuanbiao").on( 'click','u',function(){
		 alert("queryBudgetByDC");
		 	var row = $(this).parents("tr").index()-1; 
		//	 var row = $(this).parents("tr").index() + 1; // 行位置
	     var col = $(this).parents("td").index()+1; // 列位置
	     alert("行数："+row+"列数："+col);
	     var departmentName=$(this).parents("tr").find("td").eq(0).text();
	     //alert("departName："+departmentName);
	     queryBudgetByDC(departmentName);
	     	 }); 
});  
