/**
 * 
 */
let counter = 0;

function process_file() {
	const [file] = document.querySelector("input[type=file]").files;
	const reader = new FileReader();
	$('button').prop("disabled", true);
	$('span').prop("hidden", true);

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
		counter++;
		$.ajax({
			type: "GET",
        	url: "/status-change",
        	data: data,
        	success: function (data) {
				$('table tbody').append("<tr><td>"+data["number"]+"</td><td>"+data["message"]+"</td></tr>");
				counter--;
				if(counter == 0){
					$('button').prop("disabled", false);
					$('span').prop("hidden", false);
				}
        	},
        	error: function (e) {
        	}
    	});
    }
}
