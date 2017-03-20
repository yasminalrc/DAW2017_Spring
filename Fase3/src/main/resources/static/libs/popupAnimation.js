;(function($) {
    
    $(function(){
       $('#changePass').bind('click', function(event){
           popupForm(event, "#password");
        });
    });
    
    $(function(){
       $('#oferta').bind('click', function(event){
           popupForm(event, "#ofertaForm");
        });
    });
    
    function popupForm(event, formulario){
        event.preventDefault();
        $(formulario).bPopup({
            position: ['auto',120],
            speed: 450,
            transition: 'slideDown'
        });
    }

 })(jQuery);