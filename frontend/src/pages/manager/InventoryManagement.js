import React, { useEffect, useState } from "react";
import axios from "axios";
import styled from "styled-components";

import { ADD_TO_CART_MSG, COMPLETE_ORDER_MSG } from "../../constants/messages";
import { c_dark_yellow } from "../../utils/colors";
import { formatDataToCart } from "../../utils/format";
import Cart from "../../components/Cart/Cart";
import Table from "../../components/elements/Table/Table";
import Title from "../../components/elements/Title/Title";
import LoadingSpin from "../../components/elements/LoadingSpin/LoadingSpin";

const API_URL = process.env.REACT_APP_API_INVENTORY_URL;

const Root = styled.div``;

const InventoryManagement = () => {
  const [inventoryData, setInventoryData] = useState([]);
  const [inputMatrix, setInputMatrix] = useState({
    personId: 2,
    orderIngredients: [],
  });

  const headers = [
    "Name",
    "Stock Quantity",
    "Price (CAD)",
    "Quantity to order",
    "",
  ];
  const labels = ["name", "quantity", "price"];
  const cartHeaders = [
    "",
    "Name",
    "Quantity",
    "Price",
    "Tax (5%)",
    "TotalPrice",
    "",
  ];
  const cartLabels = [
    "index",
    "name",
    "quantity",
    "price",
    "tax",
    "totalPrice",
  ];

  useEffect(() => {
    getInventory();
  }, []);

  const getInventory = () => {
    axios.get(API_URL).then((response) => {
      if (response.status === 200) {
        const temp = response.data.data.map((ingredient) => {
          return {
            name: ingredient.ingredientName,
            quantity: ingredient.stockQuantity,
            price: ingredient.priceInCent / 100,
          };
        });

        setInventoryData(temp);
      }
    });
  };

  const handleOnAddToCart = (e) => {
    if (e.name) {
      const { name, value } = e;

      const exist = inputMatrix.orderIngredients.find(
        (oi) => oi.ingredient === name
      );
      // if input value is valid
      if (parseInt(value)) {
        // if it is already in cart
        if (exist) {
          const updated = inputMatrix.orderIngredients.map((oi) =>
            oi === exist ? { ...oi, quantity: value } : oi
          );

          setInputMatrix({
            ...inputMatrix,
            orderIngredients: updated,
          });
        } else {
          // if it is not in cart
          setInputMatrix({
            ...inputMatrix,
            orderIngredients: [
              ...inputMatrix.orderIngredients,
              {
                ingredient: name,
                price: inventoryData.find((i) => i.name === name).price,
                quantity: value,
              },
            ],
          });
        }
      }
    }
  };

  const handleInputChange = ({ name, value }) => {
    setInputMatrix({
      ...inputMatrix,
      [name]: value,
    });
  };

  return inventoryData && inventoryData.length ? (
    <Root>
      <Title>Ingredients Inventory</Title>
      <Table
        buttonColor={c_dark_yellow}
        buttonLabel="Add to Cart"
        data={inventoryData}
        headers={headers}
        inputField
        inputMatrix={inputMatrix}
        labels={labels}
        onBlur={handleOnAddToCart}
        onClick={handleOnAddToCart.bind(this)}
      />
      <Cart
        apiUrl={process.env.REACT_APP_API_ORDER_URL}
        cartNameLabel={"ingredient"}
        headers={cartHeaders}
        inputMatrix={inputMatrix}
        labels={cartLabels}
        onChange={handleInputChange}
        formatData={formatDataToCart}
        orderLabel={"orderIngredients"}
        orderProductLabel={"ingredient"}
        refresh={getInventory}
        successMessage={COMPLETE_ORDER_MSG}
        warningMessage={ADD_TO_CART_MSG}
      />
    </Root>
  ) : (
    <LoadingSpin />
  );
};

export default InventoryManagement;
