function readURL(input) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function(e) {
      document.getElementById('preview').src = e.target.result;
    };
    reader.readAsDataURL(input.files[0]);
  } else {
    document.getElementById('preview').src = "";
  }
}

function validateForm() {
    var checkboxCount = document.querySelectorAll('input[type="checkbox"]:checked').length;

    if (checkboxCount === 0) {
        alert("최소 한 개 이상의 정답을 선택해주세요.");
        return false;
    }
    return true;
}