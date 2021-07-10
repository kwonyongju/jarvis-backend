import React, { useEffect, useState } from "react";

import axios from "axios";
import styled from "styled-components";

import { MENU_HEADERS, MENU_LABELS } from "../../constants/list";
import AddModalContent from "./utils/AddModalContent";
import { c_brown, c_dark_yellow } from "../../utils/colors";
import { Modal } from "antd";
import LoadingSpin from "../../components/elements/LoadingSpin/LoadingSpin";
import Row from "../../components/layout/Row";
import Table from "../../components/elements/Table/Table";

const API_URL = process.env.REACT_APP_API_MENU_URL;

const Root = styled.div``;

const Title = styled.div`
  font-size: 1.8rem;
`;

const AddButton = styled.button`
  background-color: ${c_dark_yellow};
  border: transparent;
  border-radius: 15px;
  cursor: pointer;
  height: 30px;
  width: 100px;
`;

const SubmitButton = styled.button`
  align-self: center;
  background-color: ${c_brown};
  border: transparent;
  border-radius: 25px;
  cursor: pointer;
  height: 50px;
  margin-bottom: 30px;
  width: 380px;
`;

const MenuManagement = () => {
  const [inputMatrix, setInputMatrix] = useState({
    personId: 1,
    items: [],
  });
  const [menu, setMenu] = useState([]);
  const [modalVisible, setModalVisible] = useState(false);
  const [formData, setFormData] = useState({
    itemName: "",
    itemDescription: "",
    priceInCent: "",
  });
  const [selectedOptions, setSelectedOptions] = useState([]);

  useEffect(() => {
    if (!modalVisible) setSelectedOptions([]);
  }, [modalVisible]);

  useEffect(() => {
    if (!selectedOptions.length) setFormData(null);
  }, [selectedOptions]);

  useEffect(() => {
    getMenu();
  }, []);

  const getMenu = () => {
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
  };

  const handleInputChange = ({ name, value }) => {
    setInputMatrix({
      ...inputMatrix,
      [name]: value,
    });
  };

  const handleOnAdd = () => {
    toggleModal();
  };

  const handleOnAddToCart = ({ itemIndex }) => {
    setInputMatrix({
      ...inputMatrix,
      items: [...inputMatrix.items, menu[itemIndex]],
    });
  };

  const handleOnFormDataChange = (e) => {
    const { name, value } = e.target;

    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const toggleModal = () => {
    setModalVisible(!modalVisible);
  };

  const handleOnSelect = (selectedOption) => {
    setSelectedOptions(selectedOption);
  };

  const handleOnOrder = () => {
    if (Object.keys(formData).length === 3 && selectedOptions.length > 0) {
      const body = constructRequestBody();

      axios.post(API_URL, body).then((response) => {
        if (response.status === 200) {
          toggleModal();
          getMenu();
        }
      });
    }
  };

  const constructRequestBody = () => {
    const body = {};

    /* TODO: proper handling */
    if (isNaN(parseFloat(formData.priceInCent)) || formData.priceInCent <= 0) {
      alert("Please check if price correctly follows its rule");
    }

    body.itemName = formData.itemName;
    body.itemDescription = formData.itemDescription;
    body.ingredients = selectedOptions.map((option) => option.value);
    body.priceInCent = parseFloat(formData.priceInCent).toFixed(2) * 100;

    return body;
  };

  return menu && menu.length ? (
    <Root>
      <Row spaceBetween>
        <Title>Menu</Title>
        <AddButton onClick={() => handleOnAdd()}>Add Menu</AddButton>
      </Row>

      <Table
        data={menu}
        headers={MENU_HEADERS}
        inputMatrix={inputMatrix}
        labels={MENU_LABELS}
        onChange={handleInputChange}
        onClick={handleOnAddToCart.bind(this)}
      />
      <Modal
        title="Add Menu"
        centered
        visible={modalVisible}
        onCancel={toggleModal}
        footer={[
          <SubmitButton onClick={handleOnOrder} key="submit">
            Submit
          </SubmitButton>,
        ]}
      >
        {/** TODO: control number of ingredient */}
        <AddModalContent
          formData={formData}
          handleChange={handleOnFormDataChange.bind(this)}
          handleOnSelect={handleOnSelect}
          selectedOptions={selectedOptions}
        />
      </Modal>
    </Root>
  ) : (
    <LoadingSpin />
  );
};

export default MenuManagement;
