/*!
    * Start Bootstrap - SB Admin v7.0.5 (https://startbootstrap.com/template/sb-admin)
    * Copyright 2013-2022 Start Bootstrap
    * Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-sb-admin/blob/master/LICENSE)
    */
    // 
// Scripts
// 

window.addEventListener('DOMContentLoaded', event => {

    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

});

function fc_showAlertAutoClose (iddiv, clase, mensajeStrong, mensaje ){
    var idmensaje='#' + iddiv;

    $(idmensaje).html("");
    $(idmensaje).html('<div class="alert alert-' + clase + ' alert-dismissible fade show" role="alert"> \n\
                                <strong> ' + mensajeStrong +"</strong>"  + mensaje + ' \n\
                                  <span aria-hidden="true">&times;</span>\n\
                                </button> \n\
                                </div>');
    TriggerAlertClose();

    function TriggerAlertClose() {
        window.setTimeout(function () {
            $(".alert").fadeTo(1000, 0).slideUp(1000, function () {
                    $(this).remove();
                });
            }, 5000);
    };
}

function fc_showAlertmensaje(iddiv, clase, mensajeStrong, mensaje ){
    var idmensaje='#' + iddiv;

    $(idmensaje).html("");
    $(idmensaje).html('<div class="alert alert-' + clase + ' alert-dismissible fade show" role="alert"> \n\
                                <strong> ' + mensajeStrong+"</strong>"  +  mensaje + ' \n\
                                </button> \n\
                                </div>');

}

