import React, { useEffect, useState } from "react";

import axios from "axios";
import styled from "styled-components";
import { Modal } from "antd";

import LoadingSpin from "../elements/LoadingSpin/LoadingSpin";
import Table from "../elements/Table/Table";
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

const Cart = ({
  errorMessage,
  headers,
  inputMatrix,
  labels,
  onChange,
  onFormatTableData,
  orderLabel,
  orderProductLabel,
  successMessage,
  warningMessage,
}) => {
  const [data, setData] = useState([]);
  const [modalVisible, setModalVisible] = useState(false);
  const [modalContent, setModalContent] = useState("");

  useEffect(() => {
    if (inputMatrix[orderLabel])
      setData(onFormatTableData(inputMatrix[orderLabel]));
  }, [inputMatrix]);

  const toggleModal = (message) => {
    setModalContent(message);
    setModalVisible(!modalVisible);
  };

  const handleOnOrder = () => {
    if (data.length > 1) {
      const body = JSON.parse(constructRequestBody());
      axios.post(API_URL, body).then((response) => {
        console.log(response);
        if (response.status === 200 && response.data.id > 0) {
          toggleModal(successMessage);
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
      (item) => item.name === data[itemIndex].name
    );
    const index = inputMatrix[orderLabel].indexOf(remove);
    inputMatrix[orderLabel].splice(index, 1);

    onChange({
      name: orderLabel,
      value: inputMatrix.items,
    });
  };

  return data && data.length ? (
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
  ) : (
    <LoadingSpin />
  );
};

export default Cart;
