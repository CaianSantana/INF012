import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import GetPatient from './patient/components/get'
import PostPatient from './patient/components/post'
import DeletePatient from './patient/components/delete'
import PostConsult from './consult/components/post'
import PostDoctor from './doctor/components/post'
import GetDoctor from './doctor/components/get'

function App() {
  return(
   <GetDoctor/>
  )
}

export default App
