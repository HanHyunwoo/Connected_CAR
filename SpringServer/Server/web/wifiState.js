onmessage = function(evt) {

	var curr_val = -1;
	var new_val = -1;
	console.log("new_val : "+ new_val);
		setInterval(function() {
			var xhttp = new XMLHttpRequest();
			  xhttp.onreadystatechange = function() {
			    if (xhttp.readyState == 4 && xhttp.status == 200) {
			    	new_val = xhttp.responseText;
			    	console.log("new_val : "+ new_val);
			    	  if(new_val != curr_val){
						  curr_val = new_val;
						  postMessage(curr_val);				  
					  }
			    }
			  };
			  xhttp.open("GET", "wifi.do?id=1001", true);
			  xhttp.send();
			  
			
		}, 3000);
//	};
};
