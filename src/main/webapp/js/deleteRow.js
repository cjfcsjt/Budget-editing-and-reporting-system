/**
 * 科室页
 */
 //<script type="text/javascript">   
//页面装载就加载函数 ，删除按钮
$(document).ready(function() {   
	 $("#collectionBody").on( 'click','img',function(){
		 //alert("queryAcByDC");
		 	var row = $(this).parents("tr").index()-1; 
		//	 var row = $(this).parents("tr").index() + 1; // 行位置
	     var col = $(this).parents("td").index()+1; // 列位置
	     alert("行数："+row+"列数："+col);
	      $(this).parents("tr").remove();    
	     	 }); 
});  
   // </script>