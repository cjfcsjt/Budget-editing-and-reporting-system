/**
 * 
 */

        function getControlGross()  //
        { 
        	alert("getControlGross");
        var trList = $("#downControlBody").find("tr");
        alert("数组长度："+trList.length);
        var gross=0;
            for (var i=2;i<trList.length;i++) {
            		//选中，返回true，没选中，返回false
                    var tdArr = trList.eq(i).find("td");
                    	 var control = parseFloat(tdArr.eq(2).find("input").val());
                         gross+=control;                  
            }
            if(isNaN(gross)){
            	alert("控制金额未填完！");
            	$("#controlGross").html("预算控制金额：");
            }
            else 
            	$("#controlGross").html("预算控制金额："+gross);
            
        }  
      