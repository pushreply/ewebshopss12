/**
 * 
 */
var var_1 = new Array();
var_1[0] = new Array('Titel','e','Sie haben keine Titel angegeben','');
var_1[1] = new Array('artist','n','ist keine Zahl','');
var_1[2] = new Array('diskAnzahl','n','ist keine gültige Emailadresse','');
var_1[3] = new Array('preis','p','ist kein gültiger Preis','');
var_1[4] = new Array('preis_dot','p.','ist kein gültiger Preis','');
var_1[5] = new Array('anzahl','n','ist kein gültige Zahl','');
var_1[6] = new Array('trackAnzahl','n','ist kein gültige Tackanzahl','');
var_1[7] = new Array('label','p','ist kein gültiger Label','');

var msg_1 = 'Fehler:';

function validate(sender,myarray,err_hd){

var err_msg = !err_hd?new Array('Folgende Fehler sind aufgetreten:\n'):new Array(err_hd+'\n');
var error = false;

for (i=0;i<myarray.length;i++){
  field = document.forms[sender.name].elements[myarray[i][0]];

/* Block 1 überprüft Felder, die ausgefüllt sein müssen */
  if (myarray[i][1].indexOf('e')>-1){
    if (!field.value){
      error = true;
      err_msg.push(myarray[i][2]);
    }
  }

/* Block 3 überprüft Felder, deren Wert eine Zahl sein muss */
  else if (myarray[i][1].indexOf('n')>-1) {
    var num_error = false;
    if(field.value) {
      var myvalue = field.value;
      var num = myvalue.match(/[^0-9,\.]/gi)
      var dot = myvalue.match(/\./g);
      var com = myvalue.match(/,/g);
        if (num!=null) {
          num_error = true;
        }
        else if ((dot!=null)&&(dot.length>1)) {
          num_error = true;
        }
        else if ((com!=null)&&(com.length>1)) {
          num_error = true;
        }
        else if ((com!=null)&&(dot!=null)) {
          num_error = true;
        }
    }
    if (num_error==true) {
        error = true;
        err_msg.push(myvalue+" "+myarray[i][2]);
    }
  }

/* überprüft Felder, die als Preis formatiert sein müssen, ändert die Formatierung eventuell */
  else if (myarray[i][1].indexOf('p')>-1) {
    var myvalue = field.value;
    var reg = /,-{1,}|\.-{1,}/;
    var nantest_value = myvalue.replace(reg,"");
    var num = nantest_value.match(/[^0-9,\.]/gi);
    sep = myarray[i][1].substr(1,1)?myarray[i][1].substr(1,1):',';
    if (field.value) {
      var myvalue = field.value.replace(/\./,',');
      if (myvalue.indexOf(',')==-1) {
        field.value = myvalue+sep+'00';
      }
      else if (myvalue.indexOf(",--")>-1) {
        field.value = myvalue.replace(/,--/,sep+'00');
      }
      else if (myvalue.indexOf(",-")>-1) {
        field.value = myvalue.replace(/,-/,sep+'00');
      }
      else if (!myvalue.substring(myvalue.indexOf(',') + 2)) {
        error=true;
        err_msg.push(field.value+" "+myarray[i][2]);
      }
      else if (myvalue.substring(myvalue.indexOf(',') + 3)!='') {
        error=true;
        err_msg.push(field.value+" "+myarray[i][2]);
      }
      else if (num!=null) {
        error=true;
        err_msg.push(field.value+" "+myarray[i][2]);
      }
    
  }

/* im Fehlerfall werden hier die gesammelten Fehlermeldungen verarbeitet und angezeigt. Wenn das
Formular ohne Beanstandung ist, wird es übertragen */
  if (error) { 
    err_msg = err_msg.join('\n\xB7 ');
    alert(err_msg);
    return false;
  }
  else {
    return true;
  }
 }
}