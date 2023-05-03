import React from 'react'
import { useNavigate } from 'react-router-dom'

const StoryCIrcle = () => {
  const nagivate = useNavigate();
  const handleNavigate=()=>{
    nagivate("/story")
  }
  return (
    <div onClick={handleNavigate} className='cursor-pointer flex flex-col items-center'>
      <img className='w-16 h-16 rounded-full' src="https://cdn.pixabay.com/photo/2023/04/13/13/17/lotus-7922528__340.jpg" alt="" />
      <p>username</p>
    </div>
  )
}

export default StoryCIrcle
