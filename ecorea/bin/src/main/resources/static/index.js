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