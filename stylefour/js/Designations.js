const designationsTableBodyRef = document.getElementById('designationsTableBody');

var designations = [];

fetchDesignations();

function fetchDesignations() {
  designations = [];
  sendXHR("/stylefour/getDesignations", 'GET')
    .then(response => {
      
      if(response.status === 'UNAUTHORIZED') {
        window.location.href=response.data
      } else if (response.status === 'SUCCESS') {
        designations = response.data;
      } else if (response.status === 'DATABASE_ERROR') {
        showNotification(
          response.data,
          'Ok',
          '/stylefour/Designations.html'
        )
      }
      
      // let desginationStr = response.split(':');

      // for (str of desginationStr) {
      //   let designation = {};
      //   designation.code = str.split(',')[0];
      //   designation.title = str.split(',')[1];
      //   designations.push(designation);
      // }
      // designations.pop();
      showDesignations();
    }).catch(err => {
      console.log(err);
    })
}


function showDesignations() {
  designationsTableBodyRef.innerHTML = '';
  let index = 1;
  for (let designation of designations) {
    let htmlStr = `<tr><td style='text-align:right'>${index}</td>
                    <td>${designation.title}</td>
                    <td style='text-align:center'><a href='/stylefour/editDesignation?code=${designation.code}'>Edit</a></td>
                    <td style='text-align:center'><a href='/stylefour/confirmDeleteDesignation?code=${designation.code}'>Delete</a></td>
                  </tr>`
    index++;
    designationsTableBodyRef.insertAdjacentHTML('beforeend', htmlStr);
  }
  
}