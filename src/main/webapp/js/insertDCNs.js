/**
 * 
 */
//修改list为for循环
        function insertDCNs()  
        { 
        	alert("insertDCNs");
        	var con=confirm("确认下达控制数？");
        	if(con==true){
        		//var data=[];
                var trList = $("#downControlBody").find("tr");
                var repeat =0;
                var insert =0;
                var error =0;
                    for (var i=2;i<trList.length;i++) {
                    		//选中，返回true，没选中，返回false
                            var tdArr = trList.eq(i).find("td");
                    		
                            var departmentName = tdArr.eq(0).text();
                            var budgetControlNum = tdArr.eq(2).find('input').val();
                            var notes = tdArr.eq(3).find('input').val();
                           
                            /*var row= {}; 
                            row['departmentName']=departmentName;
                            row['budgetControlNum']=budgetControlNum;
                            row['notes']=notes;
                            data.push(row); */
                          //  alert("科室:"+departmentName+"控制金额:"+budgetControlNum+"描述:"+notes);
                            $.ajax({
                                url:'college/insertDCN',
                                type:'post',
                                data:{"departmentName":departmentName,"budgetControlNum":budgetControlNum,"notes":notes},
                                dataType:"json", 
                                cache:false,
                                success:function(data){
                                   //alert(data.data.state);
                                	if(parseInt(data.data.state) == 1)
                                		{insert= insert+1;}
                                	else if (parseInt(data.data.state) == -1)
                                		{repeat +=1;}
                                	else 
                                		{error+=1;}
                                }
                          });
                    }
                    alert("下达："+insert+"重复下达:"+repeat+"出错:"+error);
                   
        	}
        }  
       