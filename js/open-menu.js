$(document).ready(function(){

    if ($(window).width() < 768) {

    }else{

        $(".dropdown").hover(            
            function() {
                $('.dropdown-menu', this).not('.in .dropdown-menu').stop(true,true).slideDown("400");
                $(this).toggleClass('open');        
            },
            function() {
                $('.dropdown-menu', this).not('.in .dropdown-menu').stop(true,true).slideUp("400");
                $(this).toggleClass('open');       
            }
        );
    }

});