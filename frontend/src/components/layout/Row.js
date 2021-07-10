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
  alignEnd,
  children,
  justifyCenter,
  marginBottom,
  marginBottomDouble,
  marginTopDouble,
  spaceBetween,
}) => {
  const styleProps = {
    alignEnd: alignEnd,
    justifyCenter: justifyCenter,
    marginBottom: marginBottom,
    marginBottomDouble: marginBottomDouble,
    marginTopDouble: marginTopDouble,
    spaceBetween: spaceBetween,
  };

  return <Root {...styleProps}>{children}</Root>;
};

export default Row;
