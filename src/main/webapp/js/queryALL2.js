/**
 * 
 */
//这个方法不对应controller中的queryAll2,而是调用controller中的queryALL
    	function queryALL2(){
 		alert("queryALL2");
 			$.ajax({
        	    type: "get",
        	    dataType: "json",
        	    url: "college/queryALL",
        	    success: function(objects){
                    var tr = $("#downControl1");
                    var tr2 = $("#downControl2");
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
                                            	{alert("00");
                                               $(this).html(item.departmentName);
                                               break;
                                            	}
                                            case(1):
                                            	{alert("111");
                                               $(this).html(item.firstSummaryTable.summaryBudget);
                                               break;
                                            	}
                                     }//end switch                        
                      	});//end children.each
                     //把克隆好的tr追加原来的tr后面
                     	clonedTr.insertAfter(tr2);
                  	});//end $each
                 	$("#downControl1").hide();
                 	$("#downControl2").hide();
                  	$("#xiadabiao").show();//first是表的id
   				}//end success
			});//end ajaxpost   
			alert("queryByCollegeName");
			$.ajax({
        	    type: "get",
        	    dataType: "json",
        	    url: "college/queryByCollegeName",
        	    success: function(object){
        	    	$("#controlNum").text("预算控制数："+object.budgetControlNum);
        	    	$("#schoolApprove").text("校级批复说明："+object.notes);
   				}//end success
			});//end ajaxpost 
        }
  