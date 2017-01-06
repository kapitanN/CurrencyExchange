$(document).ready(function() {
    $('a[name=modal]').click(function(e) {
        e.preventDefault();
        var id = $(this).attr('href');
        var maskHeight = $(document).height();
        var maskWidth = $(window).width();
        $('#mask').css({'width':maskWidth,'height':maskHeight});
        $('#mask').fadeIn(1500);
        $('.main-signin .main-signin_middle').css({'opacity':0});
        $('.main-signin .main-signin_head').css({'z-index':1000});
        $('#mask').fadeTo("normal",0.7);
        var winH = $(window).height();
        var winW = $(window).width();
        $(id).css('top',  winH/2-$(id).height()/2);
        $(id).css('left', winW/2-$(id).width()/2);
        $(id).fadeIn(2000);
        $(id).fadeTo("normal",1);
    });
    $('.window #close').click(function (e) {
        e.preventDefault();
        $('#mask, .window').hide();
        $('.main-signin .main-signin_middle').css({'opacity':1});
    });
    $('#mask').click(function () {
        $(this).hide();
        $('.window').hide();
        $('.main-signin .main-signin_middle').css({'opacity':1});
    });
});