document.addEventListener("DOMContentLoaded", function () {

    var buttonB1 = document.querySelector('#btnBranch_form');
    var buttonB2 = document.querySelector('#btnBranch_ready');
    var sectionBranchForm = document.querySelector('section.branchForm');
    var sectionBranchReady = document.querySelector('section.branchReady');

    sectionBranchReady.style.display = 'none';

    console.log(buttonB1);
    buttonB1.addEventListener("click", function (event) {
        sectionBranchForm.style.display = 'block';
        sectionBranchReady.style.display = 'none';
    })
    console.log(buttonB2);

    buttonB2.addEventListener("click", function (event) {
        sectionBranchForm.style.display = 'none';
        sectionBranchReady.style.display = 'block';
    })


    var btnBranchReady = document.querySelector('#b2')
    var selectBranchElem = document.querySelector('#branch_ready')
    btnBranchReady.addEventListener('click', function () {
        var id = selectBranchElem.value;
        console.log(id);
        window.location.href = "/branch/project/prepareBranch/" + id
    })

});