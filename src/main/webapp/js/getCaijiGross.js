/**
 * keshi
 */
 //<script type="text/javascript">
          function getCaijiGross()  //
        { 
        	alert("getCaijiGross");
        var trList = $("#collectionBody").find("tr");
        alert("数组长度："+trList.length);
        var gross=0;
            for (var i=1;i<trList.length;i++) {
            		//选中，返回true，没选中，返回false
                    var tdArr = trList.eq(i).find("td");
                    	 var caiji = parseFloat(tdArr.eq(1).find("input").val());
                         gross+=caiji;                  
            }
            if(isNaN(gross)){
            	alert("采集条目未填写完整！");
            	$("#controlGross").html("采集预算总额：");
            }
            else 
            	$("#controlGross").html("采集预算总额："+gross);
        }  
         // </script>