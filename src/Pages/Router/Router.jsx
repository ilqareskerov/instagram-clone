import React from 'react'
import SideBar from '../../Components/SideBar/SideBar'
import { Route, Routes } from 'react-router-dom'
import HomePage from '../HomePage/HomePage.jsx'
import Profile from '../Profile/Profile'
import Story from '../Story/Story'
const Router = () => {
  return (
    <div>
      <div className="flex">
        <div className="w-[20%] border boder-l-slate-500 ">
          <SideBar />
        </div>
        <div className="w-full">
          <Routes>
            <Route path="/" element={<HomePage />}></Route>
            <Route path="/username" element={<Profile />}></Route>
            <Route path='/story' element={<Story/>}></Route>
          </Routes>
        </div>
      </div>
    </div>
  )
}

export default Router
