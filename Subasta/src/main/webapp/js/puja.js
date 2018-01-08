/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
      var refreshId =  setInterval( function(){
    $('#feedback-bg-info').load('/jsp/articulo.jsp');//actualizas el div
   }, 1000 );
});
