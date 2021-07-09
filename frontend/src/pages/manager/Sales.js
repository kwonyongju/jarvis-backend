import React, { useEffect, useState } from "react";
import axios from "axios";
import styled from "styled-components";

import Table from "../../components/elements/Table/Table";
import LoadingSpin from "../../components/elements/LoadingSpin/LoadingSpin";

const API_URL = process.env.REACT_APP_API_PURCHASE_URL;

const Root = styled.div``;

const Title = styled.div`
  font-size: 1.8rem;
`;

const Sales = () => {
  const [sales, setSales] = useState([]);
  const headers = ["Date", "Customer Name", "Items", "Total Price"];
  const labels = ["date", "customerName", "items", "totalPrice"];
  const subLabels = ["name", "quantity", "price"];

  useEffect(() => {
    axios.get(API_URL).then((response) => {
      const salesData = response.data.data.map((sale) => {
        let sum = 0;
        console.log(sale);
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
      console.log(salesData);
      setSales(salesData);
    });
  }, []);

  return sales && sales.length ? (
    <Root>
      <Title>Order History</Title>

      <Table
        data={sales}
        headers={headers}
        labels={labels}
        subLabelHeader="Items"
        subLabels={subLabels}
      />
    </Root>
  ) : (
    <LoadingSpin />
  );
};

export default Sales;
