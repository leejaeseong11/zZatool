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

function writeComment(testIdValue) {
    var inputValue = $("#commentValue").val();

    $.ajax({
            type: "POST",
            url: "/comment",
            data: {
                    value: inputValue,
                    testId: testIdValue
                },
            success: function(response) {
                alert("한줄평이 성공적으로 추가되었습니다.");
                location.reload();
            },
            error: function(xhr, status, error) {
                alert("오류가 발생했습니다.");
                location.reload();
            }
    });
}