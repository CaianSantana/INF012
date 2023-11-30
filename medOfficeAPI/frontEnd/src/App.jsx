import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  const [repositories, setRepositories] = useState([])
  
  useEffect(() => {
    fetch("http://localhost:8082/patient-ms/patients/findAll/0")
    .then(Response => Response.json())
    .then(data => {
      setRepositories(data);
    })
  }, [])

  return(
    <ul>
      {repositories.map(patient => {
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

export default App
