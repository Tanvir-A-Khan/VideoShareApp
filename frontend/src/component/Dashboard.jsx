import axios from 'axios';
import { useEffect, useState } from 'react';
import { Container, Row, Col, Form, Button } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

const Dashboard = () => {
  const navigate = useNavigate();
  // Define states for form fields
  const [title, setTitle] = useState('');
  const [videoLink, setVideoLink] = useState('');
  const [videos, setVideos] = useState([]);

  useEffect(() => {
    const fetchVideos = async () => {
      try {
        const jsonString = localStorage.getItem('user');
        const user = JSON.parse(jsonString);
        const list = await getVideosByUploaderName(user.name);
        console.log(list);
      } catch (error) {
        // Handle error, such as logging or displaying an error message
        console.error('Error fetching videos:', error);
      }
    };

    fetchVideos();
  }, []);
  const handleWatch = (index) => {
    console.log(videos);
    // http://localhost:8080/v1/videos/65abe123ada01609d9457965
    navigate(`/video/${videos[index].videoId}`);
  };


  const getVideosByUploaderName = async (uploaderName) => {
    try {
      const response = await axios.get(`http://localhost:8080/v1/videos/list/${uploaderName}`);

      if (response.status === 200) {
        // Videos found
        setVideos(response.data);
        return response.data;
      } else {
        // Unexpected response status
        console.error('Unexpected response status:', response.status);
        return [];
      }
    } catch (error) {
      // Handle error, such as logging or displaying an error message
      console.error('Error fetching videos:', error);
      throw error; // You may choose to handle or rethrow the error based on your needs
    }
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    console.log('Title:', title);
    console.log('Video Link:', videoLink);

    const jsonString = localStorage.getItem('user');
    const user = JSON.parse(jsonString);

    console.log(user);

    const video = {
      videoTitle: title,
      uploaderName: user.name,
      videoLink: videoLink,
      viewCount: 0,
      likes: [],
      disLikes: [],
    };

    console.log(video);

    try {
      const response = await axios.post('http://localhost:8080/v1/videos/add-a-video', video);
      console.log('Response:', response.data);
    } catch (error) {
      console.error('Error:', error);
    }

    setTitle('');
    setVideoLink('');
    navigate('/dashboard');
  };

  return (
    <Container>
      <Row className="mt-4">
        <Col>
          <h1>Dashboard</h1>
        </Col>
      </Row>
      <Row className="mt-4">
        <h5>Upload a youtube video</h5>
        <Col>
          <Form onSubmit={handleSubmit}>
            {/* Title Field */}
            <Form.Group controlId="title">
              <Form.Control
                type="text"
                placeholder="Enter title"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
              />
            </Form.Group>

            {/* Video Link Field */}
            <Form.Group controlId="videoLink" className="mt-2">
              <Form.Control
                type="text"
                placeholder="Enter Youtube video link"
                value={videoLink}
                onChange={(e) => setVideoLink(e.target.value)}
              />
            </Form.Group>

            {/* Submit Button */}
            <Button variant="primary" type="submit" className="mt-2">
              Submit
            </Button>
          </Form>
        </Col>
      </Row>

      <div className="row mt-4">
        <h2>Uploaded videos</h2>
        {videos.length > 0 ? (
          videos.map((video, index) => (
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
          ))
        ) : (
          <p>No videos available</p>
        )}
      </div>
    </Container>
  );
};

export default Dashboard;
