/**
 * 
 */
//<script type="text/javascript">
      function updateByCollegefst()  //
        { 
        	alert("updateByCollegefst"); 
        	var con=confirm("确认上报？");
        	if(con==true){
        		$.ajax({
                    url:'college/updatefst',
                    type:'post',
                    dataType:"json",
                    data:{"Status":true},
                    cache:false,
                    success:function(data){
                       alert(data.data.stateinfo);
                    }
              });
        	}
        }  
   //   </script>