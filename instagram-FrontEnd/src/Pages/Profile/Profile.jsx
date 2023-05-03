import React from 'react'
import ProfileUserDetails from '../../Components/ProfileComponents/ProfileUserDetails'
import ReqUserPostPart from '../../Components/ProfileComponents/ReqUserPostPart'

const Profile = () => {
  return (
    <div>
      <div className="px-20">
        <ProfileUserDetails />
      </div>
      <div className="px-20">
        <ReqUserPostPart />
      </div>
    </div>
  )
}

export default Profile
