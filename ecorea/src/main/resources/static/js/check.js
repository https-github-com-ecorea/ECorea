const idPattern = /^[0-9a-z]{8,20}$/;
const namePattern = /^[가-힣]{2,20}$/;
// ()는 독립된 조건. ?=는 앞부터 찾아라(전방 탐색).    .은 임의의 글자가 * 0글자 이상 -> 특수문자가 1글자 이상
const pwPattern = /(?=.*[!@#$%^&*])^[A-Za-z0-9!@#$%^&*]{8,10}$/;
const emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
const corpnumPattern = /^[0-9]{1,10}$/;


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

// 3.1 입력한 아이디를 소문자로 변경 후 확인 함수
function idCheck() {
	const $id = $("#id").val().toLowerCase();
	$("#id").val($id);
	return check($id, idPattern, "아이디는 소문자와 숫자 8~20자입니다", $("#id_msg"));
}

// 3.2 입력한 이름 확인 함수
function nameCheck() {
	$("#name_msg").text("");
	return check($("#name").val(), namePattern, "이름은 한글 2~20자입니다", $("#name_msg"));
}

// 3.3 입력한 비밀번호 확인 함수
function pwCheck() {
	$("#pw_msg").text("");
	return check($("#pw").val(), pwPattern, "비밀번호는 영숫자와 특수문자 8~10자입니다", $("#pw_msg"));	
}

// 3.4 입력한 비밀번호 확인이 비밀번호와 일치하는 지 확인 함수 - 패턴이 없으므로 check() 함수를 사용하지 않았다
function pw2Check() {
	$("#pw2_msg").text("");
	const $pw2 = $("#pw2").val();
	if($pw2=="") {
		$("#pw2_msg").text("필수입력입니다").attr("class","fail");
		return false;
	} 
	if($pw2!==$("#pw").val()) {
		$("#pw2_msg").text("비밀번호가 일치하지 않습니다").attr("class","fail");
		return false;
	}
	return true;
}

// 3.5 입력한 이메일 확인 함수
function emailCheck() {
	$("#email_msg").text("");
	return check($("#email").val(), emailPattern, "정확한 이메일을 입력하세요", $("#email_msg"))
}


// 3.6 사업자번호 확인 함수
function corpnumCheck() {
	$('#corpnum_msg').text("");
	return check($("#corpnum").val(), corpnumPattern, "사업자 번호를 확인해주세요", $('#corpnum_msg'))
}



