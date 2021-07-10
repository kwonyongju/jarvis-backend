import React from "react";
import styled from "styled-components";

import { Spin } from "antd";
import { LoadingOutlined } from "@ant-design/icons";

const Root = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;

  .ant-spin-dot {
    font-size: 60px;
  }
`;

const LoadingSpin = () => {
  return (
    <Root>
      <Spin indicator={<LoadingOutlined spin />} />
    </Root>
  );
};

export default LoadingSpin;
