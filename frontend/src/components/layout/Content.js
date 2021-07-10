import React from "react";
import styled from "styled-components";

const Root = styled.div`
  max-width: 1080px;
  margin: 0 auto;
  padding: 20px 20px;
`;

const Content = ({ children }) => {
  return <Root>{children}</Root>;
};

export default Content;
