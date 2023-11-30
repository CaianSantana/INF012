import { useEffect, useState } from 'react'

function GetPatient(){

    const [person, setPerson] = useState([])
  
    useEffect(() => {
      fetch("http://localhost:8082/patient-ms/patients/findAll/0")
      .then(Response => Response.json())
      .then(data => {
        setPerson(data);
      })
    }, [])
  
    return(
      <ul>
        {person.map(patient => {
          return (
            <li key={patient.name}>
              <strong>{patient.name}</strong>
              <p>{patient.email}</p>
            </li>
          )
          })}
      </ul>
    )
  }

  export default GetPatient