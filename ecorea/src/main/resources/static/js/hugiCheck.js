function check(value) {
	// 입력 안함
  	if(value=="") {
  		return false;
  	}
  	return true;
}

function titleCheck() {
	return check($('#htitle').val());
}

function contentCheck() {
	return check($("#hcontent").val());
}