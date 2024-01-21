import axios from 'axios';
import { useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';

function Login() {
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null);

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.get('http://localhost:8080/v1/login', {
              params: {
                email: email,
                password: password
              }
            });
        
            if (response.status === 200) {
              // User found
              console.log('User found:', response.data);
              localStorage.setItem("user", JSON.stringify(response.data))
            //   console.log(JSON.parse(localStorage.getItem("user")))
              navigate("/dashboard")

            } else {
              // Unexpected response status
              console.error('Unexpected response status:', response.status);
              return null;
            }
          } catch (error) {
            // Handle error, such as logging or displaying an error message
            console.error('Error fetching user:', error);
            setError("Username or Password didnt match");
            //throw error; // You may choose to handle or rethrow the error based on your needs
          }
    };

    return (
        <div className='m-5 p-5  rounded w-50 m-auto'>
            <Form onSubmit={handleLogin}>
                <h1>Login Here</h1>
                <Form.Group className='mb-3' controlId='formBasicEmail'>
                    <Form.Control
                        type='email'
                        placeholder='Enter email'
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </Form.Group>

                <Form.Group className='mb-3' controlId='formBasicPassword'>
                    <Form.Control
                        type='password'
                        placeholder='Password'
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </Form.Group>

                {error && <p style={{ color: 'red' }}>{error}</p>}

                <Button variant='primary' type='submit'>
                    Submit
                </Button>

                <p>
                    Dont have an account? <Link to='/signup'>Signup</Link>
                </p>
            </Form>
        </div>
    );
}

export default Login;
