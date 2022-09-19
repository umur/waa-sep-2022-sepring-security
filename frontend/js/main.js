function signin(){
    let data = {};
    data["email"] = document.querySelector('[name=email]').value;
    data["password"] = document.querySelector('[name=password]').value;
    console.log(data);

    fetch('http://localhost:8080/uaa/', {
        method: 'POST', 
        headers: {
             'Content-Type': 'application/json',
        },
      body: JSON.stringify(data),
   }).then(response => {
    if(response.status == 403){
        alert("Unautorized User")
        throw 'Unauthorized User' 
    }
    else{
        return response.json();
    }

   }).then(data => {
    let { accessToken: atoken, refreshToken: ftoken } = data;
    localStorage.setItem('accessToken',atoken);
   
   } )
   .catch(err => console.log("Error " +  err))
}

function signup(){
    let data = {};
    data["firstname"] = document.querySelector('[name=firstname]').value;
    data["email"] = document.querySelector('[name=email]').value;
    data["password"] = document.querySelector('[name=password]').value;
    data["role"] = document.querySelector('[name=role]').value;

    fetch('http://localhost:8080/uaa/signup', {
        method: 'POST', 
        headers: {
             'Content-Type': 'application/json',
        },
      body: JSON.stringify(data),
   }).then(response => {
        if(response.status == 200){
            alert("Sigin up succesful")
        }
   })
   .catch(err => console.log("Error " +  err))
    

}