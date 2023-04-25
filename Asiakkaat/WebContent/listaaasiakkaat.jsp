<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/main.css">
<title>Asiakkaiden listaus</title>
</head>
<body onkeydown="tutkiKey(event)">
	<span id="ilmo"></span>
	<table id="listaus">
		<thead>	
			<tr>
				<th colspan="5" class="oikealle"><a id="uusiAsiakas" href="lisaaasiakas.jsp">Lis‰‰ uusi asiakas</a></th>
			</tr>
			<tr>
				<th colspan="3" class="oikealle">Hakusana:</th>
				<th><input type="text" id="hakusana"></th>
				<th><input type="button" id="hae" value="Hae" onclick="haeTiedot()"></th>
			</tr>		
			<tr>
				<th>Etunimi</th>
				<th>Sukunimi</th>
				<th>Puhelin</th>
				<th>S-posti</th>	
				<th></th>			
			</tr>
		</thead>
		<tbody id="tbody">
		</tbody>
	</table>
<script>
	
haeTiedot();	
document.getElementById("hakusana").focus();//vied‰‰n kursori hakusana-kentt‰‰n sivun latauksen yhteydess‰

function tutkiKey(event){
	if(event.keyCode==13){//Enter
		haeTiedot();
	}		
}
//Funktio tietojen hakemista varten
//GET   /asiakkaat/{hakusana}
function haeTiedot(){	
	document.getElementById("tbody").innerHTML = "";
	fetch("asiakkaat/" + document.getElementById("hakusana").value,{
	      method: 'GET'	      
	    })
	.then( function (response) {
		return response.json()
	})
	.then( function (responseJson) {
		var asiakkaat = responseJson.asiakkaat;	
		console.log(asiakkaat);
		var htmlStr="";
		for(var i=0;i<asiakkaat.length;i++){			
        	htmlStr+="<tr>";
        	htmlStr+="<td>"+asiakkaat[i].etunimi+"</td>";
        	htmlStr+="<td>"+asiakkaat[i].sukunimi+"</td>";
        	htmlStr+="<td>"+asiakkaat[i].puhelin+"</td>";
        	htmlStr+="<td>"+asiakkaat[i].sposti+"</td>";  
        	htmlStr+="<td><a href='muutaasiakas.jsp?asiakas_id="+asiakkaat[i].asiakas_id+"'>Muuta</a>&nbsp;";
        	htmlStr+="<span class='poista' onclick=poista("+asiakkaat[i].asiakas_id+",'"+asiakkaat[i].etunimi+"','"+asiakkaat[i].sukunimi+"')>Poista</span></td>";
        	htmlStr+="</tr>";        	
		}
		document.getElementById("tbody").innerHTML = htmlStr;		
	})	
}

//Funktio tietojen poistamista varten. Kutsutaan backin DELETE-metodia ja v‰litet‰‰n poistettavan tiedon id. 
//DELETE /asiakkaat/id
function poista(asiakas_id, etunimi, sukunimi){
	if(confirm("Poista asiakas " + etunimi +" "+ etunimi +"?")){	
		fetch("asiakkaat/" + asiakas_id,{
		      method: 'DELETE'	      
		    })
		.then( function (response) {
			return response.json()
		})
		.then( function (responseJson) {
			var vastaus = responseJson.response;		
			if(vastaus==0){
				document.getElementById("ilmo").innerHTML= "Asiakkaan poisto ep‰onnistui.";
	        }else if(vastaus==1){	        	
	        	alert("Asiakkaan " + etunimi +" "+ sukunimi +" poisto onnistui.");
				haeTiedot();        	
			}	
		})		
	}	
}
</script>
</body>
</html>