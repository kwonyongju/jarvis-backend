import React, { useState } from "react";
import styled from "styled-components";

const Root = styled.input`
  border: 1px solid black;
  border-radius: 15px;
  height: 30px;
  width: 100px;
  text-align: right;
  overflow: hidden;

  :focus {
    outline: none;
  }
`;

const Input = ({ min, max, name, placeholder, step, type }) => {
  const [orderIngredients, setOrderIngredients] = useState({
    [name]: "",
  });

  const handleOrderIngredients = (e) => {
    const { name, value } = e.target;

    setOrderIngredients({
      [name]: value,
    });
  };

  return (
    <Root
      type={type ? type : "text"}
      step={step ? step : null}
      name={name}
      min={min ? min : null}
      max={max ? max : null}
      placeholder={placeholder ? placeholder : null}
      onChange={(e) => handleOrderIngredients(e)}
      value={orderIngredients[name]}
    />
  );
};

export default Input;
