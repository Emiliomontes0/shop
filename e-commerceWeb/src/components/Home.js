import React from 'react';
import styled from 'styled-components';

const PageContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  min-height: 100vh;
  background-color: #f0f0f0;
`;

const HeroSection = styled.div`
  width: 100%;
  padding: 20vh 0;
  background-color: #f4f4f4;
  text-align: center;
  background-image: url('https://example.com/your-banner-image.jpg'); /* Change URL */
  background-size: cover;
  background-position: center;
  color: #ffffff; /* Assuming white text for visibility */
  margin-bottom: 20px;
`;

const Heading = styled.h1`
  margin: 0;
  color: #ffffff; /* Adjusted for better visibility on potential dark background */
`;

const SubHeading = styled.p`
  color: #b0b0b0; /* Lighter text for contrast */
`;

const Section = styled.section`
  width: 90%;
  max-width: 1200px;
  margin: 20px 0;
`;

const SectionTitle = styled.h2`
  font-size: 1.5em;
  color: #333;
  margin-bottom: 10px; /* Added spacing between title and products */
`;

const ProductGrid = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 20px;
`;

const ProductCard = styled.div`
  border: 1px solid #ccc;
  padding: 1rem;
  background-color: #fff;
  border-radius: 8px;
  text-align: center;
  box-shadow: 0 2px 5px rgba(0,0,0,0.1); /* Subtle shadow */
`;

const Footer = styled.footer`
  width: 100%;
  background-color: #333;
  color: white;
  padding: 20px;
  text-align: center;
  margin-top: auto;
`;

// Example product data
const products = Array(10).fill(null).map((_, index) => ({
  id: index,
  name: `Product ${index + 1}`,
  price: (19.99 + index).toFixed(2)
}));

const Home = () => {
  return (
    <PageContainer>
      <HeroSection>
        <Heading>Welcome to Our Online Store</Heading>
        <SubHeading>Find the best products at the lowest prices</SubHeading>
      </HeroSection>
      <Section>
        <SectionTitle>Best Sellers</SectionTitle>
        <ProductGrid>
          {products.map(product => (
            <ProductCard key={product.id}>
              <h3>{product.name}</h3>
              <p>${product.price}</p>
            </ProductCard>
          ))}
        </ProductGrid>
      </Section>
      <Section>
        <SectionTitle>Recently Added</SectionTitle>
        <ProductGrid>
          {products.slice(0, 5).map(product => (
            <ProductCard key={product.id}>
              <h3>{product.name}</h3>
              <p>${product.price}</p>
            </ProductCard>
          ))}
        </ProductGrid>
      </Section>
      <Footer>
        © 2024 Your Store Name - All Rights Reserved
      </Footer>
    </PageContainer>
  );
};

export default Home;
