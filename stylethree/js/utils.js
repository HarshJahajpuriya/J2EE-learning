function sendXHR(requestURL, method, dataToSend = null) {
  return new Promise((resolve, reject) => {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = () => {
      if (xhr.readyState == 4) {
        if (xhr.status == 200) {
          let data = xhr.responseText;
          console.log(data);
          resolve(data);
        } else {
          let err = xhr.response;
          console.log(err)
          reject(err);
        }
      }
    }

    xhr.open(method, requestURL, true)
    if (method == 'POST') {
      console.log(dataToSend);
      xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
      xhr.send(dataToSend);
    } else
      xhr.send();
  })
}

function addBackdrop() {
  let htmlStr = `<div id='backdrop'> </div>`
  let body = document.querySelector('body');
  body.insertAdjacentHTML('afterbegin', htmlStr);
}

function removeBackdrop() {
  document.getElementById('backdrop')?.remove();
}

function showNotification(text, btn1Text, btn1Link, btn2Text, btn2Link) {
  addBackdrop();
  console.log(text, btn1Text, btn1Link, btn2Text, btn2Link);
  let htmlstr = `
    <div id="notificationDialog">
      <div id="notification">${text}</div>

      <div id="buttons">
        <button type="button" onclick="redirect('${btn1Link}')">${btn1Text}</button>
        
  `;
  if(btn2Text) {
    htmlstr += `
      &nbsp;
      &nbsp;
      <button type="button" onclick="redirect('${btn2Link}')">${btn2Text}</button>
    `;
  }

  htmlstr += `
      </div>
    </div>
  `;
  
  document.getElementById('backdrop').innerHTML = htmlstr;
}

function redirect(path) {
  console.log(path)
  window.location.href = path;
}