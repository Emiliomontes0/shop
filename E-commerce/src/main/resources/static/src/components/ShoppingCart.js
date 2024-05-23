// components/ShoppingCart.js
import React from 'react';
import styled from 'styled-components';

const CartContainer = styled.div`
  position: fixed;
  top: 0;
  right: 0;
  width: 300px;
  height: 100%;
  background-color: white;
  border-left: 1px solid #ccc;
  // Add other styles for the cart
`;

const ShoppingCart = ({ cartItems }) => {
  return (
    `<CartContainer>
      <h2>Shopping Cart</h2>
      {/* Iterate over cart items and display them */}
      {cartItems.map((item, index) => (
        <div key={index}>
          <span>{item.title}</span>
          <span>{item.quantity}</span>
          <span>$ {item.price}</span>
        </div>
      ))}
      {/* Add Checkout button */}
    </CartContainer>`
  );
};

export default ShoppingCart;
