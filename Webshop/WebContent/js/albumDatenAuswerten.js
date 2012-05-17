/**
 * 
 */

function checkAlbumInput() {
  if (document.Formular.titel.value == "") {
    alert("Bitte Titel eingeben!");
    document.Formular.titel.focus();
    return false;
  }
  if (document.Formular.artist.value == "") {
    alert("Bitte Artist eingeben!");
    document.Formular.artist.focus();
    return false;
  }
    
  if (document.Formular.diskAnzahl.value == "") {
    alert("Bitte Diskanzahl eingeben!");
    document.Formular.diskAnzahl.focus();
    return false;
  }
  
  if (document.Formular.preis.value == "") {
	    alert("Bitte Preis eingeben!");
	    document.Formular.preis.focus();
	    return false;
	  }
  if (document.Formular.anzahl.value == "") {
	    alert("Bitte Anzahl eingeben!");
	    document.Formular.anzahl.focus();
	    return false;
	  }
  
  if (document.Formular.trackanzahl.value == "") {
	    alert("Bitte Preis eingeben!");
	    document.Formular.trackanzahl.focus();
	    return false;
	  }
  if (document.Formular.label.value == "") {
	    alert("Bitte Label eingeben!");
	    document.Formular.label.focus();
	    return false;
	  }
  
  var chkZ = 1;
  for (i = 0; i < document.Formular.anzahl.value.length; ++i)
    if (document.Formular.Alter.value.charAt(i) < "0" ||
        document.Formular.Alter.value.charAt(i) > "9")
      chkZ = -1;
  if (chkZ == -1) {
    alert("Das ist keine Zahl!");
    document.Formular.Alter.focus();
    return false;
  }
}