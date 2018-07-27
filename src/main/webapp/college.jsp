<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学院财务</title>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>

<script type="text/javascript" src="js/getByStatus.js"></script>
<script type="text/javascript" src="js/queryALL.js"></script>
<script type="text/javascript" src="js/updates.js"></script>
<script type="text/javascript" src="js/queryAcByDC.js"></script>
<script type="text/javascript" src="js/queryALL2.js"></script>
<script type="text/javascript" src="js/getControlGross.js"></script>
<script type="text/javascript" src="js/getSuggestGross.js"></script>
<script type="text/javascript" src="js/insertDCNs.js"></script>
<script type="text/javascript" src="js/lookForDetailAcquisition.js"></script>
<script type="text/javascript" src="js/queryBudgetByCollegeName.js"></script>
<script type="text/javascript" src="js/updateBudgetStatusList.js"></script>
<script type="text/javascript" src="js/updateByCollegefst.js"></script>
<script type="text/javascript" src="js/queryALLFromSST.js"></script>
<script type="text/javascript" src="js/queryBudgetByDC.js"></script>
<script type="text/javascript" src="js/lookForDetailBudget.js"></script>

	<link rel="stylesheet" type="text/css" href="css/form.css" />
	<script type="text/javascript">
    // JS实现选项卡切换
        /* var isTwoFirst=true;
        var isThreeFirst=true;
        var isFourFirst=true;
        var isFiveFirst=true;
        var isSixFirst=true; */
    	window.onload=function(){
    	//	alert(isFirst);
        var myTab = document.getElementById("tab");    //整个div
    	var mynav = document.getElementById("nav");    //整个div
        var myUl = mynav.getElementsByTagName("ul")[0];//一个节点
        var myLi = myUl.getElementsByTagName("li");    //数组
    	var myTnav = document.getElementById("tnav");   //整个div
        var myDiv = myTnav.getElementsByTagName("div"); //数组
        getByStatus();
        /* getByStatus();
        queryALL();
        queryALL2();
        queryBudgetByCollegeName();
        queryALLFromSST(); */
        for(var i = 0; i<myLi.length;i++){
            myLi[i].index = i;
            myLi[i].onclick = function(){
                for(var j = 0; j < myLi.length; j++)
				{
                    myLi[j].className="off";
                    myDiv[j].className = "hide";
                }
                this.className = "on";
                myDiv[this.index].className = "show";
               
                	switch(this.index){
                    case(0): 
                       getByStatus();
                       break;
                    case(1):
                   // if(isTwoFirst){
                	   queryALL(); // isTwoFirst=false;}
                       break;
                    case(2): 
                   // if(isThreeFirst){ 
                	   queryALL2(); //isThreeFirst=false;}
                        break;
                    case(3):
                   // if(isFourFirst){
                	   queryBudgetByCollegeName(); //isFourFirst=false;}
                        break;
                    case(4):
                    //if(isFiveFirst){   
                    	queryALLFromSST();//isFiveFirst=false;}
                        break;
                    case(5):
                    	//if(isSixFirst){
                    		getBudgetForShow();//isSixFirst=false;}
             } //end switch   
            }
          }       
        }
    	//=change();
    </script>
    
   	<script type="text/javascript">
   	function getBudgetForShow(){//公示,queryDepartmentByCollegeName应为管理层方法，未写,queryAllDepartment有问题
 		alert("getBudgetForShow");
 		$.ajax({
    	    type: "get",
    	    dataType: "json",
    	    url: "admin/queryDepartmentByCollegeName",    
    	    success: function(objects){
                var tr = $("#chakanRow1");
                var tr2 = $("#chakanRow2");
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
             	$("#chakanRow1").hide();//
             	$("#chakanRow2").hide();
              	$("#chakan").show();//first是表的id
				}//end success
		});//end ajaxpost 
		//alert("queryBudgetByCollegeName");
 			$.ajax({
        	    type: "get",
        	    dataType: "json",
        	    data:{"auditResult":true},
        	    url: "college/getBudgetByStatus",    
        	    success: function(objects){
        	    	for ( var i = 0; i < objects.length; i++) {
        	            var name = objects[i].departmentName;
        	            var src = objects[i].projectName;
        	            var sid = objects[i].budget.budgetAmount;
        	           // alert("departmentName="+name+"projectName="+src);
        	            var rowNum;
                        var colNum;
                        $("#chakanBody tr").each(function(){
                            var text = $(this).children("td:first").text();
                            //alert(text);
                            if(text==objects[i].departmentName)
                            { rowNum=$(this).index();
                                 //alert("row="+rowNum);
                                 }
                          });
                        $("#chakanHead th").each(function(){
                            var text = $(this).text();
                           // alert(text);
                            if(text==objects[i].projectName)
                            { colNum=$(this).index();
                                // alert("col="+colNum);
                                 }
                          });
                        $("#chakanBody tr").eq(rowNum).find("td").eq(colNum).html(objects[i].budget.budgetAmount);  
                      //  $("#chakanBody").children("tr:eq(rowNum)").children("td:eq(colNum)").html(objects[i].budgetAmount);
        	            }
                 	
                  	$("#chakan").show();//first是表的id
   				//end success
			} 
 			});
        }
    
 </script >
 
 
 <!-- <script type="text/javascript">
    function add_department() {//未调试
    	var c=document.getElementById("guanli");
    	var index=c.rows.length;
    	var x=c.insertRow(c.rows.length-1);
    	var l = c.rows[0].cells.length;
    	for(var i=0;i<l;i++){
    		var y=x.insertCell(i);
    		if(i==(l-1)){
				y.innerHTML='<input type="button" id="srue" value="确认添加" ></input>';
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
			                url:"admin/insertDepartment",//这是我在后台接受数据的文件名
			                data:{"departmentName":name,"address":address,"head":head},   
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
				y.innerHTML='<input type="text"></input>';
			}
    	}
    }
</script>
 
 <script type="text/javascript">
function delete_department(element) {//未调试
	var con=confirm("确认删除该科室信息?"); //在页面上弹出对话框
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
	              data:{"name":college_name},   
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
</script>    -->
 
</head>
<body>

<div id="bg_fuceng" style="display:none"></div>
<form id="divId" style="display:none"> 
 	<h3 class="dialog_title">查看明细</h3>
	<table id="details" class="short-form" >
		 <thead>
			<tr>
				<th style='width:10%;'>条目</th>
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
	<ul id="tabParent">
		
		<li class="on"><img src="images/arrow.png"  alt="山东大学预算编报系统" />    预算采集</li>
		<li class="off"><img src="images/arrow.png"  alt="山东大学预算编报系统" />    预算上报</li>
		<li class="off"><img src="images/arrow.png"  alt="山东大学预算编报系统" />    预算控制下达</li>
		<li class="off"><img src="images/arrow.png"  alt="山东大学预算编报系统" />    预算控制审核</li>
		<li class="off"><img src="images/arrow.png"  alt="山东大学预算编报系统" />    预算控制上报</li>
		<li class="off"><img src="images/arrow.png"  alt="山东大学预算编报系统" />    公示</li>
		<!-- <li class="off"><img src="images/arrow.png"  alt="山东大学预算编报系统" />    科室管理</li> -->
	</ul>
	</div>
	<div id="tnav">
		<div id="1" class="show">
			<table class="rounded-corner" id="caijibiao">
    	          <thead>
    		            <tr>
       	     	           <th>科室</th>
            	           <th>条目</th>
            	           <th>预算建议金额</th>
            	           <th>详细描述</th>
            	           <th>审核</th>
        	           </tr>
    	          </thead>           
                  <tbody id="collectBody">
    	                <tr class="odd" id="collect1">
        	               <td></td>
                           <td></td>
                           <td></td> 
                           <td></td>
                           <td>
                           <input type="checkbox" name="" />
                           </td> 
                        </tr>
                        <tr class="even" id="collect2">
        	               <td></td>
                           <td></td>
                           <td></td> 
                           <td></td>
                           <td>
                           <input type="checkbox" name="" />
                           </td> 
                        </tr>
                  </tbody>
           </table>
		   <p>
			   <input type="button" class="button white" value="审核下达与上传" onclick="updates()" >
			   <!-- <input type="button" value="获取" onclick="getByStatus()" > -->
		   </p>
	  </div>
		
	  <div id="2" class="hide">	
		  <table class="rounded-corner" id="shangbaobiao">
    	  <thead>
    		    <tr>
       	     	   <th>科室</th>
            	   <th>预算建议金额</th>
            	   <th>说明</th>
        	    </tr>
    	  </thead>
          <tbody id="suggestUpBody">
    	        <tr class="odd" id="upSuggest1">
                   <td></td>
                   <td></td>
                   <td><u class="input-line" >点击可查看明细</u></td>
                </tr>
                <tr class="even" id="upSuggest2">
                   <td></td>
                   <td></td>
                   <td><u class="input-line" >点击可查看明细</u></td>
                </tr>
         </tbody>
         </table>
         <p><input type="button" value="上报" class="button white" onclick="updateByCollegefst()" >
            <!-- <input type="button" value="获取" onclick="queryALL()" > -->
            </p>
	 </div>
		
	 <div id="3" class="hide">
		 <table class="rounded-corner" id="xiadabiao">
    	 <thead>
    		   <tr>
       	     	  <th>科室</th>
            	  <th>预算建议金额</th>
            	  <th>预算控制</th>
            	  <th>（给科室的）批复</th>
        	   </tr>
    	 </thead>
         <tbody id="downControlBody">
    	       <tr class="odd" id="downControl1">
                  <td></td>
                  <td></td>
                  <td><input type="text" /></td>
                  <td><input type="text" /></td>
               </tr>
			   <tr class="even" id="downControl2">
                  <td></td>
                  <td></td>
                  <td><input type="text" /></td>
                  <td><input type="text" /></td>
               </tr>
         </tbody>
         <tfoot>
    	       <tr>
                  <td id="controlNum">预算控制数：</td> 
		          <td id="suggestGross" onclick="getSuggestGross()">预算建议总额：</td>  
		          <td id="controlGross" onclick="getControlGross()">预算控制总额：</td>  
               </tr>
               <tr>
                  <td id="schoolApprove">校级批复说明:</td> 
               </tr>
         </tfoot>
         </table>
         <p>
		   <input type="button" value="下达" class="button white" onclick="insertDCNs()" >
		  <!--  <input type="button" value="获取" onclick="queryALL2()" > -->
		 </p>
	 </div>
	 <div id="4" class="hide">
		 <table class="rounded-corner" id="kongzhibiao">
    	          <thead>
    		            <tr>
       	     	           <th>科室</th>
            	           <th>条目</th>
            	           <th>预算控制金额</th>
            	           <th>详细说明</th>
            	           <th>全选</th>
        	           </tr>
    	          </thead>           
                  <tbody id="controlBody">
    	                <tr class="odd" id="control1">
        	               <td></td>
                           <td></td>
                           <td></td> 
                           <td></td> 
                           <td>
                           <input type="checkbox" name="" />
                           </td> 
                        </tr>
                        <tr class="even" id="control2">
        	               <td></td>
                           <td></td>
                           <td></td> 
                           <td></td> 
                           <td>
                           <input type="checkbox" name="" />
                           </td> 
                        </tr>
                  </tbody>
           </table>
		   <p>
			   <input type="button" value="审核下达" class="button white" onclick="updateBudgetStatusList()" >
			  <!--  <input type="button" value="获取" onclick="queryBudgetByCollegeName()" > -->
		   </p>
	 </div>
	 <div id="5" class="hide">	
		  <table class="rounded-corner" id="yusuanbiao">
    	  <thead>
    		    <tr>
       	     	   <th>科室</th>
            	   <th>预算控制金额</th>
            	   <th>说明</th>
        	    </tr>
    	  </thead>
          <tbody id="upBudgetBody">
    	        <tr class="odd" id="upBudget1">
                   <td></td>
                   <td></td>
                   <td><u>查看明细</u></td>
                </tr>
                 <tr class="even" id="upBudget2">
                   <td></td>
                   <td></td>
                   <td><u>查看明细</u></td>
                </tr>
         </tbody>
         </table>
         <p><input type="button" value="上报" class="button white" onclick="" ></p>
	 </div>
	 <div id="6" class="hide">
		 <table class="rounded-corner" id="chakan">
    	 <thead id="chakanHead">
    		   <tr>
       	     	  <th>科室</th>
            	  
            	  <th>差旅费</th>
            	  <th>购置费</th>
            	  <th>修缮费</th>
            	  <th>业务招待费</th>
            	  <th>印刷费</th>
            	  <th>办公用品费</th>
            	  <th>招聘费</th>
            	  <th>公务出租车费</th>
            	  <th>书报资料费</th>
            	  <th>车辆管理费</th>
            	  <th>员工福利费</th>
            	  <th>教育培训经费</th>
            	  <th>供暖水电费</th>
            	  <th>绿植费</th>
            	  <th>税费</th>
            	  <th>上网费</th>
        	   </tr>
    	 </thead>
         <tbody id="chakanBody">
    	       <tr class="odd" id="chakanRow1">
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
			   <tr class="even" id="chakanRow2">
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
         </tbody>
         <tfoot>
    	       <tr>
        	      <td colspan="17">科级预算总额:</td>
               </tr>
         </tfoot>
         </table>
         <form action="college/export" method="post">
         <p><input type="submit" class="button white" value="导出为Excel表格" onclick="" ></p>
         </form>
	 </div>
	 <!-- <div id="7" class="hide">
		 <table class="short-form" id="guanli">
  	  			<thead>
    				<tr>
       	     			<th>科室</th>
       	     			<th>负责人</th>
       	     			<th>联系电话</th>
            			<th></th>
        			</tr>
    			</thead>
    			<tbody id="guanliBody">
    				<tr class="odd" id="guanlirow">
        				<td></td>
        				<td></td>
        				<td></td>
            			<td><img src="images/ic_remove.png"  alt="error" /></td>
        			</tr>
    				
    			</tbody>
			</table>
			<p><input type="image" src="images/ic_add.png" onclick=""></p>
	 </div> -->
 </div>
</div>
</body>
</html>