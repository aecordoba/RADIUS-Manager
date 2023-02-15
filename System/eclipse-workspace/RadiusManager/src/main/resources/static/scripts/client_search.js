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
		}else{
			$(".toggleable").prop("disabled", true);
		}
	});
	
	$("#name").keyup(function(){
		if($("#name").val().length > 2){
			data["number"] = "";
			data["name"] = $("#name").val();
			get_client();
		}else{
			$(".toggleable").prop("disabled", true);
		}
	});
	
	function get_client(){
		$('#password').val("");
		$("#radusergroup").val(0);
		$('#ipaddress').val("");
		
    	$.ajax({
        	type: "GET",
        	url: "/client-search",
        	data: data,
        	success: function (data) {
  				$.each(data, function(key, value){
					  if(value != null){
						  if($('[name='+key+']', '#form').is("select"))
							  $("option",$('[name='+key+']', '#form')).each(function(){
								  if (this.value == value["id"])
								  		this.selected = true;
        					  });
					  	  else if($('[name='+key+']', '#form').is(":checkbox"))
					  	  		$('[name='+key+']', '#form').prop('checked', value);
					  	  else
								$('[name='+key+']', '#form').val(value);
						  $(".toggleable").prop("disabled", false);
					  }
  				});
        	},
        	error: function (e) {
				console.log("ERROR : ", e);
        	}
    	});
	}
});