import React, { useEffect, useState } from "react";
import styled from "styled-components";

import Table from "../../components/elements/Table/Table";
import { c_brown } from "../../utils/colors";

const Root = styled.div`
  margin-top: 2vh;
`;

const Title = styled.div`
  font-size: 1.8rem;
`;

const ButtonWrapper = styled.div`
  display: flex;
  justify-content: center;
`;

const OrderButton = styled.button`
  background-color: ${c_brown};
  border: transparent;
  border-radius: 25px;
  height: 50px;
  cursor: pointer;
  width: 200px;
`;

const Cart = ({ inputMatrix }) => {
  const [data, setData] = useState([]);
  console.log(inputMatrix.items);

  useEffect(() => {
    formatTableData(inputMatrix.items);
  }, [inputMatrix.items]);

  const cartTableHeaders = ["#", "Item", "Quantity", "Price", "TotalPrice"];
  const labels = ["index", "name", "quantity", "price", "totalPrice"];

  const formatTableData = (items) => {
    const map = new Map();
    const temp = [];

    items.forEach((item) => {
      if (!map.has(item.name)) map.set(item.name, 1);
      else map.set(item.name, map.get(item.name) + 1);
    });

    let index = 0;
    let totalPrice = 0;
    map.forEach((value, key) => {
      const item = items.find((i) => i.name === key);
      const price = (item.price * value).toFixed(2);

      temp.push({
        index: ++index,
        name: item.name,
        quantity: value,
        price: price,
      });

      totalPrice += parseFloat(price);
    });

    temp.push({
      totalPrice: `$ ${totalPrice.toFixed(2)}`,
    });

    setData(temp);
  };

  return (
    <Root>
      <Title>Cart</Title>
      <Table
        headers={cartTableHeaders}
        data={data}
        labels={labels}
        tdHeight="20px"
      />
      <ButtonWrapper>
        <OrderButton>Place Order</OrderButton>
      </ButtonWrapper>
    </Root>
  );
};

export default Cart;
