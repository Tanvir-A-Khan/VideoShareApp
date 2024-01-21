
// import { useEffect, useState } from 'react';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Form from 'react-bootstrap/Form';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { useNavigate } from 'react-router-dom';


function NavScrollExample() {
  const navigate = useNavigate();

  JSON.parse(localStorage.getItem('user'))?.userId




  const handleLogout = (e)=>{
    e.preventDefault();
    localStorage.clear();
    navigate("/")
    
  }
  const handleSignup = (e)=>{
    e.preventDefault();
    navigate("/login")
  }

  const gotoHome = ()=>{
    navigate("/")
  }
  const handleDash = ()=>{
    navigate("/dashboard")
  }

  return (
    <Navbar expand="lg" className="bg-body-tertiary ps-5 pe-5">
      <Container fluid>
        <Navbar.Brand >VideoShareApp</Navbar.Brand>
        <Navbar.Toggle aria-controls="navbarScroll" />
        <Navbar.Collapse id="navbarScroll">
          <Nav
            className="me-auto my-2 my-lg-0"
            style={{ maxHeight: '100px' }}
            navbarScroll
          >
            <Nav.Link onClick={gotoHome}>Home</Nav.Link>
           
          </Nav>
          <Form className="d-flex">
        
            {
                 JSON.parse(localStorage.getItem('user'))?.userId
                 ?
                 <Nav.Link className='p-2 w-50 ps-3' onClick={handleDash}>Dashboard</Nav.Link>
                 :
                 null
            }

            {
                 JSON.parse(localStorage.getItem('user'))?.userId
                 ?
             
                 <Button className='bg-danger ms-2'onClick={handleLogout}>Logout</Button>
                
                 :
                 <Button className='bg-primary ms-2' onClick={handleSignup}>Login</Button>
               
            }
          </Form>

        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default NavScrollExample;