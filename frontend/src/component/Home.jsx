import { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useNavigate } from 'react-router-dom';

function VideoList() {
  const navigate = useNavigate();
  const [videos, setVideos] = useState([]);

  useEffect(() => {
    // Fetch data from the API
    fetch('http://localhost:8080/v1/videos/get-all-videos') // Replace with your actual API URL
      .then(response => response.json())
      .then(data => setVideos(data))
      .catch(error => console.error('Error fetching data:', error));
  }, []);

  const handleWatch = (index) => {
    console.log(videos);
    // http://localhost:8080/v1/videos/65abe123ada01609d9457965
    navigate(`/video/${videos[index].videoId}`);
  };

  return (
    <div className="container">
      <h1 className="text-center display-4">Amazing Video Collection</h1>
      <p className="lead text-center">Explore our most popular videos</p>

      <div className="container">
        <div className="row row-cols-1 row-cols-md-3 g-4">
          {videos.map((video, index) => (
            <div key={video.videoId} className="col">
              <div className="card h-100">
                <div className="aspect-ratio ratio-16x9">

                </div>
                <div className="card-body">
                  <h5 className="card-title">{video.videoTitle}</h5>
                  <p className="card-text">Views: {video.viewCount}</p>
                  <button className="btn btn-primary" onClick={() => handleWatch(index)}>
                    Watch Now
                  </button>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>

    </div>
  );
}

export default VideoList;
