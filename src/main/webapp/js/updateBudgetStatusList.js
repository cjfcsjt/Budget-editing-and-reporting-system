/**
 * 
 */
//修改list为for循环
//<script type="text/javascript">
      function updateBudgetStatusList()  //
        { 
        	alert("updateBudgetStatusList");
        	var con=confirm("确认提交审核结果？");
        	if(con==true){
        		//var data=[];
                var trList = $("#controlBody").find("tr");
                alert("数组长度："+trList.length);
                    for (var i=2;i<trList.length;i++) {
                    		//选中，返回true，没选中，返回false
                    		
                            var tdArr = trList.eq(i).find("td");
                            if(tdArr.eq(4).find('input').is(":checked")){
                            var departmentName = tdArr.eq(0).text();//
                           // alert(project2);
                            var projectName = tdArr.eq(1).text();//
                            
                            /*var row= {}; 
                            row['departmentName']=departmentName;
                            row['projectName']=projectName;
                            row['auditResult']=true;
                            data.push(row); */
                            
                            $.ajax({
                                url:'college/updateBudgetStatus',
                                type:'post',
                                data:{"departmentName":departmentName,"projectName":projectName,"auditResult":true},
                                dataType:"json", 
                                cache:false,
                                success:function(data){
                                   alert(data.result);
                                }
                          });
                            }
                    }
                    
                    
                    alert("第二步，insert");
                    $.ajax({
                	    type: "get",
                	    dataType: "json",
                	    url: "college/insertSST",
                	    data:{"notes":"说明","auditTime":"2018-07-21"},
                	    success: function(data){
                           alert(data.result);
           				}//end success
        			});//end ajaxpost  
        	}
        }  
     // </script>