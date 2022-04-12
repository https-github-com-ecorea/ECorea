/*
	항목			패턴
	아이디		대문자,숫자 8~10자
	비밀번호		특수문자를 포함하는 영숫자 8~10자
	비밀번호확인	비밀번호와 같다
	이름			한글 2~10자
	이메일		/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
				/i - case insensitive. 대소문자 구분하지 마라
				/g - global. 정규식은 패턴을 찾으면 중지한다 -> 중지하지 말고 모두 찾아라
					 js에서 test()말고 replace()할 때 필요 
	생일			숫자4자-숫자2자-숫자2자	
*/

// 1. 패턴 정의
const usernamePattern = /^[0-9A-Z]{8,10}$/;
const irumPattern = /^[가-힣]{2,10}$/;
// ()는 독립된 조건. ?=는 앞부터 찾아라(전방 탐색).    .은 임의의 글자가 * 0글자 이상 -> 특수문자가 1글자 이상
const passwordPattern = /(?=.*[!@#$%^&*])^[A-Za-z0-9!@#$%^&*]{8,10}$/;
const emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

// 2. 오류메시지 출력 함수 : 아이디~생일까지 6개 input에 대한 공통 오류 메시지 처리
// function printCheckMessage(입력값, 패턴, 출력할 메시지, 출력한 span)
function check(value, pattern, message, span) {
	// 입력 안함
  	if(value=="") {
  		span.text('필수입력입니다').attr('class','fail');
  		return false;
  	}
  	// 패턴 체크
  	if(pattern.test(value)==false) {
  		span.text(message).attr('class','fail');
  		return false;
  	}
  	return true;
}

// 3.1 입력한 아이디를 대문자로 변경 후 확인 함수
function usernameCheck() {
	const $username = $("#username").val().toLowerCase();
	$("#username").val($username);
	return check($username, usernamePattern, "아이디는 대문자와 숫자 8~10자입니다", $("#username_msg"));
}

// 3.2 입력한 이름 확인 함수
function irumCheck() {
	$("#irum_msg").text("");
	return check($("#irum").val(), irumPattern, "이름은 한글 2~10자입니다", $("#irum_msg"));
}

// 3.3 입력한 비밀번호 확인 함수
function passwordCheck() {
	$("#password_msg").text("");
	return check($("#password").val(), passwordPattern, "비밀번호는 영숫자와 특수문자 8~10자입니다", $("#password_msg"));	
}

// 3.4 입력한 비밀번호 확인이 비밀번호와 일치하는 지 확인 함수 - 패턴이 없으므로 check() 함수를 사용하지 않았다
function password2Check() {
	$("#password2_msg").text("");
	const $password2 = $("#password2").val();
	if($password2=="") {
		$("#password2_msg").text("필수입력입니다").attr("class","fail");
		return false;
	} 
	if($password2!==$("#password").val()) {
		$("#password2_msg").text("비밀번호가 일치하지 않습니다").attr("class","fail");
		return false;
	}
	return true;
}

// 3.5 입력한 이메일 확인 함수
function emailCheck() {
	$("#email_msg").text("");
	return check($("#email").val(), emailPattern, "정확한 이메일을 입력하세요", $("#email_msg"))
}







