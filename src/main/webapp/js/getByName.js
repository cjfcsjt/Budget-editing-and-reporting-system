/**
 * 
 */
function getByName(){//正式预算,获取采集信息
 		alert("getByName");
 			$.ajax({
        	    type: "get",
        	    dataType: "json",
        	    url: "department/getByName",
        	    success: function(objects){
                    var tr = $("#cloneTr");
                    var tr2 = $("#cloneTr2");
                 	$.each(objects, function(index,item){ 
                       var clonedTr;
                       if(index%2==0)clonedTr = tr.clone();
                       else clonedTr = tr2.clone();
                         var _index = index;
                         alert("111");
                         clonedTr.children("td").each(function(inner_index){
                                      switch(inner_index){
                                            case(0): 
                                            	
                                               $(this).html(item.projectName);
                                               break;
                                            case(1):
                                               $(this).html(item.acqtable.budgetProposal);
                                               break;
                                            case(4):
                                                $(this).html(item.state);
                                                break;
                                     }//end switch                        
                      	});//end children.each
                     	clonedTr.insertAfter(tr2);
                  	});//end $each
                 	$("#cloneTr").hide();
                 	$("#cloneTr2").hide();
                  	$("#yusuanbiao").show();//first是表的id
   				}//end success
			});//end ajaxpost   
			
 			$.ajax({
        	    type: "get",
        	    dataType: "json",
        	    url: "department/queryDCN",
        	    success: function(object){
        	    	$("#budgetControlNum").text("预算控制数："+object.budgetControlNum);
        	    	$("#collegeApprove").text("院级批复说明："+object.notes);
   				}//end success
			});//end ajaxpost 
        }