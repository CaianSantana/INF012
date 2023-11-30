import { useEffect, useState } from 'react'

function DeleteConsult(){

    useEffect(() => {
        fetch('http://localhost:8082/consult-ms/consults/1', {
            method: "DELETE",
        })
        .then(response => response.json()) 
        .then(json => console.log(json));
    }, [])
  
    return(
      <h1>Consulta deletada com sucesso!</h1>
    )
  }

  export default DeleteConsult