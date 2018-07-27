<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset=UTF-8">
<title>业务部门</title>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/insertACList.js"></script>
<script type="text/javascript" src="js/getByName.js"></script>
<script type="text/javascript" src="js/getBudgetByName.js"></script>
<script type="text/javascript" src="js/insertBudgetList.js"></script>
<script type="text/javascript" src="js/addRow.js"></script>
<script type="text/javascript" src="js/deleteRow.js"></script>
<script type="text/javascript" src="js/getSuggestGrossForD.js"></script>
<script type="text/javascript" src="js/getControlGrossForD.js"></script>
<script type="text/javascript" src="js/getByNameForCollection.js"></script>
<script type="text/javascript" src="js/getCaijiGross.js"></script>


	<link rel="stylesheet" type="text/css" href="css/form.css" />
	<script type="text/javascript">
    // JS实现选项卡切换
        var isTwoFirst=true;
        var isThreeFirst=true;
        
    	window.onload=function(){
        var myTab = document.getElementById("tab");    //整个div
    	var mynav = document.getElementById("nav");    //整个div
        var myUl = mynav.getElementsByTagName("ul")[0];//一个节点
        var myLi = myUl.getElementsByTagName("li");    //数组
    	var myTnav = document.getElementById("tnav");   //整个div
        var myDiv = myTnav.getElementsByTagName("div"); //数组
        getByNameForCollection();
        
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
                  // getByStatus();
                   break;
                case(1):
                if(isTwoFirst){ getByName();  isTwoFirst=false;}
                   break;
                case(2): 
                if(isThreeFirst){ getBudgetByName(); isThreeFirst=false;}
                    break;
                
         } //end switch  
            }
         
        }}
     </script>
     

</head>
<body>
	<div>
		<img src="images/title.jpg"  alt="山东大学预算编报系统" />
	</div>
<div id="tab">
	<div id="nav">
	<ul class="ul_bg">
	  <li class="on"><img src="images/arrow.png"  alt="山东大学预算编报系统" />    采集</li>
	  <li class="off"><img src="images/arrow.png"  alt="山东大学预算编报系统" />    正式预算</li>
		<li class="off"><img src="images/arrow.png"  alt="山东大学预算编报系统" />    查看预算</li>
	</ul>
	</div>
	<div id="tnav">
		<div id="1" class="show">
		<!-- <input type="button" value="获取" onclick="getByNameForCollection()" > -->
		<table class="rounded-corner" id="caijibiaoshow">
    	          <thead>
    		            <tr>
       	     	           <th>条目</th>
            	           <th>预算建议金额</th>
            	           <th>详细描述</th>
            	           <th>审核状态</th>
            	           
        	           </tr>
    	          </thead>
                  <tbody id="collectionshowBody">
    	                <tr class="odd" id="addrowshow">
        	               <td></td>
                           <td></td>
                           <td></td> 
                           <td></td>
                        </tr>
                         <tr class="odd" id="addrowshow2">
        	               <td></td>
                           <td></td>
                           <td></td> 
                           <td></td>
                        </tr>
                  </tbody>
                  <tfoot>
        			<tr>
        	            <!-- <td>采集预算总额：</td> -->
				        <td colspan="4">   </td>
				     
                    </tr>
    			</tfoot>
                  
            </table>
            <h1 style="height:20px;"></h1>
			<table class="rounded-corner" id="caijibiao">
    	          <thead>
    		            <tr>
       	     	           <th>条目</th>
            	           <th>预算建议金额</th>
            	           <th>详细描述</th>
            	           <th></th>
        	           </tr>
    	          </thead>
                  <tbody id="collectionBody">
    	                <tr class="odd" id="addrow">
        	               <td>
        	                   <select class="select_s" name="expressType"> 
					              <option value="">差旅费</option> 
					              <option value="">购置费</option> 
					              <option value="">修缮费</option> 
					              <option value="">业务招待费</option> 
					              <option value="">印刷费</option> 
					              <option value="">办公用品费</option> 
					              <option value="">招聘费</option> 
					              <option value="">公务出租车费</option> 
					              <option value="">书报资料费</option> 
					              <option value="">车辆管理费</option> 
					              <option value="">员工福利费</option> 
					              <option value="">教育培训经费</option> 
					              <option value="">供暖水电费</option> 
					              <option value="">绿植费</option> 
					              <option value="">税费</option> 
					              <option value="">上网费</option> 
				               </select>   
				               
                           </td>
                           <td><input type="text" class="input_bg"/></td>
                           <td><input type="text" class="input_bg"/></td> 
                           <td>
                              <img type="image" id="deletee1" src="images/ic_remove.png" onclick=""><img>
                           </td>
                        </tr>
                  </tbody>
                  <tfoot>
    				<tr>
        				<td colspan="4" align="center"><input type="image" src="images/ic_add.png" id="addNew" onclick="addRow()"></td>
        			</tr>
<!--         			<tr>
        	            <td colspan="3" id="caijiGross" onclick="getCaijiGross()">采集预算总额：</td>
                    </tr> -->
    			</tfoot>
                  
            </table>
            <p>
		      <input type="button" class="button white" value="上报" onclick="insertACList()" >
		    </p>
	  </div>
		
	  <div id="2" class="hide">	
	      <table id="yusuanbiao" class="rounded-corner" >
				 <thead>
				<tr>
					<th>条目</th>
            	    <th>预算建议金额</th>
            	    <th>预算控制金额</th>
            	    <th>详细描述</th>
            	    <th>审核状态</th>				
				</tr>
			    </thead>
			<tbody id="budgetBody">
			    <tr id="cloneTr" class="odd" >
				   <td></td>
 				   <td></td>
 				   <td><input type="text" /></td>
                   <td><input type="text" /></td>	
                   <td></td>	
			     </tr>
			     <tr id="cloneTr2" class="even" >
				   <td></td>
 				   <td></td>
 				   <td><input type="text" /></td>
                   <td><input type="text" /></td>	
                   <td></td>	
			     </tr>
			 </tbody>
			 <tfoot>
    	       <tr>
		          <td id="budgetControlNum">预算控制数：</td>  
			      <td id="budgetSuggestG" onclick="getSuggestGrossForD()">预算建议总额：</td>  
			      <td id="budgetControlG" onclick="getControlGrossForD()">预算控制总额：</td>  
			      <td>审核状态：</td>
			      <td><input type="button" class="button white" value="上报" onclick="insertBudgetList()" ></td>
               </tr>
               <tr>
                  <td id="collegeApprove">院级批复说明:</td> 
                <!--   <td><input type="button" value="获取" onclick="getByName()" ></td>  -->
               </tr>
         </tfoot>
			</table> 
	 </div>
		
	 <div id="3" class="hide">
		 <table class="rounded-corner" id="chakan">
    	 <thead>
    		   <tr>
       	     	  <th>条目</th>
            	  <th>预算金额</th>
            	  <th>详细说明</th>
        	   </tr>
    	 </thead>
         <tbody id="gongshiBody">
    	       <tr class="odd" id="gongshi1">
                  <td></td>
                  <td></td>
                  <td></td>
               </tr>
			   <tr class="even" id="gongshi2">
                  <td></td>
                  <td></td>
                  <td></td>
               </tr>
         </tbody>
         <tfoot>
    	       <tr>
        	      <td colspan="3">科级预算总额:</td>
               </tr>
         </tfoot>
         </table>
         
            <!-- <input type="button" value="获取" onclick="getBudgetByName()" > -->
            </p>
	 </div>

 </div>
</div>
</body>
</html>