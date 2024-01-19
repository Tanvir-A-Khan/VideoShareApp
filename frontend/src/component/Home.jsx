import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useNavigate } from 'react-router-dom';

function VideoList() {
  const navigate= useNavigate();
  const handleWatch = ()=>{
    navigate("/video")
  }
  const videos = [
    {
      id: 1,
      title: 'Video Title 1',
      views: 1000,
      thumbnail: '/path/to/thumbnail1.jpg', // Replace with actual image path
    },
    {
      id: 1,
      title: 'Video Title 1',
      views: 1000,
      thumbnail: '/path/to/thumbnail1.jpg', // Replace with actual image path
    },
    {
      id: 1,
      title: 'Video Title 1',
      views: 1000,
      thumbnail: '/path/to/thumbnail1.jpg', // Replace with actual image path
    },
    {
      id: 1,
      title: 'Video Title 1',
      views: 1000,
      thumbnail: '/path/to/thumbnail1.jpg', // Replace with actual image path
    },
    {
      id: 1,
      title: 'Video Title 1',
      views: 1000,
      thumbnail: '/path/to/thumbnail1.jpg', // Replace with actual image path
    },
    // Add more videos here
  ];

  return (
    <div className="container">
      <h1 className="text-center display-4">Amazing Video Collection</h1>
      <p className="lead text-center">Explore our most popular videos</p>

      <div className="row mt-4">
        {videos.map((video) => (
          <div key={video.id} className="col-md-4 mt-4">
            <div className="card">
              <img
                className="card-img-top"
                src={video.thumbnail}
                alt={video.title}
              />
              <div className="card-body">
                <h5 className="card-title">{video.title}</h5>
                <p className="card-text">Views: {video.views}</p>
                <button className="btn btn-primary" onClick={handleWatch}>Watch Now</button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default VideoList;
