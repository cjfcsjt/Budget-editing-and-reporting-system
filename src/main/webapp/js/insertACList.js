/**
 * 科室级别，插入采集列表，传递list到后台
 */
//<script>
        function insertACList()  //采集页面，有问题
        { 
        	alert("insertACList");
        	var con=confirm("确认上报采集条目？");
        	if(con==true)
        	{
        		//var data=[];
                var trList = $("#collectionBody").find("tr");
                    for (var i=0;i<trList.length;i++) {
                    		//选中，返回true，没选中，返回false
                            var tdArr = trList.eq(i).find("td");
                    		
                            var projectName = tdArr.eq(0).find('select').find('option:selected').text();//条目
                            var budgetProposal = tdArr.eq(1).find('input').val();//建议金额
                            var notes = tdArr.eq(2).find('input').val();//详细描述
                            var mydate = new Date();
                            var t=mydate.toLocaleString();
                            alert(t);
                           /* var row= {}; 
                            row['projectName']=projectName;
                            row['budgetProposal']=budgetProposal;
                            row['notes']=notes;
                            row['auditTime']=t;
                            data.push(row); */
                            $.ajax({
                                url:'department/insert',
                                type:'post',
                                data:{"projectName":projectName,"budgetProposal":budgetProposal,"notes":notes,"auditTime":"111"},
                                dataType:"json", 
                                cache:false,
                                success:function(data){
                                   alert(data.result);
                                }
                          });
                            //alert("条目:"+projectName+"金额:"+budgetProposal+"描述:"+notes+"时间："+t);
                    }
                   
        	}
        }  
        
   