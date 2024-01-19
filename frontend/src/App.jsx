import { useState } from 'react'
import Home from "./component/Home"
import EachVideo from './component/EachVideo'
import Navbar from './component/Navbar'

import './App.css'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Footer from './component/Footer'
import Login from './component/Login'
import Signup from './component/Signup'

function App() {
  const [count, setCount] = useState(0)

  return (
    <BrowserRouter>
    <Navbar></Navbar>
      <Routes>
        <Route path='/' element={<Home/>}/>
        <Route path='/video' element={<EachVideo/>}/>
        <Route path='/login' element={<Login/>}/>
        <Route path='/signup' element={<Signup/>}/>
      </Routes>
    <Footer></Footer>
    </BrowserRouter>
  )
}

export default App
