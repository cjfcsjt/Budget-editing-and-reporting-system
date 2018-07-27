/**
 * 
 */

	function queryAcByDC(departmentName){//
 		alert("queryAcByDC");
 		document.getElementById("bg_fuceng").style.display="";
 			$.ajax({
        	    type: "get",
        	    dataType: "json",
        	    url: "college/queryAcByDC",
        	    data:{"departmentName":departmentName},
        	    success: function(objects){
        	    	var tr = $("#clone");
                 	$.each(objects, function(index,item){                            
                       //克隆tr，每次遍历都可以产生新的tr                            
                         var clonedTr = tr.clone();
                         var _index = index;
                      
                         //循环遍历cloneTr的每一个td元素，并赋值
                         clonedTr.children("td").each(function(inner_index){                          
                                //根据索引为每一个td赋值
                                      switch(inner_index){
                                            case(0):
                                               $(this).html(item.projectName);
                                               break;
                                            case(1):
                                               $(this).html(item.acqtable.budgetProposal);
                                               break;
/*                                             case(2):
                                                $(this).html(_index + 1);
                                                break; */
                                     }//end switch                        
                      	});//end children.each
                  
                     //把克隆好的tr追加原来的tr后面
                     	clonedTr.insertAfter(tr);
                     	clonedTr.show();
                  	});//end $each
                 	$("#clone").hide();//隐藏id=clone的tr，因为该tr中的td没有数据，不隐藏起来会在生成的table第一行显示一个空行
                  	$("#details").show();//first是表的id
                 	/*$.each(objects, function(index,item){ 
                 		
                       alert("departmentName="+item.departmentName);
                  	});//end $each
*/   				
        	    	}//end success
			});//end ajaxpost   
 			 document.getElementById("divId").style.display="";
        }
	function closeDiv(){
		//设置div关闭
			document.getElementById("divId").style.display="none";
			document.getElementById("bg_fuceng").style.display="none";
		}
