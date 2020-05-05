$(function(){
  $('.to_top').click(function(){
    $('body').scrollTop(0);
  });
});

// for big_footer
$(function(){
  $('.close_btn').click(function(){
    $('.big_footer').slideToggle();
  });
});

// for btn_area_2
$(function(){
  $('.btn_area_1>a').click(function(){
    $('.btn_area_2').slideDown();
  });
});

function changeAllFontSize(vstrSize) {
    switch (vstrSize) {
        case "l":
            document.getElementById('body').style.fontSize = '1.250em';
            document.getElementById('size_l').className += ' active';
            document.getElementById('size_m').className = 'font_size';
            document.getElementById('size_s').className = 'font_size';
            break;
        case "m":
            
            document.getElementById('body').style.fontSize = '1.125em';
            document.getElementById('size_l').className = 'font_size';
            document.getElementById('size_m').className += ' active';
            document.getElementById('size_s').className = 'font_size';
            break;
        case "s":
            document.getElementById('body').style.fontSize = '1em';
            document.getElementById('size_l').className = 'font_size';
            document.getElementById('size_m').className = 'font_size';
            document.getElementById('size_s').className += ' active'; 
            break;
       
    }
}
