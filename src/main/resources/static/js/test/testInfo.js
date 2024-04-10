function goToSolve(testId) {
    console.log(testId);
}

function shareThis() {
    var url = window.location.href;
    var input = document.createElement('input');
    input.setAttribute('value', url);
    document.body.appendChild(input);
    input.select();
    document.execCommand('copy');
    document.body.removeChild(input);
    alert('링크 복사 완료!');
}