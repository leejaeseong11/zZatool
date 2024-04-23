let emailchk = false;
let nicknamechk = false;
let pwdchk = false;

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

function nicknameDupChk(){
    const email = document.getElementById('nickname').value; // 이메일 입력란의 값을 가져옴
    $.ajax({
        url: "/nicknamedupchk", // AJAX 요청을 보낼 URL
        data: { "nickname": email }, // 이메일 값을 서버로 전달
        type: 'GET',
        success: function(response) { // 요청이 성공했을 때 실행할 콜백 함수
              if(response==1){
                alert("사용 가능한 닉네임입니다.");
                emailchk = true;
                console.log(emailchk);
              }else{
                alert("사용 불가한 닉네임입니다.");
              }
              updateSignupButtonState();
        },
        error: function(xhr, status, error) { // 요청이 실패했을 때 실행할 콜백 함수
            console.error(xhr.responseText); // 오류 메시지를 콘솔에 출력
        }
    });
}

function emailOnInput(){
    emailchk = false;
    updateSignupButtonState();
}

function nicknameOnInput(){
    nicknamechk = false;
    updateSignupButtonState();
}

function pwdOnInput(){
    pwdchk = false;
    document.getElementById('pwdmessage').style.display = 'block';
    var pwd = document.getElementById('pwd').value;

    if(strongPassword(pwd)){
        document.getElementById('pwdmessage').style.display = 'none';
    }
    updateSignupButtonState();
}

function pwd2OnInput(){
    pwdchk = false;
    document.getElementById('pwdmessage2').style.display = 'block';
    var pwd = document.getElementById('pwd').value;
    var pwd2 = document.getElementById('pwd2').value;
    if(strongPassword(pwd) && isMatch(pwd,pwd2)){
        document.getElementById('pwdmessage2').style.display = 'none';
        pwdchk = true;
    }else{
        pwdchk = false;
    }
    updateSignupButtonState();
}

function updateSignupButtonState() {
    if(emailchk && nicknamechk && pwdchk) {
        document.getElementById('signupBtn').disabled = false;
    } else {
        document.getElementById('signupBtn').disabled = true;
    }
}

function strongPassword (str) {
  return /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,16}$/.test(str);
}

function isMatch (pwd, pwd2) {
  return pwd === pwd2;
}