import React, { useEffect } from "react";
import styled from "styled-components";
import axios from "axios";

const Root = styled.div``;

const Menu = () => {
  const [items, setItems] = useEffect([]);

  useEffect(() => {
    axios.get("");
  }, []);

  return <Root></Root>;
};

export default Menu;
