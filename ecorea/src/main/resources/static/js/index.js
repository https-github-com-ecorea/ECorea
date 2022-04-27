history.scrollRestoration = "auto"


const slickSlide = jQuery('.services-col')

if (slickSlide) {
  slickSlide.slick({
	infinite: true,
	variableWidth: true,
    dots: false,
    nextArrow: $('.next'),
    prevArrow: $('.prev'),
    slideToScroll: 1,
    autoplay: true,
    autoplaySpeed: 3000,
  })
}


function clickme() {
  window.scrollTo({top:0, left:0, behavior:'smooth'});
}
