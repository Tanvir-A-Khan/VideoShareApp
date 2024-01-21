import  { useState, useEffect } from 'react';
import ReactPlayer from 'react-player';
import 'bootstrap/dist/css/bootstrap.min.css';
import Modal from 'react-bootstrap/Modal';
import { useParams } from 'react-router-dom';
import { Button } from 'react-bootstrap';
import axios from 'axios';

function VideoPage() {
  const { videoId } = useParams();
  // console.log(videoId);

  const [user, setUser] = useState(null);

  const [videoData, setVideoData] = useState(null);
  const [likes, setLikes] = useState(0);
  const [dislikes, setDislikes] = useState(0);
  const [showModal, setShowModal] = useState(false);
  // const [views, setViews] = useState(100);
  // const [res, setRes] = useState(100);

  
  
  useEffect(() => {
    const jsonString = localStorage.getItem('user');
  
    const user0 = JSON.parse(jsonString);
    // Fetch video data from the API
    console.log(user0);
    setUser(user0);
    fetch(`http://localhost:8080/v1/videos/${videoId}`)
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        // console.log(response.json());
        // setRes(response.json());
        return response.json();
      })
      .then(data => {
        console.log(data);
        setDislikes(data.disLikes)
        setLikes(data.likes);
        // setViews(data.views)
        setVideoData(data);
        
      })
      .catch(error => console.error('Error fetching video data:', error));
  }, []);

  const handleLike = async() => {

    try {
      const response = await axios.put(`http://localhost:8080/v1/videos/${videoId}/addLike/${user.name}`);
      // If the request is successful, you can handle the response here if needed
      console.log('Like added successfully', response.data);
      setVideoData(response.data)
      return response.data;
    } catch (error) {
      // Handle error, such as logging or displaying an error message
      console.error('Error adding like:', error);
      throw error; // You may choose to handle or rethrow the error based on your needs
    }

  };

  const handleDislike = async() => {
    try {
      const response = await axios.put(`http://localhost:8080/v1/videos/${videoId}/toggle-dislike/${user.name}`);
      // If the request is successful, you can handle the response here if needed
      console.log('DisLiked added successfully', response.data);
      setVideoData(response.data)
      return response.data;
    } catch (error) {
      console.error('Error adding like:', error);
      throw error; 
    }
  };

  const handleDetailsClick = () => {
    console.log(likes)
    setShowModal(true);
  };

  return (
    <div className="container">
      <div className="hero">
        <center>
          <ReactPlayer url={videoData?.videoLink} playing={true} muted={true} controls={true} />
        </center>
        <h1 className="text-center display-6">{videoData?.videoTitle}</h1>
        {/* Description removed */}
      </div>

      <div className="engagement-section text-center mt-4">
        <p className="small mt-2">Views: {videoData?.viewCount || 0}</p>
      </div>
      
      {
                 JSON.parse(localStorage.getItem('user'))?.userId
                 ?
                
                          
                    <section>

                      <div className="engagement-section text-center mt-4">
                        <button className="btn btn-success ms-1" onClick={handleLike}>
                          Like {videoData?.likes.length}
                        </button>
                        <button className="btn btn-danger ms-1" onClick={handleDislike}>
                          Dislike {videoData?.disLikes.length}
                        </button>
                        <button className="btn btn-primary ms-1" onClick={handleDetailsClick}>
                          Details
                        </button>
                      </div>

                      <Modal show={showModal} onHide={() => setShowModal(false)}>
                      <Modal.Header closeButton>
                        <Modal.Title>Video Details</Modal.Title>
                      </Modal.Header>
                      <Modal.Body style={{ maxHeight: '400px', overflowY: 'auto' }}>
                        {/* Video Publisher Name */}
                        <h5>Publisher: {videoData?.uploaderName}</h5>

                        <p>Video Title: {videoData?.videoTitle}</p>
                        {/* Add any other video details you want to display */}

                        {/* Likes and Dislikes */}
                        <div className="d-flex justify-content-between mt-4">
                          <div>
                            <h6>Likes {likes ? likes.length : 0}</h6>
                            <ul>
                              {likes && likes.length > 0 ? (
                                likes.map((name, index) => (
                                  <li key={index}>{name}</li>
                                  // You can perform other actions for each like here
                                  ))
                                  ) : (
                                    <li>No likes yet</li>
                              )}
                            </ul>
                          </div>
                          <div>
                            <h6>Dislikes {dislikes?.length}</h6>
                            <ul>
                              {dislikes && dislikes.length > 0 ? (
                                dislikes.map((name, index) => (
                                  <li key={index}>{name}</li>
                                  // You can perform other actions for each dislike here
                                ))
                              ) : (
                                <li>No dislikes yet</li>
                              )}
                            </ul>
                          </div>
                        </div>
                      </Modal.Body>
                      <Modal.Footer>
                        <Button variant="secondary" onClick={() => setShowModal(false)}>
                          Close
                        </Button>
                        {/* You can add additional buttons or actions here */}
                      </Modal.Footer>
                    </Modal>
                  </section>
            :
            null
    }

    </div>
  );
}

export default VideoPage;
