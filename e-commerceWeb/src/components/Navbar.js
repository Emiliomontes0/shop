// components/Navbar.js
import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';

const Nav = styled.nav`
  background-color: #333;
  color: white;
  padding: 1rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const NavLink = styled(Link)`
  color: white;
  margin-right: 1rem;
  text-decoration: none;
`;

const LeftNav = styled.div`
  display: flex;
  align-items: center;
`;

const RightNav = styled.div`
  display: flex;
  align-items: center;
`;

const Navbar = () => {
  return (
    <Nav>
      <LeftNav>
        <NavLink to="/">Home</NavLink>
        <NavLink to="/shop">Shop</NavLink>
      </LeftNav>
      <RightNav>
        <NavLink to="/login">Login</NavLink>
        <NavLink to="/cart">Cart</NavLink>
      </RightNav>
    </Nav>
  );
};

export default Navbar;
