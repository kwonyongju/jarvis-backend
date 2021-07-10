import React, { useEffect, useState } from "react";

import axios from "axios";
import styled from "styled-components";
import { Modal } from "antd";

import Table from "../elements/Table/Table";
import { c_brown, c_light_red } from "../../utils/colors";

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

const Cart = ({
  apiUrl,
  cartNameLabel,
  errorMessage,
  formatData,
  headers,
  inputMatrix,
  labels,
  onChange,
  orderLabel,
  orderProductLabel,
  refresh,
  successMessage,
  warningMessage,
}) => {
  const [data, setData] = useState([]);
  const [modalVisible, setModalVisible] = useState(false);
  const [modalContent, setModalContent] = useState("");

  useEffect(() => {
    if (inputMatrix[orderLabel])
      setData(
        formatData({
          items: inputMatrix[orderLabel],
          nameLabel: cartNameLabel,
        })
      );
  }, [inputMatrix]);

  const toggleModal = (message) => {
    setModalContent(message);
    setModalVisible(!modalVisible);
  };

  const handleOnOrder = () => {
    if (data.length > 1) {
      const body = JSON.parse(constructRequestBody());

      axios.post(apiUrl, body).then((response) => {
        if (response.status === 200 && response.data.id > 0) {
          toggleModal(successMessage);
          refresh ? refresh() : null;
          setData([]);
          onChange({ name: orderLabel, value: [] });
        }

        if (response.data.id === -1) {
          toggleModal(errorMessage);
        }
      });
    } else toggleModal(warningMessage);
  };

  const constructRequestBody = () => {
    const items = [];

    data.forEach((item, index) => {
      // except the last row (total price)
      if (index !== data.length - 1)
        items.push({
          [orderProductLabel]: item.name,
          quantity: item.quantity,
        });
    });

    return JSON.stringify({
      personId: inputMatrix.personId,
      [orderLabel]: items,
    });
  };

  const handleOnRemove = ({ itemIndex }) => {
    const remove = inputMatrix[orderLabel].find(
      (item) => item[cartNameLabel] === data[itemIndex].name
    );

    onChange({
      name: orderLabel,
      value: inputMatrix[orderLabel].filter((i) => i != remove),
    });
  };

  return (
    <Root>
      <Title>Cart</Title>
      <Table
        buttonColor={c_light_red}
        buttonLabel="Remove"
        headers={headers}
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
        centered
        visible={modalVisible}
        onOk={() => toggleModal()}
        onCancel={() => toggleModal()}
      >
        {modalContent}
      </Modal>
    </Root>
  );
};

export default Cart;
