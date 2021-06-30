import React from "react";
import styled from "styled-components";
import { Icon } from "semantic-ui-react";

const Root = styled.div`
  margin: 0 auto;
  max-width: 1080px;
  padding-bottom: 300px;

  border: 2px solid green;
`;

const Wrapper = styled.div`
  display: flex;
  justify-content: center;
  margin-top: 10rem;

  width: 100%;
`;

const Footer = () => {
  return (
    <Root>
      <Wrapper>
        <Icon name="copyright" />
        Yongju Kwon. ALL RIGHT RESERVED.
      </Wrapper>
    </Root>
  );
};

export default Footer;
