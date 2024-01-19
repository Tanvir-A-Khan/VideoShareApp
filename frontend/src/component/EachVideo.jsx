import React, { useState } from 'react';
import ReactPlayer from 'react-player';
import 'bootstrap/dist/css/bootstrap.min.css';
import Modal from 'react-bootstrap/Modal';

function VideoPage() {
  const [likes, setLikes] = useState(0);
  const [dislikes, setDislikes] = useState(0);
  const [showModal, setShowModal] = useState(false);
  const [views, setViews] = useState(100);

  const handleLike = () => {
    setLikes(likes + 1);
  };

  const handleDislike = () => {
    setDislikes(dislikes + 1);
  };

  const handleDetailsClick = () => {
    setShowModal(true);
  };

  return (
    <div className="container">
      <div className="hero">
        <center>
          <ReactPlayer url="your-video-url" playing={true} muted={true} controls={true} />
        </center>
        <h1 className="text-center display-4">Compelling Video Title</h1>
        {/* Description removed */}
      </div>

      <div className="engagement-section text-center mt-4">
        <p className="small mt-2">Views: {views}</p>
        <button className="btn btn-success ms-1" onClick={handleLike}>
          Like {likes}
        </button>
        <button className="btn btn-danger  ms-1" onClick={handleDislike}>
          Dislike {dislikes}
        </button>
        <button className="btn btn-primary  ms-1" onClick={handleDetailsClick}>
          Details
        </button>
      </div>

      <Modal show={showModal} onHide={() => setShowModal(false)}>
        <Modal.Header closeButton>
          <Modal.Title>Video Details</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {/* Additional video details here */}
        </Modal.Body>
      </Modal>
    </div>
  );
}

export default VideoPage;
