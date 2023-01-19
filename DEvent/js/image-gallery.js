var displayImageBackdrop = null;
var displayImageImg = null;
var mainConatinerDivBackdrop = null;

$(() => {
  $('.column >img').on('click', selectImage);
  displayImageBackdrop = $('#display-image-backdrop').get(0);
  displayImageImg = $('#display-image-img').get(0);
  mainConatinerDivBackdrop = $('#main-container-div-backdrop').get(0);
});

function selectImage(ev) {
  displayImage(ev.target.src)
}

function displayImage(src) {
  displayImageBackdrop.style.zIndex = 200;
  // mainConatinerDivBackdrop.style.backgroundColor = 'rgba(0, 0, 0, 0.45)';
  displayImageImg.src = src;
}

function hideImage() {
  displayImageBackdrop.style.zIndex = -200;
  // mainConatinerDivBackdrop.style.backgroundColor = 'rgba(231, 229, 226, 0.414)';
}