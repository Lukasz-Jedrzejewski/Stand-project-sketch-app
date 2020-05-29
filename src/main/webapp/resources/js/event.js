document.addEventListener("DOMContentLoaded", function () {
    var button1 = document.querySelector('#btnEvent_form');
    var button2 = document.querySelector('#btnEvent_ready');
    var sectionEventForm = document.querySelector('section.eventForm');
    var sectionEventReady = document.querySelector('section.eventReady');

    sectionEventReady.style.display = 'none';

    console.log(button1);
    button1.addEventListener("click", function (event) {
       sectionEventForm.style.display = 'block';
       sectionEventReady.style.display = 'none';
    })
    console.log(button2);

    button2.addEventListener("click", function (event) {
        sectionEventForm.style.display = 'none';
        sectionEventReady.style.display = 'block';
    })


    var btnEventReady = document.querySelector('#e2')
    var selectEventElem = document.querySelector('#event_ready')
    btnEventReady.addEventListener('click', function(){
        var id = selectEventElem.value;
        console.log(id);
        window.location.href = "/event/project/prepare/"+id
    })



});

