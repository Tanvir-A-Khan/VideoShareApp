import { useState } from 'react';
import { Form, Button } from 'react-bootstrap';

import { Link } from 'react-router-dom';


function Signup() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [username, setUsername] = useState('');
    const [error, setError] = useState(null);

    const handleSignup = (e) => {
        e.preventDefault();



    
    };

    return (
        <div className=' p-5 rounded w-50 m-auto'>
            <Form>
                <h1>Register yourself</h1>
                <Form.Group className="mb-3">
                    {/* <Form.Label>User Name</Form.Label> */}
                    <Form.Control type="text" placeholder="Enter username" value={username} onChange={(e) => setUsername(e.target.value)} />
                </Form.Group>

                <Form.Group className="mb-3" >
                    {/* <Form.Label>Email address</Form.Label> */}
                    <Form.Control type="email" placeholder="Enter email" value={email} onChange={(e) => setEmail(e.target.value)} />
                    <Form.Text className="text-muted">
                        {/* <p>We will never share your email with anyone else.</p> */}
                    </Form.Text>
                </Form.Group>

                <Form.Group className="mb-3"  >
                    {/* <Form.Label>Password</Form.Label> */}
                    <Form.Control type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
                </Form.Group>

                {error && <p style={{ color: 'red' }}>{error}</p>}

                <Button variant="primary" type="submit" onClick={handleSignup}>
                    Submit
                </Button>
                <p>Already have an account? <Link to='/login'>Login</Link></p>
            </Form>
        </div>
    );
}

export default Signup;
