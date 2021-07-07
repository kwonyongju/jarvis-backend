import React, { useEffect, useState } from "react";

import axios from "axios";
import styled from "styled-components";

import { Spin } from "antd";
import { LoadingOutlined } from "@ant-design/icons";

import { c_dark_yellow } from "../../utils/colors";
import Table from "../../components/elements/Table/Table";
import Cart from "./Cart";

const API_URL = process.env.REACT_APP_API_MENU_URL;

const Root = styled.div`
  padding: 20px 20px;
`;

const Title = styled.div`
  font-size: 1.8rem;
`;

const SpinWrapper = styled.div`
  margin: 0 auto;
`;

const Menu = () => {
  const [menu, setMenu] = useState([]);
  const [inputMatrix, setInputMatrix] = useState({
    personId: 1,
    items: [],
  });
  const menuTableHeaders = [
    "Name",
    "Description",
    "Ingredients",
    "Price (CAD)",
  ];
  const labels = ["name", "description", "ingredients", "price"];

  useEffect(() => {
    axios.get(API_URL).then((response) => {
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

  const handleOnOrder = ({ itemIndex }) => {
    setInputMatrix({
      ...inputMatrix,
      items: [...inputMatrix.items, menu[itemIndex]],
    });
  };

  const handleInputChange = ({ name, value }) => {
    setInputMatrix({
      ...inputMatrix,
      [name]: value,
    });
  };

  return menu.length > 0 ? (
    <Root>
      <Title>Menu</Title>
      <Table
        buttonColor={c_dark_yellow}
        buttonLabel="Add to Cart"
        data={menu}
        headers={menuTableHeaders}
        inputMatrix={inputMatrix}
        labels={labels}
        onChange={handleInputChange}
        onClick={handleOnOrder.bind(this)}
      />
      <hr />
      <Cart inputMatrix={inputMatrix} onChange={handleInputChange} />
    </Root>
  ) : (
    <SpinWrapper>
      <Spin indicator={<LoadingOutlined style={{ fontSize: 24 }} spin />} />
    </SpinWrapper>
  );
};

export default Menu;
