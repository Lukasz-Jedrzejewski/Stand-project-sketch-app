document.addEventListener("DOMContentLoaded", function () {
    var button1 = document.querySelector('#btn_form');
    var button2 = document.querySelector('#btn_ready');
    var sectionForm = document.querySelector('section.form');
    var sectionReady = document.querySelector('section.ready');

    // sectionForm.style.display = 'none';
    sectionReady.style.display = 'none';

    console.log(button1);
    button1.addEventListener("click", function (event) {
       sectionForm.style.display = 'block';
       sectionReady.style.display = 'none';
    })
    console.log(button2);

    button2.addEventListener("click", function (event) {
        sectionForm.style.display = 'none';
        sectionReady.style.display = 'block';
    })


    var btnReady = document.querySelector('#b2')
    var selectElem = document.querySelector('#event_ready')
    btnReady.addEventListener('click', function(){
        var id = selectElem.value;
        console.log(id);
        window.location.href = "/event/project/prepare/"+id
    })

});

