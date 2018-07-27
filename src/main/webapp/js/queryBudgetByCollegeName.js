/**
 * 
 */

   
    	function queryBudgetByCollegeName(){//
 		alert("queryBudgetByCollegeName");
 			$.ajax({
        	    type: "get",
        	    dataType: "json",
        	    url: "college/queryBudgetByCollegeName",
        	    success: function(objects){
                    var tr = $("#control1");
                    var tr2 = $("#control2");
                 	$.each(objects, function(index,item){ 
                 		
                       //克隆tr，每次遍历都可以产生新的tr  
                       var clonedTr;
                       if(index%2==0)clonedTr = tr.clone();
                       else clonedTr = tr2.clone();
                         var _index = index;
                         //循环遍历cloneTr的每一个td元素，并赋值
                         clonedTr.children("td").each(function(inner_index){                          
                                //根据索引为每一个td赋值
                        	
                                      switch(inner_index){
                                            case(0): 
                                               $(this).html(item.departmentName);
                                               break;
                                            case(1):
                                               $(this).html(item.projectName);
                                               break;
                                            case(2): 
                                                $(this).html(item.budget.budgetAmount);
                                                break;
                                            case(3):
                                                $(this).html(item.budget.budgetNotes);
                                                break;
                                     }//end switch                        
                      	});//end children.each
                     //把克隆好的tr追加原来的tr后面
                     	clonedTr.insertAfter(tr2);
                  	});//end $each
                 	$("#control1").hide();//隐藏id=clone的tr，因为该tr中的td没有数据，不隐藏起来会在生成的table第一行显示一个空行
                 	$("#control2").hide();
                  	$("#kongzhibiao").show();//first是表的id
   				}//end success
			});//end ajaxpost   
        }
  