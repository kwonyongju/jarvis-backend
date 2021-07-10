import React, { useEffect, useState } from "react";

import axios from "axios";
import styled from "styled-components";

import {
  ADD_TO_CART_MSG,
  COMPLETE_PURCHASE_MSG,
  OUT_OF_STOCK_MSG,
} from "../../constants/messages";
import {
  CART_HEADERS,
  CART_LABELS,
  MENU_HEADERS,
  MENU_LABELS,
} from "../../constants/list";
import { c_dark_yellow } from "../../utils/colors";
import { formatDataToCart } from "../../utils/format";
import Cart from "../../components/Cart/Cart";
import LoadingSpin from "../../components/elements/LoadingSpin/LoadingSpin";
import Table from "../../components/elements/Table/Table";

const API_URL = process.env.REACT_APP_API_MENU_URL;

const Root = styled.div``;

const Title = styled.div`
  font-size: 1.8rem;
`;

const Menu = () => {
  const [menu, setMenu] = useState([]);
  const [inputMatrix, setInputMatrix] = useState({
    personId: 1,
    items: [],
  });

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

  const handleOnAddToCart = ({ itemIndex }) => {
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

  return menu && menu.length ? (
    <Root>
      <Title>Menu</Title>
      <Table
        buttonColor={c_dark_yellow}
        buttonLabel="Add to Cart"
        data={menu}
        headers={MENU_HEADERS}
        inputMatrix={inputMatrix}
        labels={MENU_LABELS}
        onChange={handleInputChange}
        onClick={handleOnAddToCart.bind(this)}
      />
      <Cart
        apiUrl={process.env.REACT_APP_API_PURCHASE_URL}
        errorMessage={OUT_OF_STOCK_MSG}
        headers={CART_HEADERS}
        inputMatrix={inputMatrix}
        labels={CART_LABELS}
        onChange={handleInputChange}
        formatData={formatDataToCart}
        cartNameLabel={"name"}
        orderLabel={"items"}
        orderProductLabel={"itemName"}
        successMessage={COMPLETE_PURCHASE_MSG}
        warningMessage={ADD_TO_CART_MSG}
      />
    </Root>
  ) : (
    <LoadingSpin />
  );
};

export default Menu;
