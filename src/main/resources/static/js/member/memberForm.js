
/* 화면 랜더링 후 */
$(document).ready(function(){

    $("form#memberForm").on('submit',function(event){
        // 서브밋을 일시적으로 막음
        event.preventDefault();

        // 서브밋을 누른 경우 false 특정 조건
        var pwdVaild = false;  // 예시로 폼이 유효하지 않다고 가정

        var pwd = $("#password").val();
        var r_pwd = $('#password-repeat').val();

        // 비밀번호 검증이 통과하면 true
        if (pwd == r_pwd) {
            pwdVaild = true;
        }

        // 결과
        if(pwdVaild) {
            // 비밀번호 확인이 유효한 경우 서브밋
            $(this).off('submit');  // preventDefault() 제거
            $(this).submit();  // 실제 폼 서브밋
        }
        else {
            // 비밀번호 확인이 유효하지 않은 경우
            alert("비밀번호가 같지 않습니다.");
            return;  // 폼 서브밋 취소
        }
    });

}); /* 화면 랜더링 후 */