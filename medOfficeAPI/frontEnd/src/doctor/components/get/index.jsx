import { useEffect, useState } from 'react'

function GetDoctor(){

    const [person, setPerson] = useState([])
  
    useEffect(() => {
      fetch("http://localhost:8082/doctor-ms/doctors/findAll/0")
      .then(Response => Response.json())
      .then(data => {
        setPerson(data);
      })
    }, [])
  
    return(
      <ul>
        {person.map(doctor => {
          return (
            <ul>
              <li key={doctor.id}>
                <strong>{doctor.name}</strong>
                <p>{doctor.specialty}</p>
                <p>{doctor.email}</p>
              </li>
            </ul>
          )
          })}
      </ul>
    )
  }

  export default GetDoctor