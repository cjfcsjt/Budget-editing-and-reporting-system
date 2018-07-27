/**
 * 科室页
 */
//<script type="text/javascript">
        function addRow(){
        	var tr = $("#addrow");
           // var tr2 = $("#cloneTr2");
          var  clonedTr = tr.clone();
          clonedTr.children("td").each(function(inner_index){                          
              //根据索引为每一个td赋值
                                      
    	});//end children.each
          clonedTr.insertAfter(tr);
        	$("#caijibiao").show();
        }
     //   </script>