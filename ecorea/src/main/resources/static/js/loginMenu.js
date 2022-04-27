function loginMenu(role) {
	if(role=="ROLE_MEMBER") {
  			$('.member').css('display','block');
  			$('.all').css('display','none');
  		} else if(role=="ROLE_CORP") {
  			$('.corp').css('display','block');
  			$('.all').css('display','none');
  		}
}