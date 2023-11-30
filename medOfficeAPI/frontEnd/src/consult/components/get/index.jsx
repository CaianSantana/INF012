import { useEffect, useState } from 'react'

function GetConsult(){

    const [consult, setConsult] = useState([])
  
    useEffect(() => {
      fetch("http://localhost:8082/consult-ms/consults")
      .then(Response => Response.json())
      .then(data => {
        setConsult(data);
      })
    }, [])
  
    return(
      <ul>
        {consult.map(consult => {
          return (
            <li key={consult.id}>
              <strong>{consult.patientName}</strong>
              <p>{consult.patientEmail}</p>
              <strong>{consult.doctorName}</strong>
              <p>{consult.specialty}</p>
              <p>{consult.doctorEmail}</p>
            </li>
          )
          })}
      </ul>
    )
  }

  export default GetConsult