<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="scripts/main.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css">
<title>Asiakkaan tietojen muutos</title>
</head>
<body onkeydown="tutkiKey(event)">
<form id="tiedot">
	<table class="table">
		<thead>
			<tr>
				<th colspan="5" class="oikealle"><a href="listaaasiakkaat.jsp" id="takaisin">Takaisin listaukseen</a></th>
			</tr>
			<tr>
				<th>Etunimi</th>
				<th>Sukunimi</th>
				<th>Puhelin</th>
				<th>Sposti</th>			
				<th>Hallinta</th>
			</tr>
		</thead>
			<tr>
				<td><input type="text" name="etunimi" id="etunimi"></td>
				<td><input type="text" name="sukunimi" id="sukunimi"></td>
				<td><input type="text" name="puhelin" id="puhelin"></td>
				<td><input type="text" name="sposti" id="sposti"></td>			
				<td><input type="button" name="nappi" value="Tallenna" id="tallenna" onclick="vieTiedot()"></td>
			</tr>
		<tbody>
		</tbody>
	</table>
	<input type="hidden" name="asiakas_id" id="asiakas_id">
</form>
<span id="ilmo"></span>
<script>

function tutkiKey(event){
	if(event.keyCode==13){//Enter
		vieTiedot();
	}		
}

document.getElementById("etunimi").focus();//vied‰‰n kursori etunimi-kentt‰‰n sivun latauksen yhteydess‰

//Haetaan muutettavan asiakkaan tiedot. Kutsutaan backin GET-metodia ja v‰litet‰‰n kutsun mukana muutettavan tiedon id
//GET /asiakkaat/haeyksi/id
var asiakas_id = requestURLParam("asiakas_id"); //Funktio lˆytyy scripts/main.js 
fetch("asiakkaat/haeyksi/" + asiakas_id,{
      method: 'GET'	      
    })
.then( function (response) {
	return response.json()
})
.then( function (responseJson) {	
	document.getElementById("etunimi").value = responseJson.etunimi;		
	document.getElementById("sukunimi").value = responseJson.sukunimi;	
	document.getElementById("puhelin").value = responseJson.puhelin;	
	document.getElementById("sposti").value = responseJson.sposti;	
	document.getElementById("asiakas_id").value = responseJson.asiakas_id;	
});	

//Funktio tietojen muuttamista varten. Kutsutaan backin PUT-metodia ja v‰litet‰‰n kutsun mukana muutetut tiedot json-stringin‰.
//PUT /asiakkaat/
function vieTiedot(){
	var etunimi = document.getElementById("etunimi").value;
	var sukunimi = document.getElementById("sukunimi").value;
	var puhelin = document.getElementById("puhelin").value;
	var sposti = document.getElementById("sposti").value;	
	if(etunimi.length<2||sukunimi.length<2||puhelin*1!=puhelin||sposti.indexOf("@")==-1){
		document.getElementById("ilmo").innerHTML = "Antamasi arvot eiv‰t kelpaa!"
		return;
	}
	var formJsonStr=formDataToJSON(document.getElementById("tiedot")); //muutetaan lomakkeen tiedot json-stringiksi
	//L‰het‰‰n muutetut tiedot backendiin
	fetch("asiakkaat",{
	      method: 'PUT',
	      body:formJsonStr
	    })
	.then( function (response) {
		return response.json();
	})
	.then( function (responseJson) {
		var vastaus = responseJson.response;		
		if(vastaus==0){
			document.getElementById("ilmo").innerHTML= "Tietojen p‰ivitys ep‰onnistui";
        }else if(vastaus==1){	        	
        	document.getElementById("ilmo").innerHTML= "Tietojen p‰ivitys onnistui";			      	
		}	
	});	
}
</script>
</body>
</html>