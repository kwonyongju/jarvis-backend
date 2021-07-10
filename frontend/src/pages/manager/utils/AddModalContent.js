import React from "react";
import styled from "styled-components";
import Select from "react-select";

import { INGREDIENT_SELECT_OPTIONS } from "../../../constants/list";
import Row from "../../../components/layout/Row";

const API_URL = process.env.REACT_APP_API_MENU_URL;

const ModalForm = styled.form`
  action: ${API_URL};
`;

const ModalRow = styled(Row)``;

const ModalLabel = styled.label`
  display: inline-block;
  text-align: left;
  width: 120px;
`;

const ModalInput = styled.input`
  display: table-cell;
  padding: 5px;
  text-align: center;
  width: 250px;

  :focus {
    outline: none;
  }
`;

const ModalSelectWrapper = styled.div`
  width: 250px;
`;

const AddMenuModal = ({
  handleChange,
  handleOnSelect,
  selectedOptions,
  formData,
}) => {
  return (
    <ModalForm id="addMenuForm">
      <ModalRow justifyCenter marginBottom>
        <ModalLabel htmlFor="itemName">Item name</ModalLabel>
        <ModalInput
          value={(formData && formData.itemName) || ""}
          type="text"
          name="itemName"
          onChange={handleChange}
        />
      </ModalRow>
      <ModalRow justifyCenter marginBottom>
        <ModalLabel htmlFor="itemDescription">Item description</ModalLabel>
        <ModalInput
          value={(formData && formData.itemDescription) || ""}
          type="text"
          name="itemDescription"
          onChange={handleChange}
        />
      </ModalRow>
      <ModalRow justifyCenter marginBottom>
        <ModalLabel htmlFor="ingredients">Ingredients</ModalLabel>
        <ModalSelectWrapper>
          <Select
            className="modalSelect"
            isMulti
            onChange={handleOnSelect}
            options={INGREDIENT_SELECT_OPTIONS}
            value={selectedOptions}
          />
        </ModalSelectWrapper>
      </ModalRow>
      <ModalRow justifyCenter>
        <ModalLabel htmlFor="priceInCent">Price</ModalLabel>
        <ModalInput
          value={(formData && formData.priceInCent) || ""}
          type="number"
          step="0.01"
          min="0.01"
          name="priceInCent"
          placeholder="ex) 9.99 (with dot, minimum 0.01)"
          onChange={handleChange}
        />
      </ModalRow>
    </ModalForm>
  );
};

export default AddMenuModal;
