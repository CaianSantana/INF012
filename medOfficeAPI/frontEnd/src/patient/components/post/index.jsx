import { useEffect, useState } from 'react'

function PostPatient(){
    // dados a serem enviados pela solicitação POST
    let data = {
		name: "Caca",
		cpf: "06600870577",
		email: "caian0923@gmail.com",
		address:{
			publicPlace: "Rua 8 de Fevereiro",
			number: 54,
			complement: null,
			neighborhood:"Nova Cidade",
			city: "Salvador",
			state: "BA",
			zipCode: "40313-170"
		}
	}

    useEffect(() => {
        fetch('http://localhost:8082/patient-ms/patients', {
            method: "POST",
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

  export default PostPatient


