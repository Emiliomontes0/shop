import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  justify-content: center;
  align-items: flex-start; // Align items at the start of the flex container
  gap: 20px; // Space between form and preview
  padding: 40px; // Padding around the container
`;

const Card = styled.div`
  border: 1px solid #ccc;
  border-radius: 8px;
  width: 200px;
  height: 210px;  // Fixed height
  margin: 40px auto;
  padding: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  background-image: url(${props => props.backgroundImage});
  background-size: cover;
  background-position: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  color: white; // Ensure text is visible on varying backgrounds
  overflow: hidden; // Adds scrollbars if content exceeds the element's box
  text-align: center; // Center text horizontally
`;

const StyledForm = styled.form`
  display: flex;
  flex-direction: column;
  gap: 10px;
  width: 100%;
`;
const Input = styled.input`
  padding: 10px;
  margin: 5px 0;
  border: 1px solid #ccc;
  border-radius: 5px;
  width: calc(100% - 20px);
`;

const Button = styled.button`
  padding: 10px;
  margin-top: 10px;
  color: white;
  background-color: #007bff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;

  &:hover {
    background-color: #0056b3;
  }

  &:disabled {
    background-color: #ccc;
    cursor: not-allowed;
  }
`;

const ErrorMessage = styled.p`
  color: red;
  font-size: 0.8rem;
`;

const CustomUploadButton = styled.label`
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: 100px;  // Size of the button
  height: 100px;
  background-color: #f0f0f0;  // Light grey background
  border: 2px dashed #ccc;  // Dashed border looks like a typical upload area
  border-radius: 5px;
  cursor: pointer;
  color: #333;
  font-size: 24px;  // Size of the plus sign
  &:hover {
    background-color: #e9e9e9;  // Slightly darker on hover
  }
`;

const HiddenInput = styled.input`
  display: none;  // Hide the input but keep it functional
`;

const ProductImage = styled.img`
  width: 100%;  // Make image fill the card width
  height: auto;
  border-radius: 4px;
`;

const ImagePreviewSection = styled.div`
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  align-items: flex-start;
  margin-top: 20px;  // Space from the form or button
`;

const PreviewImage = styled.img`
  width: 100px;  // Fixed size for uniformity
  height: 100px;
  object-fit: cover;  // Ensures images are scaled correctly
  border-radius: 5px;
`;


const ProductForm = () => {
  const [name, setName] = useState('');
  const [price, setPrice] = useState('');
  const [images, setImages] = useState([]);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsSubmitting(true);
    setError('');

    console.log('Submitting with the following details:');
    console.log('Name:', name);
    console.log('Price:', price);
    console.log('Images:', images);

    const formData = new FormData();
    formData.append('name', name);
    formData.append('price', parseFloat(price));

    images.forEach((file, index) => {
        // Append each file under the same name 'images', which the backend will interpret as an array
        formData.append('images', file.originalFile); // Assuming file.originalFile holds the actual file object
    });

    try {
        const response = await axios.post('http://localhost:3001/api/products', formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        });
        console.log('Product Created:', response.data);
        navigate('/shop');
    } catch (error) {
        console.error('Error creating product:', error);
        setError('Failed to create product. Please try again.');
    } finally {
        setIsSubmitting(false);
    }
};

const handleImageChange = (event) => {
    if (event.target.files) {
        const filesArray = Array.from(event.target.files);
        const filesWithDetails = filesArray.map(file => ({
            originalFile: file, // Store the actual file object for upload
            url: URL.createObjectURL(file), // For preview purposes
            name: file.name,
            type: file.type,
            size: file.size
        }));
  
        // Store files with details including the actual file object
        setImages(images => [...images, ...filesWithDetails]);
    }
};

return (
    <Container>
        <StyledForm onSubmit={handleSubmit}>
            <h2>Create a New Product</h2>
            {error && <ErrorMessage>{error}</ErrorMessage>}
            <Input
                type="text"
                placeholder="Product Name"
                value={name}
                onChange={(e) => setName(e.target.value)}
                required
            />
            <Input
                type="number"
                placeholder="Product Price"
                value={price}
                onChange={(e) => setPrice(e.target.value)}
                required
            />
            <CustomUploadButton htmlFor="file-upload">+</CustomUploadButton>
            <HiddenInput
                id="file-upload"
                type="file"
                onChange={handleImageChange}
                accept="image/*"
                multiple
            />
            <ImagePreviewSection>
                {images.map((fileDetail, index) => (
                    <PreviewImage key={index} src={fileDetail.url} alt={`Preview ${fileDetail.name}`} />
                    //console.log(`Preview URL for image ${index}:`, url); // Logs the URL for each image
                    //return <PreviewImage key={index} src={url} alt={`Preview ${index}`} />;
                ))}
            </ImagePreviewSection>
            <Button type="submit" disabled={isSubmitting}>Submit</Button>
        </StyledForm>
        <Card>
            <ProductImage src={images.length > 0 ? images[0].url : ''} alt={name || "Product Name"} />
            <h3>{name || "Product Name"}</h3>
            <p>${price || "0.00"}</p>
        </Card>
    </Container>
);
};

export default ProductForm;
