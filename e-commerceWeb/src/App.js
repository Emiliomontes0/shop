import React from 'react';
import styled from 'styled-components';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import Home from './components/Home';  // Assuming you have a Home component
import Shop from './components/Shop';
import ProductForm from './components/ProductForm';
import ShoppingCart from './components/ShoppingCart';
import Login from './components/Login';
import Register from './components/Register';
const AppContainer = styled.div`
  // your app container styles
`;

function App() {
  return (
    <Router>
      <AppContainer>
        <Navbar />
        <Routes> 
          <Route path="/" element={<Home />} />  
          <Route path="/shop" element={<Shop />} />
          <Route path="/create-product" element={<ProductForm />} />
          <Route path="/Cart" element = {<ShoppingCart />} />
          <Route path="/login" element = {<Login />} />
          <Route path="/register" element = {<Register/>}/>
          {/* Add more routes as necessary */}
        </Routes>
      </AppContainer>
    </Router>
  );
}

export default App;

