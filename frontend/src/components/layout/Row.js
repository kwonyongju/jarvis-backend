import React from "react";
import styled from "styled-components";
import { FormatStyles } from "../../constants/formatStyle";

const Root = styled.div`
  box-sizing: border-box;
  display: flex;
  align-items: center;
  width: 100%;

  ${FormatStyles}
`;

const Row = ({
  children,
  justifyCenter,
  marginBottom,
  marginBottomDouble,
  marginTopDouble,
}) => {
  const styleProps = {
    justifyCenter: justifyCenter,
    marginBottom: marginBottom,
    marginBottomDouble: marginBottomDouble,
    marginTopDouble: marginTopDouble,
  };

  return <Root {...styleProps}>{children}</Root>;
};

export default Row;
