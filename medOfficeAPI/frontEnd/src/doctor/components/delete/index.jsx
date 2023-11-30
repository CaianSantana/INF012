import { useEffect, useState } from 'react'

function DeletePatient(){

    useEffect(() => {
        fetch('http://localhost:8082/doctor-ms/doctors/2', {
            method: "DELETE",
        })
        .then(response => response.json()) 
        .then(json => console.log(json));
    }, [])
  
    return(
      <h1>Deletado com sucesso!</h1>
    )
  }

  export default DeletePatient