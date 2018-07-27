/**
 * 
 */

        function getSuggestGross()  //
        { 
        	alert("getSuggestGross");
        var trList = $("#downControlBody").find("tr");
        alert("数组长度："+trList.length);
        var gross=0;
            for (var i=2;i<trList.length;i++) {
            		//选中，返回true，没选中，返回false
                    var tdArr = trList.eq(i).find("td");
            		var suggest = parseFloat(tdArr.eq(1).text());
                    alert(tdArr.eq(1).text());
                    gross=gross+suggest;
                   
            }
            $("#suggestGross").text("预算建议金额："+gross);
        }  
  