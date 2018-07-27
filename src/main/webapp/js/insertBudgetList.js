/**
 * 
 */
//修改list为for循环
// <script>
        function insertBudgetList()  //正式预算，已调试
        { 
        	alert("insertBudgetList");
        	var con=confirm("确认上报正式预算条目？");
        	if(con==true){
        		// var data=[];
        	        var trList = $("#budgetBody").find("tr");
        	        //alert("数组长度："+trList.length);
        	            for (var i=2;i<trList.length;i++) {
        	                    var tdArr = trList.eq(i).find("td");
        	                    
        	                    var projectName = tdArr.eq(0).text();//条目
        	                    var budgetAmount = tdArr.eq(2).find('input').val();//控制金额
        	                    var budgetNotes = tdArr.eq(3).find('input').val();//详细描述
        	                    var mydate = new Date();
        	                    var t=mydate.toLocaleString();
        	                    alert(t);
        	                    /*var row= {}; 
        	                    row['projectName']=projectName;
        	                    row['budgetAmount']=budgetAmount;
        	                    row['budgetNotes']=budgetNotes;
        	                    row['auditTime']=t;
        	                    row['auditResult']=false;*/

        	                   // data.push(row); 
        	                   // alert("条目:"+"金额:"+"描述:");
        	                    $.ajax({
                	                url:'department/insertBudget',
                	                type:'post',
                	                data:{"projectName":projectName,"budgetAmount":budgetAmount,"budgetNotes":budgetNotes,"auditTime":t,"auditResult":false},
                	                dataType:"json", 
                	                cache:false,
                	                success:function(data){
                	                   alert(data.result);
                	                }
                	          });
        	            }
        	           
        	}

        }  
     //   </script>