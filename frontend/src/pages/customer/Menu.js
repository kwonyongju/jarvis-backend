import React, { useEffect, useState } from "react";

import axios from "axios";
import styled from "styled-components";

import { Spin } from "antd";
import { LoadingOutlined } from "@ant-design/icons";

import Table from "../../components/elements/Table/Table";
import Cart from "./Cart";

const API_URL = process.env.REACT_APP_API_URL;

const Root = styled.div`
  padding: 20px 20px;
`;

const Title = styled.div`
  font-size: 1.8rem;
`;

const SpinWrapper = styled.div`
  margin: 0 auto;
`;

const OrderButton = styled.button``;

const Menu = () => {
  const [menu, setMenu] = useState([]);
  const [inputMatrix, setInputMatrix] = useState({
    personId: 1,
    items: [],
  });

  const menuTableHeaders = ["name", "description", "ingredients", "price"];

  useEffect(() => {
    axios.get(`${API_URL}/menu`).then((response) => {
      const menu = response.data.data.map((item) => {
        return {
          name: item.itemName,
          description: item.itemDescription,
          ingredients: item.ingredients.join(", "),
          price: item.priceInCent / 100,
        };
      });

      setMenu(menu);
    });
  }, []);

  return menu.length > 0 ? (
    <Root>
      <Title>Menu</Title>
      <Table
        headers={menuTableHeaders}
        data={menu}
        button={<OrderButton>Order</OrderButton>}
      />
      <hr />
      <Cart />
    </Root>
  ) : (
    <SpinWrapper>
      <Spin indicator={<LoadingOutlined style={{ fontSize: 24 }} spin />} />
    </SpinWrapper>
  );
};

export default Menu;
