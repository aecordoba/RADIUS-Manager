/**
 * 
 */

 function process_file() {
	const [file] = document.querySelector("input[type=file]").files;
	const reader = new FileReader();

	reader.addEventListener("load", () => {
	    numbers = reader.result.split(/\s+/);
	    numbers.map(pack_data).forEach(change_status);
	}, false);

	if (file) {
		reader.readAsText(file);
	}
}

function pack_data(value, index, array){
	if(value){
		data = {};
		data["number"] = value;
		data["suspend"] = $("input[name=suspend]:checked").val();
		return data;
	}
}

function change_status(data) {
	if(data) {
		$.ajax({
			type: "GET",
        	url: "/status-change",
        	data: data,
        	success: function (data) {
				$('table tbody').append("<tr><td>"+data["number"]+"</td><td>"+data["message"]+"</td></tr>");
        	},
        	error: function (e) {
        	}
    	});
    }
}
