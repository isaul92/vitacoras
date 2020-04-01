$(document).ready(function (e) {
$('.up').hide();
$(window).scroll(function(){
        if ($(this).scrollTop()>100) {
            $('.up').fadeIn();
        }else{
            $('.up').fadeOut();
        }
    });
$('.up').click(function(){
   $("body,html").animate({
       scrollTop:0
   }) });
});