import React, { useEffect, useState } from "react";
import axios from "axios";
import styled from "styled-components";
import { InstagramOutlined } from "@ant-design/icons";

import { c_dim_green } from "../../utils/colors";
import { NO_SALES_YET_MSG } from "../../constants/messages";
import Table from "../../components/elements/Table/Table";

const API_URL = process.env.REACT_APP_API_PURCHASE_URL;

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
  align-items: flex-end;
`;

const Sales = () => {
  const [inputMatrix, setInputMatrix] = useState([]);
  const headers = ["Date", "Customer Name", "Items", "Total Price"];
  const labels = ["date", "customerName", "items", "totalPrice"];
  const subLabels = ["name", "quantity", "price"];

  useEffect(() => {
    axios.get(API_URL).then((response) => {
      const temp = response.data.data.map((sale) => {
        let sum = 0;
        return {
          date: `${sale.purchaseDate.slice(0, 3).join("-")} ${sale.purchaseDate
            .slice(3, 6)
            .join(":")}`,
          customerName: `${sale.customer.firstName} ${sale.customer.lastName}`,
          items: sale.purchaseItems.map((purchaseItem) => {
            sum += purchaseItem.totalPriceInCent;
            return {
              name: purchaseItem.itemName,
              quantity: purchaseItem.quantity,
              price: purchaseItem.totalPriceInCent / 100,
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
      <Title>Sales History</Title>

      <Table
        data={inputMatrix}
        headers={headers}
        labels={labels}
        subItemsLabel="items"
        subLabels={subLabels}
        subParentHeader="Items"
      />
    </Root>
  ) : (
    <NoOrderWrapper>
      {NO_SALES_YET_MSG}
      <InstagramOutlined
        style={{ fontSize: "40px", color: `${c_dim_green}` }}
      />
    </NoOrderWrapper>
  );
};

export default Sales;
