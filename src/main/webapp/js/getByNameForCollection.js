/**
 * 
 */
 //<script type="text/javascript">
         function getByNameForCollection(){
 		alert("getByNameForCollection");

 			$.ajax({
        	    type: "get",
        	    dataType: "json",
        	    url: "department/getByName",
        	    success: function(objects){
        	    	if(objects.length==0){$("#caijibiaoshow").hide();}
        	    	else{var tr = $("#addrowshow");
                    var tr2 = $("#addrowshow2");
                 	$.each(objects, function(index,item){ 
                       var clonedTr;
                       if(index%2==0)clonedTr = tr.clone();
                       else clonedTr = tr2.clone();
                         var _index = index;
                        
                         clonedTr.children("td").each(function(inner_index){
                                      switch(inner_index){
                                            case(0): 
                                               $(this).html(item.projectName);
                                               break;
                                            case(1):
                                               $(this).html(item.acqtable.budgetProposal);
                                               break;
                                            case(2):
                                                $(this).html(item.acqtable.notes);
                                                break;
                                            case(3):
                                                $(this).html(item.acqtable.auditResult);
                                                break;
                                     }//end switch                        
                      	});//end children.each
                     	clonedTr.insertAfter(tr2);
                  	});//end $each
                 	$("#addrowshow").hide();
                 	$("#addrowshow2").hide();
                  	$("#caijibiaoshow").show();//first是表的id
                  	}
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
        // </script>