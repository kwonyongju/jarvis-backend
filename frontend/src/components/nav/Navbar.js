import React from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";

import NavItem from "./components/NavItem";
import { c_dim_gray } from "../../utils/colors";

const Root = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: ${(props) => !props.noPadding && "4rem 0"};
  width: 100%;
  font-weight: ${(props) => !props.noFontWeight && "bold"};
`;

const NavLeft = styled.div`
  padding-left: 20px;
  font-size: 1.2rem;

  a:hover {
    color: ${c_dim_gray};
  }
`;
const NavRight = styled.div`
  display: flex;
  flex-direction: row;
  padding-right: 20px;
`;

const Navbar = ({ navItems, navLeft, noPadding, noFontWeight }) => {
  const styleProps = {
    noPadding: noPadding,
    noFontWeight: noFontWeight,
  };
  return (
    <Root {...styleProps}>
      <NavLeft>
        <Link to={"/"}>{navLeft}</Link>
      </NavLeft>
      <NavRight>
        {navItems.map((item, index) => (
          <NavItem
            key={index}
            last={index === navItems.length - 1}
            // linkTo={item.label}
            name={item.name}
            subItems={item.subItems}
          />
        ))}
      </NavRight>
    </Root>
  );
};

export default Navbar;
