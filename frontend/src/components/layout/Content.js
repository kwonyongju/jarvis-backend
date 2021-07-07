import React from "react";
import styled from "styled-components";

const Root = styled.div`
  max-width: 1080px;
  margin: 0 auto;
  border: 1px solid red;
`;

const Content = ({ children }) => {
  return <Root>{children}</Root>;
};

export default Content;
