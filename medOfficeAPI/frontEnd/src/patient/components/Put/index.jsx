import { useEffect, useState } from 'react'

function PutPatient(){
    // dados a serem enviados pela solicitação POST
    let data = {
		name: "Carlos",
		address:{
			publicPlace: "Rua 8 de Fevereiro",
			neighborhood:"Nova Cidade",
			city: "Salvador",
			state: "BA",
			zipCode: "40313-170"
		}
	}

    useEffect(() => {
        fetch('http://localhost:8082/patient-ms/patients/1', {
            method: "PUT",
            body: JSON.stringify(data),
            headers: {"Content-type": "application/json; charset=UTF-8"}
        })
        .then(response => response.json()) 
        .then(json => console.log(json));
    }, [])
  
    return(
      <ul>
            <li key={data.name}>
              <strong>{data.name}</strong>
              <p>{data.email}</p>
            </li>
      </ul>
    )
  }

  export default PutPatient