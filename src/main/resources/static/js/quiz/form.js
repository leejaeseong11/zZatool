function setView() {
    let selectBox = document.getElementById("view-count-select");
//    let viewInputContainer = document.getElementById("view-content-container");
//    viewInputContainer.replaceChildren();
//
//    for (let i = 0; i < selectBox.value; i++) {
//        let formCheck = document.createElement("div");
//        formCheck.classList.add("form-check");
//
//        let formCheckInput = document.createElement("input");
//        formCheckInput.setAttribute("type", "radio");
//        formCheckInput.classList.add("form-check-input");
//        if (i === 0) {
//            formCheckInput.setAttribute("checked", true);
//        }
//        formCheck.appendChild(formCheckInput);
//
//        let formControl = document.createElement("input");
//        formControl.classList.add("form-control");
//        formControl.setAttribute("type", "text");
//        formCheck.appendChild(formControl);
//
//        viewInputContainer.appendChild(formCheck);
//    }
        console.log(this)
        console.log(selectBox.value)
        location.href = '?viewCount=' + selectBox.value;

}

function fetchViewPost(quizId, type) {
    const url = window.location.protocol
    + window.location.host + "/view/add";

    let viewInputContainer = document.getElementById("view-content-container");

    let corrects = document.querySelector("#view-content-container .form-check")
    let contents = document.querySelector("#view-content-container .form-control")

    for (let i = 0; i < corrects.length; i++) {
        fetch("url", {
          method: "POST",
          body: JSON.stringify({
            viewContent: contents.value,
            quizId: quizId,
            viewNumber: i+1,
            isCorrect: corrects[i].checked? 1 : 0
          }),
        })
    }
}