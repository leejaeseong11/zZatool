let emailchk = false;
let nicknamechk = false;

function emailDupChk(){
    const email = document.getElementById('email').value; // 이메일 입력란의 값을 가져옴
    $.ajax({
        url: "/emaildupchk", // AJAX 요청을 보낼 URL
        data: { "email": email }, // 이메일 값을 서버로 전달
        type: 'GET',
        success: function(response) { // 요청이 성공했을 때 실행할 콜백 함수
            if(response.status==1){
                alert("이미 계정이 존재합니다");
            }else{
                alert("사용 가능한 이메일입니다");
                emailchk = true;
            }
        },
        error: function(xhr, status, error) { // 요청이 실패했을 때 실행할 콜백 함수
            console.error(xhr.responseText); // 오류 메시지를 콘솔에 출력
        }
    });
}