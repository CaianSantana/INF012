import { useEffect, useState } from 'react'

function PostPatient(){
    // dados a serem enviados pela solicitação POST
    let data = {
		name: "Caca",
		email: "caian0923@gmail.com",
    phone: "12312123",
		address:{
			publicPlace: "Rua 8 de Fevereiro",
			number: 54,
			complement: null,
			neighborhood:"Nova Cidade",
			city: "Salvador",
			state: "BA",
			zipCode: "40313-170"
		},
    crm: "6543321",
	  specialty: "GYNECOLOGIST"
	}

    useEffect(() => {
        fetch('http://localhost:8082/doctor-ms/doctors', {
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


