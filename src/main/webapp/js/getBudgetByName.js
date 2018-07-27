/**
 * 
 */
function getBudgetByName(){//正式预算,获取预算信息
 		alert("getBudgetByName");
 			$.ajax({
        	    type: "get",
        	    dataType: "json",
        	    url: "department/getBudgetByName",
        	    success: function(objects){
                    var tr = $("#gongshi1");
                    var tr2 = $("#gongshi2");
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
                                               $(this).html(item.projectName);
                                               break;
                                            case(1):
                                               $(this).html(item.budget.budgetAmount);
                                               break;
                                            case(2):
                                                $(this).html(item.budget.budgetNotes);
                                                break;
                                     }//end switch                        
                      	});//end children.each
                     	clonedTr.insertAfter(tr2);
                  	});//end $each
                 	$("#gongshi1").hide();//
                 	$("#gongshi2").hide();
                  	$("#yusuanbiao").show();//first是表的id
   				}//end success
			});//end ajaxpost   
        }