
function sendData(obj){
	$.ajax({
	    url: 'InsertTestData',
	    dataType: 'json',
	    type: 'post',
	    contentType: 'application/json',
	    data: JSON.stringify( { "name": obj.name, "date": obj.date } ),
	    processData: false,
	    success: function( data, textStatus, jQxhr ){
	        //getData();
	    	console.log(data);
	    	fillOne(data);
	    	
	    },
	    error: function( jqXhr, textStatus, errorThrown ){
	    	//console.log(errorThrown);
	    	showError();
	    	connectionLost = true;
	    	return;
	        
	    }
	    
	});
}

function getData(){
	$.ajax({
	    url: 'InsertTestData',
	    dataType: 'json',
	    type: 'get',
	    contentType: 'application/json',
	    processData: false,
	    success: function( data, textStatus, jQxhr ){
	    	console.log(textStatus);
	        fillTable(data.things);
	    },
	    error: function( jqXhr, textStatus, errorThrown ){
	    	console.log(textStatus);
	        
	        
	    }
	});
}

function fillTable(things){
	$.each( things, function(index, value) {
		  $("#dataTable").append(
			'<tr>'+
				'<td>' + value.id + '</td>' + 
				'<td>' + value.name  + '</td>' +
				'<td>' + value.date  + '</td>' +
			'</tr>'
		  );
	});
}

function fillOne(thing){
	 $("#dataTable").append(
				'<tr>'+
					'<td>' + thing.id + '</td>' + 
					'<td>' + thing.name  + '</td>' +
					'<td>' + thing.date  + '</td>' +
				'</tr>'
	);
}

let connectionLost = false;
let index = 0;

document.getElementById("sendDataButton").addEventListener("click", function(){
	connectionLost = false;
	startSendingData();
});



function startSendingData() {
	if(connectionLost){
		return;
	}
    setTimeout(function () {
    	index++;
    	let object = {name : "t" + index, date: new Date()};
    	sendData(object);
        
        
    	startSendingData();
    }, 5000);
}

function showError(){
	$("#errorHeading").html("Connection closed!");
}


document.getElementById("stop").addEventListener("click", function(){
	connectionLost = true;
});



