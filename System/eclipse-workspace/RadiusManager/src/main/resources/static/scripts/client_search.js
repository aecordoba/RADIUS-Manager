/**
 * 
 */

 $(document).ready(function() {
	$(".toggleable").prop("disabled", true);
	let data = {};
	$("#number").keyup(function(){
		if($("#number").val().length > 6){
			data["number"] = $("#number").val();
			data["name"] = "";
			get_client(data);
			$(".toggleable").prop("disabled", false);
		}else{
			$(".toggleable").prop("disabled", true);
		}
	});
	
	$("#name").keyup(function(){
		if($("#name").val().length > 2){
			data["number"] = "";
			data["name"] = $("#name").val();
			get_client();
			$(".toggleable").prop("disabled", false);
		}else{
			$(".toggleable").prop("disabled", true);
		}
	});
	
	function get_client(){
    	$.ajax({
        	type: "GET",
        	url: "/client-search",
        	data: data,
        	success: function (data) {
				const client = JSON.stringify(data, null, 4);
				console.log("SUCCESS : ", data);
				console.log("JSON: ", client);
				console.log(data["password"]);
				//$("#password").val(data["password"]);
				
  				$.each(data, function(key, value){
					  if($('[id='+key+']', '#form').is("select")){
        					$("option",$('[id='+key+']', '#form')).each(function(){
            					if (this.value==value) { this.selected=true; }
        				});
					  }
					  $('[id='+key+']', '#form').val(value);
  				});
//				console.log(client.password);
/*            	var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                	+ JSON.stringify(data, null, 4) + "&lt;/pre&gt;";
            	$('#feedback').html(json);

            	console.log("SUCCESS : ", data);
            	$("#btn-search").prop("disabled", false);
*/
        	},
        	error: function (e) {
				console.log("ERROR : ", e);
 /*           	var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                	+ e.responseText + "&lt;/pre&gt;";
            	$('#feedback').html(json);

            	console.log("ERROR : ", e);
            	$("#btn-search").prop("disabled", false);
*/
        	}
    	});

	}
});
