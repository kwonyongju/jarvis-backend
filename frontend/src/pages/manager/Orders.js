import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import styled from "styled-components";

import Table from "../../components/elements/Table/Table";
import { NO_INGREDIENT_ORDER_YET_MSG } from "../../constants/messages";

const API_URL = process.env.REACT_APP_API_ORDER_GET_URL;

const Root = styled.div``;

const Title = styled.div`
  font-size: 1.8rem;
`;

const NoOrderWrapper = styled.div`
  font-size: 1.3rem;
  display: flex;
  justify-content: center;
  text-align: center;
  white-space: break-spaces;
  flex-direction: column;
`;

const OrderLink = styled(Link)`
  font-size: 1.1rem;
  font-weight: bold;
  margin-top: 30px;
`;

const OrderHistory = () => {
  const [inputMatrix, setInputMatrix] = useState([]);
  const headers = ["Date", "Employee Name", "Ingredients", "Total Price"];
  const labels = ["date", "employeeName", "orderIngredients", "totalPrice"];
  const subLabels = ["name", "quantity", "price"];

  useEffect(() => {
    axios.get(API_URL).then((response) => {
      const temp = response.data.data.map((order) => {
        let sum = 0;
        return {
          date: `${order.orderDate.slice(0, 3).join("-")} ${order.orderDate
            .slice(3, 6)
            .join(":")}`,
          employeeName: `${order.person.firstName} ${order.person.lastName}`,
          orderIngredients: order.orderIngredients.map((item) => {
            sum += item.totalPriceInCent;
            return {
              name: item.ingredient.name,
              quantity: item.count,
              price: item.totalPriceInCent / 100,
            };
          }),
          totalPrice: `$${((sum / 100) * 1.05).toFixed(2)}`,
        };
      });
      temp.reverse();
      setInputMatrix(temp);
    });
  }, []);

  return inputMatrix && inputMatrix.length ? (
    <Root>
      <Title>Order History</Title>
      <Table
        data={inputMatrix}
        headers={headers}
        labels={labels}
        subItemsLabel="orderIngredients"
        subLabels={subLabels}
        subParentHeader="Ingredients"
      />
    </Root>
  ) : (
    <>
      <NoOrderWrapper>
        {NO_INGREDIENT_ORDER_YET_MSG}
        <OrderLink to="/inventoryManagement">Click to order</OrderLink>
      </NoOrderWrapper>
    </>
  );
};

export default OrderHistory;
