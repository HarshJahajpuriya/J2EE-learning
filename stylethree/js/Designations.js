const designationsTableBodyRef = document.getElementById('designationsTableBody');

var designations = [];

fetchDesignations();

function fetchDesignations() {
  designations = [];
  sendXHR("/stylethree/getDesignations", 'GET')
    .then(response => {
      let desginationStr = response.split(':');

      for (str of desginationStr) {
        let designation = {};
        designation.code = str.split(',')[0];
        designation.title = str.split(',')[1];
        designations.push(designation);
      }
      designations.pop();
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
                    <td style='text-align:center'><a href='/stylethree/editDesignation?code=${designation.code}'>Edit</a></td>
                    <td style='text-align:center'><a href='/stylethree/confirmDeleteDesignation?code=${designation.code}'>Delete</a></td>
                  </tr>`
    index++;
    designationsTableBodyRef.insertAdjacentHTML('beforeend', htmlStr);
  }
  
}