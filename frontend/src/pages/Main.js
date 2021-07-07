import React from "react";
import styled from "styled-components";

import { PROJECT_DESCRIPTION, PROJECT_TITLE } from "../constants/description";

const Root = styled.div`
  margin-top: 2vh;

  a {
    display: block;
    width: 80%;
  }
`;

const Title = styled.div`
  display: flex;
  justify-content: center;
  font-size: 2rem;
  font-weight: bold;
  margin-bottom: 20px;
`;

const Description = styled.div`
  display: flex;
  justify-content: center;
`;

const Main = () => {
  return (
    <Root>
      <Title>{PROJECT_TITLE}</Title>
      <Description>{PROJECT_DESCRIPTION}</Description>
    </Root>
  );
};

export default Main;
