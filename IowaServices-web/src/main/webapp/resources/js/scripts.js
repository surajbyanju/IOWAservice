/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function toggle() {

    var user_types = document.getElementsByName('userType');
    var user_type;

    for (var i = 0; i < user_types.length; i++) {
        if (user_types[i].checked) {
            user_type = user_types[i].value;
        }
    }


    if (user_type === 'serviceProvider') {

        document.getElementById('selUserSkills').style.display = 'block';
    } else {

        document.getElementById('selUserSkills').style.display = 'none';
    }


}
jQuery(document).ready(function ($) {

//scroll to specific section when clicked
    $('.navbar-nav > li.onepage ').on('click', 'a', function (e) {
        e.preventDefault();
        var $this = $(this),
            $href = $this.attr('href'),
            $sectionOffset = $($href).offset().top,
            $ul = $this.parents('ul'),
            $parentLi = $this.parent('li');
        $('html,body').animate({scrollTop: $sectionOffset}, 1000, function () {
            $ul.find('li').removeClass('current-menu-item');
            $parentLi.addClass('current-menu-item');
        });
    });
});