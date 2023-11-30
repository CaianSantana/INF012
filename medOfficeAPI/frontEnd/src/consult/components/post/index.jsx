import { useEffect, useState } from 'react'

function PostConsult(){
    // dados a serem enviados pela solicitação POST
    let data = {
      crm: "123456",
      cpf: "06600870574",
      scheduling:{
        schedule: "12 01 2023 17:30"
      }
    }

    useEffect(() => {
        fetch('http://localhost:8082/consult-ms/Consults', {
            method: "POST",
            body: JSON.stringify(data),
            headers: {"Content-type": "application/json; charset=UTF-8"}
        })
        .then(response => response.json()) 
        .then(json => console.log(json));
    }, [])
  
    return(
      <ul>
            <li key={data.id}>
              <strong>{data.patientName}</strong>
              <p>{data.patientEmail}</p>
              <strong>{data.doctorName}</strong>
              <p>{data.specialty}</p>
              <p>{data.doctorEmail}</p>
            </li>
      </ul>
    )
  }

  export default PostConsult


