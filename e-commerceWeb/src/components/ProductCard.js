import React from 'react';
import styled from 'styled-components';

const Card = styled.div`
  border: 1px solid #ccc;
  border-radius: 8px;
  padding: 1rem;
  text-align: center;
  width: 200px;
`;

const ProductImage = styled.img`
  width: 100%;  // Make image fill the card width
  height: auto;
  border-radius: 4px;
`;

const ProductCard = ({ product }) => {
  const imageUrl = product.images && product.images.length > 0 ? product.images[0] : '';  // Access the first image
  //console.log("Image URL:", imageUrl);
  return (
    <Card>
      <ProductImage src={imageUrl} alt={product.name} />
      <h2>{product.name}</h2>
      <p>${product.price}</p>
    </Card>
  );
};

export default ProductCard;