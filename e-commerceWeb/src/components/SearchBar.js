// components/SearchBar.js
import React, { useState } from 'react';
import styled from 'styled-components';

const SearchContainer = styled.div`
  // styles for search container
`;

const Input = styled.input`
  // styles for input
`;

const SearchBar = ({ onSearch }) => {
  const [searchTerm, setSearchTerm] = useState('');

  const handleSearch = (event) => {
    setSearchTerm(event.target.value);
    if (onSearch) {
      onSearch(event.target.value);
    }
  };

  return (
    <SearchContainer>
      <Input
        type="text"
        placeholder="Search for products..."
        value={searchTerm}
        onChange={handleSearch}
      />
    </SearchContainer>
  );
};

export default SearchBar;
