import React from "react";
import styled from "styled-components";

import {
  PROJECT_DESCRIPTION,
  PROJECT_SUB_TITLE,
  PROJECT_TITLE,
} from "../constants/description";

const Root = styled.div`
  align-items: center;
  display: flex;
  flex-direction: column;
  margin-top: 2vh;

  a {
    display: block;
    width: 80%;
  }
`;

const Title = styled.div`
  font-size: 2rem;
  font-weight: bold;
  margin-bottom: 20px;
`;

const SubTitle = styled.div`
  font-size: 1.5rem;
`;

const Description = styled.div`
  white-space: break-spaces;
`;

const Main = () => {
  return (
    <Root>
      <Title>{PROJECT_TITLE}</Title>
      <SubTitle>{PROJECT_SUB_TITLE}</SubTitle>
      <Description>{PROJECT_DESCRIPTION}</Description>
    </Root>
  );
};

export default Main;
