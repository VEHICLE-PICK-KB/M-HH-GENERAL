<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="scripts/main.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body onkeydown="tutkiKey(event)">
<form id="tiedot">
<table>
	<thead>
		<tr>
			<th colspan="5" class="oikealle"><a href="listaaasiakkaat.jsp" id="takaisin">Takaisin listaukseen</a></th>
		</tr>
		<tr>
			<th>Etunimi</th>
			<th>Sukunimi</th>
			<th>Puhelin</th>
			<th>Sposti</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><input type="text" name="etunimi" id="etunimi"></td>
			<td><input type="text" name="sukunimi" id="sukunimi"></td>
			<td><input type="text" name="puhelin" id="puhelin"></td>
			<td><input type="text" name="sposti" id="sposti"></td> 
			<td><input type="button" name="nappi" id="tallenna" value="Lis‰‰" onclick="lisaaTiedot()"></td>
		</tr>
	</tbody>
</table>
</form>
<span id="ilmo"></span>
<script>

function tutkiKey(event){
	if(event.keyCode==13){//Enter
		lisaaTiedot();
	}		
}
document.getElementById("etunimi").focus();//vied‰‰n kursori etunimi-kentt‰‰n sivun latauksen yhteydess‰

//funktio tietojen lis‰‰mist‰ varten. Kutsutaan backin POST-metodia ja v‰litet‰‰n kutsun mukana uudet tiedot json-stringin‰.
//POST /asiakkaat/
function lisaaTiedot(){	
	var etunimi = document.getElementById("etunimi").value;
	var sukunimi = document.getElementById("sukunimi").value;
	var puhelin = document.getElementById("puhelin").value;
	var sposti = document.getElementById("sposti").value;	
	if(etunimi.length<2||sukunimi.length<2||puhelin*1!=puhelin||sposti.indexOf("@")==-1){
		document.getElementById("ilmo").innerHTML = "Antamasi arvot eiv‰t kelpaa!"
		return;
	}
	var formJsonStr=formDataToJSON(document.getElementById("tiedot")); //muutetaan lomakkeen tiedot json-stringiksi
	//L‰het‰‰n uudet tiedot backendiin
	fetch("asiakkaat",{
	      method: 'POST',
	      body:formJsonStr
	    })
	.then( function (response) {
		return response.json()
	})
	.then( function (responseJson) {
		var vastaus = responseJson.response;		
		if(vastaus==0){
			document.getElementById("ilmo").innerHTML= "Asiakkaan lis‰‰minen ep‰onnistui";
        }else if(vastaus==1){	        	
        	document.getElementById("ilmo").innerHTML= "Asiakkaan lis‰‰minen onnistui";			      	
		}	
	});		
}
</script>
</body>
</html>