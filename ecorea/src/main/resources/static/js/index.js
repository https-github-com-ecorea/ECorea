history.scrollRestoration = "auto"


const slickSlide = jQuery('#slick-slide')

if (slickSlide) {
  slickSlide.slick({
    dots: true,
    arrows: false,
    slidesToShow: 3,
    slideToScroll: 1,
    autoplay: true,
    autoplaySpeed: 3000,
    responsive: [
      {
        breakpoint: 768,
        settings: {
          slidesToShow: 2
        }
      },
      {
        breakpoint: 576,
        settings: {
          slidesToShow: 1
        }
      }
    ]
  })
}


function clickme() {
  window.scrollTo({top:0, left:0, behavior:'smooth'});
}

function loginMenu() {
	if(role=="ROLE_MEMBER") {
  			$('.member').css('display','block');
  			$('.all').css('display','none');
  		} else if(role=="ROLE_CORP") {
  			$('.corp').css('display','block');
  			$('.all').css('display','none');
  		}
}