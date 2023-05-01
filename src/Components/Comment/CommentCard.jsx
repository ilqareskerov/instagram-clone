import React, { useState } from 'react'
import { AiFillHeart, AiOutlineHeart } from 'react-icons/ai';

const CommentCard = () => {
    const [isCommentLiked,setIsCommentLiked] = useState(false);
    const handleLikeComment=()=>{
        setIsCommentLiked(!isCommentLiked);
    }
  return (
    <div>
      <div className='flex items-center justify-between py-5'>
        <div className="flex items-center">
          <div>
            <img
              className="w-9 h-9 rounded-full"
              src="https://cdn.pixabay.com/photo/2023/04/19/19/47/gosling-7938462_960_720.jpg"
              alt=""
            />
          </div>
          <div className="ml-3">
            <p>
              <span className="font-semibold">username</span>
              <span className="ml-2">nice photo</span>
            </p>
            <div className='flex items-center space-x-3 text-xs opacity-60'>
              <span>1 min ago</span>
              <span>23 likes</span>
            </div>
          </div>
        </div>


{isCommentLiked?<AiFillHeart onClick={handleLikeComment} className='text-xs hover hover:opacity-50 cursor-pointer text-red-600'/>:<AiOutlineHeart className='text-xs hover hover:opacity-50 cursor-pointer' onClick={handleLikeComment}/>}
      </div>
    </div>
  )
}

export default CommentCard
