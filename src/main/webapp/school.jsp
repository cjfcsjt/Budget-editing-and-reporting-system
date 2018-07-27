<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>学校财务</title>
<link rel="stylesheet" type="text/css" href="css/form.css" />
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript">
   	function getBudgetShow(){//公示,queryDepartmentByCollegeName应为管理层方法，未写,queryAllDepartment有问题
 		//alert("getBudgetShow");
 		$.ajax({
    	    type: "get",
    	    dataType: "json",
    	    url: "admin/queryAllCollege",    
    	    success: function(objects){
                var tr = $("#cloneTr5odd");
                var tr2 = $("#cloneTr5even");
             	$.each(objects, function(index,item){   
                   var clonedTr;
                   if(index%2==0)clonedTr = tr.clone();
                   else clonedTr = tr2.clone();
                     var _index = index;
                     //循环遍历cloneTr的每一个td元素，并赋值
                     clonedTr.children("td").each(function(inner_index){                          
                            //根据索引为每一个td赋值
                                  switch(inner_index){
                                         case(0): 
                                           $(this).html(item.collegeName);
                                           break;
                                      
                                 }//end switch                        
                  	});//end children.each
                 	clonedTr.insertAfter(tr2);
              	});//end $each
             	$("#cloneTr5odd").hide();//
             	$("#cloneTr5even").hide();
              	$("#table5").show();//first是表的id
				}//end success
		});//end ajaxpost 
		//alert("queryBudgetByCollegeName");
 			$.ajax({
        	    type: "get",
        	    dataType: "json",
        	    url: "school/queryProjectBudget",    
        	    success: function(objects){
        	    	for ( var i = 0; i < objects.length; i++) {
        	            var name = objects[i].collegeName;
        	            var src = objects[i].projectName;
        	            var sid = objects[i].budgetAmount;
        	           // alert("departmentName="+name+"projectName="+src);
        	            var rowNum;
                        var colNum;
                        $("#table5Body tr").each(function(){
                            var text = $(this).children("td:first").text();
                            //alert(text);
                            if(text==objects[i].collegeName)
                            { rowNum=$(this).index();
                                 //alert("row="+rowNum);
                                 }
                          });
                        $("#table5Head th").each(function(){
                            var text = $(this).text();
                           // alert(text);
                            if(text==objects[i].projectName)
                            { colNum=$(this).index();
                                // alert("col="+colNum);
                                 }
                          });
                        $("#table5body tr").eq(rowNum).find("td").eq(colNum).html(objects[i].budgetAmount);  
                      //  $("#chakanBody").children("tr:eq(rowNum)").children("td:eq(colNum)").html(objects[i].budgetAmount);
        	            }
                 	
                  	$("#table5").show();//first是表的id
   				//end success
			} 
 			});
        }
    
 </script >
	
<script type="text/javascript">
    // JS实现选项卡切换
    window.onload=function(){
    	//隐藏first表格的第一行
    	var table1 = document.getElementById("table1");
    	table1.rows[1].style.display ="none";
    	/* 查询第一个选项卡的学院和预算金额 */
    	$.ajax({
    	    type: "get",
    	    dataType: "json",
    	    url: "school/queryCollegeBudget",
    	    //complete :function(){$("#load").hide();},//AJAX请求完成时隐藏loading提示
    	    success: function(objects){
    	    	//1,获取上面id为cloneTr的tr元素
    	    	if(objects.length==0){
    	    		alert("查询数据为空");
    	    	}	
    	    	//alert("行数"+objects.length);
                var tr = $("#cloneTr");

             	$.each(objects, function(index,item){                            
                   //克隆tr，每次遍历都可以产生新的tr                            
                     var clonedTr = tr.clone();
                     var _index = index;
                  
                     //循环遍历cloneTr的每一个td元素，并赋值
                     clonedTr.children("td").each(function(inner_index){                          
                            //根据索引为每一个td赋值
                                  switch(inner_index){
                                        case(0): 
                                           $(this).html(item.collegeName);
                                           break;
                                        case(1):
                                           $(this).html(item.summaryBudget);
                                           break;
/*                                             case(2):
                                            $(this).html(_index + 1);
                                            break; */
                                 }//end switch                        
                  	});//end children.each
              
                 //把克隆好的tr追加原来的tr后面
                 	clonedTr.insertAfter(tr);
                 	clonedTr.show();
              	});//end $each
             	$("#cloneTr").hide();//隐藏id=clone的tr，因为该tr中的td没有数据，不隐藏起来会在生成的table第一行显示一个空行
              	$("#table1").show();//first是表的id
				}//end success
		});//end ajaxpost   
        var myTab = document.getElementById("tab");    //整个div
    	var mynav = document.getElementById("nav");    //整个div
        var myUl = mynav.getElementsByTagName("ul")[0];//一个节点
        var myLi = myUl.getElementsByTagName("li");    //数组
    	var myTnav = document.getElementById("tnav");   //整个div
        var myDiv = myTnav.getElementsByTagName("div"); //数组

        for(var i = 0; i<myLi.length;i++){
            myLi[i].index = i;
            myLi[i].onclick = function(){
                for(var j = 0; j < myLi.length; j++){
                    myLi[j].className="off";
                    myDiv[j].className = "hide";
                }
                this.className = "on";
                myDiv[this.index].className = "show";
            }
        }
     }
</script>
<script>
function addrowe(a){
	var c=document.getElementById(a);//获得表格的信息
	  	if( c.rows.length==0){//如果是向一个空表增加一行
	 	var x=c.insertRow(0);//向空表插入一行
	 	var y=x.insertCell(0);//向新行插入一列
	 	y.innerHTML="new cell0";
	}
	else{
		var z=c.rows[1].cells;//如果不是空表，首先获得表格有多少列，先获取再插入新行
		var x=c.insertRow(c.rows.length-1);
		for(var i=0;i<z.length;i++){//依次向新行插入表格列数的单元格
	     	var y=x.insertCell(i);
			if(i==1){
				var iden="deletee"+c.rows.length;
				y.innerHTML='<input type="button" id="'+iden+'" value="'+iden+'" ></input>';
				var bu=document.getElementById(iden);
				bu.onclick=function(){
					var c=document.getElementById(a);//获得表格的信息
					var con=confirm("确认删除该行?"); //在页面上弹出对话框
	        		if(con==true)
					{
						var line_num = bu.parentElement.parentElement.rowIndex;
						c.deleteRow(line_num);
					}
	        	}
			}else{
				y.innerHTML=z[i].innerHTML;
			}
	 	}
	}
}
</script> 
<script type="text/javascript">
//获取某学院所有部门的总预算。
function check(element){
      //执行删除行的操作
      //alert("执行删除行的操作");
      var node = element.parentNode;
      //var node = element.parentElement.rowIndex;
      var c=document.getElementById("details");
      document.getElementById("bg_fuceng").style.display="";
      //alert("node内容"+node.innerText);
      //alert("所在行：" +node);
      //alert("danyuange"+element.parentNode.children[1].textContent);
      //alert("第一个单元格内容"+$("#details").rows(2).cells(1).innerText);
      var college_name=node.children[0].textContent;
      //alert("内容"+node.children[0].textContent);
      //将college-name传给后台
       $.ajax({
                //cache:true,//保留缓存数据
                type:"post",//为post请求
                url:"school/queryDepartmentBudget",//这是我在后台接受数据的文件名
                data:{"collegeName":college_name},   
                //async:false,//false同步，true异步
                dataType:"json",
                success:function (objects) {
                    //alert("行  "+c.rows.length);
                    //alert("列  "+c.rows[0].cells.length);
                   	for(var i = c.rows.length-1 ;i > 1;i--){
                   		c.deleteRow(i);
                	}
        	    	if(objects.length==0){
        	    		alert("数据为空");
        	    	}
                   	//alert("删除成功");
                    //alert("删除后，行  "+c.rows.length);
                    //alert("删除后，列  "+c.rows[0].cells.length);
                    
                   	//this做为参数传过来是方法中的element,parentNode就是获取父节点，获取了连个父节点，就相当于获取了tr
                   	var tr = $("#clone");
                 	$.each(objects, function(index,item){                            
                       //克隆tr，每次遍历都可以产生新的tr                            
                         var clonedTr = tr.clone();
                         var _index = index;
                      
                         //循环遍历cloneTr的每一个td元素，并赋值
                         clonedTr.children("td").each(function(inner_index){                          
                                //根据索引为每一个td赋值
                                      switch(inner_index){
                                            case(0): 
                                               $(this).html(item.departmentName);
                                               break;
                                             case(1):
                                               $(this).html(item.firstSummaryTable.summaryBudget);
                                               break; 
/*                                             case(2):
                                                $(this).html(_index + 1);
                                                break; */
                                     }//end switch                        
                      	});//end children.each
                  
                     //把克隆好的tr追加原来的tr后面
                     	clonedTr.insertAfter(tr);
                     	clonedTr.show();
                  	});//end $each
                 	$("#clone").hide();//隐藏id=clone的tr，因为该tr中的td没有数据，不隐藏起来会在生成的table第一行显示一个空行
                  	$("#details").show();//first是表的id
                },
	    		error:function(){
	    			alert("传数据出现异常");
	    		}
            });
       document.getElementById("divId").style.display="";

    }
	function closeDiv(){
	//设置div关闭
		document.getElementById("divId").style.display="none";
		document.getElementById("bg_fuceng").style.display="none";
	}
</script>

<script type="text/javascript">
//获取某学院所有部门的总控制数。
function show_details(element){
      //执行删除行的操作
      //alert("执行删除行的操作");
      var node = element.parentNode;
      //var node = element.parentElement.rowIndex;
      var c=document.getElementById("details");
      var dt=document.getElementById("div_title");
      dt.textContent = "预算控制数";
      document.getElementById("bg_fuceng").style.display="";
      document.getElementById("divId").style.display="";
      //alert("node内容"+node.innerText);
      //alert("所在行：" +node);
      //alert("danyuange"+element.parentNode.children[1].textContent);
      //alert("第一个单元格内容"+$("#details").rows(2).cells(1).innerText);
      var college_name=node.children[0].textContent;
      //alert("内容"+node.children[0].textContent);
      //将college-name传给后台
       $.ajax({
                //cache:true,//保留缓存数据
                type:"post",//为post请求
                url:"school/queryDepartmentBudget2",//这是我在后台接受数据的文件名
                data:{"collegeName":college_name},   
                //async:false,//false同步，true异步
                dataType:"json",
                success:function (objects) {
                    //alert("行  "+c.rows.length);
                    //alert("列  "+c.rows[0].cells.length);  
        	    	if(objects.length==0){
        	    		alert("查询数据为空");
        	    	}
                   	for(var i = c.rows.length-1 ;i > 1;i--){
                   		c.deleteRow(i);
                	}
                   	//alert("删除成功");
                  
                   	//this做为参数传过来是方法中的element,parentNode就是获取父节点，获取了连个父节点，就相当于获取了tr
                   	var tr = $("#clone");
                 	$.each(objects, function(index,item){                            
                       //克隆tr，每次遍历都可以产生新的tr                            
                         var clonedTr = tr.clone();
                         var _index = index;
                      
                         //循环遍历cloneTr的每一个td元素，并赋值
                         clonedTr.children("td").each(function(inner_index){                          
                                //根据索引为每一个td赋值
                                      switch(inner_index){
                                            case(0): 
                                               $(this).html(item.departmentName);
                                               break;
                                            case(1):
                                               $(this).html(item.secondSummaryTable.summaryBudget);
                                               break;
/*                                             case(2):
                                                $(this).html(_index + 1);
                                                break; */
                                     }//end switch                        
                      	});//end children.each
                  
                     //把克隆好的tr追加原来的tr后面
                     	clonedTr.insertAfter(tr);
                     	clonedTr.show();
                  	});//end $each
                 	$("#clone").hide();//隐藏id=clone的tr，因为该tr中的td没有数据，不隐藏起来会在生成的table第一行显示一个空行
                  	$("#details").show();//first是表的id
                }
            });

    }
	function closeDiv(){
	//设置div关闭
		document.getElementById("divId").style.display="none";
		document.getElementById("bg_fuceng").style.display="none";
	}
</script>
<script type="text/javascript">
function write_(element){
      //执行删除行的操作
      //alert("写事由");
      var node = element.parentNode;
      document.getElementById("bg_fuceng").style.display="";
      var reason=node.children[5].textContent;
      //alert("内容"+node.children[5].textContent);
      document.getElementById("divWrite").style.display=""; 
      var wr=document.getElementById("write_reason");
      wr.value = reason;
      var i = element.parentElement.rowIndex;
      var index=document.getElementById("row_index");
      index.textContent = i;
      //alert("行数"+index.textContent);
      //alert("传递前文本框中的值"+wr.value);
    }
    function write_submit(){
    	var c=document.getElementById("table1");
  		document.getElementById("divWrite").style.display="none";
		document.getElementById("bg_fuceng").style.display="none";
	    var wr=document.getElementById("write_reason");
	    var index=document.getElementById("row_index");
	    var i=index.textContent;
	    //alert("传递后行数"+index.textContent);
		c.rows[i].cells[5].textContent = wr.value;
		
		//alert("文本框中的值"+wr.value);
		//alert("给表格赋值"+c.rows[i].cells[5].textContent);
    }
    function write_cancel(){
    	document.getElementById("divWrite").style.display="none";
  		document.getElementById("bg_fuceng").style.display="none";
    }
</script>
<script type="text/javascript">
//插入collegeControlnum，某一学院的预算控制数
function insert_submit(element){
      //执行删除行的操作
      //alert("执行删除行的操作");
      var node = element.parentNode.parentNode;
      //var node = element.parentElement.rowIndex;
     // var c=document.getElementById("details");
      var college_name=node.children[0].textContent;
      var contral_num=node.children[3].children[0].value;
      var note=node.children[5].textContent;
      //alert("内容"+node.children[0].textContent);
      //alert("控制数"+node.children[3].children[0].value);
      //alert("事由"+node.children[5].textContent);
      //将college-name传给后台
       $.ajax({
                //cache:true,//保留缓存数据
                type:"post",//为post请求
                url:"school/insertccn",//这是我在后台接受数据的文件名
                data:{"collegeName":college_name,"budgetControlNum":contral_num,"notes":note},   
                //async:false,//false同步，true异步
                dataType:"json",
                success:function (objects) {
                    //alert("传递成功 ");
                    //alert("列  "+c.rows[0].cells.length);
                   	if(objects){
                       	alert("成功写入数据库");
                   	} 
                }
            });
    }
</script>

 	<script type="text/javascript">
 	$(function(){
        //获取List值得ajax
        //获取每一个学院总的控制数。
    	 $("#second").click(function(){
    	var c=document.getElementById("table2");
 		//alert("qqq");
 			$.ajax({
    	    type: "get",
    	    dataType: "json",
    	    url: "school/queryCollegeBudget2",
    	    //complete :function(){$("#load").hide();},//AJAX请求完成时隐藏loading提示
    	    success: function(objects){
    	    	//1,获取上面id为cloneTr的tr元素
    	    	if(objects.length==0){
    	    		alert("查询数据为空");
    	    	}
                var tr = $("#cloneTr2");
                for(var i = c.rows.length-1 ;i > 1;i--){
               		c.deleteRow(i);
            	}
             	$.each(objects, function(index,item){                            
                   //克隆tr，每次遍历都可以产生新的tr                            
                     var clonedTr = tr.clone();
                     var _index = index;
                  
                     //循环遍历cloneTr的每一个td元素，并赋值
                     clonedTr.children("td").each(function(inner_index){                          
                            //根据索引为每一个td赋值
                                  switch(inner_index){
                                        case(0): 
                                           $(this).html(item.collegeName);
                                           break;
                                        case(1):
                                           $(this).html(item.summaryBudget);
                                           break;
/*                                             case(2):
                                            $(this).html(_index + 1);
                                            break; */
                                 }//end switch                        
                  	});//end children.each
              
                 //把克隆好的tr追加原来的tr后面
                 	clonedTr.insertAfter(tr);
                 	clonedTr.show();
              	});//end $each
             	$("#cloneTr2").hide();//隐藏id=clone的tr，因为该tr中的td没有数据，不隐藏起来会在生成的table第一行显示一个空行
              	$("#table2").show();//first是表的id
				}//end success
		});//end ajaxpost   
 		});  
        $("#third").click(function(){
        	alert("getBudgetShow");
     		$.ajax({
        	    type: "get",
        	    dataType: "json",
        	    url: "admin/queryAllCollege",    
        	    success: function(objects){
        	    	//alert("222");
                    var tr = $("#table3odd");
                    var tr2 = $("#table3even");
                 	$.each(objects, function(index,item){   
                       var clonedTr;
                       if(index%2==0)clonedTr = tr.clone();
                       else clonedTr = tr2.clone();
                         var _index = index;
                         //循环遍历cloneTr的每一个td元素，并赋值
                         clonedTr.children("td").each(function(inner_index){                          
                                //根据索引为每一个td赋值
                                      switch(inner_index){
                                             case(0): 
                                               $(this).html(item.name);
                                               break;
                                          
                                     }//end switch                        
                      	});//end children.each
                     	clonedTr.insertAfter(tr2);
                  	});//end $each
                 	$("#table3odd").hide();//
                 	$("#table3even").hide();
                  	$("#table3").show();//first是表的id
    				}//end success
    		});//end ajaxpost 
    		//alert("queryBudgetByCollegeName");
     			$.ajax({
            	    type: "get",
            	    dataType: "json",
            	    url: "school/queryProjectBudget",    
            	    success: function(objects){
            	    	alert("222");
            	    	for ( var i = 0; i < objects.length; i++) {
            	            var name = objects[i].budget.collegeName;
            	            var src = objects[i].projectName;
            	            var sid = objects[i].budget.budgetAmount;
            	           alert("departmentName="+name+"projectName="+src);
            	            var rowNum;
                            var colNum;
                            $("#table3body tr").each(function(){
                                var text = $(this).children("td:first").text();
                                //alert(text);
                                if(text==objects[i].budget.collegeName)
                                { rowNum=$(this).index();
                                     alert("row="+rowNum);
                                     }
                              });
                            $("#table3head th").each(function(){
                                var text = $(this).text();
                               // alert(text);
                                if(text==objects[i].projectName)
                                { colNum=$(this).index();
                                    alert("col="+colNum);
                                     }
                              });
                            $("#table3body tr").eq(rowNum).find("td").eq(colNum).html(objects[i].budget.budgetAmount);  
                          //  $("#chakanBody").children("tr:eq(rowNum)").children("td:eq(colNum)").html(objects[i].budgetAmount);
            	            }
                     	
                      	$("#table3").show();//first是表的id
       				//end success
    			} 
     			});
        });
    	$("#department").click(function(){
    		var c=document.getElementById("table4");
     		//alert("qqq");
     			$.ajax({
        	    type: "get",
        	    dataType: "json",
        	    url: "admin/queryAllCollege",
        	    //complete :function(){$("#load").hide();},//AJAX请求完成时隐藏loading提示
        	    success: function(objects){
        	    	//1,获取上面id为cloneTr的tr元素
        	    	if(objects.length==0){
        	    		alert("查询数据为空");
        	    	}
                    var tr = $("#cloneTr4");
                    for(var i = c.rows.length-2 ;i > 1;i--){
                   		c.deleteRow(i);
                	}
                 	$.each(objects, function(index,item){                            
                       //克隆tr，每次遍历都可以产生新的tr                            
                         var clonedTr = tr.clone();
                         var _index = index;
                      
                         //循环遍历cloneTr的每一个td元素，并赋值
                         clonedTr.children("td").each(function(inner_index){                          
                                //根据索引为每一个td赋值
                                      switch(inner_index){
                                            case(0): 
                                               $(this).html(item.name);
                                               break;
                                            case(1):
                                               $(this).html(item.address);
                                               break;
                                            case(2):
                                                $(this).html(item.head);
                                               break;
                                     }//end switch                        
                      	});//end children.each
                  
                     //把克隆好的tr追加原来的tr后面
                     	clonedTr.insertAfter(tr);
                     	clonedTr.show();
                  	});//end $each
                 	$("#cloneTr4").hide();//隐藏id=clone的tr，因为该tr中的td没有数据，不隐藏起来会在生成的table第一行显示一个空行
                  	$("#table4").show();//first是表的id
    				}//end success
    		});//end ajaxpost 
    	});
    	$("#worker").click(function(){
    		var c=document.getElementById("table5");
     		//alert("qqq");
     			$.ajax({
        	    type: "get",
        	    dataType: "json",
        	    url: "admin/queryAllworker",
        	    //complete :function(){$("#load").hide();},//AJAX请求完成时隐藏loading提示
        	    success: function(objects){
        	    	//1,获取上面id为cloneTr的tr元素
        	    	if(objects.length==0){
        	    		alert("查询数据为空");
        	    	}
                    var tr = $("#cloneTr5");
                    for(var i = c.rows.length-2 ;i > 1;i--){
                   		c.deleteRow(i);
                	}
                 	$.each(objects, function(index,item){                            
                       //克隆tr，每次遍历都可以产生新的tr                            
                         var clonedTr = tr.clone();
                         var _index = index;
                      
                         //循环遍历cloneTr的每一个td元素，并赋值
                         clonedTr.children("td").each(function(inner_index){                          
                                //根据索引为每一个td赋值
                                      switch(inner_index){
                                            case(0): 
                                               $(this).html(item.worker.idworker);
                                               break;
                                            case(1):
                                               $(this).html(item.worker.name);
                                               break;
                                            case(2):
                                                $(this).html(item.worker.sex);
                                               break;
                                            case(3):
                                                $(this).html(item.worker.tel);
                                               break;
                                            case(4):
                                                $(this).html(item.worker.title);
                                               break;
                                            case(5):
                                                $(this).html(item.worker.address);
                                               break;
                                            case(6):
                                                $(this).html(item.worker.birth);
                                               break;
                                            case(7):
                                                $(this).html(item.worker.password);
                                               break;
                                            case(8):
                                                $(this).html(item.worker.collegeName);
                                               break;
                                            case(9):
                                                $(this).html(item.departmentName);
                                               break;
                                     }//end switch                        
                      	});//end children.each
                  
                     //把克隆好的tr追加原来的tr后面
                     	clonedTr.insertAfter(tr);
                     	clonedTr.show();
                  	});//end $each
                 	$("#cloneTr5").hide();//隐藏id=clone的tr，因为该tr中的td没有数据，不隐藏起来会在生成的table第一行显示一个空行
                  	$("#table5").show();//first是表的id
    				}//end success
    		});//end ajaxpost 
    	});
 	});
    </script>
<!--     <script type="text/javascript">
    function query_department(){
    	var c=document.getElementById("table4");
 		//alert("qqq");
 			$.ajax({
    	    type: "get",
    	    dataType: "json",
    	    url: "admin/queryAllCollege",
    	    //complete :function(){$("#load").hide();},//AJAX请求完成时隐藏loading提示
    	    success: function(objects){
    	    	//1,获取上面id为cloneTr的tr元素
                var tr = $("#cloneTr4");
                for(var i = c.rows.length-2 ;i > 1;i--){
               		c.deleteRow(i);
            	}
             	$.each(objects, function(index,item){                            
                   //克隆tr，每次遍历都可以产生新的tr                            
                     var clonedTr = tr.clone();
                     var _index = index;
                  
                     //循环遍历cloneTr的每一个td元素，并赋值
                     clonedTr.children("td").each(function(inner_index){                          
                            //根据索引为每一个td赋值
                                  switch(inner_index){
                                        case(0): 
                                           $(this).html(item.name);
                                           break;
                                        case(1):
                                           $(this).html(item.address);
                                           break;
                                        case(2):
                                            $(this).html(item.head);
                                           break;
                                 }//end switch                        
                  	});//end children.each
              
                 //把克隆好的tr追加原来的tr后面
                 	clonedTr.insertAfter(tr);
                 	clonedTr.show();
              	});//end $each
             	$("#cloneTr4").hide();//隐藏id=clone的tr，因为该tr中的td没有数据，不隐藏起来会在生成的table第一行显示一个空行
              	$("#table4").show();//first是表的id
				}//end success
		});//end ajaxpost    
    }
    </script> -->
<script type="text/javascript">
function delete_department(element) {
	var con=confirm("确认删除该学院信息?"); //在页面上弹出对话框
	if(con==true)
	{
		var node = element.parentNode.parentNode;
	    var index = node.rowIndex;
	    var c=document.getElementById("table4");
	    var college_name=node.children[0].textContent;
	    //alert("内容"+node.children[0].textContent);
	    //将college-name传给后台
	     $.ajax({
	              //cache:true,//保留缓存数据
	              type:"post",//为post请求
	              url:"admin/deleteCollege",//这是我在后台接受数据的文件名
	              data:{"collegeName":college_name},   
	              //async:false,//false同步，true异步
	              dataType:"json",
	              success:function (objects) {  
	            	  if(objects){
	            		  c.deleteRow(index);
	            		  alert("删除成功");
	            	  }               	
	              }
	     });
	}
}
</script>   
    <script type="text/javascript">
    function chooseAll() {
        if ($("#chooseAll").is(':checked')) {
            $(".choose").attr("checked", true);
        } else {
            $(".choose").attr("checked", false);
        }
    }
    </script>
<script type="text/javascript">
    function add_department() {
    	var c=document.getElementById("table4");
    	var index=c.rows.length;
    	var x=c.insertRow(c.rows.length-1);
    	var l = c.rows[0].cells.length;
    	for(var i=0;i<l;i++){
    		var y=x.insertCell(i);
    		if(i==(l-1)){
				y.innerHTML='<input type="button" id="srue" class="button white" value="确认添加" ></input>';
				var bu=document.getElementById("srue");
				bu.onclick=function(){
					var con=confirm("确认添加吗?"); //在页面上弹出对话框
	        		if(con==true)
					{
						//添加该学院到数据库
						var t=bu.parentNode.parentNode;
						var name=t.children[0].children[0].value;
						var address=t.children[1].children[0].value;
						var head=t.children[2].children[0].value;
						$.ajax({
			                //cache:true,//保留缓存数据
			                type:"post",//为post请求
			                url:"admin/insertCollege",//这是我在后台接受数据的文件名
			                data:{"collegeName":name,"address":address,"head":head},   
			                //async:false,//false同步，true异步
			                dataType:"json",
			                success:function (objects) {
			                    alert("传递成功 ");
			                    //alert("列  "+c.rows[0].cells.length);
			                   	if(objects){
			                       	alert("成功添加该学院的信息");
			                       	c.deleteRow(index-1);
			                       	var new_de=c.insertRow(c.rows.length-1);
			                       	new_de.insertCell(0).textContent=name;
			                       	new_de.insertCell(1).textContent=address;
			                       	new_de.insertCell(2).textContent=head;
			                       	new_de.insertCell(3).innerHTML='<img src="images/ic_remove.png"  alt="error" onclick="delete_department(this)"/>';
			                       	//query_department();
			                   	} 
			                }
			            });
					}
	        	}
			}else{
				y.innerHTML='<input class="input_bg" type="text"></input>';
			}
    	}
    }
</script>
<script type="text/javascript">
    function submit_audit() {
    	alert("111");
    	var c=document.getElementById("table2");
    	//alert("start"+c.rows[2].cells[0].textContent);
    	var index=c.rows.length;
    	for(var i=2;i<index;i++){
    		if(c.rows[i].cells[3].children[0].checked==true){
    			var college_name=c.rows[i].cells[0].textContent;
    			alert("222");
        		//alert("选中该学院"+college_name);
        		$.ajax({
                    //cache:true,//保留缓存数据
                    type:"post",//为post请求
                    url:"school/updateByCollege",//这是我在后台接受数据的文件名
                    data:{"collegeName":college_name,"Status":true},   
                    //async:false,//false同步，true异步
                    dataType:"json",
                    success:function (objects) {
                        //alert("行  "+c.rows.length);
                        //alert("列  "+c.rows[0].cells.length);
                    }
                });
        	}
    	} 
    }
</script>
<script type="text/javascript">
function delete_worker(element) {
	var con=confirm("确认删除该员工信息?"); //在页面上弹出对话框
	if(con==true)
	{
		var node = element.parentNode.parentNode;
	    var index = node.rowIndex;
	    var c=document.getElementById("table5");
	    var id=node.children[0].textContent;
	    //alert("内容"+node.children[0].textContent);
	    //将college-name传给后台
	     $.ajax({
	              //cache:true,//保留缓存数据
	              type:"post",//为post请求
	              url:"admin/deleteWorker",//这是我在后台接受数据的文件名
	              data:{"workerid":id},   
	              //async:false,//false同步，true异步
	              dataType:"json",
	              success:function (objects) {  
	            	  if(objects){
	            		  c.deleteRow(index);
	            		  alert("删除成功");
	            	  }               	
	              }
	     });
	}
}
</script>
<script type="text/javascript">
    function add_worker() {
    	var c=document.getElementById("table5");
    	var index=c.rows.length;
    	var x=c.insertRow(c.rows.length-1);
    	var l = c.rows[0].cells.length;
    	for(var i=0;i<l;i++){
    		var y=x.insertCell(i);
    		if(i==(l-1)){
				y.innerHTML='<input type="button" class="button white" id="srue" value="添加" ></input>';
				var bu=document.getElementById("srue");
				bu.onclick=function(){
					var con=confirm("确认添加吗?"); //在页面上弹出对话框
	        		if(con==true)
					{
						//添加该员工到数据库
						var t=bu.parentNode.parentNode;
						//alert("开始");
						//alert(t.children[4].children[0].value);
						/* id，name, sex, tel, title, address, birth, password, college name, department name */
						var workerid=t.children[0].children[0].value;
						var name=t.children[1].children[0].value;
						var sex=t.children[2].children[0].value;
						var tel=t.children[3].children[0].value;
						var title=t.children[4].children[0].value;
						var address=t.children[5].children[0].value;
						var birth=t.children[6].children[0].value;
						var password=t.children[7].children[0].value;
						var collegeName=t.children[8].children[0].value;
						var departmentName=t.children[9].children[0].value;
						$.ajax({
			                //cache:true,//保留缓存数据
			                type:"post",//为post请求
			                url:"admin/insertWorker",//这是我在后台接受数据的文件名
			                data:{"workerid":workerid,"name":name,"sex":sex,"tel":tel,"title":title,"address":address,"birth":birth,"password":password,"collegeName":collegeName,"departmentName":departmentName},   
			                //async:false,//false同步，true异步
			                dataType:"json",
			                success:function (objects) {
			                    //alert("传递成功 ");
			                    //alert("列  "+c.rows[0].cells.length);
			                   	if(objects){
			                       	alert("成功添加该学院的信息");
			                       	c.deleteRow(index-1);
			                       	var new_de=c.insertRow(c.rows.length-1);
			                       	new_de.insertCell(0).textContent=workerid;
			                       	new_de.insertCell(1).textContent=name;
			                       	new_de.insertCell(2).textContent=sex;
			                       	new_de.insertCell(3).textContent=tel;
			                       	new_de.insertCell(4).textContent=title;
			                       	new_de.insertCell(5).textContent=address;
			                       	new_de.insertCell(6).textContent=birth;
			                       	new_de.insertCell(7).textContent=password;
			                       	new_de.insertCell(8).textContent=collegeName;
			                       	new_de.insertCell(9).textContent=departmentName;
			                       	new_de.insertCell(10).innerHTML='<img src="images/ic_remove.png"  alt="error" onclick="delete_department(this)"/>';
			                       	//query_department();
			                   	} 
			                }
			            });
					}
	        	}
			}
    		else if(i==4){
    			y.innerHTML='<select Width="100%" Height="100%" ><option value="财务部门管理员">财务部门管理员</option><option value="财政部门管理员">财政部门管理员</option><option value="业务部门管理员">业务部门管理员</option></select>';
    		}
    		else{
				y.innerHTML='<input type="text" class="input_bg" style="width:70px"></input>';
			}
    	}
    }
</script>
</head>
<body>
<div id="bg_fuceng" style="display:none"></div>
<form id="divId" style="display:none"> 
 	<h3 class="dialog_title">查看明细</h3>
	<table id="details" class="short-form" >
		 <thead>
			<tr>
				<th style='width:10%;'>科室名称</th>
				<th id="div_title" style='width:15%;'>预算建议数</th>						
			</tr>
		</thead>
		<tbody>
		    <tr id="clone" class="even" style ="display: none;">
			   <td></td>
			   <td></td>	
		     </tr>
		</tbody>
	</table>
	<br>
	<a  style="width:400px;height:40px;background:#dfdfdf">
		<input class="div_bt" type="button" value="关闭" onclick="closeDiv()">
	</a>
</form>
<div>
		<img src="images/title.jpg"  alt="山东大学预算编报系统" />
	</div>
	<div id="tab">
		<div id="nav">
		<ul class="ul_bg">
			<li class="on"><img src="images/arrow.png"  alt="山东大学预算编报系统" />    预算采集</li>
			<!-- <li class="off">预算控制</li> -->
			<li id="second" class="off"><img src="images/arrow.png"  alt="山东大学预算编报系统" />    预算控制审核</li>
			<li id="third" class="off"><img src="images/arrow.png"  alt="山东大学预算编报系统" />    查看预算条目</li>
			<li id="department" class="off"><img src="images/arrow.png"  alt="山东大学预算编报系统" />    学院管理</li>
			<li class="off" id="worker"><img src="images/arrow.png"  alt="山东大学预算编报系统" />    员工管理</li>
		</ul>
		</div>
		<div id="tnav">
		<div id="one" class="show">	
			<form id="divWrite" style="display:none"> 
				<h3 class="dialog_title">查看并编辑修改事由</h3>
				<textarea  id="write_reason" type="text"></textarea>
			<br>
				<a  style="width:580px;height:40px;background:#dfdfdf">
					<input id="Write_submit" class="div_bt" type="button" value="确认" onclick="write_submit()">
					<input id="Write_cancel" class="div_bt" type="button" value="取消" onclick="write_cancel()">
				</a>
			</form>
			<table class="rounded-corner" id="table1">
  	  			<thead>
    				<tr>
       	     			<th>学院</th>
            			<th>预算建议金额</th>
            			<th>查看明细</th>
            			<th>预算控制数</th>
						<th>修改事由</th>
						<th style ="display: none;">具体内容</th>
            			<th>确认</th>
        			</tr>
    			</thead>
    			<tbody>
    				<tr id="cloneTr" class="odd" style ="display: none;">
        				<td></td>
            			<td></td>
            			<td onclick="check(this)"><h1 class="input-line" >点击可查看明细</h1></td>
            			<td><input class="input_bg" type="number"/></td>
             			<!-- 要写出编辑框并提交 -->
						<td onclick="write_(this)"><h1 class="input-line" >点击可查看并编辑事由</h1></td>
						<td style ="display: none;"></td>
                        <!-- 点击一条提交有一条的数据 -->
            			<td><input type="button" class="button white" value="确认" onclick="insert_submit(this)"></td>
        			</tr>
    			</tbody>
			</table>
			<input id="row_index" style="display:none">
		</div>
		
		<div id="two" class="hide">
		<!-- 查询学院名称和学院上传的控制数 -->
			<table class="rounded-corner" id="table2">
  	  			<thead>
    				<tr>
       	     			<th>学院</th>
            			<th>学院上传的预算控制金额</th>
            			<th>查看明细</th>
            			<th>审核</th>
        			</tr>
    			</thead>
    			<tbody>
    				<tr class="odd" id="cloneTr2" style ="display: none;">
        				<td></td>
            			<td></td>
            			<td onclick="show_details(this)"><h1 class="input-line" >点击可查看明细</h1></td>
            			<td><input id="choose" type="checkbox" /></td>
        			</tr>
    			</tbody>
			</table>
			<!-- 点击提交将所有的状态都改为已通过 -->
			<p><input id="aaa" type="button" class="button white" value="审核通过并公示" onclick="submit_audit()"></p>
			

		</div>
		
		<div id="three" class="hide">
			<table class="rounded-corner" id="table3">
			<!-- 查询。。。。。 -->
  	  			<thead id="table3head">
    				<tr>
       	     			<th>学院</th>
            			<th>差旅费</th>
            			<th>购置费</th>
						<th>修缮费</th>
            			<th>业务招待费</th>
						<th>印刷费</th>
						<th>办公用品费</th>
						<th>招聘费</th>
						<th>出租车费</th>
						<th>书报资料费</th>
						<th>车辆管理费</th>
						<th>保险费</th>
						<th>员工福利费</th>
						<th>教育培训费</th>
						<th>供暖水电费</th>
						<th>绿植费</th>
						<th>税费</th>
						<th>上网费</th>
        			</tr>
    			</thead>
    			<tbody id="table3body">
    				<tr class="odd" id="table3odd">
        				<td></td>
            			<td></td>
            			<td></td>
						<td></td>
            			<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
        			</tr>
        			<tr class="even" id="table3even">
        				<td></td>
            			<td></td>
            			<td></td>
						<td></td>
            			<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
        			</tr>
<!--    				<tr class="even">
        				<td>数学院</td>           	
            			<td>20000</td>
            			<td>点击可查看明细</td>
						<td>22000</td>
            			<td><input type="checkbox" name="" /></td>
        			</tr>-->
    			</tbody>
			</table>
			<!-- 点击按钮通知后台导出Excel -->
			<form action="school/export" method="post">
				<p><input type="submit" class="button white" value="导出为Excel表格"></p>
			</form>
			
		</div>	
	
		<div id="four" class="hide">
		<!-- 查询所有的信息 -->
		    <table class="short-form" id="table4">
  	  			<thead>
    				<tr>
       	     			<th>学院</th>
       	     			<th>地址</th>
       	     			<th>管理者</th>
            			<th>管理</th>
        			</tr>
    			</thead>
    			<tfoot>
    				<tr>
        				<td colspan="4" align="center"><input type="image" src="images/ic_add.png" onclick="add_department()"></td>
        			</tr>
    			</tfoot>
    			<tbody>
    				<tr id="cloneTr4" style ="display: none;" class="odd">
        				<td></td>
        				<td></td>
       	     			<td></td>
       	     			<!-- 点击会删除该行，并向后台传递学院名称 -->
            			<td><img src="images/ic_remove.png"  alt="error" onclick="delete_department(this)"/></td>
        			</tr>
    			</tbody>
			</table>
		</div>
		<div id="five" class="hide">
		     <table class="short-form" id="table5">
		    <!--  查询所有的信息 -->
  	  			<thead>
    				<tr>
       	     			<th>用户名</th>
       	     			<th>名字</th>
       	     			<th>性别</th>
       	     			<th>电话</th>
       	     			<th>身份</th>
       	     			<th>地址</th>
       	     			<th>出生日期</th>
       	     			<th>密码</th>
            			<th>所在学院</th>
            			<th>所在科室</th>
            			<th>管理</th>
        			</tr>
    			</thead>
    			<tfoot>
    				<tr>
        				<td colspan="10" align="center"><input type="image" src="images/ic_add.png" onclick="add_worker()"></td>
        			</tr>
    			</tfoot>
    			<tbody>
    				<tr id="cloneTr5" style ="display: none;" class="odd">
        				<td></td>
        				<td></td>
        				<td></td>
        				<td></td>
        				<!--<select Width="100%" Height="100%" >
							<option value="caiwu">财务部门管理员</option>
							<option value="caizheng">财政部门管理员</option>
							<option value="yewu">业务部门管理员</option> -->
        				<td></td>
        				<td></td>
        				<td></td>
        				<td></td>
        				<td></td>
        				<td></td>
        				<!-- 点击后删除改行，并向后台返回员工id -->
            			<td><img src="images/ic_remove.png"  alt="error" onclick="delete_worker(this)" /></td>
        			</tr>
    			</tbody>
			</table>
		</div>
	</div>
	
</div>

</body>
</html>