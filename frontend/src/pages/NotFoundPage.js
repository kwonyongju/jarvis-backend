import React from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";
import { NOT_FOUND_MSG } from "../constants/messages";

const Root = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  white-space: break-spaces;
`;

const MessageWrapper = styled.div`
  font-size: 1.3rem;
  magrin-bottom: 20px;
`;

class NotFoundPage extends React.Component {
  render() {
    return (
      <Root>
        <MessageWrapper>{NOT_FOUND_MSG}</MessageWrapper>
        <Link to="/">Go to Home</Link>
      </Root>
    );
  }
}
export default NotFoundPage;
