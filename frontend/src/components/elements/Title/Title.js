import React from "react";
import styled from "styled-components";

const Root = styled.div`
  font-size: 1.8rem;
`;

const Title = ({ children }) => {
  return <Root>{children}</Root>;
};

export default Title;
