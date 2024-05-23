import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import styled from 'styled-components';
import ProductCard from './ProductCard';
import Layout from './Layout';

const ProductsContainer = styled.div`
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  justify-content: center;
  margin-top: 20px;  // Adjust top margin if necessary
`;

const HeadersContainer = styled.div`
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  align-items: center; 
`;

const StyledButton = styled.button`
  padding: 0.8em 1.5em;
  border: none;
  background-color: #007BFF;
  color: white;
  font-size: 1rem;
  font-weight: bold;
  border-radius: 5px;
  cursor: pointer;
  outline: none;
  transition: background-color 0.3s, box-shadow 0.3s, transform 0.3s;

  &:hover {
    background-color: #0056b3;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
  }

  &:active {
    transform: scale(0.98);
  }

  &:disabled {
    background-color: #ccc;
    cursor: not-allowed;
  }
`;


const Shop = () => {
  const navigate = useNavigate();
  const [products, setProducts] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await axios.get('http://localhost:3001/api/products'); // Adjust the URL as needed
        setProducts(response.data);
        setIsLoading(false);
      } catch (err) {
        setError('Failed to fetch products');
        setIsLoading(false);
        console.error(err);
      }
    };

    fetchProducts();
  }, []); // Empty dependency array ensures this runs only once on mount

  const handleCreateProductClick = () => {
    navigate('/create-product');
  };

  if (isLoading) return <p>Loading...</p>;
  if (error) return <p>{error}</p>;

  return (
    <Layout>
      <HeadersContainer>
        <h1>Shop</h1>
        <StyledButton onClick={handleCreateProductClick} style={{ marginTop: '20px' }}>Create New Product</StyledButton>
      </HeadersContainer>
      <ProductsContainer>
        {products.map((product) => (
          <ProductCard key={product.id} product={product} />
        ))}
      </ProductsContainer>
    </Layout>
  );
};

export default Shop;
