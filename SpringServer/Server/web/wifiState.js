onmessage = function(evt) {

	var curr_val = -1;
	var new_val = -1;
	while (true) {
		const http = new XMLHttpRequest();
		http.open("GET", "http://192.168.43.101/Server/wifi.do");
		new_val = http.send();
		/*setTimeout(function() {
			if(curr_val != new_val){
				curr_val = new_val;
				postMessage(curr_val);
			}
		}, 30000);*/
		setTimeout(function() {
			  console.log('Works!');
			}, 3000);
	};
};