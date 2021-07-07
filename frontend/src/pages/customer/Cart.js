import React, { useEffect, useState } from "react";

import axios from "axios";
import styled from "styled-components";
import { Modal } from "antd";

import Table from "../../components/elements/Table/Table";
import { c_brown, c_light_red } from "../../utils/colors";

const API_URL = process.env.REACT_APP_API_PURCHASE_URL;

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

const Cart = ({ inputMatrix, onChange }) => {
  const [data, setData] = useState([]);
  const [modalVisible, setModalVisible] = useState(false);
  const cartTableHeaders = [
    "",
    "Item",
    "Quantity",
    "Price",
    "Tax (5%)",
    "TotalPrice",
  ];
  const labels = ["index", "name", "quantity", "price", "tax", "totalPrice"];

  useEffect(() => {
    if (inputMatrix.items) formatTableData(inputMatrix.items);
  }, [inputMatrix]);

  const toggleModal = () => {
    setModalVisible(!modalVisible);
  };

  const handleOnOrder = () => {
    const body = JSON.parse(constructRequestBody());
    axios.post(API_URL, body).then((response) => {
      console.log(response);
      if (response.status === 200 && response.data.id > 0) {
        setData([]);
        onChange({ name: "items", value: [] });
      }

      if (response.data.id === -1) {
        toggleModal();
      }
    });
  };

  const constructRequestBody = () => {
    const items = [];

    data.forEach((item, index) => {
      // except the last row (total price)
      if (index !== data.length - 1)
        items.push({
          itemName: item.name,
          quantity: item.quantity,
        });
    });

    return JSON.stringify({
      personId: inputMatrix.personId,
      items: items,
    });
  };

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
        tax: (price * 0.05).toFixed(2),
      });

      totalPrice += parseFloat(price) * 1.05;
    });

    temp.push({
      totalPrice: `$ ${totalPrice.toFixed(2)}`,
    });

    setData(temp);
  };

  const handleOnRemove = ({ itemIndex }) => {
    const remove = inputMatrix.items.find(
      (item) => item.name === data[itemIndex].name
    );
    const index = inputMatrix.items.indexOf(remove);
    inputMatrix.items.splice(index, 1);

    onChange({
      name: "items",
      value: inputMatrix.items,
    });
  };

  console.log(inputMatrix.items);
  console.log(data);
  return (
    <Root>
      <Title>Cart</Title>
      <Table
        buttonColor={c_light_red}
        buttonLabel="Remove"
        headers={cartTableHeaders}
        data={data}
        labels={labels}
        last={data.length - 1}
        tdHeight="20px"
        onClick={handleOnRemove.bind(this)}
      />
      <ButtonWrapper>
        <OrderButton onClick={() => handleOnOrder()}>Place Order</OrderButton>
      </ButtonWrapper>
      <Modal
        title="Some ingredients are out of stock"
        centered
        visible={modalVisible}
        onOk={() => toggleModal()}
        onCancel={() => toggleModal()}
      >
        <p>
          We are very sorry, we just had a mukbang show with a Youtuber. He has
          eaten all of our burgers..
          <br />
          <br />
          Please come back next time..
          <br />
          <br />* If you ask our manager to stock those, it will be delivered
          right away!
        </p>
      </Modal>
    </Root>
  );
};

export default Cart;
