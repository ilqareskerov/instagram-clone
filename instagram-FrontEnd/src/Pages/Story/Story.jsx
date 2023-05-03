import React from 'react'
import StoryViewer from '../../Components/StoryComponents/StoryViewer'

const Story = () => {
    const story=[{
        image:"https://cdn.pixabay.com/photo/2023/04/27/11/36/maine-coon-7954384__340.jpg"
    },
    {
        image:"https://cdn.pixabay.com/photo/2023/04/30/10/05/philodendron-7960228__340.jpg"
    },
    {
        image:"https://cdn.pixabay.com/photo/2023/04/30/10/05/philodendron-7960228__340.jpg"
    },
    {
        image:"https://cdn.pixabay.com/photo/2023/04/28/19/27/cow-7957275__340.jpg"
    }]
  return (
    <div>
      <StoryViewer stories={story}/>
    </div>
  )
}

export default Story
