import React from "react";
import styled from "styled-components";

const Root = styled.div`
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  width: 100%;
  align-items: ${(props) => props.center && "center"};
`;

const Col = ({ children, center }) => {
  const RootStyle = {
    center: center,
  };
  return <Root {...RootStyle}>{children}</Root>;
};

export default Col;
