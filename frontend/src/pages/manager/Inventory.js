import React, { useEffect, useState } from "react";
import axios from "axios";
import styled from "styled-components";
import Table from "../../components/elements/Table/Table";
import Title from "../../components/elements/Title/Title";
// import Cart from "../customer/Cart";

import { c_dark_yellow } from "../../utils/colors";
import LoadingSpin from "../../components/elements/LoadingSpin/LoadingSpin";

const API_URL = process.env.REACT_APP_API_INVENTORY_URL;

const Root = styled.div``;

const Inventory = () => {
  const [inventoryData, setInventoryData] = useState([]);
  const [counts, setCounts] = useState([]);
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
  }, []);

  useEffect(() => {
    if (inventoryData.length) {
      setCounts(
        inventoryData.map((inventory) => {
          return {
            name: inventory.name,
            count: null,
          };
        })
      );
    }
  }, [inventoryData]);

  const handleOnAddToCart = (e) => {
    if (e.name) {
      const { name, value } = e;

      const exist = inputMatrix.orderIngredients.find(
        (oi) => oi.ingredient === name
      );
      if (parseInt(value)) {
        if (exist) {
          const updated = inputMatrix.orderIngredients.map((oi) =>
            oi === exist ? { ...oi, quantity: value } : oi
          );

          setInputMatrix({
            ...inputMatrix,
            orderIngredients: updated,
          });
        } else {
          setInputMatrix({
            ...inputMatrix,
            orderIngredients: [
              ...inputMatrix.orderIngredients,
              {
                ingredient: name,
                quantity: value,
              },
            ],
          });
        }
      }
    }
  };

  console.log(inventoryData);
  console.log(inputMatrix);

  const handleInputChange = ({ name, value }) => {
    console.log("name: " + name);
    console.log("value: " + value);

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
      {/* <Cart
        errorMessage={ADD_TO_CART_MSG}
        headers={cartHeaders}
        inputMatrix={inputMatrix}
        labels={cartLabels}
        onChange={handleInputChange}
        onFormatTableData={formatTableData}
        orderLabel={"orderIngredients"}
        orderProductLabel={"ingredient"}
      /> */}
    </Root>
  ) : (
    <LoadingSpin />
  );
};

export default Inventory;
