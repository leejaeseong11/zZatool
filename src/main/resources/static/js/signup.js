let emailchk = false;
let nicknamechk = false;

function emailDupChk(){
    const email = document.getElementById('email').value; // 이메일 입력란의 값을 가져옴
    $.ajax({
        url: "/emaildupchk", // AJAX 요청을 보낼 URL
        data: { "email": email }, // 이메일 값을 서버로 전달
        type: 'GET',
        success: function(response) { // 요청이 성공했을 때 실행할 콜백 함수
              if(response==1){
                alert("사용 가능한 이메일입니다.");
                emailchk = true;
                console.log(emailchk);
              }else{
                alert("사용 불가한 이메일입니다.");
              }
              updateSignupButtonState();
        },
        error: function(xhr, status, error) { // 요청이 실패했을 때 실행할 콜백 함수
            console.error(xhr.responseText); // 오류 메시지를 콘솔에 출력
        }
    });
}

function emailOnChange(){
    emailchk = false;
    updateSignupButtonState();
}

function nicknameOnChange(){
    nicknamechk = false;
    updateSignupButtonState();
}

function updateSignupButtonState() {
    // 이메일과 닉네임의 중복 여부를 모두 확인하고 나서 버튼을 활성화 또는 비활성화합니다.
    if(emailchk && nicknamechk) {
        document.getElementById('signupBtn').disabled = false;
    } else {
        document.getElementById('signupBtn').disabled = true;
    }
}