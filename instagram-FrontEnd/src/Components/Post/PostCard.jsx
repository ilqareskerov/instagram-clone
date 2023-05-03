import React, { useState } from 'react'
import {
  BsBookmark,
  BsBookmarkFill,
  BsEmojiSmile,
  BsThreeDots,
} from 'react-icons/bs'
import './PostCard.css'
import {AiFillHeart, AiOutlineHeart } from 'react-icons/ai'
import { FaRegComment } from 'react-icons/fa'
import { RiSendPlaneLine } from 'react-icons/ri'
import CommentModel from '../Comment/CommentModal'
import { useDisclosure } from '@chakra-ui/react'


const PostCard = () => {
  const [isPostLiked, setIsPostLiked] = useState(false)
  const [showDropDown, setShowDropDown] = useState(false)
  const [isSaved, setIsSaved] = useState(false)
  const { isOpen, onOpen, onClose } = useDisclosure();

  const handlePostSave = () => {
    setIsSaved(!isSaved)
  }

  const handlePostLike = () => {
    setIsPostLiked(!isPostLiked)
  }

  const handleClick = () => {
    setShowDropDown(!showDropDown)
  }

  const handleOpenCommentModal=()=>{
    onOpen();
  }
  
  return (
    <div>
      <div className="border rounded-md w-full">
        <div className="flex justify-between items-center w-full py-4 px-5">
          <div className="flex items-center">
            <img
              className="w-12 h-12 rounded-full"
              src="https://cdn.pixabay.com/photo/2023/04/22/22/58/bird-7944578__340.jpg"
              alt=""
            />
            <div className="pl-2">
              <p className="font-semibold text-sm">username</p>
              <p className="font-thin text-sm">location</p>
            </div>
          </div>
          <div className="dropdown">
            <BsThreeDots className="dots" onClick={handleClick} />
            <div onClick={handleClick} className="dropdown-content">
              {showDropDown && (
                <p className="bg-black text-white py-1 px-4 rounded-md cursor-pointer">
                  Delete
                </p>
              )}
            </div>
          </div>
        </div>

        <div className="w-full">
          <img
            className="w-full"
            src="https://cdn.pixabay.com/photo/2023/04/22/02/20/insect-7942861_640.jpg"
            alt=""
          />
        </div>

        <div className="flex justify-between items-center w-full px-5 py-4">
          <div className="flex items-center space-x-2">
            {isPostLiked ? (
              <AiFillHeart
                className="text-2xl hover:opacity-50 cursor-pointer text-red-700"
                onClick={handlePostLike}
              />
            ) : (
              <AiOutlineHeart
                className="text-2xl hover:opacity-50 cursor-pointer "
                onClick={handlePostLike}
              />
            )}
            <FaRegComment onClick={handleOpenCommentModal} className="text-xl hover:opacity-50 cursor-pointer" />
            <RiSendPlaneLine className="text-xl hover:opacity-50 cursor-pointer" />
          </div>

          <div className="cursor-pointer">
            {isSaved ? (
              <BsBookmarkFill
                className="text-xl hover:opacity-50 cursor-pointer"
                onClick={handlePostSave}
              />
            ) : (
              <BsBookmark
                className="text-xl hover:opacity-50 cursor-pointer"
                onClick={handlePostSave}
              />
            )}
          </div>
        </div>

        <div className="w-full py-2 px-5">
          <p>10 likes</p>
          <p className="opacity-50 py-2 cursor-pointer">view all 10 comments</p>
        </div>

        <div className="border border-t w-full">
          <div className="flex w-full items-center px-5">
            <BsEmojiSmile />
            <input
              className="commentInput"
              type="text"
              placeholder="Add a comment..."
            />
          </div>
        </div>
      </div>
      <CommentModel 
      handlePostLike={handlePostLike} 
      onClose={onClose}  
      isOpen={isOpen} 
      handlePostSave={handlePostSave} 
      isPostLiked={isPostLiked} 
      isSaved={isSaved}/>
    </div>
  )
}

export default PostCard
