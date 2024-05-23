import styled from 'styled-components';

const MainLayout = styled.div`
  display: flex;
  flex-direction: column;
  min-height: 100vh;  // Make sure the layout covers the full viewport height
  position: relative;
`;

const Content = styled.main`
  flex: 1;  // Allows the content area to grow and fill available space, pushing the footer down
  padding: 20px;
`;

const Footer = styled.footer`
  padding: 20px;
  text-align: center;
  background-color: #333;
  color: white;
  width: 100%;
`;


const Layout = ({ children }) => {
  return (
    <MainLayout>
      <Content>{children}</Content>
     <Footer>© 2024 Your Store Name - All Rights Reserved </Footer> 
    </MainLayout>
  );
};

export default Layout;
