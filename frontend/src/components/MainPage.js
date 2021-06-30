import React from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";

import Col from "./layout/Col";
import { c_dim_green, c_brown } from "../utils/colors";
import { PROJECT_DESCRIPTION } from "../constants/description";
import Row from "./layout/Row";

const Root = styled.div`
  a {
    display: block;
    width: 80%;
  }
`;

const Button = styled.button`
  border: none;
  cursor: pointer;
  font-size: 12px;
  height: 50px;
  width: 100%;
`;

const CustomerButton = styled(Button)`
  background-color: ${c_dim_green};
`;

const ManagerButton = styled(Button)`
  background-color: ${c_brown};
`;

const MainPage = () => {
  return (
    <Root>
      <Row>{PROJECT_DESCRIPTION}</Row>
      <Row marginTopDouble>
        <Col center>
          <Link to="/api/menu">
            <CustomerButton>Customer</CustomerButton>
          </Link>
        </Col>
        <Col center>
          <Link to="/api/sales">
            <ManagerButton>Manager</ManagerButton>
          </Link>
        </Col>
      </Row>
    </Root>
  );
};

export default MainPage;
